package charnpreet.movie_world.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Content_Review : Serializable {

    @SerializedName("id")
    @Expose
     var id: Int = 0
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

    @SerializedName("results")
    @Expose
    var results: List<reviewDetails>? = null
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


    @SerializedName("total_results")
    @Expose
    private var totalResults: Int = 0
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(id:Int, page: Int, results: List<reviewDetails>?,totalPages: Int, totalResult: Int){
        this.results = results
        this.totalPages = totalPages
        this.totalResults = totalResults
        this.id = id
    }
    override fun toString(): String {
        return "Content_Review{" +
                "page=" + page +
                ", results=" + results +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}'.toString()
    }
}