package charnpreet.movie_world.user_credential

/*
* this class is will be inherted by login and sign up class
* will intially collect user email and password to login and sign up
* will also have object to connect to movie_db
* */
abstract class  user_crdential {
    var email :String =""
    var password :String=""

    fun load_data(){

    }
    abstract fun connect(string: String);


    //
    fun db_connection(){

    }
}