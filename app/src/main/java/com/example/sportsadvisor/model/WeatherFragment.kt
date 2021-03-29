package com.example.sportsadvisor.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportsadvisor.R
import kotlinx.android.synthetic.main.fragment_weather.view.*


class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)

        val fragmentName = arguments?.getString("fragmentName")

        rootView.fragment_weather.text = fragmentName

        return rootView
    }
}