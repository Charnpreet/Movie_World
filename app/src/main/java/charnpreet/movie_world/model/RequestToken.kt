package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestToken {

    @SerializedName("success")
    @Expose
    var success :Boolean
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("expires_at")
    @Expose
    var expires_at: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("request_token")
    @Expose
    var request_token: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(success :Boolean, expires_at: String, request_token:String ){
        this.success = success
        this.expires_at = expires_at
        this.request_token = request_token
    }
}