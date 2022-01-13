package com.yuri.apprickmorty.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.models.Personagem
import kotlin.coroutines.coroutineContext

class ListaPersonagemAdapter : RecyclerView.Adapter<ListaPersonagemAdapter.PersonagemViewHolder>() {

    private var listaDePersonagens = emptyList<Personagem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.persongem_item_view_recyclerview, parent, false)
        return PersonagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        holder.bind(listaDePersonagens[position])

        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return listaDePersonagens.size
    }

    fun setPersonagensParaAdapter(listaDePersonagem: List<Personagem>){
        listaDePersonagens = listaDePersonagem
        notifyDataSetChanged()
    }

    class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imagemPersonagem: ImageView = itemView.findViewById(R.id.imagemViewPersonagem)
        private var statusPersonagem: TextView = itemView.findViewById(R.id.textViewStatusPersonagem)
        private var id_number: TextView = itemView.findViewById(R.id.textViewIdPersonagem)
        private var name_character: TextView = itemView.findViewById(R.id.textViewNomePersonagem)

        fun bind(personagem: Personagem){
            Picasso.get().load(personagem.image).into(imagemPersonagem)
            statusPersonagem.text = personagem.status
            id_number.text = personagem.id.toString()
            name_character.text = personagem.nome
        }
    }
}