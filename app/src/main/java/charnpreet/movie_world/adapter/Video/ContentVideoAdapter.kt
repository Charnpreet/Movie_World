package charnpreet.movie_world.adapter.Video

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Content_Review
import charnpreet.movie_world.model.Movies
import charnpreet.movie_world.model.VideoDetails

class ContentVideoAdapter(movie_Videos:List<VideoDetails>?): RecyclerView.Adapter<ContentVideoAdapter.ContentVideoViewHolder>() {
    lateinit var v : View

    private val movie_Videos: List<VideoDetails>? = movie_Videos

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContentVideoViewHolder {

        val inflater: LayoutInflater =  LayoutInflater.from(p0.context)

        v =inflater.inflate(R.layout.content_video,p0, false)

        return ContentVideoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movie_Videos!!.size
    }

    override fun onBindViewHolder(p0: ContentVideoViewHolder, p1: Int) {

        p0.webView.loadUrl("https://www.youtube.com/watch?v="+movie_Videos!![p1].key)
    }


    class ContentVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val v: View = itemView

        val webView: WebView =v.findViewById(R.id.webView_for_video)

    }
}