package charnpreet.movie_world.utility

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import charnpreet.movie_world.R

/*
* this class will all the utitility method
* will have singlton design pattern
* therefore will share only 1 instance of an object accross all activities
* */
class utility{
    val TOP_RATED_MOVIES   = 0
    val POPULAR_MOVIES     = 1
    val UPCOMING_MOVIES    = 2
    val NOW_PLAYING_MOVIES = 3

    companion object{
        val utility_instance =utility()
    }


    //
    // will return passed scroll view for layoutmanager
    // it has been used with recyler view
    //
     fun scroll_view_for_recylerView_layoutmanager(context: Context, layoutStyle:Int): LinearLayoutManager {

        return LinearLayoutManager(context, layoutStyle,false)
    }

    fun loadFragment(fragmentManager: FragmentManager,fragmenttobeLoaded: Fragment, addtobackstack:Boolean){

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragmenttobeLoaded)
        if(addtobackstack){
            // the add

        }else{
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.commit()
    }

    //
    // this returns devider for recylerview
    fun GetRecylerViewDivider(m:RecyclerView.LayoutManager, context: Context):DividerItemDecoration{

        return DividerItemDecoration(context, (m as LinearLayoutManager).orientation)
    }

    // not used can be used to get progress bar
    fun getProgressBarReference(v:View):ProgressBar{

        return  v.findViewById(R.id.pbHeaderProgress)
    }
}