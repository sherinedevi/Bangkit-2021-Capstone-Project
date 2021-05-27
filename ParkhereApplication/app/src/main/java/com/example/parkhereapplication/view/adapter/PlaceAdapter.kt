package com.example.parkhereapplication.view.adapter

import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.ItemPlaceBinding
import com.example.parkhereapplication.model.Place
import kotlin.collections.ArrayList

class PlaceAdapter : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(){
    private val listPlace = ArrayList<Place>()
    private lateinit var onItemClick: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClick = onItemClickCallback
    }

    fun setData(items: ArrayList<Place>) {
        listPlace.clear()
        listPlace.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(listPlace[position])
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    inner class PlaceViewHolder(itemView: View) :   RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPlaceBinding.bind(itemView)
        fun bind(place: Place) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(Base64.decode(place.thumbnail, Base64.DEFAULT))
                    .into(binding.ivPlace)
                binding.tvName.text = place.name
                binding.tvStreet.text = place.street

                itemView.setOnClickListener { onItemClick.onItemClicked(place) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }
}