package com.example.parkhereapplication.model

data class PlaceDetail(
    val address: String?,
    val available: Int?,
    val capacity: Int?,
    val description: String?,
    val imageParkingLot: ByteArray?
)
