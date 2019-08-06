package charnpreet.movie_world.utility

// this class will only be used to provide constants
//
class ConstantProvider {

    // we use companion object instead of static in kotlin
    companion object
    {
        //
        // constant been used inside different actvities and fragments
        val TOP_RATED_MOVIES   = 0
        val POPULAR_MOVIES     = 1
        val UPCOMING_MOVIES    = 2
        val NOW_PLAYING_MOVIES = 3
        val TITLE_TOP_RATED_MOVIES       = "Top Rated Movies"
        val TITLE_POPULAR_MOVIES         =  "Popular Movies"
        val TITLE_UPCOMING_MOVIES        =  "Upcomming Movies"
        val TITLE_NOW_PLAYING_MOVIES     =  "Now Playing Movies"
        val NO_OF_CATOGORIES_TO_DISPLAY_ON_HOME_SCREEN = 4
        val PLEASE_SELECT_AN_ITEM_FROM_SPINNER = "Please Select An Item From Spinner"
        val YOUR_PREFERENCE_HAS_BEEN_SAVED = "Your Preference has been saved"
        val SELECT_YOUR_LANGAUAGE = "Please Select Language"
        val SELECT_YOUR_COUNTRY = "Please Select Country"
        val AUTHENTICATION_BASE_ADDRESS = "https://www.themoviedb.org/authenticate/"
        val SESSION_ID_TAG = "sessionId"
        val PROFILE_ID_TAG = "profileId"
        val MOVIE_TAG = "movie"
        val MOVIE_TYPE_MEDIA = "movie"
        val SIGN_IN_BUTTON_TEXT ="Sign In"
        val CANCEL_BUTTON_TEXT = "Cancel"
        val LOG_OUT_TEXT = "Your are Already Logged Out "
        val VIEW_PAGER_TAB_SUMMARY ="SUMMARY"
        val VIEW_PAGER_TAB_REVIEWS ="REVIEWS"
        val VIEW_PAGER_TAB_VIDEO ="VIDEO"
        val LOADING_COUNTRIES_TEXT = "loading Countries"
        val LOADING_LANGUAGES_TEXT = "loading languages"
        val COUNTRY_TEXT = "country"
        val LANGUAGE_TEXT  ="language"
        val SHARED_PREFERENCE_NAME="PREFERENCE_NAME"
        val NOW_LOADING_TOP_RATED_MOVIES="Now loading Top Rated Movies"
        val NOW_LOADING_TOP_POPULAR_MOVIES="Now loading Top Popular Movies"
        val LOADING_NOW_PLAYING_MOVIES="loading Now Playing Movies"
        val NOW_LOADING_UPCOMING_MOVIES="Now loading Upcoming Movies"
        val LOADING_ACCOUNT_DETAILS="Loading Account Details"
        val NOT_LOGGED_IN_TEXT = "You are Not Logged In"
        val UNABLE_TO_LOAD_MOVIES ="Unable To Load Movies"
        val LOADING_FAV_MOVIE_COLLECTION ="Loading Fav Movie Collection"
        val EMPTY_FAV_LIST_TEXT ="OPPS! List is Empty"
        val DENIED = "deny"
        val ALLOW = "allow"
        val NAME="Name: "
        val USER_NAME ="User Name: "
        val NO_GRANT_ACCESS_TO_MOVIE_WORLD_APP = "You chose not Grant Access to Movie World App. To Approve Please Click Sign In Again"
        val TOKEN_TAG = "token"
        val CONNECTING_TO_SERVER= "Connecting to server"
        val NO_REVIEW_FOUND ="No Reviews Found"
        val NO_TRAILER_FOUND_TEXT ="No Trailer Found"
        val OK = "ok"
    }
}