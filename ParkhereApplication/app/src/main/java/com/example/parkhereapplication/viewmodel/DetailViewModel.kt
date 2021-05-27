package com.example.parkhereapplication.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkhereapplication.model.PlaceDetail
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class DetailViewModel : ViewModel() {
    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
    private val placeDetail = MutableLiveData<PlaceDetail>()
    private lateinit var errorMessage: String

    fun setPlaceDetail(detailUrl: String, context: Context) {
        val client = AsyncHttpClient()
        client.get(detailUrl, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = responseBody?.let { String(it) }
                    val responseObject = JSONObject(result!!)
                    val data = responseObject.getJSONObject("data")

                    val address = data.getString("address")
                    val available = data.getInt("available")
                    val capacity = data.getInt("capacity")
                    val description = data.getString("description")
                    val imageParkingLot = data.getString("image_parkinglot")

                    val placeData = PlaceDetail(address, available, capacity, description, imageParkingLot)
                    placeDetail.postValue(placeData)
                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }

                Log.d(TAG, errorMessage)
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun getPlaceDetail() : LiveData<PlaceDetail> {
        return placeDetail
    }
}