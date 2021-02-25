package com.example.sportsadvisor
//android
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsadvisor.model.Data
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


import okhttp3.*
import java.io.IOException
//realm
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import org.bson.Document


class MainActivity : AppCompatActivity() {

    //navbar
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.weather, "Weather"),
        NavigationItemModel(R.drawable.scores, "Scores"),
        NavigationItemModel(R.drawable.profile, "Profile"),
        NavigationItemModel(R.drawable.settings, "Settings")
    )

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
            "steven2021"
        )

        var user: User? = null
        app.loginAsync(emailPasswordCredentials) {
            if (it.isSuccess) {
                Log.v("AUTH", "Successfully authenticated using an email and password.")
                user = app.currentUser()

                val mongoClient =
                    user!!.getMongoClient("mongodb-atlas") // service for MongoDB Atlas cluster containing custom user data
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

      /*  val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }*/

        drawerLayout = findViewById(R.id.drawer_layout)

        // Set the toolbar
        // setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

        // Add Item Touch Listener
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # Home Fragment
                        val homeFragment = DemoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        // # Music Fragment
                        val weatherFragment = DemoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, weatherFragment).commit()
                    }
                    2 -> {
                        // # Movies Fragment
                        val scoresFragment = DemoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, scoresFragment).commit()
                    }
                    3 -> {
                        // # Profile Activity
                        val intent = Intent(this@MainActivity, DemoActivity::class.java)
                        startActivity(intent)
                    }
                    4 -> {
                        // # Settings Fragment
                        val settingsFragment = DemoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, settingsFragment).commit()
                    }

                }

                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        // Update Adapter with item data and highlight the default menu item ('Home' Fragment)
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")
        val homeFragment = DemoFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    fun fetchJson() {
        println("Attempting to Fetch JSON")

        //val url = "https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>";
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)
            }

        })

    }

    fun login(view: View) {
        val intentLog = Intent(this, LoginActivity::class.java)
        startActivity(intentLog)
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



