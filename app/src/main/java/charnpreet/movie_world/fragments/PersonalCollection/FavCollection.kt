package charnpreet.movie_world.fragments.PersonalCollection

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.NoResult.NoResult
import charnpreet.movie_world.adapter.topRated.Home_Screen_Movies_adapter
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavCollection :Fragment(), DialogInterface.OnClickListener{


    private var utility = charnpreet.movie_world.utility.utility.utility_instance
    lateinit var v: View
    lateinit var recyclerView: RecyclerView
    private lateinit var progressbar: ProgressBar
    private lateinit var progressBarTextView: TextView
    private val LOADING_FAV_MOVIE_COLLECTION ="Loading Fav Movie Collection"
    private val EMPTY_FAV_LIST_TEXT ="OPPS! List is Empty"
    private val NOT_LOGGED_IN_TEXT = "You are Not Logged In"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fav_collection, container, false)
        Init()
        return v
    }

    companion object{
        fun newInstance(): FavCollection {
            return FavCollection()
        }
    }

private fun Init(){
    progressbar = v.findViewById(R.id.pbHeaderProgress)
    progressBarTextView = v.findViewById(R.id.progressBarTextView)
    progressbar.setVisibility(View.VISIBLE)
    progressBarTextView.setText(LOADING_FAV_MOVIE_COLLECTION)
    init_recylerView()
    getfavMovieList()
}
    
    private fun init_recylerView(){
        recyclerView = v.findViewById(R.id.recylerView_for_content);
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(v.context, 2)
        //
        //
        // setting up a divider for recylerview
        recyclerView.addItemDecoration(utility.GetRecylerViewDivider(gridLayoutManager,recyclerView.context))
        recyclerView.layoutManager=gridLayoutManager

    }

    // this will be used to get list fav movies
    // then comapring each movie id with movie id of selected movie
    // if matches will set fav movie swtich to on else it will remain off
    private fun getfavMovieList(){

        val sessionID = utility.RetrivingDataFromSharedPreferences(utility.SESSION_ID_TAG, v.context)
        val profileID = utility.RetrivingDataFromSharedPreferences(utility.PROFILE_ID_TAG, v.context)
        if(sessionID!=null){

            API.search_In_Movies().getFavMovies(profileID, Movie_db_config.API_KEY, sessionID).enqueue(object :
                Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                    Log.i("hello", t!!.message)
                    progressbar.setVisibility(View.INVISIBLE)
                    progressBarTextView.visibility = View.INVISIBLE
                }

                override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                    val  movies: List<Movies>? = response!!.body().results
                    progressbar.setVisibility(View.INVISIBLE)
                    progressBarTextView.visibility = View.INVISIBLE
                    if(movies!!.size>0){
                        recyclerView.adapter = Home_Screen_Movies_adapter(movies, v.context)
                    }else{
                        recyclerView.adapter = NoResult(EMPTY_FAV_LIST_TEXT )
                    }


                }

            })
        }else{
            progressbar.setVisibility(View.INVISIBLE)
            progressBarTextView.visibility = View.INVISIBLE

            CreateAlertBox(NOT_LOGGED_IN_TEXT,utility.SIGN_IN_BUTTON_TEXT)
        }
    }
    fun CreateAlertBox(text:String,buttonText:String){
        val dilogBuilder = utility.getCustomAlertDialogBuilder("",text, false
            , v.context)
        dilogBuilder.setPositiveButton(buttonText ,this)
        dilogBuilder.setNegativeButton(utility.CANCEL_BUTTON_TEXT, this)
        dilogBuilder.create().show()
    }

    // loading profile frgament
    private fun loadingProfileFragment(){
        val fragmentManager: FragmentManager? = getFragmentManager()
        val profile = charnpreet.movie_world.fragments.Profile.Profile.newInstance()
        if(fragmentManager!=null){

            utility.loadFragment(fragmentManager,profile,false)
        }

    }

    override fun onClick(p0: DialogInterface?, p1: Int) {

        if(DialogInterface.BUTTON_POSITIVE==p1){

            loadingProfileFragment()

        }else{

            p0!!.dismiss()

            recyclerView.adapter = NoResult(NOT_LOGGED_IN_TEXT)
        }
    }

}