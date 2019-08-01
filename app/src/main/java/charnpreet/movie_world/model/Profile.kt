package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {
    @SerializedName("name")
    @Expose
    var name: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("username")
    @Expose
    var username: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("include_adult")
    @Expose
    var include_adult: Boolean
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("id")
    @Expose
    var id: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    @SerializedName("iso_3166_1")
    @Expose
    var iso_3166_1: String
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    @SerializedName("avatar")
    @Expose
    var avatar: Gravatar
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    constructor(name:String, username:String, adult:Boolean, id:String,
                iso_639_1:String, iso_3166_1:String, avatar:Gravatar){
        this.name = name
        this.username = username
        this.include_adult = adult
        this.id = id
        this.iso_639_1 = iso_639_1
        this.iso_3166_1 = iso_3166_1
        this.avatar = avatar
    }
}