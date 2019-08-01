package charnpreet.movie_world.movie_db_connect

import charnpreet.movie_world.model.*
import retrofit2.Call
import retrofit2.http.*


interface  Search_In_Movies {


    //MOVIE SEARCH AUTOCOMPLETE
    @GET("/3/search/movie/")
    fun search(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MoviesResponse>

//    //TOP RATED MOVIES
    @GET("movie/top_rated")
    fun topRated(@Query("api_key") apiKey: String, @Query("region") region: String, @Query("language") language: String): Call<MoviesResponse>
//
//    //MOVIE DETAIL
    @GET("/3/movie/{id}")
    fun movieDetails(@Path("id") movieID: Int, @Query("api_key") apiKey: String): Call<MoviesResponse>
//
//    //MOVIE IMAGES
//    @GET("/movie/{id}/images")
//    fun movieImages(@Query("api_key") apiKey: String, @Path("id") movieID: Int): Call<ImagesResponse>

    //    //Popular MOVIES
    @GET("movie/popular")
    fun popularMovies(@Query("api_key") apiKey: String, @Query("region") region: String, @Query("language") language: String): Call<MoviesResponse>

    //    //Now Playing MOVIES
    @GET("movie/now_playing")
    fun nowPlaying(@Query("api_key")apiKey: String, @Query("region") region: String, @Query("language") language: String ): Call<MoviesResponse>

    //    //Upcoming MOVIES
    @GET("movie/upcoming")
    fun upComing(@Query("api_key") apiKey: String, @Query("region") region: String, @Query("language") language: String): Call<MoviesResponse>
    //    //Upcoming MOVIES
    @GET("configuration/countries")
    fun countries(@Query("api_key") apiKey: String): Call<Array<Countries>>

    // movie Reviews
    @GET("movie/{id}/reviews")
    fun contentReviews( @Path("id") movieID :Int?, @Query("api_key")apiKey: String): Call<Content_Review>

    // movie Trailer and Teaser
    @GET("movie/{id}/videos")
    fun contentVideo(@Path("id") movieID :Int?, @Query("api_key")apiKey: String):Call<ContentVideo>

    // languages
    @GET("configuration/languages")
    fun languages(@Query("api_key") apiKey: String): Call<Array<MovieLanguages>>

    // to get temp token
    //
    @GET("authentication/token/new")
    fun requestToken(@Query("api_key") apiKey: String):Call<RequestToken>

    // to get session id
    @POST("authentication/session/new")
    @Headers("Content-Type: application/json")
    fun CreateSession(@Query("api_key") apiKey: String, @Query("request_token") request_token: String ):Call<SessionSuccess>

    // to get account details
    @GET("account")
    fun AccountDetails(@Query("api_key") apiKey: String, @Query("session_id") sessionID: String):Call<Profile>

    @DELETE("authentication/session")
    fun LogOut(@Query("api_key") apiKey: String, @Query("session_id") sessionID: String):Call<DeleteSession>

    //  add to Faveriotes
    @Headers("Content-Type: application/json")
    @POST("account/{account_id}/favorite")
    fun addToFav(@Body requestbody:FavBody, @Path("account_id") ccountId :String?, @Query("api_key") apiKey: String, @Query("session_id") sessionID: String):Call<FavAddedSuccessFully>

    // to load fav movies
    @GET("account/{account_id}/favorite/movies")
    fun getFavMovies(@Path("account_id") accountId :String?, @Query("api_key") apiKey: String, @Query("session_id") sessionID: String):Call<MoviesResponse>
}