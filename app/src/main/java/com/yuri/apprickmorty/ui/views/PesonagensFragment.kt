package com.yuri.apprickmorty.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.databinding.FragmentPesonagensBinding
import com.yuri.apprickmorty.ui.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.adapters.ListaPersonagemAdapterPaging
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModel
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModelPaging
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelFactory
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelPagingFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

var PAGINA_INICIAL = 1

@AndroidEntryPoint
class PesonagensFragment : Fragment() {

    private lateinit var binding: FragmentPesonagensBinding

    @Inject
    lateinit var viewModelFactory: PersonagemViewModelPagingFactory

    lateinit var viewModel: PersonagemViewModelPaging

    @Inject
    lateinit var adapter: ListaPersonagemAdapterPaging

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
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonagemViewModelPaging::class.java]
        iniciaComponentesView()
        configuraObserverIsCarregando()
        configuraObserverListaPersonagens()
        //viewModel.getPersonagens(PAGINA_INICIAL)
    }

    private fun configuraObserverListaPersonagens() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                adapter.submitData(it)
            }
        }

        //viewModel.personagensLiveData.observe(viewLifecycleOwner, { response ->
        //adapter.setPersonagensParaAdapter(response.data!!.results)
        //  })
    }

    private fun configuraObserverIsCarregando() {
        viewModel.isCarregandoLiveData.observe(viewLifecycleOwner, {
            when {
                it -> {
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

    }

    private fun eventoPesquisaPersonagemSearchView() {

    }

    private fun configuraRecyclerView() {
        binding.recycclerviewPersonagens.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recycclerviewPersonagens.adapter = adapter
    }

}