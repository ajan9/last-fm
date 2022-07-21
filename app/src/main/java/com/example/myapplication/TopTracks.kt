package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.TrackAdapter
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.databinding.FragmentTopTracksBinding
import com.example.myapplication.models.TopTrackResponse
import com.example.myapplication.models.Track
import com.example.myapplication.network.RetrofitApiCall
import com.example.myapplication.viewmodel.TopTracksViewModel
import retrofit2.Call


class TopTracks : Fragment() {

    private var _binding: FragmentTopTracksBinding? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var model : RetrofitApiCall
    private lateinit var topTracksViewModel: TopTracksViewModel
    private lateinit var trackAdapter: TrackAdapter
    private val binding get() = _binding!!
    var tracks: MutableList<Track> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trackAdapter = TrackAdapter(tracks)

        recyclerView = binding.recycleView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = trackAdapter

        topTracksViewModel = ViewModelProvider(this)[TopTracksViewModel::class.java]
        model = RetrofitApiCall()
        topTracksViewModel.getTopTracksList(model)

        setLiveDataListeners()

        Log.d("andreaa", "1")

    }

    private fun setLiveDataListeners() {
        Log.d("andreaa", "2")
        topTracksViewModel.topTracksLiveData.observe(viewLifecycleOwner, Observer { tracks
            Log.d("andreaa", "3")
            trackAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}