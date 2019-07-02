package charnpreet.movie_world.fragments.home

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.Home_screen_adapter
import charnpreet.movie_world.model.Countries
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class home_screen: Fragment() {

    val TOP_RATED_MOVIES   = 0
    val POPULAR_MOVIES     = 1
    val UPCOMING_MOVIES    = 2
    val NOW_PLAYING_MOVIES = 3
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance;
    lateinit var  v : View
    lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager;
    private  var maps:  MutableMap<Int,List<Movies>?> = mutableMapOf<Int,List<Movies>?>()
    private lateinit var progressbar: ProgressBar;
    private var countries:Array<Countries> =arrayOf();


    companion object{
        fun newInstance(): home_screen {
            return home_screen();
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v= inflater.inflate(R.layout.home_screen, container,false);

        init();
        return v;
    }
private fun init(){
    progressbar = v.findViewById(R.id.pbHeaderProgress);
    init_recylerView();
    Load_Movie_Categories()

    load_countries()

    // temp freezing main thread to load all data from server
    // not a good option, need to replace with Rxjava Observable and Zip methods
    HoldingMainThreadUnitlDataIsLoaded()

}
    private fun init_recylerView(){
        recyclerView = v.findViewById(R.id.home_screen);
        linearLayoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context,LinearLayoutManager.VERTICAL);
        recyclerView.layoutManager=linearLayoutManager

    }
    private fun Load_Movie_Categories(){
        load_TopRated_Movies()
        load_Popular_movies()
       load_Now_Playing_movies()
        load_Upcoming_movies()


        //Log.i("hello", maps.size.toString())
    }

    // below method is used to laod top rated movies movie_db

    private fun load_TopRated_Movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().topRated(Movie_db_config.API_KEY, "IN")
        loadMovieCategories(call, TOP_RATED_MOVIES)

    }

    private fun load_Popular_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().popularMovies(Movie_db_config.API_KEY,"IN")
        loadMovieCategories(call, POPULAR_MOVIES)

    }
    private fun load_Now_Playing_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().nowPlaying(Movie_db_config.API_KEY, "IN")
        loadMovieCategories(call, NOW_PLAYING_MOVIES)
    }
    private fun load_Upcoming_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().upComing(Movie_db_config.API_KEY, "IN")
        loadMovieCategories(call, UPCOMING_MOVIES)
    }
    private fun load_countries(){
        val call: Call<Array<Countries>> = API.search_In_Movies().countries(Movie_db_config.API_KEY)
       call.enqueue(object :Callback<Array<Countries>>{
           override fun onResponse(call: Call<Array<Countries>>?, response: Response<Array<Countries>>?) {

               if (call != null) {
                   countries= response!!.body()
               }
           }

           override fun onFailure(call: Call<Array<Countries>>?, t: Throwable?) {

           }
       })
    }



    //
    //
    private fun loadMovieCategories(call: Call<MoviesResponse>?, index:Int){
        call!!.enqueue(object: Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                if(response!!.isSuccessful){
                    Log.i("hello", call!!.request().toString());
                    passingdatatorecyerview(call,response,index);
                }

            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                Log.i("hello", call!!.request().toString());
                Log.i("hello", "failed to load data");
                progressbar.setVisibility(View.INVISIBLE)
            }

        })
    }

  //   this mehthod is used to pass data to recyerview holder
  //   it deserilsed the retrofit response into an object

    private fun passingdatatorecyerview(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?,index:Int)
    {
        if (call != null) {
            var  movies: List<Movies>? = response!!.body().results;
            maps.put(index,movies);
//            recyclerView!!.adapter = Home_screen_adapter(maps);

        }
    }


    // temp freezing main thread to load all data from server
    // not a good option, need to replace with Rxjava Observable and Zip methods
    fun HoldingMainThreadUnitlDataIsLoaded() {
        val handler = Handler()
        val runnable = Runnable {
            recyclerView!!.adapter = Home_screen_adapter(maps,countries);
            progressbar.setVisibility(View.INVISIBLE)
        }
        handler.postDelayed(runnable, 5000)
    }
}