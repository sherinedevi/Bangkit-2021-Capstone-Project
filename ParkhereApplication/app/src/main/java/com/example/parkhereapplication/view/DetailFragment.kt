package com.example.parkhereapplication.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.FragmentDetailBinding
import com.example.parkhereapplication.model.Place
import com.example.parkhereapplication.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

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

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        detailViewModel.setPlace(arguments?.getParcelable<Place>(HomeFragment.EXTRA_PLACE) as Place)
        detailViewModel.getPlace().observe(viewLifecycleOwner, { place ->
            setData(place)
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

    private fun setData(place: Place) {
        Glide.with(this@DetailFragment)
                .load(place.image)
                .into(binding.ivPlace)
        binding.layoutContent.tvName.text = place.name
        binding.layoutContent.tvAvailablility.text = place.available.toString()
        binding.layoutContent.tvDescription.text = place.description
        binding.layoutContent.tvAddress.text = place.address
    }
}