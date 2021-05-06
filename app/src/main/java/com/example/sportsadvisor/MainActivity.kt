package com.example.sportsadvisor
//android


//realm
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsadvisor.model.HomeFragment
import com.example.sportsadvisor.model.ScoreFragment
import com.example.sportsadvisor.model.SettingsFragment
import com.example.sportsadvisor.model.WeatherFragment
import com.google.gson.Gson
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType.Companion.toMediaType

class MainActivity : AppCompatActivity() {
    //
    var dataRetreive:String = ""
    var courseCode:String = "208539"

    //navbar
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.home, "Home"),
        NavigationItemModel(R.drawable.weather, "Weather"),
        NavigationItemModel(R.drawable.scores, "Scores"),
        NavigationItemModel(R.drawable.profile, "User History"),
        NavigationItemModel(R.drawable.settings, "Settings")
    )

    lateinit var app: App
    lateinit var uiThreadRealm: Realm
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initiate a link to the server
        Realm.init(this)
        val appID = "sportsadvisor-gztkm"
        app = App(AppConfiguration.Builder(appID).build())
        // gson for parsing data

        //set drawer layout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Set the toolbar
        setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val displayName = sp.getString("displayName", "")
        val course = sp.getString("course", "")
        //WeatherDataProcessor.callCurrentData(courseCode)
        //WeatherDataProcessor.callHourlyData(courseCode)
        // Add Item Touch Listener

        //refreshHourlyApp()
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                val bundle = Bundle()
                val course = sp.getString("course", "")
                val displayName = sp.getString("displayName", "")
                when (position) {
                    0 -> {
                        refreshCurrentApp()
                        if(course == "Oughterard GC") {
                        courseCode = "208587"
                        WeatherDataProcessor.callCurrentData(courseCode)

                    }
                    else if(course == "Galway GC") {
                        courseCode = "208539"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Galway Bay GC") {
                        courseCode = "3549260"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Bearna GC") {
                        courseCode = "208553"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Glenlo Abbey GC") {
                        courseCode = "208539"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Connemara Championship Links") {
                        courseCode = "1651911"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Athenry GC") {
                        courseCode = "208546"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Gort GC") {
                        courseCode = "208564"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Loughrea GC") {
                        courseCode = "208542"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Tuam GC") {
                        courseCode = "208543"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Dunmore Demesne GC") {
                        courseCode = "208562"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }
                    else if(course == "Mountbellew GC") {
                        courseCode = "3545589"
                        WeatherDataProcessor.callCurrentData(courseCode)
                    }

                        bundle.putString("fragmentName", "The Average Weather today for $course")
                        bundle.putString("fullList", WeatherDataProcessor.hourlyListString)
                        // # Home Fragment
                        val homeFragment = HomeFragment()
                        homeFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.activity_main_content_id, homeFragment).commit()
                        }
                    }
                    1 -> {

                        refreshHourlyApp()
                        if(course == "Oughterard GC") {
                            courseCode = "208587"
                            WeatherDataProcessor.callHourlyData(courseCode)

                        }
                        else if(course == "Galway GC") {
                            courseCode = "208539"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Galway Bay GC") {
                            courseCode = "3549260"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Bearna GC") {
                            courseCode = "208553"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Glenlo Abbey GC") {
                            courseCode = "208539"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Connemara Championship Links") {
                            courseCode = "1651911"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Athenry GC") {
                            courseCode = "208546"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Gort GC") {
                            courseCode = "208564"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Loughrea GC") {
                            courseCode = "208542"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Tuam GC") {
                            courseCode = "208543"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Dunmore Demesne GC") {
                            courseCode = "208562"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        else if(course == "Mountbellew GC") {
                            courseCode = "3545589"
                            WeatherDataProcessor.callHourlyData(courseCode)
                        }
                        // # Weather Fragment
                        bundle.putString("courseName", "Next 12 Hours of Weather for $course")
                        bundle.putString("fullList", WeatherDataProcessor.hourlyListString)

                        val weatherFragment = WeatherFragment()
                        weatherFragment.arguments = bundle
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.activity_main_content_id, weatherFragment).commit()
                        }
                    }
                    2 -> {
                        // #Score Activity
                        val intent = Intent(this@MainActivity, ScoreFragment::class.java)
                        startActivity(intent)

                    }
                    3 -> {
                        // # Profile Activity
                        val intent = Intent(this@MainActivity, UserHistory::class.java)
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
                //updates adapter with curretn position selected
                updateAdapter(position)

                Handler(Looper.getMainLooper()).postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)

                //Thread.sleep(100)
            }
        }))

        // Update Adapter with item data and highlight the default menu item ('Home' Fragment)
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        refreshCurrentApp()
        val bundle = Bundle()
        WeatherDataProcessor.callCurrentData(courseCode)
        bundle.putString("fragmentName", "Weather for Galway GC")
        bundle.putString("fullList", WeatherDataProcessor.hourlyListString)
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_content_id, homeFragment).commit()
        }

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
        //navigation_header_img.setImageResource(R.drawable.golfbag)

        // Set background of Drawer
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))


    }
    // inspired by https://www.youtube.com/watch?v=oOIoRR0AiGo
    private fun refreshHourlyApp() {
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val bundle = Bundle()
        val course = sp.getString("course", "")
        swipeToRefresh.setOnRefreshListener {

            Toast.makeText(this, "Page Refreshed!", Toast.LENGTH_SHORT).show()
            swipeToRefresh.isRefreshing = false
            WeatherDataProcessor.callHourlyData(courseCode)

            bundle.putString("courseName", "Next 12 Hours of Weather for $course")
            bundle.putString("fullList", WeatherDataProcessor.hourlyListString)

            val weatherFragment = WeatherFragment()
            weatherFragment.arguments = bundle
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_content_id, weatherFragment).commit()
            }
        }
    }
    // inspired by https://www.youtube.com/watch?v=oOIoRR0AiGo
    private fun refreshCurrentApp() {
        val sp = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val bundle = Bundle()
        val course = sp.getString("course", "")
        swipeToRefresh.setOnRefreshListener {

            Toast.makeText(this,"Page Refreshed!", Toast.LENGTH_SHORT).show()
            swipeToRefresh.isRefreshing = false
            WeatherDataProcessor.callCurrentData(courseCode)

            bundle.putString("fragmentName", "The Average Weather today for $course")
            bundle.putString("fullList", WeatherDataProcessor.hourlyListString)
            // # Home Fragment
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_content_id, homeFragment).commit()
            }
        }
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    companion object {
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    }

    fun login(view: View) {
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
    }

    fun register(view: View){
        val reg = Intent(this, RegisterActivity::class.java)
        startActivity(reg)
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




