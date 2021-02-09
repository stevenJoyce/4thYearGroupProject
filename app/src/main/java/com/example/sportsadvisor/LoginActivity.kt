package com.example.sportsadvisor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportsadvisor.model.Users
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmException


class LoginActivity : AppCompatActivity() {
    //variables for user authentication
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        realm = Realm.getDefaultInstance()

    }

    fun addUser(v:View){
        println("User name added "+ username.text.toString())
        print("Password Added " + password.text.toString())
        realm.beginTransaction()
        try{
            val nextId: Long = realm.where(Users::class.java).count() + 1
            val u = realm.createObject(Users::class.java,nextId)
            var uName:String = username.toString()
            var uPwd:String = password.toString()
            realm.commitTransaction()
            showToast("User successfully added")

        }catch(e: RealmException)
        {
            Log.d("TAG",e.message.toString())
        }
    }

    fun viewUser(v:View){
        val realmUsers = realm.where(Users::class.java).contains("Username",username.text.toString()).findAll()
        var k = ""
        showToast(k)

    }

    fun viewUsers(v:View){
        val realmUsers = realm.where(Users::class.java).findAll()
        var k = ""
        showToast(k)

    }


    fun updateUser(v:View){
        realm.executeTransaction { realm ->
            val u: Users = realm.where(Users::class.java).equalTo("username", "abc").findFirst()!!
            showToast("User successfully updated")
        }
    }

    fun deleteUser(v:View){

        val savedUsers:RealmResults<Users> = realm.where(Users::class.java).equalTo("username","abc").findAll()
        realm.executeTransaction {
            savedUsers.deleteAllFromRealm()
            showToast("User successfully deleted")
        }

    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()

    }
}