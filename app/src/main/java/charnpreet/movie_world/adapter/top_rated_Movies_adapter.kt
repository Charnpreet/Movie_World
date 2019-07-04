package charnpreet.movie_world.adapter

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
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
import charnpreet.movie_world.utility.utility
import com.squareup.picasso.Picasso

class Home_Screen_Movies_adapter(movies: List<Movies>?, context:Context):RecyclerView.Adapter<Home_Screen_Movies_adapter.Home_Screen_Movies_viewHolder>() {
    private var movies: List<Movies>? = movies
    lateinit var view:View
    val context = context

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

        //
        // implementing onclick listener
        //
        p0.imageView.setOnClickListener(View.OnClickListener {
            Log.i("hello","you clicked an image of a Movie " +movies!![p1].title);
            load_content_details_Fragment(movies!![p1])

        })

    }

    private fun load_content_details_Fragment(movie : Movies){

        var content_details: Content_details = Content_details.newInstance(movie)
        val activity : AppCompatActivity = context as AppCompatActivity
        val utility: utility = utility.utility_instance
        val fragmentManager: FragmentManager =activity.supportFragmentManager
        utility.loadFragment(fragmentManager,content_details,false)

    }

    class Home_Screen_Movies_viewHolder(itemview:View): RecyclerView.ViewHolder(itemview){
        val imageView: ImageView = itemview.findViewById(R.id.top_rated_movies_image_view);
        val textView: TextView = itemview.findViewById(R.id.top_rated_movies_textView);

    }


}