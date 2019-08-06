package charnpreet.movie_world.fragments.Summary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import charnpreet.movie_world.R
import charnpreet.movie_world.adapter.Sumarry.ContentSummaryAdapter
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.utility.ConstantProvider
import charnpreet.movie_world.utility.utility
import java.io.Serializable

class ContentSummary : Fragment() {
    lateinit var v:View
    private lateinit var movie:Movies
    val utility: utility = charnpreet.movie_world.utility.utility.utility_instance;
    lateinit var recyclerView: RecyclerView
    private lateinit var progressbar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.recyler_view,container,false)

        Init()

        return v
    }

    companion object{
        fun newInstance(movie: Movies): ContentSummary {

            val contentSummary  = ContentSummary()

            var bundle: Bundle = Bundle().apply {

                putSerializable("movie", movie as Serializable)
            }
            contentSummary.setArguments(bundle)

            return contentSummary
        }
    }

    private fun ExtractBundle(){

        val args =arguments

        val Bmovie: Movies = args!!.get(ConstantProvider.MOVIE_TAG) as Movies

        movie = Bmovie
    }

    private fun Init(){
        progressbar =  utility.getProgressBarReference(v)

        progressbar.setVisibility(View.INVISIBLE)

        recyclerView = v.findViewById(R.id.recylerView_for_content)

        recyclerView.layoutManager = utility.scroll_view_for_recylerView_layoutmanager(v.context, LinearLayoutManager.VERTICAL)

         ExtractBundle()

        recyclerView.adapter = ContentSummaryAdapter(movie)     //movie object extracted from bundle
    }
}