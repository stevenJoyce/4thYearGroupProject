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
import android.os.Looper
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsadvisor.model.ScoreFragment
import com.example.sportsadvisor.model.SettingsFragment
import com.example.sportsadvisor.model.WeatherFragment


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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import org.bson.Document
import java.io.File


class MainActivity : AppCompatActivity() {
    //
    var dataRetreive:String = ""

    //navbar
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.home, "Home"),
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
        // Fetching JSON DATA
        //val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        //val url = "https://raw.github.com/square/okhttp/master/README.md"
        //val url = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/3528176?apikey=Gngag9jfLyY2fDDrLSr27EVYD1TarOiW&language=en-us&details=true&metric=true"
        val url = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/3528176?apikey=Gngag9jfLyY2fDDrLSr27EVYD1TarOiW&language=en-us&details=true&metric=true"
        //val url = "https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,daily&appid={8dd12c7a3f8395577f85810b0ecd47f3}"
        fetchJson(url)


        //val config = RealmConfiguration.Builder().name("Users:Realm").build()
        //val longitude = 53.2707;
        // val latitude = 9.0568;
        // val result = URL("https://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=<53.2707>;long=<9.0568>").readText()
        // println(result)
        // Log.d("Result From API", result)
        //fetchJson()

        val button: Button = findViewById(R.id.loginNavbutton)
        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        // Set the toolbar
        setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)


        // Add Item Touch Listener
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # Home Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Home Fragment")
                        val homeFragment = DemoFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        // # Weather Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Weather Fragment")
                        val weatherFragment = WeatherFragment()
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.activity_main_content_id, weatherFragment).commit()
                        }
                    }
                    2 -> {
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Scores Fragment")
                        // # Scores Fragment
                        val scoresFragment = ScoreFragment()
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.activity_main_content_id, scoresFragment).commit()
                        }

                    }
                    3 -> {
                        // # Profile Activity
                        val intent = Intent(this@MainActivity, DemoActivity::class.java)
                        startActivity(intent)
                    }
                    4 -> {
                        // # Settings Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Settings Fragment")
                        val settingsFragment = SettingsFragment()
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.activity_main_content_id, settingsFragment).commit()
                        }
                    }

                }

                Handler(Looper.getMainLooper()).postDelayed({
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


        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            activity_main_toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Set Header Image
        navigation_header_img.setImageResource(R.drawable.logo)

        // Set background of Drawer
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    fun fetchJson(url: String): String {
        println("Attempting to Fetch JSON")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        var body = ""
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                println("Fetched JSON Data")
                body = response.body?.string().toString()
                saveData(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
        return body
    }

    fun saveData(body: String){
        dataRetreive = body
        println(dataRetreive)
    }

    // Posting to Server - Not fully complete
    fun postJson (dataFetch: String){
        val file = File("data.txt")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(file.asRequestBody(MEDIA_TYPE_MARKDOWN))
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            println(response.body!!.string())
        }

    }
    companion object {
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
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




