package com.example.sportsadvisor.model

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.sportsadvisor.R
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import kotlinx.android.synthetic.main.login_main.*
import kotlinx.android.synthetic.main.login_main.button
import java.io.Console


class ScoreFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_score)
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val handicap = sp.getString("handicap","")

        button.setOnClickListener{
            try {
                front9par.text = "" + (hole1Par.text.toString().toInt() + hole2par.text.toString().toInt() + hole3par.text.toString().toInt() + hole4par.text.toString().toInt() + hole5par.text.toString().toInt() + hole6par.text.toString().toInt() + hole7par.text.toString().toInt() + hole8par.text.toString().toInt() + hole9par.text.toString().toInt()).toString()
                front9Score.text = "" + (hole1Score.text.toString().toInt() + hole2Score.text.toString().toInt() + hole3Score.text.toString().toInt() + hole4Score.text.toString().toInt() + hole5Score.text.toString().toInt() + hole6Score.text.toString().toInt() + hole7Score.text.toString().toInt() + hole8Score.text.toString().toInt() + hole9Score.text.toString().toInt()).toString()
                back9Par.text = "" + (hole10par.text.toString().toInt() + hole11par.text.toString().toInt() + hole12par.text.toString().toInt() + hole13par.text.toString().toInt() + hole14par.text.toString().toInt() + hole15par.text.toString().toInt() + hole16par.text.toString().toInt() + hole17par.text.toString().toInt() + hole18par.text.toString().toInt()).toString()
                back9Score.text = "" + (hole10Score.text.toString().toInt() + hole11Score.text.toString().toInt() + hole12Score.text.toString().toInt() + hole13Score.text.toString().toInt() + hole14Score.text.toString().toInt() + hole15Score.text.toString().toInt() + hole16Score.text.toString().toInt() + hole17Score.text.toString().toInt() + hole18Score.text.toString().toInt()).toString()
                totalPar.text ="" + (front9par.text.toString().toInt() + back9Par.text.toString().toInt())
                totalScore.text ="" + (front9Score.text.toString().toInt() + back9Score.text.toString().toInt())
                netPar.text= handicap.toString()
                NettScore.text ="" + (totalScore.text.toString().toInt() - handicap.toString().toInt())


            }catch (e:Exception){
                println(e)
                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            }
        }
    }
}