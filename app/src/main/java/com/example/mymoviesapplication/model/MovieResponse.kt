package com.example.mymoviesapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(@SerializedName("page")val page:Int,
                         @SerializedName("results")val movieMainObject:ArrayList<MovieMainObject>,
                         @SerializedName("total_results")val totalResults:Int) : Parcelable
