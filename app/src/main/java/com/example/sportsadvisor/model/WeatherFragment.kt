package com.example.sportsadvisor.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.example.sportsadvisor.R
import kotlinx.android.synthetic.main.fragment_weather.view.*


class WeatherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false)
        val courseName = arguments?.getString("courseName")
        val dataWeather = arguments?.getString("fullList")
        println("DATA WEATHER: "+ dataWeather.toString())
        //data passed through bundle obj from MainActivity is displayed in XML page
        rootView.fragment_weather.text = courseName
        if (dataWeather != null) {
            rootView.display_data.text = dataWeather.toString()
        }


        return rootView
    }
}