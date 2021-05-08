package com.example.sportsadvisor.model

import androidx.fragment.app.Fragment
import com.example.sportsadvisor.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_demo.view.*
import kotlinx.android.synthetic.main.fragment_demo.view.display_data
import kotlinx.android.synthetic.main.fragment_demo.view.fragment_weather
import kotlinx.android.synthetic.main.fragment_weather.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo, container, false)

        val fragmentName = arguments?.getString("fragmentName")
        val dataWeather = arguments?.getString("fullList")
        println("DATA WEATHER: "+ dataWeather.toString())

        //data passed through bundle obj from MainActivity is displayed in XML page
        rootView.fragment_weather.text = fragmentName
        if (dataWeather != null) {
            rootView.display_data.text = dataWeather.toString()
        }

        return rootView
    }
}
