package charnpreet.movie_world.adapter.Reviews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charnpreet.movie_world.R
import charnpreet.movie_world.model.reviewDetails

class contentReviewsAdapter(movie_reviews:List<reviewDetails>?): RecyclerView.Adapter<contentReviewsAdapter.ContentReviewViewHolder>() {
    lateinit var v : View

    private val movie_reviews:List<reviewDetails>? = movie_reviews

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContentReviewViewHolder {
        val inflater: LayoutInflater =  LayoutInflater.from(p0.context)
        v =inflater.inflate(R.layout.content_reviews,p0, false)
        return ContentReviewViewHolder(v)
    }

    override fun getItemCount(): Int {
       return movie_reviews!!.size
    }

    override fun onBindViewHolder(p0: ContentReviewViewHolder, p1: Int) {

        p0.textView.setText(movie_reviews!![p1].content)
    }


    class ContentReviewViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val v:View = itemView

        val textView: TextView =v.findViewById(R.id.content_review_textView)

    }
}