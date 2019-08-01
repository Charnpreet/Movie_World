package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SessionSuccess {

    @SerializedName("success")
    @Expose
    var success :Boolean
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("session_id")
    @Expose
    var session_id: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(success :Boolean, session_id: String ){
        this.success = success
        this.session_id = session_id
    }
}