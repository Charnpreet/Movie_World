package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Gravatar {
    @SerializedName("hash")
    @Expose
    var hash: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(hash:String){
    this.hash = hash
    }
}