package com.example.parkhereapplication.view

import android.os.Bundle
import android.util.Base64
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.FragmentDetailBinding
import com.example.parkhereapplication.model.Place
import com.example.parkhereapplication.model.PlaceDetail
import com.example.parkhereapplication.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var place: Place

    private var refrestState: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        setActionBar()
        setHasOptionsMenu(true)
        showLoading(true)

        place = arguments?.getParcelable<Place>(HomeFragment.EXTRA_PLACE) as Place
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        detailViewModel.setPlaceDetail(place.detailUrl!!, requireContext())
        detailViewModel.getPlaceDetail().observe(viewLifecycleOwner, { placeDetail ->
            if (placeDetail != null) {
                showLoading(false)
                showData(placeDetail)
                refrestState = false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Place Detail"
        (activity as AppCompatActivity?)?.supportActionBar?.setHomeButtonEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showData(placeDetail: PlaceDetail) {
        if (!refrestState) {
            Glide.with(this@DetailFragment)
                    .load(place.thumbnail)
                    .into(binding.ivPlace)
        }
        binding.layoutContent.tvName.text = place.name
        binding.layoutContent.tvStreet.text = place.street
        binding.layoutContent.tvAvailablility.text = placeDetail.available.toString()
        binding.layoutContent.tvDescription.text = placeDetail.description
        binding.layoutContent.tvAddress.text = placeDetail.address
        Glide.with(this@DetailFragment)
                .load(placeDetail.imageParkingLot)
                .into(binding.layoutContent.ivCctv)

        binding.layoutContent.tvRefreshImage.setOnClickListener {
            refrestState = true
            Glide.with(this@DetailFragment)
                    .clear(binding.layoutContent.ivCctv)
            binding.layoutContent.tvAvailablility.text = null

            binding.layoutContent.progressIvCctv.visibility = View.VISIBLE
            binding.layoutContent.progressTvAvailability.visibility = View.VISIBLE
            detailViewModel.setPlaceDetail(place.detailUrl!!, requireContext())
        }
    }

    private fun showLoading(condition: Boolean) {
        if (condition) {
            View.VISIBLE.let {
                binding.progressHeadlineImage.visibility = it
                binding.layoutContent.progressTvName.visibility = it
                binding.layoutContent.progressTvStreet.visibility = it
                binding.layoutContent.progressTvAvailability.visibility = it
                binding.layoutContent.progressTvDescription.visibility = it
                binding.layoutContent.progressTvAddress.visibility = it
                binding.layoutContent.progressIvCctv.visibility = it
            }
        } else {
            View.GONE.let {
                binding.progressHeadlineImage.visibility = it
                binding.layoutContent.progressTvName.visibility = it
                binding.layoutContent.progressTvStreet.visibility = it
                binding.layoutContent.progressTvAvailability.visibility = it
                binding.layoutContent.progressTvDescription.visibility = it
                binding.layoutContent.progressTvAddress.visibility = it
                binding.layoutContent.progressIvCctv.visibility = it
            }
        }
    }
}