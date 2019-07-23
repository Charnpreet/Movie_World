package charnpreet.movie_world.Configuration

class Movie_db_config {

    // we use companion object instead of static in kotlin
    companion object
    {
        val IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w342//"
        val  API_BASE_URL = "https://api.themoviedb.org/3/"
        val API_KEY = "REPLACE ME WITH YOUR API KEY"
        val YOUTUBEPLAYER_API_KEY ="REPLACE ME WITH YOUR API KEY"
    }

}