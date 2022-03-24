package com.yuri.apprickmorty.ui.main.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.databinding.FragmentPesonagensBinding
import com.yuri.apprickmorty.ui.main.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.main.viewmodels.PersonagemViewModel
import com.yuri.apprickmorty.ui.main.viewmodelsfactory.PersonagemViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val PAGINA_INICIAL = 1

@AndroidEntryPoint
class PesonagensFragment : Fragment() {

    private lateinit var binding: FragmentPesonagensBinding

    @Inject
    lateinit var viewModelFactory: PersonagemViewModelFactory

    lateinit var viewModel: PersonagemViewModel

    @Inject
    lateinit var adapter: ListaPersonagemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pesonagens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPesonagensBinding.bind(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonagemViewModel::class.java]
        iniciaComponentesView()
        configuraObserverIsCarregando()
        configuraObserverListaPersonagens()
        viewModel.getPersonagens(PAGINA_INICIAL)
    }

    private fun configuraObserverListaPersonagens() {
        viewModel.personagensLiveData.observe(viewLifecycleOwner, {
            adapter.setPersonagensParaAdapter(it)
        })
    }

    private fun configuraObserverIsCarregando() {
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

}