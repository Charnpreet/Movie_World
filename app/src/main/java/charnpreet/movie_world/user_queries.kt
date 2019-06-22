package charnpreet.movie_world
/*
* This class wiil be responible for handling users queries
* it will retrieve user input query, will validate it, to do it will use utlility class
* it will also make use of movie_db object to access its methods
* */
class user_queries {

    var input_query : String = "" // input query string /like movie name, tv series name
    var year :Double =0.0; // will be used as a year filter
    var is_year_set :Boolean = false // will be used to check if year filter is set or not

    //
    // if  found_in__movies() section return true
    // it will retrun an object of movies details like img, cast, rating
    fun search_in_movies(){

    }
    //
    // if  found_in_tv_series() section return true
    // it will retrun an object of  tv_series details like img, cast, rating
    fun serach_in_TV_series(){

    }

    // this fun will check input string inside movie_section of movie_db
    // will return true if found
    fun found_in_movies() : Boolean{
        return false;
    }
    // this fun will check input string inside tv_series_section of movie_db
    // will return true if found
    fun found_in_tv_series():Boolean{
        return false;
    }
    //
    // will return true if year filter is set or not
    fun is_year_filter_set():Boolean{
        if(year>0.0){

            is_year_set = true;
        }

        return is_year_set;
    }
}