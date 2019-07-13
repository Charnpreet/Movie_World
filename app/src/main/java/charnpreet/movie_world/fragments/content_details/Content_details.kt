package charnpreet.movie_world.fragments.content_details

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.*
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.adapter.viewPager.View_Pager
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class Content_details(): Fragment() {
    lateinit var poster: ImageView
    lateinit var cancel_button: ImageView
    lateinit var overView:TextView
    lateinit var releaseDate: TextView
    lateinit var popularity:TextView
    lateinit var v:View
    private lateinit var movie: Movies
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout : TabLayout


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
        return v
    }

    private fun init(){
        poster = v.findViewById(R.id.content_detail_poster_view);
        viewPager = v.findViewById(R.id.view_pager);
        tabLayout = v.findViewById(R.id.tabLayout)
      //  cancel_button=v.findViewById(R.id.content_detail_cancel_button);
      //  overView=v.findViewById(R.id.content_detail_movie_overview)
        releaseDate=v.findViewById(R.id.content_detail_relase_date);
        popularity=v.findViewById(R.id.content_detail_popularity)
        ExtractBundle()
        push_content()
        SetupViewPager()
    }


    private fun SetupViewPager(){
        val adapter:PagerAdapter =
            View_Pager(childFragmentManager, tabLayout.tabCount, movie)
        viewPager.adapter = adapter
        SetupListner()
    }

    private fun SetupListner(){

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{

            override fun onTabReselected(p0: TabLayout.Tab?) {
                viewPager.setCurrentItem(p0!!.position)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })
    }

    private fun ExtractBundle(){
        val args =arguments
        val Bmovie: Movies = args!!.get("movie") as Movies
        movie = Bmovie
    }


    private fun push_content(){
        var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movie.backdropPath
        Picasso.with(context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(poster)

        releaseDate.setText("Release Date:- " + movie.releaseDate)

        popularity.setText("Popularity:- " + movie.popularity)
    }

}