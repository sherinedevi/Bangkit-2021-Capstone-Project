package com.example.parkhereapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkhereapplication.R
import com.example.parkhereapplication.model.Place

class HomeViewModel : ViewModel() {
    private val listPlaces = MutableLiveData<ArrayList<Place>>()

    fun setPlace() {
        val listItems = ArrayList<Place>()

        val placeOne = Place (
                name = "Malioboro Wilayah Utara",
                description = "Wilayah tujuan turis di Yogyakarta",
                image = R.drawable.parking_image_1,
                capacity = 15,
                available = 5,
                streetAddress = "Jalan Malioboro",
                address = "Jl. Mayjen MT Haryono, Gotong Royong, Kec. Tj. Karang Pusat, Kota Bandar Lampung, Lampung 35119",
                url = "Something")

        val placeTwo = Place(
                name = "Ambarukmo Plaza",
                description = "Wilayah tujuan turis di Yogyakarta",
                image = R.drawable.parking_image_2,
                capacity = 15,
                available = 5,
                streetAddress = "Jalan Juanda",
                address = "Jl. Mayjen MT Haryono, Gotong Royong, Kec. Tj. Karang Pusat, Kota Bandar Lampung, Lampung 35119",
                url = "Something")

        val placeThree = Place(
                name = "Stasiun Yogyakarta",
                description = "Wilayah tujuan turis di Yogyakarta",
                image = R.drawable.parking_image_3,
                capacity = 15,
                available = 5,
                streetAddress = "Jalan Margo Utomo",
                address = "Jl. Mayjen MT Haryono, Gotong Royong, Kec. Tj. Karang Pusat, Kota Bandar Lampung, Lampung 35119",
                url = "Something")

        val placeFour = Place (
                name = "Polresta Bandar Lampung",
                description = "Kantor Polresta Provinsi Bandar Lampung melayani laporan & pengaduan masyarakat. Buka 07.00-14.30.",
                image = R.drawable.parking_image_4,
                capacity = 15,
                available = 37,
                streetAddress = "Jl. Mayjen MT Haryono",
                address = "Jl. Mayjen MT Haryono, Gotong Royong, Kec. Tj. Karang Pusat, Kota Bandar Lampung, Lampung 35119",
                url = "Something")

        listItems.add(placeOne)
        listItems.add(placeFour)
        listItems.add(placeTwo)
        listItems.add(placeThree)

        listPlaces.postValue(listItems)
    }

    fun getPlaces(): LiveData<ArrayList<Place>> {
        return listPlaces
    }
}