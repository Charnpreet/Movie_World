package charnpreet.movie_world.adapter.viewPager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.fragments.Review.ContentReview
import charnpreet.movie_world.fragments.Summary.ContentSummary
import charnpreet.movie_world.fragments.Video.Content_Video
import charnpreet.movie_world.model.Content_Review
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class View_Pager(fm: FragmentManager?, items:Int, movie: Movies) : FragmentPagerAdapter(fm) {

    private val movie : Movies = movie
    private var viewPagerFragments: MutableList<Fragment> = mutableListOf()
    private val tabItems: Int = items
    private val tabTitles = arrayOf("SUMMARY", "REVIEWS", "VIDEO")


// need to implement back ground thead or async task

    override fun getItem(p0: Int): Fragment {

//        if(viewPagerFragments.size>0){
//            return viewPagerFragments.get(p0)
//        }
        if(p0==0){

            return ContentSummary.newInstance(movie)
        }
        if(p0==1){

            return ContentReview.newInstance(movie)
        }

        else{

            if(p0==2){

             return Content_Video.newInstance(movie)
            }

        }

            return Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return tabTitles[position]
    }

    override fun getCount(): Int {

        return tabItems
    }

    //
    // adds fragment to the list
   public fun AddFragment(fragment:Fragment){

        viewPagerFragments.add(fragment)

    }




}