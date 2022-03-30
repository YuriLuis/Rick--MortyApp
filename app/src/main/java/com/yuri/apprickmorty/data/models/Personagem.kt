package com.yuri.apprickmorty.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.yuri.apprickmorty.data.db.PERSONAGENS
import java.io.Serializable
@Entity(tableName = PERSONAGENS)
data class Personagem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("name")
    val nome: String?,
    val status: String?,
    @SerializedName("species")
    val especie: String?,
    @SerializedName("gender")
    val genero: String?,
    @SerializedName("origin")
    val origem: LocacaoData?,
    @SerializedName("location")
    val local: LocacaoData?,
    val image: String?,
    @SerializedName("episode")
    val episodios: List<String>?
): Serializable {
}

data class Info(val count: Int?, val pages: String?, val next: String?,val prev: String?)