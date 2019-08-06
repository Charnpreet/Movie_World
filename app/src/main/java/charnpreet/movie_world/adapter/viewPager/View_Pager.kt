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
import charnpreet.movie_world.utility.ConstantProvider
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class View_Pager(fm: FragmentManager?, items:Int, private val movie: Movies) : FragmentPagerAdapter(fm) {

    private val tabItems: Int = items
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    private val tabTitles = arrayOf(ConstantProvider.VIEW_PAGER_TAB_SUMMARY, ConstantProvider.VIEW_PAGER_TAB_REVIEWS, ConstantProvider.VIEW_PAGER_TAB_VIDEO)


// need to implement back ground thead or async task

    override fun getItem(p0: Int): Fragment {

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

}