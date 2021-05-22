package com.example.parkhereapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
        val name: String,
        val description: String,
        val image: Int,
        val capacity: Int,
        val available: Int,
        val locationAddress: String,
        val url: String
) : Parcelable