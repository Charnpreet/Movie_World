package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SessionError {

    @SerializedName("failure")
    @Expose
    var failure :Boolean
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("status_code")
    @Expose
    var status_code: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("status_message")
    @Expose
    var status_message: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(failure :Boolean, status_code: String, status_message:String ){
        this.failure = failure
        this.status_code = status_code
        this.status_message = status_message
    }
}