package charnpreet.movie_world.utility

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.content_details.Content_details
import com.google.android.youtube.player.internal.m
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* this class will all the utitility method
* will have singlton design pattern
* therefore will share only 1 instance of an object accross all activities
* */
class utility{
    companion object{
        val utility_instance =utility();
    }

    // this will trim passed in string
    // for slaces and spaces
    fun trim_data(trim_me: String):String{

    return trim_me;
    }

    // this will update passed in string
    // will call trim_data() to trim string
    // will also need to validate string against all the possibilities
    // like it contain only string
    fun validate_data(input_string: String):String{

        return input_string;
    }

    //
    // will return passed scroll view for layoutmanager
    // it has been used with recyler view
    //
     fun scroll_view_for_recylerView_layoutmanager(context: Context, layoutStyle:Int): LinearLayoutManager {

        return LinearLayoutManager(context, layoutStyle,false);
    }

    fun loadFragment(fragmentManager: FragmentManager,fragmenttobeLoaded: Fragment, addtobackstack:Boolean){

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmenttobeLoaded);
        if(addtobackstack){
            // the add

        }else{
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
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