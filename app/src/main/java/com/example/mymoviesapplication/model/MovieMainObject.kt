package com.example.mymoviesapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieMainObject(@SerializedName("poster_path")val posterPath:String?,
                           @SerializedName("overview")val overview:String?,
                           @SerializedName("title")val title:String?,
                           @SerializedName("release_date")val releaseDate:String?,
                           @SerializedName("vote_average")val voteAverage:Double?,
                           @SerializedName("genre_ids")val genreIds:ArrayList<Int>?) : Parcelable

