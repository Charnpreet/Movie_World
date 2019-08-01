package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteSession {

    @SerializedName("success")
    @Expose
    var success: Boolean = false
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    constructor(success: Boolean){
        this.success =success
    }
}