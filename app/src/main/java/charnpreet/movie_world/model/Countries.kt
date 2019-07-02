package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Countries {
    @SerializedName("iso_3166_1")
    @Expose
     var iso_3166_1 :String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("english_name")
    @Expose
    var english_name: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(iso_3166_1 :String, english_name: String){
        this.iso_3166_1 = iso_3166_1
        this.english_name = english_name
    }
}