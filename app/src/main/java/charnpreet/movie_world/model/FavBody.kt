package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FavBody {
    @SerializedName("media_type")
    @Expose
    var media_type: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("media_id")
    @Expose
    var media_id: Int
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("favorite")
    @Expose
    var favorite: Boolean
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    constructor(media_type:String, media_id:Int, favorite:Boolean){
        this.media_type = media_type
        this.media_id = media_id
        this.favorite = favorite
    }
}