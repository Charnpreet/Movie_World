package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FavAddedSuccessFully(status_code: Int, status_message: String) {

    @SerializedName("status_code")
    @Expose
    var status_code :Int = status_code
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("status_message")
    @Expose
    var status_message: String = status_message
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

}