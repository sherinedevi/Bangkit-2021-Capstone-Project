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
            image = R.drawable.parking_image,
            capacity = 15,
            available = 5,
            locationAddress = "Jalan Malioboro",
            url = "Something")

        val placeTwo = Place(
            name = "Malioboro Wilayah Utara",
            description = "Wilayah tujuan turis di Yogyakarta",
            image = R.drawable.parking_image,
            capacity = 15,
            available = 5,
            locationAddress = "Jalan Malioboro",
            url = "Something")

        val placeThree = Place(
            name = "Malioboro Wilayah Utara",
            description = "Wilayah tujuan turis di Yogyakarta",
            image = R.drawable.parking_image,
            capacity = 15,
            available = 5,
            locationAddress = "Jalan Malioboro",
            url = "Something")

        listItems.add(placeOne)
        listItems.add(placeTwo)
        listItems.add(placeThree)

        listPlaces.postValue(listItems)
    }

    fun getPlaces(): LiveData<ArrayList<Place>> {
        return listPlaces
    }
}