package charnpreet.movie_world.fragments.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.display_movie_adapter
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class search_in_movies : Fragment(){
    lateinit var  v : View
    lateinit var recyclerView_for_search_movies: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager;
    private lateinit var search_view :android.widget.SearchView
    companion object{
        fun newInstance(): search_in_movies {
            return search_in_movies();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v  = inflater.inflate(R.layout.display_movie_fragment,container,false);
        init();
        return v;
//

    }

    private fun init(){

        search_view = v.findViewById(R.id.searchview_for_search_Movie);
        init_recylerview();
        setingup_listeners();



    }

    private fun init_recylerview(){
        recyclerView_for_search_movies = v.findViewById(R.id.display_movies_recylerview);
        if(recyclerView_for_search_movies!=null){
            linearLayoutManager = LinearLayoutManager(v.context);
            recyclerView_for_search_movies !!.layoutManager = linearLayoutManager
        }
        else {
            Log.i("hello", "error ataching recyler View ");
        }
    }

    private fun setingup_listeners(){
        search_view.setOnQueryTextListener(object :android.widget.SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query:String):Boolean{
               fetech_searchView_data(query);
               Log.i("hello", query);
               return false;
           }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false;
            }
        })

    }

    private fun fetech_searchView_data(user_query:String){

        if(user_query!=null){
            userquery_movie_result(user_query);
        }

    }
    //userquery:String
    private fun userquery_movie_result(user_query: String){

        API.search_In_Movies().search(Movie_db_config.API_KEY, user_query).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                if(call!=null){
                    //
                    if (call != null) {
                        var  movies: List<Movies>? = response!!.body().results;
                        Log.i("helllo", movies!!.size.toString())
                        recyclerView_for_search_movies!!.adapter = display_movie_adapter(movies, R.layout.display_movie_recylerview_holder, v.context);
//
                    }

                }
            }
            //
            // to do can make it more user friendly
            // can print error on user screen or to write to log file
            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                Log.i("hello", "failed to load data");
            }


        })

    }


    override fun onResume() {
        super.onResume()

        //(activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
      //  (activity as AppCompatActivity).supportActionBar!!.show()
    }
}


