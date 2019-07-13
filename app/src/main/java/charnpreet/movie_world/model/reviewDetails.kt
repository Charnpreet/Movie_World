package charnpreet.movie_world.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class reviewDetails {

    @SerializedName("author")
    @Expose
    private var author: String? =null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("content")
    @Expose
     var content: String? =null
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

    @SerializedName("url")
    @Expose
    private var url: String? =null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(author:String, content:String,id:String, url : String){
        this.author=author
        this.content=content
        this.id=id
        this.url=url
    }
}