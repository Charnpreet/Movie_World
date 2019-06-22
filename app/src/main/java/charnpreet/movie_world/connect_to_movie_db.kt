package charnpreet.movie_world

/*
* this class will be responsible for setting up connection
* with movie world db
* will have single instance of an its class object
* */

class connect_to_movie_db {

    var connect_to_movie_db :connect_to_movie_db? = null


    // this method will be used to make a connection with movie_db
    //
    fun setup_Connection(){

    }
    //
    // this will return an instance of class connect_to_movie_db
    //
    fun get_connect_to_movie_db_instance():connect_to_movie_db{

        if (connect_to_movie_db==null){

            connect_to_movie_db = connect_to_movie_db();
        }

        return connect_to_movie_db as connect_to_movie_db;
    }
}