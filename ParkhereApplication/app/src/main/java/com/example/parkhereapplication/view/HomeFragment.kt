package com.example.parkhereapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkhereapplication.R
import com.example.parkhereapplication.databinding.FragmentHomeBinding
import com.example.parkhereapplication.model.Place
import com.example.parkhereapplication.view.adapter.PlaceAdapter
import com.example.parkhereapplication.viewmodel.HomeViewModel
import java.util.ArrayList

class HomeFragment : Fragment() {
    companion object {
        const val EXTRA_PLACE = "extra_place"
    }

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

        setActionBar()
        showRecyclerView()
        showLoading(true)
        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        context?.let { homeViewModel.setPlace(it) }
        homeViewModel.getPlaces().observe(viewLifecycleOwner, { placeItems ->
            if (placeItems != null) {
                showLoading(false)
                showList(placeItems)
                adapter.setData(placeItems)
            }
        })
    }

    private fun setActionBar() {
        (activity as AppCompatActivity?)?.supportActionBar?.setHomeButtonEnabled(false)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity?)?.supportActionBar?.title = "Parkhere Dashboard"
    }

    private fun showRecyclerView() {
        adapter = PlaceAdapter()
        adapter.notifyDataSetChanged()
        binding.rvPlace.layoutManager = LinearLayoutManager(view?.context)
        binding.rvPlace.adapter = adapter
        binding.rvPlace.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Place) {
                val mBundle = Bundle()
                mBundle.putParcelable(EXTRA_PLACE, data)
                NavHostFragment
                    .findNavController(this@HomeFragment)
                    .navigate(R.id.action_homeFragment_to_detailFragment, mBundle)
            }
        })
    }

    private fun showList(placeItems: ArrayList<Place>) {
        adapter.setData(placeItems)
    }

    private fun showLoading(condition: Boolean) {
        binding.progressList.visibility = if (condition) View.VISIBLE else View.GONE
    }

}