package charnpreet.movie_world.fragments.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.display_movie_adapter
import charnpreet.movie_world.fragments.search.search_in_movies
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home_screen: Fragment() {

    lateinit var  v : View
    lateinit var recyclerView_for_toprated_movies: RecyclerView
    lateinit var recyclerView_for_fav_collection: RecyclerView
    private lateinit var linearLayoutManager_for_fav_collection: LinearLayoutManager;
    private lateinit var linearLayoutManager_for_toprated_movies: LinearLayoutManager;

    companion object{
        fun newInstance(): home_screen {
            return home_screen();
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v= inflater.inflate(R.layout.home_screen_fragment, container,false);
        init();
        return v;
    }
private fun init(){
    init_recylerView();
    load_Top_Movies();

}
    private fun init_recylerView(){
        recyclerView_for_fav_collection = v.findViewById(R.id.fav_collection_recyler_view);
        recyclerView_for_toprated_movies = v.findViewById(R.id.display_movies_recylerview1);
        linearLayoutManager_for_toprated_movies = Horizontal_scroll_view_for_recylerView_layoutmanager()
        linearLayoutManager_for_fav_collection = Horizontal_scroll_view_for_recylerView_layoutmanager()
        recyclerView_for_fav_collection .layoutManager = linearLayoutManager_for_fav_collection
        recyclerView_for_toprated_movies.layoutManager=linearLayoutManager_for_toprated_movies

    }
    //
    // will return horizontal scroll view for layoutmanager
    private fun Horizontal_scroll_view_for_recylerView_layoutmanager(): LinearLayoutManager {
        return LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL,false);
    }
    //
    // below method is used to laod top rated movies movie_db
    private fun load_Top_Movies(){
        API.search_In_Movies().topRated(Movie_db_config.API_KEY).enqueue(object: Callback<MoviesResponse> {
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
    private fun passingdatatorecyerview(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?)
    {
        if (call != null) {
            var  movies: List<Movies>? = response!!.body().results;
            recyclerView_for_toprated_movies!!.adapter = display_movie_adapter(movies, R.layout.display_movie_recylerview_holder, v.context);
//
        }
    }
}