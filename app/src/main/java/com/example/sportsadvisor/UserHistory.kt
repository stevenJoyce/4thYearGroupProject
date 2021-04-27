package com.example.sportsadvisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.iterable.MongoCursor
import org.bson.Document

class UserHistory : AppCompatActivity() {
    lateinit var app: App
    var list: List<String> = ArrayList()
    lateinit var result: Document
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String
    private lateinit var text: TextView
    var pkey:String = ""
    var user: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userhistory_main)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        text = findViewById(R.id.userHistory)
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        pkey = displayName.toString()

        user = app.currentUser()
       /* if(app.currentUser() == null)
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            showToast("ERROR: need to login to the application")
        }
*/

    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }
    fun userData(view: View) {
        val mongoClient =
            user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase = mongoClient.getDatabase("Users")
        val mongoCollection = mongoDatabase.getCollection("UserData")

        Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

        //getting a cluster
        val queryFilter = Document("_pkey", pkey)
        val findTask = mongoCollection.find(queryFilter).iterator()
        findTask.getAsync { task ->
            if (task.isSuccess) {
                results = task.get()
                Log.v("EXAMPLE", "successfully found all collections:")
                while (results.hasNext()) {
                    colResults = results.next().toString()
                    list = colResults.split("=",",")
                    Log.v("EXAMPLE", colResults)
                }
            } else {
                Log.e("EXAMPLE", "failed to find documents with: ${task.error}")
            }
            var i = 0
            while(i < list.size)
            {
                println("Stored data: " + list[i])
                i++
            }
        }

        text.text = list.toString()

    }


}