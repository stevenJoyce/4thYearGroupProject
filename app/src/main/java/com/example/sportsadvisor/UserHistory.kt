package com.example.sportsadvisor

import android.os.Bundle
import android.util.Log
import android.view.View
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
    var listString:String=""
    var fl:String = ""
    var fl2:String = ""
    var fl3:String = ""
    var fl4:String = ""
    var pkey:String = ""
    var user: User? = null
    var query = Document("_id",false)
    var field1 = Document("date",true)
    var field2 = Document("time",true)
    var field3 = Document("Course",true)
    var field4 = Document("parScore",true)
    var field5 = Document("handicap",true)
    var field6= Document("totalScore",true)
    var field7 = Document("_key",false)

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
    fun userData(view: View) {
        val mongoClient =
            user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
        val mongoDatabase = mongoClient.getDatabase("Users")
        val mongoCollection = mongoDatabase.getCollection("UserData")

        Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

        //getting a cluster
        val queryFilter = Document("_pkey", pkey)
        val findTask = mongoCollection.find(queryFilter).projection(query).iterator()
        findTask.getAsync { task ->
            if (task.isSuccess) {
                list.clear()
                listString ="";
                results = task.get()
                Log.v("EXAMPLE", "successfully found all collections:")
                var x = 0
                while (results.hasNext()) {
                    colResults = results.next().toString()
                    //Log.v("First colResults: ", colResults)
                    filteredList.add(colResults.split("[","{{",",","}}","Document{{","]").toString())
                    fl += " Collection \n " + filteredList[x]
                    fl2 = fl.replace("[","")
                    fl3 = fl2.replace(",","")
                    fl4 = fl3.replace("]","")
                    listString = fl4
                    x++
                    //Log.v("EXAMPLE", listString)
                }

                Log.v("List", listString)

            } else {
                Log.e("EXAMPLE", "failed to find documents with: ${task.error}")
            }
           // println("List ${listString}")


        }

        text.text = listString



    }



}