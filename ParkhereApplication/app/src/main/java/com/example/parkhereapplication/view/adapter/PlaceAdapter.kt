package com.example.parkhereapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.ItemPlaceBinding
import com.example.parkhereapplication.model.Place

class PlaceAdapter : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(){
    private val mData = ArrayList<Place>()
    private lateinit var onItemClick: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClick = onItemClickCallback
    }

    fun setData(items: ArrayList<Place>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class PlaceViewHolder(itemView: View) :   RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPlaceBinding.bind(itemView)
        fun bind(place: Place) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(place.image)
                    .into(binding.ivPlace)
                binding.tvName.text = place.name
                binding.tvLocationAddress.text = place.locationAddress
//                binding.tvCapacity.text = StringBuilder("Capacity: ${place.capacity.toString()}")

                itemView.setOnClickListener { onItemClick.onItemClicked(place) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }
}