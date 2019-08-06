package charnpreet.movie_world.fragments.Review

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.NoResult.NoResult
import charnpreet.movie_world.adapter.Reviews.contentReviewsAdapter
import charnpreet.movie_world.model.Content_Review
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.reviewDetails
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.ConstantProvider
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class ContentReview : Fragment() {

    lateinit var v: View
    private lateinit var movie:Movies
    lateinit var linearLayoutManager :RecyclerView.LayoutManager
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    private lateinit var progressbar: ProgressBar
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

        val Bmovie: Movies = args!!.get(ConstantProvider.MOVIE_TAG) as Movies

        movie = Bmovie
    }

    private fun Init(){

        progressbar =  utility.getProgressBarReference(v)

        recyclerView = v.findViewById(R.id.recylerView_for_content)

        linearLayoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context, LinearLayoutManager.VERTICAL)

        recyclerView.layoutManager = linearLayoutManager

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

                progressbar.setVisibility(View.INVISIBLE)
            }

            override fun onResponse(call: Call<Content_Review>?, response: Response<Content_Review>?) {

                if(call!=null){

                    val reviews:List<reviewDetails>? = response!!.body().results


                    if(reviews!!.size>0){
                        recyclerView.adapter = contentReviewsAdapter(reviews)

                        recyclerView.addItemDecoration(utility.GetRecylerViewDivider(linearLayoutManager,recyclerView.context))
                    }

                    else{
                        recyclerView.adapter = NoResult(ConstantProvider.NO_REVIEW_FOUND)
                    }


                    progressbar.setVisibility(View.INVISIBLE)
                }

            }

        })
    }


}