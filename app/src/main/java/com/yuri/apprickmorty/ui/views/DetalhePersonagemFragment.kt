package com.yuri.apprickmorty.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.databinding.FragmentDetalhePersonagemBinding
import com.yuri.apprickmorty.ui.adapters.PERSONAGEM_KEY
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModel
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetalhePersonagemFragment : Fragment(R.layout.fragment_detalhe_personagem) {

    private lateinit var binding: FragmentDetalhePersonagemBinding

    @Inject
    lateinit var viewModelFactory: PersonagemViewModelFactory

    lateinit var viewModel: PersonagemViewModel

    private lateinit var personagemArgs: Personagem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detalhe_personagem,
            container,
            false
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonagemViewModel::class.java]

        inciciaComponenstsView()
        clickSalvarPersonagemFavorito()
        return binding.root
    }

    private fun inciciaComponenstsView() {
        personagemArgs = arguments?.get(PERSONAGEM_KEY) as Personagem
        val telaVindaPorArgumento = arguments!!["teste"]
        binding.apply {
            textViewIdPersonagemDetalhes.text = personagemArgs.id.toString()
            textViewStatus.text = personagemArgs.status
            Picasso.get().load(personagemArgs.image).into(imageViewPersonagem)
            textViewPersonagemNomeDetalhes.text = personagemArgs.nome
            textViewEspecieDetalhes.text = personagemArgs.especie
            textViewGenero.text = personagemArgs.genero
            textViewQuantidadeEpisodios.text = personagemArgs.episodios!!.size.toString()
            textViewOrigem.text = personagemArgs.origem!!.nome
            textViewLocalizacao.text = personagemArgs.local!!.nome
            //0 = tela todos personagem para detalhes
            //1 = tela dos personagens Favoritos salvos
            if(telaVindaPorArgumento == 1){
                floatingActionButtonSavePersonagemFavorito.visibility = View.INVISIBLE
            }
        }
    }

    private fun clickSalvarPersonagemFavorito(){
        binding.floatingActionButtonSavePersonagemFavorito.setOnClickListener {
            viewModel.savePersonagemFavorito(personagemArgs)
            Snackbar.make(it, "Saved Sucessfully!", Snackbar.LENGTH_LONG).show()
        }
    }
}