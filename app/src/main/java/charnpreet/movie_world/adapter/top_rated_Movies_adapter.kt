package charnpreet.movie_world.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Movies
import com.squareup.picasso.Picasso

class Home_Screen_Movies_adapter(movies: List<Movies>?):RecyclerView.Adapter<Home_Screen_Movies_adapter.Home_Screen_Movies_viewHolder>() {
    private var movies: List<Movies>? = movies
    lateinit var view:View


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Home_Screen_Movies_viewHolder {
        view  = LayoutInflater.from(p0.context).inflate(R.layout.top_rated_movies, p0,false)
        return Home_Screen_Movies_viewHolder(view);
    }

    override fun getItemCount(): Int {
    return movies!!.size
    }

    override fun onBindViewHolder(p0: Home_Screen_Movies_viewHolder, p1: Int) {
        p0.textView.setText(movies!![p1].title);
        var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movies!!.get(p1).posterPath;
        Picasso.with(view.context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(p0.imageView);
    }


    class Home_Screen_Movies_viewHolder(itemview:View): RecyclerView.ViewHolder(itemview){
        val imageView: ImageView = itemview.findViewById(R.id.top_rated_movies_image_view);
        val textView: TextView = itemview.findViewById(R.id.top_rated_movies_textView);

    }


}