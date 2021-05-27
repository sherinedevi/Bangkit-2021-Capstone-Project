package com.example.parkhereapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
        val detailUrl: String?,
        val name: String?,
        val street: String?,
        val thumbnail: String?
) : Parcelable