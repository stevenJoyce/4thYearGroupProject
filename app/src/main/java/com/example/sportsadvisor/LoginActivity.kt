package com.example.sportsadvisor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.mongo.iterable.MongoCursor
import org.bson.Document
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList


class LoginActivity : AppCompatActivity() {
    //variables for user authentication
    lateinit var app: App
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var realm: Realm
    lateinit var emailTxt: String
    lateinit var passTxt: String
    var list: List<String> = ArrayList()
    var id: List<String> = ArrayList()
    var collectData:String = ""
    var name:String = ""


    lateinit var result: Document
    lateinit var results: MongoCursor<Document>
    lateinit var colResults:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        Realm.init(this)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        email = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        if (displayName != null) {
            name = displayName
        }

        // Current date
        val date = LocalDate.now()
        println(date)

        // Current time
        val time = LocalTime.now()
        println(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))

    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }

    //user pressed login button
    //handle all login button implications
    fun loginClicked(view: View) {
        emailTxt = email.text.toString()
        passTxt = password.text.toString()
        println("Username input: $emailTxt Password Input: $passTxt")
       val emailPasswordCredentials: Credentials = Credentials.emailPassword(
            emailTxt,
            passTxt
        )

        var user: User? = null
        app.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated using an email and password.")
                user = app.currentUser()
                // service for MongoDB Atlas cluster containing custom user data
                val mongoClient = user!!.getMongoClient("mongodb-atlas")
                val mongoDatabase = mongoClient.getDatabase("Users")
                val mongoCollection = mongoDatabase.getCollection("UserData")

                Log.v("EXAMPLE", "Successfully instantiated the MongoDB collection handle")

                //getting a cluster
                //val queryFilter = Document("_pkey", "datau1")
                val queryFilter = Document("_pkey", name)
                mongoCollection.findOne(queryFilter)
                    .getAsync { task ->
                        if (task.isSuccess) {
                            result = task.get()
                            Log.v("EXAMPLE", "successfully found a document: $result")
                            collectData = result.toString()
                            id  = collectData.split(",")
                            println("id: $id")
                            println(id[2])
                        } else {
                            Log.e("EXAMPLE", "failed to find document with: ${task.error}")
                        }
                    }

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

                }

                mongoCollection.count().getAsync { task ->
                    if (task.isSuccess) {
                        val count = task.get()
                        Log.v("EXAMPLE", "successfully counted, number of documents in the collection: $count")
                    } else {
                        Log.e("EXAMPLE", "failed to count documents with: ${task.error}")
                    }
                }

                /*mongoCollection.insertOne(Document("janeDoe01", user!!.id).append("_id", ObjectId()).append("parScore",72)
                    .append("_pkey","janeDoe01").append("roundScore",5).append("userScore",77).append("temperature",12)
                    .append("wind",24).append("humidity",63.85).append("precip",27.76).append("hour",12))
                    .getAsync { result ->
                        if (result.isSuccess) {
                            Log.v("EXAMPLE", "Inserted custom user data document. _id of inserted document: ${result.get().insertedId}")
                        } else {
                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: ${result.error}")
                        }
                    }*/
            } else {
                Log.e("AUTH", it.error.toString())
                showToast("Invalid username/password")
            }
        }
        showToast("User has Logged in")
    }

    //user pressed register button
    //handle all register button implications
    fun registerClicked(view: View) {
        //  val intent = Intent(this,Register::class.java);
        //  startActivity(intent)
        emailTxt = email.text.toString()
        passTxt = password.text.toString()

        println("Username added $emailTxt Password Added $passTxt")

        app.emailPassword.registerUserAsync(emailTxt, passTxt) {
            if (it.isSuccess) {
                Log.i("EXAMPLE", "Successfully registered user.")
                showToast("User has now Registered")
            } else {
                Log.e("EXAMPLE", "Failed to register user: ${it.error}")
                showToast("ERROR: User has failed to Register")
            }
        }

    }

    //user pressed guest button
    //handle all guest button implications
    fun guestClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //println("result stored $result")

    }
}