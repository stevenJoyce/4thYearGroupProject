package com.example.sportsadvisor
//android
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.sportsadvisor.model.Data

import okhttp3.*
import java.io.IOException
//realm
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.Document


class
MainActivity : AppCompatActivity() {
    lateinit var app: App
    lateinit var uiThreadRealm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initiate a link to the server
        Realm.init(this)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())

        /*auto login
        val anonymousCredentials: Credentials = Credentials.anonymous()
        var user: User?
        app.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated anonymously.")
                user = app.currentUser()

                val mongoClient = user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
                val mongoDatabase = mongoClient.getDatabase("Users")
                val mongoCollection = mongoDatabase.getCollection("Data")
                Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

                 //getting a cluster
                val queryFilter = Document("hour", "6")
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
        }*/
        //valid login
        val emailPasswordCredentials: Credentials = Credentials.emailPassword(
            "g00362012@gmit.ie",
            "steven2021")

        var user: User? = null
        app.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated using an email and password.")
                user = app.currentUser()

                val mongoClient = user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
                val mongoDatabase = mongoClient.getDatabase("Users")
                val mongoCollection = mongoDatabase.getCollection("Data")
                Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

                //getting a cluster
                val queryFilter = Document("hour", "6")
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





        //val config = RealmConfiguration.Builder().name("Users:Realm").build()
        //val longitude = 53.2707;
        // val latitude = 9.0568;
        // val result = URL("https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>").readText()
        // println(result)
        // Log.d("Result From API", result)
        //fetchJson()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }




    fun login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        // the ui thread realm uses asynchronous transactions, so we can only safely close the realm
        // when the activity ends and we can safely assume that those transactions have completed
        uiThreadRealm.close()
        app.currentUser()?.logOutAsync {
            if (it.isSuccess) {
                Log.v("QUICKSTART", "Successfully logged out.")
            } else {
                Log.e("QUICKSTART", "Failed to log out, error: ${it.error}")
            }
        }
    }


}

