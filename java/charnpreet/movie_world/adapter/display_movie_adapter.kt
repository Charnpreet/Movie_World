package charnpreet.movie_world.adapter

import android.content.Context
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


class display_movie_adapter(movies: List<Movies>?, lrowLayout: Int, applicationContext: Context) : RecyclerView.Adapter<display_movie_adapter.MovieDetailHolder>() {

    private var movies: List<Movies>? = movies
    private var rowLayout: Int = lrowLayout
    private var context: Context? = applicationContext


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieDetailHolder {
       var view : View  = LayoutInflater.from(p0.context).inflate(rowLayout, p0,false);
       return MovieDetailHolder(view);

    }

    override fun getItemCount(): Int {
        return movies!!.size;
    }

    override fun onBindViewHolder(p0: MovieDetailHolder, p1: Int) {
       var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movies!!.get(p1).posterPath;
        Picasso.with(context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(p0.movieImage);
        p0.movieTitle.setText("Title:- "+ movies!!.get(p1).title);
        p0.releasedate.setText("Release Date:- "+movies!!.get(p1).releaseDate);
        p0.movie_language.setText("Language:- "+movies!!.get(p1).originalLanguage);

    }


    class  MovieDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var layout :ConstraintLayout = itemView.findViewById(R.id.list_item_movie);
        var movieTitle: TextView = itemView.findViewById(R.id.movie_title_movie_view_holder);
        var movie_language: TextView = itemView.findViewById(R.id.language_movie_view_holder);
        var movieDescription: TextView? = null
        var releasedate: TextView = itemView.findViewById(R.id.realse_date_movie_view_holder);
        var movieImage: ImageView = itemView.findViewById(R.id.image_view_display_movie_view_holder);

        override fun onClick(p0: View?) {
            Log.i("hello", "CLICK!")
        }
    }

}
