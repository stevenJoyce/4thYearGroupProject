package com.example.sportsadvisor
// Imports used on this page
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import io.realm.Realm
import io.realm.mongodb.*
import java.time.*
import java.time.format.*

class LoginActivity : AppCompatActivity() {
    //variables for user authentication
    lateinit var app: App
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var realm: Realm
    lateinit var emailTxt: String
    lateinit var passTxt: String
    var name:String = ""
    // OnCreate function that launches when the page is opened
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        Realm.init(this)
        // The MongoDB Realm App ID
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        email = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        // Find the UserID value stored in the application settings page
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        // Make sure the display dame is not empty and set a local variable to this value
        if (displayName != null) {
            name = displayName
        }
        // Set the Current date and time
        val date = LocalDate.now()
        val time = LocalTime.now()
        //Output the date and time for debugging
        println(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))
        println(date)

    }
    // Function to output alerts to help user with any problems
    private fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }
    /* Function that happens when user presses the login button
        Used to collect the user input and check if the input is valid,
        if - the data is valid - user is then logged in
        else - the user gets an alert informing them what they did wrong
    */
    fun loginClicked(view: View) {
        //Collect the user input for email and password
        emailTxt = email.text.toString()
        passTxt = password.text.toString()
        println("Username input: $emailTxt Password Input: $passTxt")
        /* Check if both email and password received is not empty, if so populates them with invalid data
         to allow the mongoDb call to bring back an error */
        if (emailTxt.isEmpty() && passTxt.isEmpty()){
            emailTxt = "Email@123.ie"
            passTxt = "pass"
            println("Email and pass empty")
        }
        // A validation check to see only the password is empty
        if (emailTxt == email.text.toString() && passTxt.isEmpty()){
            passTxt = "pass"
            println("pass empty")
        }
        // A validation check to see if only the email is empty
        if (emailTxt.isEmpty() && passTxt == password.text.toString()){
            emailTxt = "Email@123.ie"
            println("Email empty")
        }

        // A variable that takes in the email and password before it is used to try and login
       val emailPasswordCredentials: Credentials = Credentials.emailPassword(
            emailTxt,
            passTxt
        )
        var user: User? = null

        /* Method to allow user to sign in to the database
         with the user input stored in the variable emailPasswordCredentials*/
        app.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated using an email and password.")
                showToast("User has Logged in")
                //When user is logged, user is redirected to the home page
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Log.e("AUTH", it.error.toString())
                showToast("Invalid username/password")
            }
        }

    }

    /* Function that when the user pressed the Home button
        allows the user to go back to the home page
     */
    fun goHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //println("result stored $result")

    }
}