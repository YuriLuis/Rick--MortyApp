package com.yuri.apprickmorty.ui.main.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuri.apprickmorty.R


/**
 * A simple [Fragment] subclass.
 * Use the [PesonagensFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PesonagensFragment : Fragment(R.layout.fragment_pesonagens) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}