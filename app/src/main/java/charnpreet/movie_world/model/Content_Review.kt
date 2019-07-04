package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Content_Review : Serializable {

    @SerializedName("review")
    @Expose
    var review: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("id")
    @Expose
    private var id: Int = 0
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("page")
    @Expose
    private var page: Int = 0
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    @SerializedName("total_results")
    @Expose
    private var totalResults: Int = 0
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("total_pages")
    @Expose
    private var totalPages: Int = 0
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(review:String, id:Int, page: Int, totalResult: Int, totalPages: Int){
        this.review = review
        this.totalPages = totalPages
        this.totalResults = totalResults
        this.id = id
    }
}