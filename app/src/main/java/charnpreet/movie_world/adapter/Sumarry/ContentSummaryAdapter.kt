package charnpreet.movie_world.adapter.Sumarry

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charnpreet.movie_world.R
import charnpreet.movie_world.model.Movies
//
class ContentSummaryAdapter(movies:Movies) : RecyclerView.Adapter<ContentSummaryAdapter.ContentSummaryViewHolder>() {
  lateinit var v :View

    private val movies:Movies = movies

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContentSummaryViewHolder {

     val inflater: LayoutInflater=  LayoutInflater.from(p0.context)

        v =inflater.inflate(R.layout.content_reviews, p0, false)

        return ContentSummaryViewHolder(v)
    }

    override fun getItemCount(): Int {

       return 1
    }



    override fun onBindViewHolder(p0: ContentSummaryViewHolder, p1: Int) {

       p0.textView.setText(movies.overview)
    }

    class ContentSummaryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val v:View = itemView

        val textView: TextView =v.findViewById(R.id.content_review_textView)

    }


}