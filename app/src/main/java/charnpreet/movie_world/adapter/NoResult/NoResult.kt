package charnpreet.movie_world.adapter.NoResult

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charnpreet.movie_world.R

class NoResult():RecyclerView.Adapter<NoResult.NoResultHolder>()  {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NoResultHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.no_result, p0, false)
      return  NoResultHolder(view)
    }

    override fun onBindViewHolder(p0: NoResultHolder, p1: Int) {
        p0.textView.setText("No Result")
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class NoResultHolder(view:View):RecyclerView.ViewHolder(view){

        val textView:TextView = view.findViewById(R.id.noResult_textView)


    }
}