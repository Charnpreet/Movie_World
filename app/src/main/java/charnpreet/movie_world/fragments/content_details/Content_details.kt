package charnpreet.movie_world.fragments.content_details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.home.home_screen
import charnpreet.movie_world.model.Movies
import com.squareup.picasso.Picasso
import java.io.Serializable

class Content_details(): Fragment() {
    lateinit var poster: ImageView
    lateinit var cancel_button: ImageView
    lateinit var overView:TextView
    lateinit var releaseDate: TextView
    lateinit var popularity:TextView
    lateinit var v:View
    private lateinit var movies: Movies


    companion object{
        fun newInstance(movie: Movies): Content_details {
            val contentDetails =Content_details()
            var bundle: Bundle = Bundle().apply {

                putSerializable("movie", movie as Serializable)
            }
            contentDetails.setArguments(bundle)
            return contentDetails
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.content_details,container,false)
        init()
        return v;
    }

    private fun init(){
        poster = v.findViewById(R.id.content_detail_poster_view);
        cancel_button=v.findViewById(R.id.content_detail_cancel_button);
        overView=v.findViewById(R.id.content_detail_movie_overview)
        releaseDate=v.findViewById(R.id.relase_date);
        popularity=v.findViewById(R.id.content_detail_popularity)
        ExtractBundle()
        push_content()
    }

    private fun ExtractBundle(){
        val args =arguments
        val movie: Movies = args!!.get("movie") as Movies
        movies = movie
    }
    private fun push_content(){
        var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movies.posterPath
        Picasso.with(context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(poster)

        overView.setText("OverView:- " + movies.overview )

        releaseDate.setText("Release Date:- " + movies.releaseDate)

        popularity.setText("Popularity:- " + movies.popularity)

    }
}