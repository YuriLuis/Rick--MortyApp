package com.yuri.apprickmorty.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.databinding.FragmentPersonagensFavoritosBinding
import com.yuri.apprickmorty.ui.adapters.ListaPersonagensFavoritosAdapter
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModel
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PersonagensFavoritosFragment : Fragment() {

    private lateinit var binding: FragmentPersonagensFavoritosBinding
    @Inject
    lateinit var viewModelFactory: PersonagemViewModelFactory

    lateinit var viewModel: PersonagemViewModel

    @Inject
    lateinit var adapter: ListaPersonagensFavoritosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personagens_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonagensFavoritosBinding.bind(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonagemViewModel::class.java]
        configuraRecyclerView()
        configuraObserverPersonagensFavoritos()
        clickFloatingActionButtonDeletarPersonagemFavorito()
    }

    private fun configuraRecyclerView() {
        binding.recycclerviewPersonagensFavoritos.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recycclerviewPersonagensFavoritos.adapter = adapter
    }

    private fun configuraObserverPersonagensFavoritos(){
        viewModel.getAllPersonagensFavoritos().observe(viewLifecycleOwner, {
            adapter.setPersonagensFavoriosAdapter(it)
        })
    }

    private fun clickFloatingActionButtonDeletarPersonagemFavorito(){
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val personagem = adapter.getListaPersonagem()[position]
                viewModel.deletePersonagemFavorito(personagem)
                Snackbar.make(view!!,"Deleted Successfully", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.savePersonagemFavorito(personagem)
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recycclerviewPersonagensFavoritos)
        }
    }
}