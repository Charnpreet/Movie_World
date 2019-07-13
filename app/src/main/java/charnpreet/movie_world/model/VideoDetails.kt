package charnpreet.movie_world.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoDetails {

    @SerializedName("id")
    @Expose
    var id: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("iso_639_1")
    @Expose
    var language : String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("iso_3166_1")
    @Expose
    var country : String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("key")
    @Expose
    var key : String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


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


    constructor(id:String,language: String, country:String,key:String, name:String, site:String, size:String, type:String){
        this.site =site
        this.size = size
        this.name = name
        this.type = type
        this.id = id
        this.language = language
        this.country=country
        this.key=key
    }
}