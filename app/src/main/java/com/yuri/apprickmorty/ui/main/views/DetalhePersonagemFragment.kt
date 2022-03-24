package com.yuri.apprickmorty.ui.main.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.databinding.FragmentDetalhePersonagemBinding
import com.yuri.apprickmorty.ui.main.adapters.PERSONAGEM_KEY

class DetalhePersonagemFragment : Fragment(R.layout.fragment_detalhe_personagem) {

    private lateinit var binding: FragmentDetalhePersonagemBinding


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
        inciciaComponenstsView()
        return binding.root
    }

    private fun inciciaComponenstsView() {
        val personagem = arguments?.get(PERSONAGEM_KEY) as Personagem
        binding.apply {
            textViewIdPersonagemDetalhes.text = personagem.id.toString()
            textViewStatus.text = personagem.status
            Picasso.get().load(personagem.image).into(imageViewPersonagem)
            textViewPersonagemNomeDetalhes.text = personagem.nome
            textViewEspecieDetalhes.text = personagem.especie
            textViewGenero.text = personagem.genero
            textViewQuantidadeEpisodios.text = personagem.episodios.size.toString()
            textViewOrigem.text = personagem.origem.nome
            textViewLocalizacao.text = personagem.local.nome
        }
    }
}