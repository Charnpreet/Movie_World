package charnpreet.movie_world.fragments.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.HomeScreen.Home_screen_adapter
import charnpreet.movie_world.adapter.NoResult.NoResult
import charnpreet.movie_world.utility.utility
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.ConstantProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home_screen: Fragment() {

    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    lateinit var  v : View
    lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private  var maps:  MutableMap<Int,List<Movies>?> = mutableMapOf<Int,List<Movies>?>()
    private lateinit var progressbar: ProgressBar
    private lateinit var progressBarTextView: TextView
    private var loaded :Boolean = true

    companion object{
        fun newInstance(): home_screen {
            return home_screen()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v= inflater.inflate(R.layout.home_screen, container,false)
        init()
        RetrivingDataFromSharedPreferences()
        return v
    }


private fun init(){
    progressbar = v.findViewById(R.id.pbHeaderProgress)
    progressBarTextView = v.findViewById(R.id.progressBarTextView)
    init_recylerView()
    if(internetConnectionAvailable()){

        Load_Movie_Categories()
    }else{
        // IF NO NETWORK IS AVAILABLE
        // IT WILL LOAD NO RESULT ADPATER
        loadNoResultAdapter()
    }

}
    private fun init_recylerView(){
        recyclerView = v.findViewById(R.id.recylerView_for_content);
        linearLayoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context,LinearLayoutManager.VERTICAL);
        recyclerView.layoutManager=linearLayoutManager

    }
    private fun Load_Movie_Categories(){
        load_TopRated_Movies()

    }

    // below method is used to laod top rated movies movie_db

    private fun load_TopRated_Movies(){

        val call: Call<MoviesResponse>? = API.search_In_Movies().topRated(Movie_db_config.API_KEY, utility.country,utility.languages)
        progressBarTextView.setText(ConstantProvider.NOW_LOADING_TOP_RATED_MOVIES)
        // passing next method to be called
        loadMovieCategories(call, ConstantProvider.TOP_RATED_MOVIES, ::load_Popular_movies)

    }

    private fun load_Popular_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().popularMovies(Movie_db_config.API_KEY,utility.country, utility.languages)
        progressBarTextView.setText(ConstantProvider.NOW_LOADING_TOP_POPULAR_MOVIES)
        loadMovieCategories(call, ConstantProvider.POPULAR_MOVIES, ::load_Now_Playing_movies)

    }
    private fun load_Now_Playing_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().nowPlaying(Movie_db_config.API_KEY, utility.country, utility.languages) //IN for india
        progressBarTextView.setText(ConstantProvider.LOADING_NOW_PLAYING_MOVIES)
        loadMovieCategories(call, ConstantProvider.NOW_PLAYING_MOVIES, ::load_Upcoming_movies)
    }
    private fun load_Upcoming_movies(){
        val call: Call<MoviesResponse>? = API.search_In_Movies().upComing(Movie_db_config.API_KEY, utility.country, utility.languages)
        progressBarTextView.setText(ConstantProvider.NOW_LOADING_UPCOMING_MOVIES)
        loadMovieCategories(call, ConstantProvider.UPCOMING_MOVIES, ::loadAdapters)
    }


    //
    //
    private fun loadMovieCategories(call: Call<MoviesResponse>?, index:Int, nextMethodTobeCalled:()->Unit){

        call!!.enqueue(object: Callback<MoviesResponse> {

            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                if(response!!.isSuccessful){

                    passingdatatorecyerview(call,response,index)
                    nextMethodTobeCalled()

                }

            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {

                recyclerView.adapter = NoResult(ConstantProvider.UNABLE_TO_LOAD_MOVIES)
                Log.i("hello", t!!.message)

                progressbar.setVisibility(View.INVISIBLE)
                progressBarTextView.visibility = View.INVISIBLE
            }

        })
    }

  //   this mehthod is used to pass data to recyerview holder
  //   it deserilsed the retrofit response into an object

    private fun passingdatatorecyerview(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?,index:Int)
    {
        if (call != null) {
            val movies: List<Movies>? = response!!.body().results;
            maps.put(index,movies)



        }
    }

    fun loadAdapters(){

        //
        // need supscriber which must observe loaded value if its get changes must trigger load apdapter methods
        if(loaded){
            recyclerView.adapter = Home_screen_adapter(maps)
            //
            //
            // setting up a divider for recylerview
            recyclerView.addItemDecoration(utility.GetRecylerViewDivider(linearLayoutManager,recyclerView.context))

        }else{
             loadNoResultAdapter()
        }

        progressbar.setVisibility(View.INVISIBLE)
        progressBarTextView.visibility = View.INVISIBLE
    }
    //
    // checking if connection is available or not
    //
    fun internetConnectionAvailable():Boolean{
          val cm :ConnectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
          val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return  activeNetwork != null && activeNetwork.isConnected

    }

    fun loadNoResultAdapter() {

        recyclerView.adapter =  NoResult(ConstantProvider.UNABLE_TO_LOAD_MOVIES)
    }

    fun RetrivingDataFromSharedPreferences(){

        val sharedPreference =  v.context.getSharedPreferences(ConstantProvider.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val country = sharedPreference.getString(ConstantProvider.COUNTRY_TEXT,"")
        val language = sharedPreference.getString(ConstantProvider.LANGUAGE_TEXT,"")
        if(country!=null){
            utility.country = country
        }
       if(language!=null){
           utility.languages = language
       }

    }


}


