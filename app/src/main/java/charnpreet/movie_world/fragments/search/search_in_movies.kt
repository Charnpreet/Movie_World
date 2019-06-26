package charnpreet.movie_world.fragments.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import charnpreet.movie_world.R

class search_in_movies : Fragment() {

    companion object{
        fun newInstance(): search_in_movies {
            return search_in_movies();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.display_movie_fragment,container,false);
    }
}