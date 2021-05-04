package com.example.sportsadvisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.iterable.MongoCursor
import org.bson.Document


class UserHistory : AppCompatActivity() {
    lateinit var app: App
    var list =  mutableListOf<String>()
    var filteredList = ArrayList<String>()
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String
    private lateinit var text: TextView

    //variables used to store the MongoDB data into a string
    var listString:String=""
    var fl:String = ""
    var fl2:String = ""
    var fl3:String = ""
    var fl4:String = ""
    var userID:String = ""
    var user: User? = null
    //Allow the data sent back to not include the ObjectID
    var query = Document("_id",false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userhistory_main)

        //The MongoDB Realm App ID
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        text = findViewById(R.id.userHistory)
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        userID = displayName.toString()
        user = app.currentUser()

        val mongoClient =
            user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase = mongoClient.getDatabase("Users")
        val mongoCollection = mongoDatabase.getCollection("UserData")

        Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

        //getting a cluster
        val queryFilter = Document("userID", userID)
        val findTask = mongoCollection.find(queryFilter).projection(query).iterator()
        findTask.getAsync { task ->
            if (task.isSuccess) {

                list.clear()
                listString = " "
                results = task.get()
                Log.v("EXAMPLE", "successfully found all collections:")
                var x = 0
                var colCount = 1
                while (results.hasNext()) {
                    colResults = results.next().toString()
                    filteredList.add(colResults.split("[","{{",",","}}","Document{{","]").toString())
                    fl += " Collection " + colCount + "\n" +"\t"+ filteredList[x]  + "\n\n"
                    fl2 = fl.replace("[","")
                    fl3 = fl2.replace(",","\n")
                    fl4 = fl3.replace("]","")
                    listString = fl4
                    x++
                    colCount++
                    Log.v("List", listString)
                }
            } else {
                Log.e("EXAMPLE", "failed to find documents with: ${task.error}")
                //listString = " No User data found"
            }
            // println("List ${listString}")

        }

    }
    fun userData(view: View) {
        //send stored data to page
        text.setText(listString)

    }



}