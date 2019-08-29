package charnpreet.movie_world.Activity.Main

import android.content.DialogInterface
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.Filter.Filter
import charnpreet.movie_world.fragments.PersonalCollection.FavCollection
import charnpreet.movie_world.fragments.home.home_screen
import charnpreet.movie_world.fragments.search.search_in_movies
import charnpreet.movie_world.model.DeleteSession
import charnpreet.movie_world.model.Profile
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.ConstantProvider
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener {


    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var userName : TextView
    lateinit var email :TextView
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_variables()
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

       // HidingLogoutButton()
        // lisner for bottam navigation view
       val mOnNavigationItemSelectedListener = bottomNavigationListner()
       // setting up listner
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

       window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        // updating navigaion drawer headers
        HeadView()


        val redirectUri :Uri? = getIntent().getData()

        if(redirectUri!=null){

            loadingProfileFragment()
            // seting profile section of bottom navigation checked true
            bottomNavigation.menu.getItem(2).setChecked(true)
        }else{
            //
            // this will make sure when apps is open
            // top rated movies loaded automatically on home screen
            //
            load_home_screen_Fragment()
        }

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
            R.id.home -> {
                load_home_screen_Fragment()
                    item.setChecked(true)

            }
            R.id.search -> {
                loading_user_search_screen()
                item.setChecked(true)
            }
            R.id.profile -> {
                loadingProfileFragment()

                item.setChecked(true)
            }
            R.id.fav -> {
               loadFavFragment()
                item.setChecked(true)

            }
            R.id.filter->{
                loadFilterFragment()
                item.setChecked(true)
            }
            R.id.logout -> {
                LogOutUser()
                item.setChecked(true)
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

        bottomNavigation=findViewById(R.id.navigationView)
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

    private fun loadFavFragment(){
        val fav = FavCollection.newInstance()
        utility.loadFragment(fragmentManager,fav,false)
    }


    // loading profile frgament
    private fun loadingProfileFragment(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val profile = charnpreet.movie_world.fragments.Profile.Profile.newInstance()
        utility.loadFragment(fragmentManager,profile,false)
    }


    //
    //
    private fun LogOutUser(){

    val sessionID = GetSessionID()
        if(sessionID!=null){
            try {

                val dilogBuilder = utility.getCustomAlertDialogBuilder("","Logging You out", false
                    , this)
             val dialog =  dilogBuilder.create()
                dialog.show()
                API.search_In_Movies().LogOut(Movie_db_config.API_KEY, sessionID).enqueue(object: Callback<DeleteSession> {
                    override fun onFailure(call: Call<DeleteSession>?, t: Throwable?) {
                        Log.i("hello", "fail to log out")
                        dialog.dismiss()
                    }

                    override fun onResponse(call: Call<DeleteSession>?, response: Response<DeleteSession>?) {
                        val logout = response!!.body()
                        if(logout.success){

                            utility.clearSharedPreferences(applicationContext, true,"")
                            dialog.dismiss()

                        }
                    }

                })

            }catch (ex:Exception){

                Log.i("hello", "we encounter a problem Please try again later")

            }
        }else{
            CreateAlertBox(ConstantProvider.LOG_OUT_TEXT,ConstantProvider.SIGN_IN_BUTTON_TEXT)
        }


    }

    fun CreateAlertBox(text:String,buttonText:String){
        val dilogBuilder = utility.getCustomAlertDialogBuilder("",text, false
            , this)
        dilogBuilder.setPositiveButton(buttonText ,this)
        dilogBuilder.setNegativeButton(ConstantProvider.CANCEL_BUTTON_TEXT, this)
        dilogBuilder.create().show()
    }


    private fun bottomNavigationListner(): BottomNavigationView.OnNavigationItemSelectedListener{
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    load_home_screen_Fragment()
                    it.setChecked(true)

                }
                R.id.search -> {
                    loading_user_search_screen()
                    it.setChecked(true)

                }
                R.id.profile -> {

                    loadingProfileFragment()
                    it.setChecked(true)
                }
                R.id.fav -> {
                    loadFavFragment()
                    it.setChecked(true)

                }
                R.id.logout -> {
                    it.setChecked(true)
                }

            }
            false
        }
        return mOnNavigationItemSelectedListener
    }

    // alert dilog button click
    override fun onClick(p0: DialogInterface?, p1: Int) {
        if(DialogInterface.BUTTON_POSITIVE==p1){

            loadingProfileFragment()

        }else{

            p0!!.dismiss()
        }

    }
    //
    // this will fetech session id
    private fun GetSessionID():String?{
        return utility.RetrivingDataFromSharedPreferences(ConstantProvider.SESSION_ID_TAG, this)
    }

    // updating navigation header
    private fun HeadView(){
        val navDraweView = navView.getHeaderView(0)
        userName = navDraweView.findViewById(R.id.navi_Drawer_username)
        email = navDraweView.findViewById(R.id.navi_Drawer_email)
        val sessionID = GetSessionID()
        if(sessionID!=null){
            LogginDetails(sessionID)
        }else{
            userName.setText("")
            email.setText("")
        }

    }


    private fun LogginDetails(sessionID:String){
            API.search_In_Movies().AccountDetails(Movie_db_config.API_KEY, sessionID).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {
                    val profile = response!!.body()
                    userName.setText(profile.name)
                    email.setText(profile.username)

                }
                override fun onFailure(call: Call<Profile>?, t: Throwable?) {

                    Log.i("hello", "unable to load prfile")

                }
            })
    }
}
