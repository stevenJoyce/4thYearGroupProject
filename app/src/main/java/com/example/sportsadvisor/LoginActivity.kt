package com.example.sportsadvisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import io.realm.Realm
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import org.bson.Document



class LoginActivity : AppCompatActivity() {
    //variables for user authentication
    lateinit var app: App
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        Realm.init(this)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)


    }

    fun addUser(v:View) {
        println("Username added " + username.text.toString() + " Password Added " + password.text.toString())

        app.emailPassword.registerUserAsync(username.toString(), password.toString()) {
            if (it.isSuccess) {
                Log.i("EXAMPLE","Successfully registered user.")
            } else {
                Log.e("EXAMPLE","Failed to register user: ${it.error}")
            }
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()

    }

    //user pressed login button
    //handle all login button implications
    fun loginClicked(view: View) {
        val emailPasswordCredentials: Credentials = Credentials.emailPassword(
            "g00362012@gmit.ie",
            "steven2021"
        )

        var user: User? = null
        app.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated using an email and password.")
                user = app.currentUser()

                val mongoClient =
                    user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
                val mongoDatabase = mongoClient.getDatabase("Users")
                val mongoCollection = mongoDatabase.getCollection("Data")
                Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

                //getting a cluster
                val queryFilter = Document("_pkey", "datau1")
                mongoCollection.findOne(queryFilter)
                    .getAsync { task ->
                        if (task.isSuccess) {
                            val result = task.get()
                            Log.v("EXAMPLE", "successfully found a document: $result")
                        } else {
                            Log.e("EXAMPLE", "failed to find document with: ${task.error}")
                        }
                    }
                mongoCollection.count().getAsync { task ->
                    if (task.isSuccess) {
                        val count = task.get()
                        Log.v("EXAMPLE", "successfully counted, number of documents in the collection: $count")
                    } else {
                        Log.e("EXAMPLE", "failed to count documents with: ${task.error}")
                    }
                }

            } else {
                Log.e("AUTH", it.error.toString())
            }
        }
    }

    //user pressed register button
    //handle all register button implications
    fun registerClicked(view: View) {
        //  val intent = Intent(this,Register::class.java);
        //  startActivity(intent)
    }

    //user pressed guest button
    //handle all guest button implications
    fun guestClicked(view: View) {
        val intent = Intent(this,MainActivity::class.java);
        startActivity(intent)
    }
}