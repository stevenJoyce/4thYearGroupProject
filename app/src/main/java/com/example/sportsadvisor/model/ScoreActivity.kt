package com.example.sportsadvisor.model

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.sportsadvisor.LoginActivity
import com.example.sportsadvisor.MainActivity
import com.example.sportsadvisor.R
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.login_main.button
import org.bson.Document
import org.bson.types.ObjectId
import java.text.SimpleDateFormat
import java.util.*

class ScoreActivity : AppCompatActivity() {
    private lateinit var userCourse: EditText

    //playedCourse
    override fun onCreate(savedInstanceState: Bundle?) {
        var userID = ""
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        val handicap = sp.getString("handicap","")

        userID = displayName.toString()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_score)

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

                var tpar = Integer.parseInt(totalPar.text as String)
                var npar = Integer.parseInt(netPar.text as String)
                var nscore = Integer.parseInt(NettScore.text as String)
                userCourse = findViewById(R.id.playedCourse)
                var courseName = userCourse.text.toString()
                /* Calling the function and sending the data generated from user input
                * alongside the course name and the unique username the user generates in the settings page*/
                sendData(tpar,npar,nscore,courseName,userID)

            }catch (e:Exception){
                println(e)
                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            }
        }
    }
    /*Function to send data from the Android Application to the MongoDB Database to be saved as a collection*/
    fun sendData(parScore: Int, handicap: Int, nettScore: Int, courseName: String, userID: String)
    {
        // Saving the data received in the function call into local variables to be sent to the server
        var par = parScore
        var hand = handicap
        var nett = nettScore
        var coursename = courseName
        var key = userID
        //variables used to access the MongoDB Server
        lateinit var app: App
        var user: User? = null
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        user = app.currentUser()
        // service for MongoDB Atlas cluster containing custom user data
        val mongoClient = user!!.getMongoClient("mongodb-atlas")
        val mongoDatabase = mongoClient.getDatabase("Users")
        val mongoCollection = mongoDatabase.getCollection("UserData")
        //Getting the current time and date
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        /*this block of code inserts the data we want to populate the database with into a new collection
        on that database. Each append is a different field in the collection and the data is stored in 1 collection
        containing everything that has been sent. The userID allows this collection to only be accessed by a user
        who inputs that id into the settings page */
        mongoCollection.insertOne(Document("UserData",user.id).append("_id", ObjectId()).append("date",currentDate)
            .append("time",currentTime).append("course",coursename).append("parScore",par).append("handicap",hand)
            .append("totalScore",nett).append("userID", key))
            .getAsync { result ->
                //An if else method used for debugging purposes
                if (result.isSuccess) {
                    Log.v(
                        "EXAMPLE",
                        "Inserted custom user data document. _id of inserted document: ${result.get().insertedId}"
                    )
                } else {
                    Log.e("EXAMPLE", "Unable to insert custom user data. Error: ${result.error}")
                }
            }
    }

    // Function used to return the user to the home page
    fun homeClicked(view: View) {
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }
}


