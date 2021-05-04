package com.example.sportsadvisor
// Imports used on this page
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.mongodb.*

class RegisterActivity: AppCompatActivity() {
    //variables for user authentication
    lateinit var app: App
    private lateinit var email: EditText
    private lateinit var password: EditText
    lateinit var emailTxt: String
    lateinit var passTxt: String

    // OnCreate function that launches when the page is opened
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)
        Realm.init(this)
        // The MongoDB Realm App ID
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        //call the edit text in the login xml page
        email = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
    }
    // Function to output alerts to help user with any problems
    private fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }

    /* Function that happens when user presses the register button
        Used to collect the user input and check if the input is valid,
        if - the data is valid - user is registered
        else - the user gets an alert informing them what they did wrong
    */
    fun registerClicked(view: View) {
        // Collect the input the user has inputted
        emailTxt = email.text.toString()
        passTxt = password.text.toString()
        //a debug line
        println("Username added $emailTxt Password Added $passTxt")

        // Send the input to the mongoDB database to see if the input is valid
        app.emailPassword.registerUserAsync(emailTxt, passTxt) {
            // A successful result
            if (it.isSuccess) {
                Log.i("EXAMPLE", "Successfully registered user.")
                showToast("User has now Registered - now click GO TO LOGIN")
                // User has not registered - output an alert that informs the user of what is not valid
            } else {
                if(it.error.equals("email already in use"))
                {
                    // Alert when the email is already registered
                    Log.e("Email incorrect: ", "Failed to register user: ${it.error}")
                    showToast("ERROR: Email is already in use")
                }
                else
                {
                    // Alert when the password length is not correct
                    Log.e("Password incorrect: ", "Failed to register user: ${it.error}")
                    showToast("ERROR: Password length is not between 6 - 12 characters")
                }

            }
        }

    }
    // Function used to return the user to the home page
    fun returnClicked(view: View) {
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
    }
}