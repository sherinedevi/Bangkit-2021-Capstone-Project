package com.example.parkhereapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
        val detailUrl: String?,
        val name: String?,
        val street: String?,
        val thumbnail: ByteArray?,
) : Parcelable