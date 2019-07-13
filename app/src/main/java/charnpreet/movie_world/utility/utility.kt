package charnpreet.movie_world.utility

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import charnpreet.movie_world.R
import charnpreet.movie_world.fragments.content_details.Content_details
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


//    //
//    // load reviews for selected movie
//    // will be used with view pager
//    private fun load_Reviews(id:Int,call: Call<T>): T?
//    {
//
//        var  review: T? = null
//
//        call.enqueue(object : Callback<T> {
//
//            override fun onFailure(call: Call<T>?, t: Throwable?) {
//
//                Log.e("hello", call!!.request().toString())
//
//                Log.e("hello", t!!.localizedMessage)
//            }
//
//            override fun onResponse(call: Call<T>?, response: Response<T>?) {
//
//                if(call!=null){
//
//                    review  = response!!.body()
//                }
//
//            }
//
//        })
//        //
//        //
//        return review
//    }
}