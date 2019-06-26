package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MoviesResponse {

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
    var results: List<Movies>? = null
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

    override fun toString(): String {
        return "MoviesResponse{" +
                "page=" + page +
                ", results=" + results +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}'.toString()
    }
}