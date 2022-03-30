package com.yuri.apprickmorty.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.models.Personagem

class ListaPersonagemAdapterPaging: PagingDataAdapter<Personagem, ListaPersonagemAdapterPaging.MyViewHolder>(DiffUtilCallBack()){

    private var listaDePersonagens = emptyList<Personagem>()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener { view ->
            val bundle = bundleOf(PERSONAGEM_KEY to getItem(position))
            bundle.putInt("teste" , 0)
            view.findNavController()
                .navigate(R.id.action_pesonagensFragment_to_detalhePersonagemFragment, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.persongem_item_view_recyclerview, parent, false)

        return MyViewHolder(inflater)
    }

    fun setPersonagensParaAdapter(listaDePersonagem: List<Personagem>) {
        listaDePersonagens = listaDePersonagem
        notifyDataSetChanged()
    }


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var imagemPersonagem: ImageView = itemView.findViewById(R.id.imagemViewPersonagem)
        private var statusPersonagem: TextView =
            itemView.findViewById(R.id.textViewStatusPersonagem)
        private var id_number: TextView = itemView.findViewById(R.id.textViewIdPersonagem)
        private var name_character: TextView = itemView.findViewById(R.id.textViewNomePersonagem)

        fun bind(personagem: Personagem) {
            Picasso.get().load(personagem.image).into(imagemPersonagem)
            statusPersonagem.text = personagem.status
            id_number.text = personagem.id.toString()
            name_character.text = personagem.nome
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Personagem>() {
        override fun areItemsTheSame(oldItem: Personagem, newItem: Personagem): Boolean {
            return oldItem.nome == newItem.nome
        }

        override fun areContentsTheSame(oldItem: Personagem, newItem: Personagem): Boolean {
            return oldItem.nome == newItem.nome
                    && oldItem.especie == newItem.especie
        }

    }
}