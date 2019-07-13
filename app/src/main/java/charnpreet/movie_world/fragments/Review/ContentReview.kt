package charnpreet.movie_world.fragments.Review

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
import charnpreet.movie_world.adapter.Reviews.contentReviewsAdapter
import charnpreet.movie_world.model.Content_Review
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.reviewDetails
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class ContentReview : Fragment() {
    lateinit var v: View
    private lateinit var movie:Movies
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.recyler_view,container,false)
        Init()
        return v
    }


    companion object{

        fun newInstance(movie: Movies): ContentReview {

            val contentReview  = ContentReview()

            var bundle: Bundle = Bundle().apply {

                putSerializable("movie", movie as Serializable)
            }
            contentReview.setArguments(bundle)

            return contentReview
        }
    }

    private fun ExtractBundle(){

        val args =arguments

        val Bmovie: Movies = args!!.get("movie") as Movies

        movie = Bmovie
    }

    private fun Init(){

        recyclerView = v.findViewById(R.id.recylerView_for_content)

        recyclerView.layoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context, LinearLayoutManager.VERTICAL)

        ExtractBundle()

        loadReviews()

    }


    //
    // load reviews for selected movie
    // will be used with view pager
    private fun loadReviews(){

        val  call: Call<Content_Review> = API.search_In_Movies().contentReviews(movie.id, Movie_db_config.API_KEY)

        call.enqueue(object : Callback<Content_Review> {

            override fun onFailure(call: Call<Content_Review>?, t: Throwable?) {

                Log.e("hello", call!!.request().toString())

                Log.e("hello", t!!.localizedMessage)
            }

            override fun onResponse(call: Call<Content_Review>?, response: Response<Content_Review>?) {

                if(call!=null){

                    val reviews:List<reviewDetails>? = response!!.body().results

                    recyclerView.adapter = contentReviewsAdapter(reviews)
                }

            }

        })
    }


}