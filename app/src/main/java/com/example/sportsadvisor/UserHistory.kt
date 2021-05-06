package com.example.sportsadvisor

// Imports used on this page
import android.content.Intent
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
    // Variables used to access the realm app and data
    lateinit var app: App
    var filteredList = ArrayList<String>()
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String
    // Used to populate the textView in the user history xml page
    private lateinit var text: TextView
    // Variables used to store the MongoDB data into a string
    var listString:String=""
    var fl:String = ""
    var fl2:String = ""
    var fl3:String = ""
    var fl4:String = ""
    var userID:String = ""
    var user: User? = null
    // Allow the data sent back to not include the ObjectID
    var query = Document("_id",false)
    // OnCreate function that launches when the page is opened
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userhistory_main)
        // The MongoDB Realm App ID
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        // Set the textView to the textView on the xml page
        text = findViewById(R.id.userHistory)
        // Find the UserID value stored in the application settings page
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        userID = displayName.toString()
        user = app.currentUser()
        // Variables used to call the MongoDB Atlas cluster containing user data
        val mongoClient = user!!.getMongoClient("mongodb-atlas")
        // Call the database
        val mongoDatabase = mongoClient.getDatabase("Users")
        // Call the collection
        val mongoCollection = mongoDatabase.getCollection("UserData")
        Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")
        // Create a variable to filter the collections to only bring back the user data that contains the UserID
        val queryFilter = Document("userID", userID)
        /* Call the collection and filter the results with the query filter and
         output all collections associated with that userID*/
        val findTask = mongoCollection.find(queryFilter).projection(query).iterator()
        findTask.getAsync { task ->
            if (task.isSuccess) {
                listString = " "
                results = task.get()
                Log.v("EXAMPLE", "successfully found all collections:")
                var x = 0
                var colCount = 1
                while (results.hasNext()) {
                    colResults = results.next().toString()
                    /* Modify the data received from the call with the split and replace embedded methods in Kotlin*/
                    filteredList.add(colResults.split("[","{{",",","}}","Document{{","]").toString())
                    fl += " Collection " + colCount + "\n" +"\t"+ filteredList[x]  + "\n\n"
                    fl2 = fl.replace("[","")
                    fl3 = fl2.replace(",","\n")
                    fl4 = fl3.replace("]","")
                    listString = fl4
                    // iterator used to store the collection
                    x++
                    //Iterator outputted to the user for better readability
                    colCount++
                    Log.v("List", listString)
                }
            } else {
                Log.e("EXAMPLE", "failed to find documents with: ${task.error}")
                //listString = " No User data found"
            }
        }
    }
    // Function used to output the collection data to the page
    fun userData(view: View) {
        //send stored data to page
        text.setText(listString)

    }

    // Function used to return the user to the home page
    fun homeClicked(view: View) {
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }



}