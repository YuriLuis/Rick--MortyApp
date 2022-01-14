package com.yuri.apprickmorty.ui.main.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.repositories.PersonagemRepositoryImpl
import com.yuri.apprickmorty.data.services.remote.api.RickMortyRetrofit
import com.yuri.apprickmorty.databinding.FragmentPesonagensBinding
import com.yuri.apprickmorty.ui.main.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.main.viewmodels.PersonagemViewModel
import com.yuri.apprickmorty.ui.main.viewmodelsfactory.PersonagemViewModelFactory

const val PAGINA_INICIAL = 1

class PesonagensFragment : Fragment(R.layout.fragment_pesonagens) {

    private lateinit var binding: FragmentPesonagensBinding
    private val viewModel: PersonagemViewModel by activityViewModels {
        PersonagemViewModelFactory(PersonagemRepositoryImpl(RickMortyRetrofit.apiRickMorty))
    }
    private val adapter = ListaPersonagemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pesonagens, container, false)
        iniciaComponentesView()
        configuraObserverIsCarregando()
        configuraObserverListaPersonagens()
        return binding.root
    }

    private fun configuraObserverListaPersonagens() {
        viewModel.personagensLiveData.observe(viewLifecycleOwner, {
            adapter.setPersonagensParaAdapter(it)
        })
    }

    private fun configuraObserverIsCarregando(){
        viewModel.isCarregandoLiveData.observe(viewLifecycleOwner, { isCarregando ->
            when {
                isCarregando -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recycclerviewPersonagens.visibility = View.INVISIBLE
                }
                else -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recycclerviewPersonagens.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun iniciaComponentesView() {
        configuraRecyclerView()
        eventoPesquisaPersonagemSearchView()
        configuraObserverPersonagensSearchView()
    }

    private fun configuraObserverPersonagensSearchView() {
        viewModel.isFiltroLiveData.observe(viewLifecycleOwner, {
            binding.textViewResete.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun eventoPesquisaPersonagemSearchView() {
        binding.searchViewPesquisaPersonagens.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                viewModel.getPersonagensPorNome(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    viewModel.getPersonagens(PAGINA_INICIAL)
                }
                return true
            }
        })
    }

    private fun configuraRecyclerView() {
        binding.recycclerviewPersonagens.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recycclerviewPersonagens.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
           viewModel.getPersonagens(PAGINA_INICIAL)
    }
}