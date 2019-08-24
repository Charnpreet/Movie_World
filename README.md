# Movie_World

1. This app  designed to explore The movie_db api
2. it  make use of retrofit2 to connect and load data from the movie_db 
3. it implementing drawer layout and have following items in drawer navigation section
     * Home  Section
     * Search Section
     * fav
     * filter
     * profile
     *log out
4. have home section which will be used to display
     * Popular Movies
     * Top Rated Movies
     * Now Playing
     * Upcoming Movies
5. search section is used to search any movie by entering its title, if a movie found user will be Able to select that movie to see further details of that movie, which will include 
     * reviews
     * OverView
     * release Date
     * Video:- Trailer and Teasers
        and an option to add movies to list of fav
6. filter is used to set country and language filter 
7. fav section will display list of fav movies
8. log out is to  log you out from your account
9. Profile section is used to fetech user profile data from tmbd. ATOM it diplay user name and name

*************** Screen shots**********
![Screenshot_1566522860](https://user-images.githubusercontent.com/29935876/63631863-24f23280-c670-11e9-9f4b-1929bfad6546.png)
![Screenshot_1566522965](https://user-images.githubusercontent.com/29935876/63631847-f1170d00-c66f-11e9-9019-2941f5abdec3.png)
![Screenshot_1566522938](https://user-images.githubusercontent.com/29935876/63631854-0d1aae80-c670-11e9-8192-6ba0afb2162a.png)
![Screenshot_1566616354](https://user-images.githubusercontent.com/29935876/63631912-f9237c80-c670-11e9-8828-b2ad16ab7136.png)
![Screenshot_1566616346](https://user-images.githubusercontent.com/29935876/63631918-faed4000-c670-11e9-8e7b-ab7edc214648.png)
![Screenshot_1566616333](https://user-images.githubusercontent.com/29935876/63631925-fcb70380-c670-11e9-8fd6-72f2173a854f.png)

****************whats used *************************
* Kotlin
* Retrofit 2
* YoututbeThumbNail API

***************how to use ****************

* To use just simply forked this repostry
* also to fetech data from tmbd you need your tmbd key,simply go to tmbd website and follow their instructions
* apart from above it also make use of YoutubeThumbNail API to load videos from youtube, Therefore you need key for that as well, follow their youtube instructions
* once you have your both keys ready, just simply open the app and find Configuration folder and Movie_db_config file, replace youtube and movie api key with yours



