package charnpreet.movie_world.adapter.HomeScreen

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.utility.utility
import android.widget.ArrayAdapter
import android.widget.Spinner
import charnpreet.movie_world.adapter.topRated.Home_Screen_Movies_adapter
import charnpreet.movie_world.model.Countries

//
// this class will act as holder to holder list adaptera
// besically it will present verticall list and each item of list can scroll horizontally
//
class Home_screen_adapter(movies:  MutableMap<Int,List<Movies>?>): RecyclerView.Adapter<Home_screen_adapter.Home_screen_view_holder>() {

    val TOP_RATED_MOVIES       = "Top Rated Movies"
    val POPULAR_MOVIES         =  "Popular Movies"
    val UPCOMING_MOVIES        =  "Upcomming Movies"
    val NOW_PLAYING_MOVIES     =  "Now Playing Movies"

    val NO_OF_CATOGORIES_TO_DISPLAY_ON_HOME_SCREEN = 4



    private var movies: MutableMap<Int,List<Movies>?> = movies
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance;
    lateinit var view:View

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Home_screen_view_holder {

        view  = LayoutInflater.from(p0.context).inflate(R.layout.home_screen_fragment, p0,false);

        return Home_screen_view_holder(view);
    }

    override fun getItemCount(): Int {

            return NO_OF_CATOGORIES_TO_DISPLAY_ON_HOME_SCREEN
    }

    override fun onBindViewHolder(p0: Home_screen_view_holder, p1: Int) {

        p0.recyclerView.apply {

            layoutManager = utility.scroll_view_for_recylerView_layoutmanager(view.context,LinearLayoutManager.HORIZONTAL);

            // setRecycledViewPool(viewPool)
        }
        if(p1==0){

            p0.textView.setText(TOP_RATED_MOVIES)
            p0.recyclerView.adapter =
                Home_Screen_Movies_adapter(movies[utility.TOP_RATED_MOVIES], view.context)

        }
        if(p1==1){
            p0.textView.setText(POPULAR_MOVIES)
           p0.recyclerView.adapter =
               Home_Screen_Movies_adapter(movies[utility.POPULAR_MOVIES], view.context)
        }
        if(p1==2){
            p0.textView.setText(UPCOMING_MOVIES)
          p0.recyclerView.adapter =
              Home_Screen_Movies_adapter(movies[utility.UPCOMING_MOVIES], view.context)
        }
        if(p1==3){
            p0.textView.setText(NOW_PLAYING_MOVIES)
            p0.recyclerView.adapter =
                Home_Screen_Movies_adapter(movies[utility.NOW_PLAYING_MOVIES], view.context)
        }

    }

    class Home_screen_view_holder(item: View):RecyclerView.ViewHolder(item){
        val v :View = item
        val recyclerView: RecyclerView = item.findViewById(R.id.display_movies_recylerview1)
        val textView:TextView = item.findViewById(R.id.text_view_top_movies)




    }

}