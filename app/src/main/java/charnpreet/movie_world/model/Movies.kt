package charnpreet.movie_world.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("poster_path")
    @Expose
    var posterPath: String
       //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }


    @SerializedName("adult")
    @Expose
    private var adult: Boolean = false
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }
    @SerializedName("overview")
    @Expose
    private var overview: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("release_date")
    @Expose
     var releaseDate: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("genre_ids")
    @Expose
    private var genreIds = ArrayList<Int>()

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("original_title")
    @Expose
     var originalTitle: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("original_language")
    @Expose
     var originalLanguage: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("title")
    @Expose
     var title: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("backdrop_path")
    @Expose
    private var backdropPath: String? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("popularity")
    @Expose
     var popularity: Double? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("vote_count")
    @Expose
    private var voteCount: Int? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

    @SerializedName("video")
    @Expose
    private var video: Boolean? = null
        //  getter
        get() = field

        // setter
        set(value) {
            field = value
        }
    @SerializedName("vote_average")
    @Expose
    private var voteAverage: Double? = null
        //  getter
        get() = field
        // setter
        set(value) {
            field = value
        }

   constructor(posterPath: String, adult: Boolean, overview: String,
               releaseDate: String, genreIds: List<Int>, id: Int?,
               originalTitle: String, originalLanguage: String,
               title: String, backdropPath: String,
               popularity: Double?, voteCount: Int?, video:
               Boolean?, voteAverage: Double?)
   {
       this.posterPath = posterPath;
       this.adult = adult;
       this.overview = overview;
       this.releaseDate = releaseDate;
       this.genreIds = genreIds as ArrayList<Int>;
       this.id = id;
       this.originalTitle = originalTitle;
       this.originalLanguage = originalLanguage;
       this.title = title;
       this.backdropPath = backdropPath;
       this.popularity = popularity;
       this.voteCount = voteCount;
       this.video = video;
       this.voteAverage = voteAverage;

   }

}