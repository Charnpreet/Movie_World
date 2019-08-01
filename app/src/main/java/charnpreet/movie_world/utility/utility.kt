package charnpreet.movie_world.utility

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* this class will all the utitility method
* will have singlton design pattern
* therefore will share only 1 instance of an object accross all activities
* */
class utility{

    //
    // constant been used inside different actvities and fragments
    val TOP_RATED_MOVIES   = 0
    val POPULAR_MOVIES     = 1
    val UPCOMING_MOVIES    = 2
    val NOW_PLAYING_MOVIES = 3
    val SESSION_ID_TAG = "sessionId"
    val PROFILE_ID_TAG = "profileId"
    val MOVIE_TAG = "movie"
    val MOVIE_TYPE_MEDIA = "movie"
    val SIGN_IN_BUTTON_TEXT ="Sign In"
    val CANCEL_BUTTON_TEXT = "Cancel"
    val LOG_OUT_TEXT = "Your are Already Logged Out "
    val VIEW_PAGER_TAB_SUMMARY ="SUMMARY"
    val VIEW_PAGER_TAB_REVIEWS ="REVIEWS"
    val VIEW_PAGER_TAB_VIDEO ="VIDEO"
    val LOADING_COUNTRIES_TEXT = "loading Countries"
    val LOADING_LANGUAGES_TEXT = "loading languages"
    val COUNTRY_TEXT = "country"
    val LANGUAGE_TEXT  ="language"
    val SHARED_PREFERENCE_NAME="PREFERENCE_NAME"
    val NOW_LOADING_TOP_RATED_MOVIES="Now loading Top Rated Movies"
    val NOW_LOADING_TOP_POPULAR_MOVIES="Now loading Top Popular Movies"
    val LOADING_NOW_PLAYING_MOVIES="loading Now Playing Movies"
    val NOW_LOADING_UPCOMING_MOVIES="Now loading Upcoming Movies"
    val LOADING_ACCOUNT_DETAILS="Loading Account Details"
    val NOT_LOGGED_IN_TEXT = "You are Not Logged In"
    //
    //
    companion object{
        val utility_instance =utility()

    }

    var languages: String = ""
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    var country: String = ""
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
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

    //
    // this  will return dilaog builder with passed in info
    // title will be used as title and message as a message for alertdialog
    // boolean here is if user can cancel alert dialog or not

    fun getCustomAlertDialogBuilder(title:String, messgae:String, cancelable:Boolean, context: Context):AlertDialog.Builder{
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(messgae).setCancelable(cancelable)
        return alertDialogBuilder

    }

    //
    // this method is been used to clear either all phreferences or specific
    // based on user slection
    fun clearSharedPreferences(context: Context, removeAll:Boolean, tag:String){
        val sharedPreference =  context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        if(removeAll){
            sharedPreference.edit().clear().apply()

        }else{

            sharedPreference.edit().remove(tag).apply()
        }

    }

    // fetech data from shared Preferences
    fun RetrivingDataFromSharedPreferences(tag:String, context: Context):String?{
        val sharedPreference =  context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val _token = sharedPreference.getString(tag,null)
        return _token
    }
// temp freezing main thread to load all data from server
// not a good option, need to replace with Rxjava Observable and Zip methods
fun HoldingMainThreadUnitlDataIsLoaded(methodToBeHold:()->Unit) {

    val handler = Handler()
    val runnable = Runnable {

            methodToBeHold()
    }
    handler.postDelayed(runnable, 20)
}
}