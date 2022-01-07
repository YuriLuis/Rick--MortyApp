package com.yuri.apprickmorty.ui.main.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuri.apprickmorty.R
import com.yuri.apprickmorty.data.repositories.PersonagemRepositoryImpl
import com.yuri.apprickmorty.data.services.remote.api.RickMortyRetrofit
import kotlinx.coroutines.runBlocking

class PesonagensFragment : Fragment(R.layout.fragment_pesonagens) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}