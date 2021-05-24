package com.example.parkhereapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkhereapplication.model.Place

class DetailViewModel : ViewModel() {
    private val listPlaces = MutableLiveData<Place>()

    fun setPlace(listItems: Place) {
        listPlaces.postValue(listItems)
    }

    fun getPlace() : LiveData<Place> {
        return listPlaces
    }
}