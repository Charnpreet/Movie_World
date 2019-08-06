package charnpreet.movie_world.fragments.Profile

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
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
import charnpreet.movie_world.utility.ConstantProvider
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
    lateinit var webView:WebView
    lateinit var webViewSettings : WebSettings
    lateinit var dialogBuilder:AlertDialog.Builder
    lateinit var alertDialog: AlertDialog
//    val DENIED = "deny"
//    val ALLOW = "allow"
//    val NAME="Name: "
//    val USER_NAME ="User Name: "
//    val NO_GRANT_ACCESS_TO_MOVIE_WORLD_APP = "You chose not Grant Access to Movie World App. To Approve Please Click Sign In Again"
//   private val NOT_LOGGED_IN_TEXT = "You are Not Logged In"
//    val TOKEN_TAG = "token"
//    val CONNECTING_TO_SERVER= "Connecting to server"

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
        webView = v.findViewById(R.id.profile_webView)
        webViewSettings = webView.settings
        progressbar = v.findViewById(R.id.pbHeaderProgress)
        progressBarTextView = v.findViewById(R.id.progressBarTextView)
        textViewName = v.findViewById(R.id.prfile_name)
        textViewUserName = v.findViewById(R.id.userName)
        imageViewProfile = v.findViewById(R.id.imageViewProfile)
        imageViewProfile.visibility = View.INVISIBLE
        progressbar.visibility = View.INVISIBLE
        LoadAccount()
        setuoWebViewClent()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setuoWebViewClent(){

        webViewSettings.javaScriptEnabled =true

        webView.setWebViewClient(object: WebViewClient(){


            // will hide progress bar and textview when page is finished loading
            override fun onPageFinished(view: WebView?, url: String?) {
                setProgressBarAndTextViewVisibility(View.INVISIBLE)
            }


            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // checking if page is loaded properly
                // beasically checking for permission
                // url either has allow or deny permission
                val loadedProperly = pagedLoadedProperly(request!!.url.toString())
               //
                if(loadedProperly) {
                if(request.url.toString().contains(ConstantProvider.DENIED)){
                    webView.visibility = View.INVISIBLE
                    setProgressBarAndTextViewVisibility(View.INVISIBLE)
                    ShowAlertDialogBox(ConstantProvider.NO_GRANT_ACCESS_TO_MOVIE_WORLD_APP)
                }
                else{
                    webView.visibility = View.INVISIBLE
                    setProgressBarAndTextViewVisibility(View.VISIBLE)
                    progressBarTextView.setText(ConstantProvider.LOADING_ACCOUNT_DETAILS)

                    val  _token  = utility.RetrivingDataFromSharedPreferences(ConstantProvider.TOKEN_TAG, v.context)
                    if(_token!=null){
                        CreateSessionID(_token)
                    }else{
                        ShowAlertDialogBox(ConstantProvider.NOT_LOGGED_IN_TEXT)
                    }
                }
                   return true
                }
               return false
            }
        })


    }

    // checking if page is loaded properly
    // beasically checking for permission
    // url either has allow or deny permission
    private fun pagedLoadedProperly(url:String):Boolean{
        return (url.contains(ConstantProvider.DENIED))||(url.contains(ConstantProvider.ALLOW))
    }

    private fun LoadAccount(){
        if( progressbar.visibility == View.INVISIBLE){
            setProgressBarAndTextViewVisibility(View.VISIBLE)
        }
        val _seesionID = utility.RetrivingDataFromSharedPreferences(ConstantProvider.SESSION_ID_TAG,v.context)

        if (_seesionID != null) {

            progressBarTextView.setText(ConstantProvider.LOADING_ACCOUNT_DETAILS)

            pullAccountsDetails(_seesionID)
        } else {

            // means user has no session id
            // therefore need to get one
            // means go back and retrieve session
            setProgressBarAndTextViewVisibility(View.INVISIBLE)
            UserNotLoggedIn()

        }
    }


    private fun pullAccountsDetails(sessionID:String)
    {
        API.search_In_Movies().AccountDetails(Movie_db_config.API_KEY, sessionID).enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {
                val profile = response!!.body()
                // saving profile id to shared preferences
                savingValuesToSharedPreferences(profile.id, ConstantProvider.PROFILE_ID_TAG)

                textViewName.setText(ConstantProvider.NAME+profile.name)

                textViewUserName.setText(ConstantProvider.USER_NAME+ profile.username)

                imageViewProfile.visibility = View.VISIBLE
                setProgressBarAndTextViewVisibility(View.INVISIBLE)

            }
            override fun onFailure(call: Call<Profile>?, t: Throwable?) {

                Log.i("hello", "unable to load prfile")
                setProgressBarAndTextViewVisibility(View.INVISIBLE)
            }
        })

    }

    // this method first check if user coming back from an intent means coming from website or not
    // based on result it calls appriporiate methods
    private fun UserNotLoggedIn(){
        ShowAlertDialogBox(ConstantProvider.NOT_LOGGED_IN_TEXT)
    }


    fun ShowAlertDialogBox(text:String){
       dialogBuilder = utility.getCustomAlertDialogBuilder("",text,
            false,v.context)
        dialogBuilder.setPositiveButton(ConstantProvider.SIGN_IN_BUTTON_TEXT, this)
        dialogBuilder.setNegativeButton(ConstantProvider.CANCEL_BUTTON_TEXT, this)
        alertDialog=  dialogBuilder.create()
        alertDialog.show()
    }
    //
    //
    fun RequestTempToken(){
        setProgressBarAndTextViewVisibility(View.VISIBLE)
        progressBarTextView.setText(ConstantProvider.CONNECTING_TO_SERVER)

        API.search_In_Movies().requestToken(Movie_db_config.API_KEY).enqueue(object : Callback<RequestToken> {
            override fun onResponse(call: Call<RequestToken>?, response: Response<RequestToken>?) {
                token = response!!.body()

                savingValuesToSharedPreferences(token!!.request_token, ConstantProvider.TOKEN_TAG)

                RequestPermissons(token!!.request_token)
                //
            }

            override fun onFailure(call: Call<RequestToken>?, t: Throwable?) {
                Log.i("hello", "Unable to get Token")
                setProgressBarAndTextViewVisibility(View.INVISIBLE)
            }
        })
    }
    //
    //
    fun RequestPermissons(token:String){
        webView.visibility = View.VISIBLE
        val baseAddress = ConstantProvider.AUTHENTICATION_BASE_ADDRESS
        // buliding a string
        val url =   baseAddress+token
        setProgressBarAndTextViewVisibility(View.INVISIBLE)
        webView.loadUrl(url)

    }

    fun CreateSessionID(token: String){

        API.search_In_Movies().CreateSession(Movie_db_config.API_KEY, token).enqueue(object :Callback<SessionSuccess>{
            override fun onFailure(call: Call<SessionSuccess>?, t: Throwable?) {

                Log.i("hello", "unable to load session id ")
            }

            override fun onResponse(call: Call<SessionSuccess>?, response: Response<SessionSuccess>?) {

                val session = response!!.body()
                
                if(session !=null){

                    savingValuesToSharedPreferences(session.session_id, ConstantProvider.SESSION_ID_TAG)
                    LoadAccount()
                    webView.clearHistory()
                    webView.clearFormData()
                    webView.destroy()
                }

            }
        })

    }
    // store data to shared preferences
    private fun savingValuesToSharedPreferences(saveMe:String, tag: String){

        val sharedPreference =  v.context.getSharedPreferences(ConstantProvider.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

        val editor = sharedPreference.edit()

        editor.putString(tag, saveMe)

        editor.apply()

    }
    //
    // this has been used to Alert Dialog box
    //
    override fun onClick(p0: DialogInterface?, p1: Int) {
        if(p1==DialogInterface.BUTTON_POSITIVE){
            alertDialog.dismiss()
            RequestTempToken()
        }
        if(p1==DialogInterface.BUTTON_NEGATIVE){

            if(webView.visibility==View.VISIBLE){
                setProgressBarAndTextViewVisibility(View.INVISIBLE)
            }
            textViewName.text = ConstantProvider.NOT_LOGGED_IN_TEXT

        }
    }

    // will progress bar visibility based on passsed in Visibilty
    private fun setProgressBarAndTextViewVisibility(visibilty:Int){
        progressbar.visibility=visibilty
        progressBarTextView.visibility = visibilty
    }

}