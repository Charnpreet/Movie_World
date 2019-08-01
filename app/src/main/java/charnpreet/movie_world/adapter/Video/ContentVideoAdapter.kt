package charnpreet.movie_world.adapter.Video

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.VideoDetails
import com.google.android.youtube.player.*


class ContentVideoAdapter(val movie_Videos: List<VideoDetails>?): RecyclerView.Adapter<ContentVideoAdapter.ContentVideoViewHolder>() {
    lateinit var v : View

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContentVideoViewHolder {

        val inflater: LayoutInflater =  LayoutInflater.from(p0.context)

        v =inflater.inflate(R.layout.content_video,p0, false)

        return ContentVideoViewHolder(v)
    }

    override fun getItemCount(): Int {

        return movie_Videos!!.size
    }



    override fun onBindViewHolder(holder: ContentVideoViewHolder, position: Int) {


        holder.youTubeThumbnailView!!.initialize(Movie_db_config.YOUTUBEPLAYER_API_KEY, object: YouTubeThumbnailView.OnInitializedListener{


            override fun onInitializationSuccess(p0: YouTubeThumbnailView?, p1: YouTubeThumbnailLoader?) {

                p1!!.setVideo(movie_Videos!![holder.adapterPosition].key)

                p1.setOnThumbnailLoadedListener(object :YouTubeThumbnailLoader.OnThumbnailLoadedListener{

                    override fun onThumbnailLoaded(p2: YouTubeThumbnailView?, p3: String?) {

                        p1.release()

                    }

                    override fun onThumbnailError(p0: YouTubeThumbnailView?, p1: YouTubeThumbnailLoader.ErrorReason?) {

                        Log.i("hello", "thumbnail unable to load")

                    }

                })

            }

            override fun onInitializationFailure(p0: YouTubeThumbnailView?, p1: YouTubeInitializationResult?) {

                    Log.i("hello", "thumbnail initilization failed")

            }

        })

            holder.playButton.setOnClickListener {

                val intent = YouTubeStandalonePlayer.createVideoIntent(v.context as Activity, Movie_db_config.YOUTUBEPLAYER_API_KEY,  movie_Videos!![0].key,0,true,true)

                v.context.startActivity(intent)
            }

    }


 inner class ContentVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val v = itemView

        val playButton:ImageView = v.findViewById(R.id.btnYoutube_player)

        val youTubeThumbnailView: YouTubeThumbnailView? = v.findViewById(R.id.youTubeVideo_thubnil)
    }
}