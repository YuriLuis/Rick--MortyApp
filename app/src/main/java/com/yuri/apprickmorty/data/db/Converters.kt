package com.yuri.apprickmorty.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yuri.apprickmorty.data.models.LocacaoData

class Converters {

    @TypeConverter
    fun fromLocacaoDataToString(locacaoData: LocacaoData):String{
        return Gson().toJson(locacaoData)
    }

    @TypeConverter
    fun stringToLocacaoData(nome: String): LocacaoData{
        return Gson().fromJson(nome, LocacaoData::class.java)
    }


    @TypeConverter
    fun toEpisodiosList(json: String): List<String>{
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(list: List<String>): String{
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().toJson(list, type)
    }
}