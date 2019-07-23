package charnpreet.movie_world.Activity.Activity.Main

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.WindowManager
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.Filter.Filter
import charnpreet.movie_world.fragments.home.home_screen
import charnpreet.movie_world.fragments.search.search_in_movies
import charnpreet.movie_world.utility.utility

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_variables();
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color=Color.RED

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

       window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        //
        // this will make sure when apps is open
        // top rated movies loaded automatically on home screen
        //
        load_home_screen_Fragment();
    }

    override fun onBackPressed() {

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            R.id.action_settings -> true

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                load_home_screen_Fragment()

            }
            R.id.nav_gallery -> {
                loading_user_search_screen()
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {
                loadFilterFragment()

            }
            R.id.nav_share -> {

            }

        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    // init all other variables
    private fun init_variables(){

        toolbar = findViewById(R.id.toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        navView= findViewById(R.id.nav_view)
    }

    // utility instance
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance

    // fragment manager used to load fragments
    val fragmentManager: FragmentManager = supportFragmentManager
    //
    // this will load search_movie fragment
    //
    private fun loading_user_search_screen(){

        val search_movies: search_in_movies = search_in_movies.newInstance()

        utility.loadFragment(fragmentManager,search_movies,false)
    }

    private fun load_home_screen_Fragment(){

        val home_screen: home_screen = home_screen.newInstance()

        utility.loadFragment(fragmentManager,home_screen,false)

    }
    private fun loadFilterFragment(){
        val filter:Filter = Filter.newInstance()
        utility.loadFragment(fragmentManager,filter,false)
    }
}
