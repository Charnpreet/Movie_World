package charnpreet.movie_world.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContentVideo: Serializable {

    @SerializedName("id")
    @Expose
    var id: Int
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("results")
    @Expose
    var results: List<VideoDetails>? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    constructor(id:Int, results: List<VideoDetails>?){
        this.id=id
        this.results = results
    }



}