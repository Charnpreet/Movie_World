package charnpreet.movie_world.Home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.display_movie_adapter
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.fragments.search.search_in_movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.RecyclerView as RecyclerView1

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

   lateinit  var recyclerView: RecyclerView1;
    lateinit var toolbar: Toolbar;
    lateinit var drawerLayout: DrawerLayout;
    lateinit var navView: NavigationView;
    //private var movies:  List<Movies>? = null
    private lateinit var linearLayoutManager: LinearLayoutManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_variables();
        initRecylerView();
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        //
        // this will make sure when apps is open
        // top rated movies loaded automatically on home screen
        //
       // load_Top_Movies();
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
        return true
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
            //    load_Top_Movies();

            }
            R.id.nav_gallery -> {
                loading_user_search_screen();
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    //
    // below method is used to laod top rated movies movie_db
    private fun load_Top_Movies(){
        API.search_In_Movies().topRated(Movie_db_config.API_KEY).enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                passingdatatorecyerview(call,response);
            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                Log.i("hello", "failed to load data");
            }

        })
    }



        // this mehthod is used to pass data to recyerview holder
        // it deserilsed the retrofit response into an object
        private fun passingdatatorecyerview(call: Call<MoviesResponse>?,response: Response<MoviesResponse>?)
        {
            if (call != null) {
                var  movies: List<Movies>? = response!!.body().results;
                recyclerView!!.adapter = display_movie_adapter(movies, R.layout.display_movie_recylerview_holder, applicationContext);
//
            }
        }

    //
    // init recyler view holder
    private fun initRecylerView(){
        recyclerView = findViewById(R.id.display_movies_recylerview1);

        if(recyclerView!=null){
            linearLayoutManager = LinearLayoutManager(this);
            recyclerView!!.layoutManager = linearLayoutManager
        }
        else {
            Log.i("hello", "error ataching recyler View ");
        }

}
    // init all other variables
    private fun init_variables(){

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout)
        navView= findViewById(R.id.nav_view)
    }

    //
    // this will load search_movie fragment
    //
    private fun loading_user_search_screen(){
        val search_movies: search_in_movies = search_in_movies.newInstance();
        val fragmentManager: FragmentManager = supportFragmentManager;
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout, search_movies);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();


    }
}
