package charnpreet.movie_world.utility
/*
* this class will all the utitility method
* will have singlton design pattern
* therefore will share only 1 instance of an object accross all activities
* */
class utility {

var utility_instance : utility? = null

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

    // this will must return utility instance
    //
    fun get_utility_instance(): utility
    {

        if(utility_instance==null)
        {

            utility_instance = utility();

        }
        return utility_instance as utility;
    }

}