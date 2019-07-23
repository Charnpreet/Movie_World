package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieLanguages {

    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1 :String
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

    @SerializedName("name")
    @Expose
    var name: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(iso_639_1 :String, english_name: String, name:String){
        this.iso_639_1 = iso_639_1
        this.english_name = english_name
        this.name = name
    }

}