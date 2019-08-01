package charnpreet.movie_world.adapter.DisplayMovie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Movies
import com.squareup.picasso.Picasso
import charnpreet.movie_world.Activity.Content_Detail.ContentDetail
import charnpreet.movie_world.utility.utility
import java.io.Serializable


class display_movie_adapter(private var movies: List<Movies>?, lrowLayout: Int, applicationContext: Context) : RecyclerView.Adapter<display_movie_adapter.MovieDetailHolder>() {

    private var rowLayout: Int = lrowLayout
    private var context: Context? = applicationContext
    private lateinit var view : View
    private val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieDetailHolder {
        view  = LayoutInflater.from(p0.context).inflate(rowLayout, p0,false)
       return MovieDetailHolder(view)

    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    override fun onBindViewHolder(p0: MovieDetailHolder, p1: Int) {

       val imageUrl : String = Movie_db_config.IMAGE_URL_BASE_PATH + movies!![p1].posterPath

        Picasso.with(context)

            .load(imageUrl)

            .placeholder(android.R.drawable.sym_def_app_icon)

            .error(android.R.drawable.sym_def_app_icon)

            .into(p0.movieImage)

        p0.movieTitle.text = movies!![p1].title
        //
        // implementing onclick listener
        //
        p0.movieImage.setOnClickListener{

            Log.i("hello","you clicked an image from Display Movie")

            loadContentDetailActivity(movies!![p1])
        }

    }


    private fun loadContentDetailActivity(movie : Movies){
        val intent = Intent(view.context, ContentDetail::class.java)
        val bundle = Bundle()
        bundle.putSerializable(utility.MOVIE_TAG, movie as Serializable)
        intent.putExtra(utility.MOVIE_TAG, bundle)
        view.context.startActivity(intent)
    }

    class  MovieDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout: ConstraintLayout = itemView.findViewById(R.id.list_item_movie)

        val movieTitle: TextView = itemView.findViewById(R.id.movie_title_movie_view_holder)

        val movieImage: ImageView = itemView.findViewById(R.id.image_view_display_movie_view_holder)

    }

}
