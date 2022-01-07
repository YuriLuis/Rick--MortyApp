package com.yuri.apprickmorty.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Personagem(
    var id: Int,
    @SerializedName("name")
    var nome: String,
    var status: String,
    @SerializedName("species")
    var especie: String,
    @SerializedName("gender")
    var genero: String,
    @SerializedName("origin")
    var origem: LocacaoData,
    @SerializedName("location")
    var local: LocacaoData,
    var image: String,
    @SerializedName("episode")
    var episodios: List<String>
): Serializable {
}