package com.example.parkhereapplication.viewmodel

import android.content.Context
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkhereapplication.model.Place
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class HomeViewModel : ViewModel() {
    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

    private val listPlaces = MutableLiveData<ArrayList<Place>>()
    private lateinit var errorMessage: String

    fun setPlace(context: Context) {
        val listItems = ArrayList<Place>()
        val url = "https://virtual-ego-312809.et.r.appspot.com/locations"
        val client = AsyncHttpClient()

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                try {
                    val result = responseBody?.let { String(it) }
                    val responseObject = JSONObject(result!!)
                    val list = responseObject.getJSONArray("data")

                    for (i in 0 until list.length()) {
                        val item = list.getJSONObject(i)
                        val detailUrl = item.getString("detail_url")
                        val name = item.getString("name")
                        val street = item.getString("street")
                        val thumbnail = Base64.decode(item.getString("thumbnail_base64"), Base64.DEFAULT)

                        val place = Place(detailUrl, name, street, thumbnail)
                        listItems.add(place)
                    }
                    listPlaces.postValue(listItems)
                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }
            }

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

    fun getPlaces(): LiveData<ArrayList<Place>> {
        return listPlaces
    }
}