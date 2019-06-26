package charnpreet.movie_world.movie_db_connect

import charnpreet.movie_world.Configuration.Movie_db_config
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.movie_db_connect.API.Companion.builder


interface API {
    // replacing static keyword with comapnion in kotlin
    companion object{
        //
        //
        fun <T> builder(endpoint: Class<T>): T {
            return Retrofit.Builder()
                .baseUrl(Movie_db_config.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint)
        }

        //
        //
        fun search_In_Movies(): Search_In_Movies {

            return builder(Search_In_Movies::class.java)
        }
    }




}