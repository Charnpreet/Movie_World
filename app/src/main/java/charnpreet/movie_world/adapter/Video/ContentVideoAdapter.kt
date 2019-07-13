package charnpreet.movie_world.adapter.Video

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import charnpreet.movie_world.Configuration.Movie_db_config
import charnpreet.movie_world.R
import charnpreet.movie_world.model.VideoDetails
import com.google.android.youtube.player.*


class ContentVideoAdapter(movie_Videos:List<VideoDetails>?): RecyclerView.Adapter<ContentVideoAdapter.ContentVideoViewHolder>() {
    lateinit var v : View

    val movie_Videos: List<VideoDetails>? = movie_Videos
    lateinit var  mInitlizier: YouTubePlayer.OnInitializedListener
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ContentVideoViewHolder {

        val inflater: LayoutInflater =  LayoutInflater.from(p0.context)

        v =inflater.inflate(R.layout.content_video,p0, false)


        return ContentVideoViewHolder(v)
    }

    override fun getItemCount(): Int {

        return movie_Videos!!.size
    }



    override fun onBindViewHolder(holder: ContentVideoViewHolder, position: Int) {



        val onThumbnailLoadedListener : YouTubeThumbnailLoader.OnThumbnailLoadedListener = object: YouTubeThumbnailLoader.OnThumbnailLoadedListener{

            override fun onThumbnailError(p0: YouTubeThumbnailView?, p1: YouTubeThumbnailLoader.ErrorReason?) {
                TODO("not implemented") //implement to handle video not available errors
            }

            override fun onThumbnailLoaded(p0: YouTubeThumbnailView?, p1: String?) {
                p0!!.setVisibility(View.VISIBLE)
                holder.relativeLayoutOverYouTubeThumbnailView.visibility = View.VISIBLE

            }
        }
        holder.youTubeThumbnailView!!.initialize(Movie_db_config.YOUTUBEPLAYER_API_KEY, object: YouTubeThumbnailView.OnInitializedListener{


            override fun onInitializationSuccess(p0: YouTubeThumbnailView?, p1: YouTubeThumbnailLoader?) {

                p1!!.setVideo(movie_Videos!![position].key)

                p1.setOnThumbnailLoadedListener(onThumbnailLoadedListener)

            }

            override fun onInitializationFailure(p0: YouTubeThumbnailView?, p1: YouTubeInitializationResult?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        holder.playButton.setOnClickListener(View.OnClickListener {

            val intent = YouTubeStandalonePlayer.createVideoIntent(v.context as Activity, Movie_db_config.YOUTUBEPLAYER_API_KEY,  movie_Videos!![position].key,0,true,true)
            v.context.startActivity(intent)
        })
    }


    class ContentVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {


        val v: View = itemView

        var playButton:ImageView = v.findViewById(R.id.btnYoutube_player)

        val relativeLayoutOverYouTubeThumbnailView: RelativeLayout = v.findViewById(R.id.relativeLayout_over_youtube_thumbnail)

        val youTubeThumbnailView: YouTubeThumbnailView? = v.findViewById(R.id.youTubeVideo_thubnil)



    }
}