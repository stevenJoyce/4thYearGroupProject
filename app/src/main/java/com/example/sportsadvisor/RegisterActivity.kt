package com.example.sportsadvisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.mongo.iterable.MongoCursor
import org.bson.Document

class RegisterActivity: AppCompatActivity() {
    //variables for user authentication
    lateinit var app: App
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var realm: Realm
    lateinit var emailTxt: String
    lateinit var passTxt: String
    var colData:Array<String> = arrayOf("")

    lateinit var result: Document
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)
        Realm.init(this)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        email = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)

    }

    fun registerClicked(view: View) {
        //  val intent = Intent(this,Register::class.java);
        //  startActivity(intent)
        emailTxt = email.text.toString()
        passTxt = password.text.toString()

        println("Username added $emailTxt Password Added $passTxt")

        app.emailPassword.registerUserAsync(emailTxt, passTxt) {
            if (it.isSuccess) {
                Log.i("EXAMPLE", "Successfully registered user.")
            } else {
                Log.e("EXAMPLE", "Failed to register user: ${it.error}")
            }
        }
    }

    fun returnClicked(view: View){
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
    }
}