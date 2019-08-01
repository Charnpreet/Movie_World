package charnpreet.movie_world.fragments.Video

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
import charnpreet.movie_world.adapter.Video.ContentVideoAdapter
import charnpreet.movie_world.model.ContentVideo
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.VideoDetails
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


class Content_Video : Fragment() {
    lateinit var v: View

    private lateinit var movie: Movies

    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance

    lateinit var recyclerView: RecyclerView

    private lateinit var progressbar: ProgressBar

    val NO_TRAILER_FOUND_TEXT ="No Trailer Found"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.recyler_view,container,false)

        Init()

        return v
    }


    companion object{

        fun newInstance(movie: Movies): Content_Video {

            val contentVideo  = Content_Video()

            var bundle: Bundle = Bundle().apply {

                putSerializable("movie", movie as Serializable)
            }
            contentVideo.setArguments(bundle)

            return contentVideo
        }
    }

    private fun ExtractBundle(){

        val args =arguments

        val Bmovie: Movies = args!!.get(utility.MOVIE_TAG) as Movies

        movie = Bmovie
    }

    private fun Init(){

        progressbar = utility.getProgressBarReference(v)

        recyclerView = v.findViewById(R.id.recylerView_for_content)

        recyclerView.layoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context, LinearLayoutManager.VERTICAL)

        ExtractBundle()

        loadVideo()

    }


    private fun loadVideo(){
        val  call: Call<ContentVideo> = API.search_In_Movies().contentVideo(movie.id, Movie_db_config.API_KEY)
        call.enqueue(object : Callback<ContentVideo> {

            override fun onResponse(call: Call<ContentVideo>?, response: Response<ContentVideo>?) {
                if(call!=null){
                    val video: List<VideoDetails>? = response!!.body().results

                    if((video!!.size>0)){

                        recyclerView.adapter = ContentVideoAdapter(video)

                    }else{

                        recyclerView.adapter = NoResult(NO_TRAILER_FOUND_TEXT)
                    }

                }
                progressbar.setVisibility(View.INVISIBLE)
            }
            //
            // to do can make it more user friendly
            // can print error on user screen or to write to log file
            override fun onFailure(call: Call<ContentVideo>?, t: Throwable?) {

                progressbar.setVisibility(View.INVISIBLE)

                Log.e("hello", call!!.request().toString())

                Log.e("hello", t!!.localizedMessage)

            }

        })

    }

}