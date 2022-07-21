package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ArtistAdapter
import com.example.myapplication.databinding.FragmentTopArtistsBinding
import com.example.myapplication.databinding.FragmentTopTracksBinding
import com.example.myapplication.models.Artist
import com.example.myapplication.models.TopArtistsResponse
import com.example.myapplication.models.Track
import com.example.myapplication.network.RetrofitApiCall
import com.example.myapplication.viewmodel.TopArtistViewModel
import com.example.myapplication.viewmodel.TopTracksViewModel
import retrofit2.Call


class TopArtists : Fragment() {

    private var _binding: FragmentTopArtistsBinding? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var model : RetrofitApiCall
    private lateinit var topArtistViewModel: TopArtistViewModel
    private lateinit var artistAdapter: ArtistAdapter
    private val binding get() = _binding!!
    var artists: MutableList<Artist> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistAdapter = ArtistAdapter(artists)

        recyclerView = binding.recycleView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = artistAdapter

        topArtistViewModel = ViewModelProvider(this)[TopArtistViewModel::class.java]
        model = RetrofitApiCall()
        topArtistViewModel.getTopArtistsList(model)

        setLiveDataListeners()
    }

    private fun setLiveDataListeners() {
        topArtistViewModel.topArtistLiveData.observe(viewLifecycleOwner, Observer { it
            setAdapterInfo(it)
        })
    }

    private fun setAdapterInfo(it: List<Artist>) {
        artists.addAll(it)
        artistAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}