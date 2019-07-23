package charnpreet.movie_world.Activity.Activity.ContentDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.viewPager.View_Pager
import charnpreet.movie_world.model.Movies
import com.squareup.picasso.Picasso

class ContentDetail : AppCompatActivity(), View.OnClickListener {
    lateinit var poster: ImageView
    lateinit var cancel_button: ImageView
    lateinit var releaseDate: TextView
    lateinit var popularity: TextView
    private lateinit var movie: Movies
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_details)
        init()
    }

    private fun init(){
        supportActionBar!!.hide()
        poster = findViewById(R.id.content_detail_poster_view);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabLayout)
        cancel_button=findViewById(R.id.cancel_imageView)
        cancel_button.setOnClickListener(this)
        releaseDate=findViewById(R.id.content_detail_relase_date);
        popularity=findViewById(R.id.content_detail_popularity)
        ExtractBundle()
        push_content()
        SetupViewPager()
    }

    override fun onClick(p0: View?) {

        finish()
    }

    private fun SetupViewPager(){
        val adapter: PagerAdapter =
            View_Pager(supportFragmentManager, tabLayout.tabCount, movie)
            viewPager.adapter = adapter
            SetupListner()
    }

    private fun SetupListner(){

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{

            override fun onTabReselected(p0: TabLayout.Tab?) {

                viewPager.setCurrentItem(p0!!.position)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })
    }

    private fun ExtractBundle(){

        val Bmovie: Movies = intent?.extras?.getBundle("movie")!!.getSerializable("movie")as Movies
        movie = Bmovie
    }


    private fun push_content(){

        var image_url : String = Movie_db_config.IMAGE_URL_BASE_PATH + movie.backdropPath

        Picasso.with(applicationContext)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(poster)

        releaseDate.setText("Release Date:- " + movie.releaseDate)

        popularity.setText("Popularity:- " + movie.popularity)
    }

    override fun onBackPressed() {
        finish()
    }
}
