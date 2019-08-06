package charnpreet.movie_world.fragments.Filter


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Countries
import charnpreet.movie_world.model.MovieLanguages
import charnpreet.movie_world.movie_db_connect.API
import charnpreet.movie_world.utility.ConstantProvider
import charnpreet.movie_world.utility.utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Filter: Fragment(), View.OnClickListener {


    private var countries:Array<Countries> =arrayOf()
    private var languages:Array<MovieLanguages> =arrayOf()
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance
    private lateinit var languageSpinner :Spinner
    private lateinit var countriesSpinner :Spinner
    private lateinit var saveButton :Button
    private lateinit var progressbar: ProgressBar
    private lateinit var progressBarTextView: TextView

    private lateinit var v:View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v  = inflater.inflate(R.layout.filters, container,false)

        initilizing()

        loadCountries()

        return v
    }

    private fun initilizing(){

        progressbar = v.findViewById(R.id.pbHeaderProgress)

        progressBarTextView = v.findViewById(R.id.progressBarTextView)

        languageSpinner = v.findViewById(R.id.languagesSpinner)

        countriesSpinner=v.findViewById(R.id.countriesSpinner)

        saveButton = v.findViewById(R.id.saveButton)

        saveButton.setOnClickListener(this)

        settingVisibility(View.INVISIBLE)



    }

    override fun onClick(p0: View?) {

        if((languageSpinner.selectedItemPosition !=0)|| (countriesSpinner.selectedItemPosition!=0)){
            if(languageSpinner.selectedItemPosition !=0){

                val l = languageSpinner.selectedItem.toString()

                for(ml:MovieLanguages in languages){

                    if(ml.english_name==l){

                        utility.languages = ml.iso_639_1

                    }
                }
            }
            if(countriesSpinner.selectedItemPosition!=0){

                val cName = countriesSpinner.selectedItem.toString()

                for(c:Countries in countries){

                    if(c.english_name==cName){

                        utility.country = c.iso_3166_1
                    }
                }
            }
            // saving data to shared preferences
            savingValuesToSharedPreferences()
        }else{
            Toast.makeText(v.context, ConstantProvider.PLEASE_SELECT_AN_ITEM_FROM_SPINNER, Toast.LENGTH_LONG).show()
        }


    }
    companion object{
        fun newInstance(): Filter {
            return Filter()
        }
    }

        private fun loadCountries(){
            progressBarTextView.setText(ConstantProvider.LOADING_COUNTRIES_TEXT)
        val call: Call<Array<Countries>> = API.search_In_Movies().countries(Movie_db_config.API_KEY)
        call.enqueue(object : Callback<Array<Countries>> {
            override fun onResponse(call: Call<Array<Countries>>?, response: Response<Array<Countries>>?) {

                if (call != null) {
                    countries= response!!.body()
                    loadLanguages()
                }
            }

            override fun onFailure(call: Call<Array<Countries>>?, t: Throwable?) {
                Log.i("hello", "unable to laod categories")

            }
        })
    }

    private fun loadLanguages(){
        progressBarTextView.setText(ConstantProvider.LOADING_LANGUAGES_TEXT)
        val call: Call<Array<MovieLanguages>> = API.search_In_Movies().languages(Movie_db_config.API_KEY)
        call.enqueue(object : Callback<Array<MovieLanguages>>{

            override fun onFailure(call: Call<Array<MovieLanguages>>?, t: Throwable?) {

                Log.i("hello","unable to load Langugaes")


            }

            override fun onResponse(call: Call<Array<MovieLanguages>>?, response: Response<Array<MovieLanguages>>?) {
                 if(call!=null){

                    languages = response!!.body()

                     pushDataToSpinners()

                }
            }

        })

    }

    private fun pushDataToSpinners(){

        val languageName: MutableList<String> = mutableListOf()

        languageName.add(ConstantProvider.SELECT_YOUR_LANGAUAGE)

        for( lan in languages){

            languageName.add(lan.english_name)
        }
        val countryName:MutableList<String> = mutableListOf()

        countryName.add(ConstantProvider.SELECT_YOUR_COUNTRY)

        for(country:Countries in countries){

            countryName.add(country.english_name)
        }

        languageSpinner.adapter= ArrayAdapter<String>(v.context, android.R.layout.simple_spinner_dropdown_item, languageName)

        countriesSpinner.adapter = ArrayAdapter<String>(v.context,android.R.layout.simple_spinner_dropdown_item, countryName)


        settingVisibility(View.VISIBLE)

        progressbar.visibility = View.INVISIBLE

        progressBarTextView.visibility = View.INVISIBLE

    }

        private fun settingVisibility(visibility: Int){

            languageSpinner.visibility = visibility

            countriesSpinner.visibility = visibility

            saveButton.visibility = visibility


        }


    private fun savingValuesToSharedPreferences(){

        val sharedPreference =  v.context.getSharedPreferences(ConstantProvider.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

        val editor = sharedPreference.edit()

        editor.putString(ConstantProvider.COUNTRY_TEXT,utility.country)

        editor.putString(ConstantProvider.LANGUAGE_TEXT,utility.languages)

        editor.apply()

        Toast.makeText(v.context, ConstantProvider.YOUR_PREFERENCE_HAS_BEEN_SAVED, Toast.LENGTH_LONG).show()
    }


}



