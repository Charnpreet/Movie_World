package charnpreet.movie_world.Activity.Content_Detail

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.NoResult.NoResult
import charnpreet.movie_world.adapter.viewPager.View_Pager
import charnpreet.movie_world.model.FavAddedSuccessFully
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.utility
import charnpreet.movie_world.model.FavBody
import charnpreet.movie_world.model.MoviesResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContentDetail : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {
    lateinit var poster: ImageView
    lateinit var cancel_button: ImageView
    lateinit var releaseDate: TextView
    private lateinit var movie: Movies
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout : TabLayout
    private lateinit var favSwitch :Switch
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    private val OK = "ok"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_details)
        _init()
    }


    private fun _init(){
        supportActionBar!!.hide()
        // getting list of movies
        getfavMovieList()
        //
        poster = findViewById(R.id.content_detail_poster_view)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabLayout)
        cancel_button=findViewById(R.id.cancel_imageView)
        cancel_button.setOnClickListener(this)
        releaseDate=findViewById(R.id.content_detail_relase_date)
        favSwitch = findViewById(R.id.favSwitch)
        favSwitch.setOnClickListener(this)
        setSwitchListner()
        ExtractBundle()
        push_content()
        SetupViewPager()
        getfavMovieList()
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==R.id.cancel_imageView){
            finish()
        }


    }

    fun setSwitchListner(){
        favSwitch.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
               if(p1){
                   AddToFavMovieCollection(true)
               }else{
                   AddToFavMovieCollection(false)
               }
            }

        })

    }
    //
    //
    private fun AddToFavMovieCollection(add:Boolean){
        val favMovie = FavBody(utility.MOVIE_TYPE_MEDIA,movie.id as Int, add)
        val sessionID = utility.RetrivingDataFromSharedPreferences(utility.SESSION_ID_TAG, this)
        val profileID = utility.RetrivingDataFromSharedPreferences(utility.PROFILE_ID_TAG, this)
        if(sessionID!= null){
            API.search_In_Movies().addToFav(favMovie, profileID, Movie_db_config.API_KEY, sessionID).enqueue(object :Callback<FavAddedSuccessFully>{
                override fun onFailure(call: Call<FavAddedSuccessFully>?, t: Throwable?) {
                    Log.i("hello", t!!.message)
                }

                override fun onResponse(call: Call<FavAddedSuccessFully>?, response: Response<FavAddedSuccessFully>?) {
                    Log.i("hello", "successfully added")
                    Log.i("hello", response!!.body().toString())
                }

            })
        }else{
            CreateAlertBox(utility.NOT_LOGGED_IN_TEXT,OK)
        }

    }
        // this will be used to get list fav movies
        // then comapring each movie id with movie id of selected movie
        // if matches will set fav movie swtich to on else it will remain off
        private fun getfavMovieList(){

            val sessionID = utility.RetrivingDataFromSharedPreferences(utility.SESSION_ID_TAG, this)
            val profileID = utility.RetrivingDataFromSharedPreferences(utility.PROFILE_ID_TAG, this)
            if(sessionID!=null){
                API.search_In_Movies().getFavMovies(profileID, Movie_db_config.API_KEY, sessionID).enqueue(object :Callback<MoviesResponse>{
                    override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                        Log.i("hello", t!!.message)
                    }

                    override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                        val  movies: List<Movies>? = response!!.body().results
                        for (id in movies!!){
                            if(id.id == movie.id){

                                favSwitch.isChecked = true
                            }
                        }
                    }

                })
            }
        }


        private fun SetupViewPager(){
        val adapter: PagerAdapter =
            View_Pager(supportFragmentManager, tabLayout.tabCount, movie)
            viewPager.adapter = adapter
            SetupListner()
        }


    private fun SetupListner(){

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{

            override fun onTabReselected(p0: TabLayout.Tab?) {

                viewPager.setCurrentItem(p0!!.position)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })
    }


    private fun ExtractBundle(){

        val Bmovie: Movies = intent?.extras?.getBundle(utility.MOVIE_TAG)!!.getSerializable(utility.MOVIE_TAG)as Movies
        movie = Bmovie
    }


    private fun push_content(){

        val image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movie.backdropPath

        Picasso.with(applicationContext)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(poster)
        releaseDate.setText("Release Date:- " + movie.releaseDate)
    }

    override fun onBackPressed() {
        finish()
    }

    fun CreateAlertBox(text:String,buttonText:String){
        val dilogBuilder = utility.getCustomAlertDialogBuilder("",text, false
            ,this)
        dilogBuilder.setPositiveButton(buttonText ,this)
        dilogBuilder.create().show()
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {

        if(DialogInterface.BUTTON_POSITIVE==p1){

            favSwitch.isChecked = false

        }else{

            p0!!.dismiss()


        }
    }
}
