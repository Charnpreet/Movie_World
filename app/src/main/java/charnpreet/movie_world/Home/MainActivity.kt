package charnpreet.movie_world.Home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.display_movie_adapter
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.RecyclerView as RecyclerView1

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var recyclerView: RecyclerView1? = null
    private var movies:  List<Movies>? = null
    private lateinit var linearLayoutManager: LinearLayoutManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navView: NavigationView = findViewById(R.id.nav_view)

        initRecylerView();


        val toggle = ActionBarDrawerToggle(

            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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
                Log.i("hello", "you have clicked home");

            }
            R.id.nav_gallery -> {
                loadMovie_section();
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

    private fun loadMovie_section(){
        API.search_In_Movies().topRated(Movie_db_config.API_KEY).enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {


                if (call != null) {
                    movies = response!!.body().results;
                    recyclerView!!.adapter = display_movie_adapter(movies, R.layout.display_movie_recylerview_holder, applicationContext);
//
                };

            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                Log.i("hello", "failed to load");
            }

        })
    }

    private fun initRecylerView(){
        recyclerView = findViewById(R.id.display_movies_recylerview1);

        if(recyclerView!=null){
            linearLayoutManager = LinearLayoutManager(this);
            recyclerView!!.layoutManager = linearLayoutManager;
        }
        else{
        Log.i("hello", "error ataching recylew ");
    }

}
}
