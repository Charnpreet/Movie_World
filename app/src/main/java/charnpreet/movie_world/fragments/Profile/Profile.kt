package charnpreet.movie_world.fragments.Profile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Profile
import charnpreet.movie_world.model.RequestToken
import charnpreet.movie_world.model.SessionSuccess
import charnpreet.movie_world.movie_db_connect.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Profile : Fragment(), DialogInterface.OnClickListener {

    private var token: RequestToken? = null
    private var utility = charnpreet.movie_world.utility.utility.utility_instance
    lateinit var v: View
    private lateinit var textViewName: TextView
    private lateinit var textViewUserName: TextView
    private lateinit var imageViewProfile :ImageView
    private lateinit var progressbar: ProgressBar
    private lateinit var progressBarTextView: TextView

    val NAME="Name: "
    val USER_NAME ="User Name: "
    val NO_GRANT_ACCESS_TO_MOVIE_WORLD_APP = "You chose not Grant Access to Movie World App. To Approve Please Click Sign In Again"
   private val NOT_LOGGED_IN_TEXT = "You are Not Logged In"
    val TOKEN_TAG = "token"
    val REDIRECTED_TO_EXTERNAL_WEBSITE = "You are been redirected to external website to sign"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.profile,container,false)
        init()
        return v
    }

    companion object{

        fun newInstance(): charnpreet.movie_world.fragments.Profile.Profile {
            return Profile()
        }
    }

    private fun init(){
        progressbar = v.findViewById(R.id.pbHeaderProgress)
        progressBarTextView = v.findViewById(R.id.progressBarTextView)
        textViewName = v.findViewById(R.id.prfile_name)
        textViewUserName = v.findViewById(R.id.userName)
        imageViewProfile = v.findViewById(R.id.imageViewProfile)
        imageViewProfile.visibility = View.INVISIBLE
        LoadAccount()
    }
    private fun LoadAccount(){
        progressbar.setVisibility(View.VISIBLE)
        val _seesionID = utility.RetrivingDataFromSharedPreferences(utility.SESSION_ID_TAG,v.context)
        if (_seesionID != null) {

            progressBarTextView.setText(utility.LOADING_ACCOUNT_DETAILS)

            pullAccountsDetails(_seesionID)
        } else {

            // means user has no session id
            // therefore need to get one
            // means go back and retrieve session
            progressbar.setVisibility(View.INVISIBLE)
            UserNotLoggedIn()

        }
    }


    private fun pullAccountsDetails(sessionID:String)
    {
        API.search_In_Movies().AccountDetails(Movie_db_config.API_KEY, sessionID).enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {
                val profile = response!!.body()
                // saving profile id to shared preferences
                savingValuesToSharedPreferences(profile.id, utility.PROFILE_ID_TAG)

                textViewName.setText(NAME+profile.name)

                textViewUserName.setText(USER_NAME+ profile.username)

                imageViewProfile.visibility = View.VISIBLE

                progressbar.setVisibility(View.INVISIBLE)
                progressBarTextView.visibility = View.INVISIBLE

            }
            override fun onFailure(call: Call<Profile>?, t: Throwable?) {

                Log.i("hello", "unable to load prfile")

                progressbar.setVisibility(View.INVISIBLE)
                progressBarTextView.visibility = View.INVISIBLE
            }
        })

    }

    // this method first check if user coming back from an intent means coming from website or not
    // based on result it calls appriporiate methods
    private fun UserNotLoggedIn(){
        // means users is comming back from website
        val redirectUri :Uri? = activity!!.getIntent().getData()
        val  _token  = utility.RetrivingDataFromSharedPreferences(TOKEN_TAG, v.context)
        val DENIED = "denied"
        try{
            if(redirectUri!=null){

                if(redirectUri.toString().contains(DENIED)){

                    ShowAlertDialogBox(NO_GRANT_ACCESS_TO_MOVIE_WORLD_APP)
                }

                if(_token!=null){
                    CreateSessionID(_token)
                }else{
                    ShowAlertDialogBox(NOT_LOGGED_IN_TEXT)
                }

            }else{
                ShowAlertDialogBox(NOT_LOGGED_IN_TEXT)
            }

        }catch (ex:Exception){

            Toast.makeText(v.context,"We have encounter a problem!", Toast.LENGTH_LONG).show()

        }

    }


    fun ShowAlertDialogBox(text:String){
       val dialogBuilder = utility.getCustomAlertDialogBuilder("",text,
            false,v.context)
        dialogBuilder.setPositiveButton(utility.SIGN_IN_BUTTON_TEXT, this)
        dialogBuilder.setNegativeButton(utility.CANCEL_BUTTON_TEXT, this)
        dialogBuilder.create().show()
    }
    //
    //
    fun RequestTempToken(){
        API.search_In_Movies().requestToken(Movie_db_config.API_KEY).enqueue(object : Callback<RequestToken> {
            override fun onResponse(call: Call<RequestToken>?, response: Response<RequestToken>?) {
                token = response!!.body()

                savingValuesToSharedPreferences(token!!.request_token, TOKEN_TAG)

                RequestPermissons(token!!.request_token)
                //
            }

            override fun onFailure(call: Call<RequestToken>?, t: Throwable?) {
                Log.i("hello", "Unable to get Token")
            }
        })
    }
    //
    //
    fun RequestPermissons(token:String){
        val redirect = "?redirect_to=https://www.movie_world.com/"
        val baseAddress = "https://www.themoviedb.org/authenticate/"
        // buliding a string
        val url =   baseAddress+token+redirect

        val i =  Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(url))
        startActivity(i)
    }

    fun CreateSessionID(token: String){

        API.search_In_Movies().CreateSession(Movie_db_config.API_KEY, token).enqueue(object :Callback<SessionSuccess>{
            override fun onFailure(call: Call<SessionSuccess>?, t: Throwable?) {

                Log.i("hello", "unable to load session id ")
            }

            override fun onResponse(call: Call<SessionSuccess>?, response: Response<SessionSuccess>?) {

                val session = response!!.body()

                if(session !=null){

                    savingValuesToSharedPreferences(session.session_id, utility.SESSION_ID_TAG)
                    LoadAccount()
                }

            }
        })

    }

    // store data to shared preferences
    private fun savingValuesToSharedPreferences(saveMe:String, tag: String){

        val sharedPreference =  v.context.getSharedPreferences(utility.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

        val editor = sharedPreference.edit()

        editor.putString(tag, saveMe)

        editor.apply()

    }
    //
    // this has been used to Alert Dialog box
    //
    override fun onClick(p0: DialogInterface?, p1: Int) {
        if(p1==DialogInterface.BUTTON_POSITIVE){
            val dialogBuilder = utility.getCustomAlertDialogBuilder("",REDIRECTED_TO_EXTERNAL_WEBSITE,
                true,v.context)
            dialogBuilder.create().show()
            utility.HoldingMainThreadUnitlDataIsLoaded(::RequestTempToken)

        }else{
            textViewName.text = NOT_LOGGED_IN_TEXT
        }


    }


}