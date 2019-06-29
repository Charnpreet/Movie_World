package charnpreet.movie_world.utility

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

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




}