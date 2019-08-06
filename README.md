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

*************** later images and demo will be added**********


****************whats used *************************
* Kotlin
* Retrofit 2
* YoututbeThumbNail API

***************how to use ****************

* To use just simply forked this repostry
* also to fetech data from tmbd you need your tmbd key,simply go to tmbd website and follow their instructions
* apart from above it also make use of YoutubeThumbNail API to load videos from youtube, Therefore you need key for that as well, follow their youtube instructions
* once you have your both keys ready, just simply open the app and find Configuration folder and Movie_db_config file, replace youtube and movie api key with yours



