package com.example.parkhereapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.ActivityMainBinding
import com.example.parkhereapplication.databinding.FragmentHomeBinding
import com.example.parkhereapplication.view.adapter.PlaceAdapter
import com.example.parkhereapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: PlaceAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        showRecyclerView()
    }

    private fun showRecyclerView() {
        adapter = PlaceAdapter()
        adapter.notifyDataSetChanged()
        binding.rvPlace.layoutManager = LinearLayoutManager(view?.context)
        binding.rvPlace.adapter = adapter
        binding.rvPlace.setHasFixedSize(true)

        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        homeViewModel.setPlace()
        homeViewModel.getPlaces().observe(viewLifecycleOwner, { placeItems ->
            adapter.setData(placeItems)
        })
    }
}