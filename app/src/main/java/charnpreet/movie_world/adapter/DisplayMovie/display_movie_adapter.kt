package charnpreet.movie_world.adapter.DisplayMovie

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.content_details.Content_details
import charnpreet.movie_world.model.Movies
import com.squareup.picasso.Picasso
import android.support.v7.app.AppCompatActivity
import charnpreet.movie_world.utility.utility


class display_movie_adapter(movies: List<Movies>?, lrowLayout: Int, applicationContext: Context) : RecyclerView.Adapter<display_movie_adapter.MovieDetailHolder>() {

    private var movies: List<Movies>? = movies
    private var rowLayout: Int = lrowLayout
    private var context: Context? = applicationContext


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieDetailHolder {
       var view : View  = LayoutInflater.from(p0.context).inflate(rowLayout, p0,false)
       return MovieDetailHolder(view)

    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    override fun onBindViewHolder(p0: MovieDetailHolder, p1: Int) {
       var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movies!!.get(p1).posterPath;
        Picasso.with(context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(p0.movieImage)

        p0.movieTitle.setText( movies!!.get(p1).title)
        //
        // implementing onclick listener
        //
        p0.movieImage.setOnClickListener(View.OnClickListener {
            Log.i("hello","you clicked an image from Display Movie")

            load_content_details_Fragment(movies!![p1])
        })

    }

    private fun load_content_details_Fragment(movie : Movies){

        var content_details: Content_details = Content_details.newInstance(movie)

        val activity :AppCompatActivity = context as AppCompatActivity

        val fragmentManager: FragmentManager =activity.supportFragmentManager

        val utility: utility = utility.utility_instance

        utility.loadFragment(fragmentManager,content_details,false)

    }


    class  MovieDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout: ConstraintLayout = itemView.findViewById(R.id.list_item_movie)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title_movie_view_holder)
        val movieDescription: TextView? = null
        val movieImage: ImageView = itemView.findViewById(R.id.image_view_display_movie_view_holder)

    }

}
