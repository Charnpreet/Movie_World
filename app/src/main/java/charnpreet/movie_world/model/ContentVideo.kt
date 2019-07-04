package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContentVideo: Serializable {

    @SerializedName("site")
    @Expose
    var site : String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("size")
    @Expose
    var size: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("name")
    @Expose
    var name : String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("type")
    @Expose
    var type: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("id")
    @Expose
    var id: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

constructor(site:String, size:String, name:String, type:String, id:String){
    this.site =site
    this.size = size
    this.name = name
    this.type = type
    this.id = id
}

}