package charnpreet.movie_world.movie_db_connect

import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Search_In_Movies {


    //MOVIE SEARCH AUTOCOMPLETE
    @GET("/search/movie")
    fun search(@Query("api_key") apiKey: String, @Query("query") query: String): Call<Movies>

//    //TOP RATED MOVIES
    @GET("movie/top_rated")
   public fun topRated(@Query("api_key") apiKey: String): Call<MoviesResponse>
//
//    //MOVIE DETAIL
//    @GET("/3/movie/{id}")
//    fun movieDetails(@Path("id") movieID: Int, @Query("api_key") apiKey: String): Call<MovieResponse>
//
//    //MOVIE IMAGES
//    @GET("/movie/{id}/images")
//    fun movieImages(@Query("api_key") apiKey: String, @Path("id") movieID: Int): Call<ImagesResponse>

}