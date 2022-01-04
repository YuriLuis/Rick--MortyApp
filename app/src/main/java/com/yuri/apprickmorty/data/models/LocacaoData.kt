package com.yuri.apprickmorty.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocacaoData(
    @SerializedName("name")
    var nome: String,
    var url: String
): Serializable {
}