package com.example.parkhereapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.FragmentDetailBinding
import com.example.parkhereapplication.model.Place

class DetailFragment : Fragment() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }
    private lateinit var binding: FragmentDetailBinding
    private lateinit var place: Place

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        getSelectedPlace()
    }

    private fun getSelectedPlace() {
        val place = arguments?.getParcelable<Place>(HomeFragment.EXTRA_USER) as Place
        binding.tvTest.text = place.name
    }
}