package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.adapters.ArtistAdapter
import com.example.myapplication.adapters.ArtistDetailAdapter
import com.example.myapplication.adapters.SearchAdapter
import com.example.myapplication.databinding.FragmentArtistDetailBinding
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitApiCall
import com.example.myapplication.viewmodel.ArtistDetailViewModel
import com.example.myapplication.viewmodel.SearchViewModel
import com.example.myapplication.viewmodel.TopArtistViewModel
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call


class ArtistDetail : Fragment() {

    private lateinit var artist_name: String
    private var name: TextView? = null
    private var artist_image: CircleImageView? = null
    private var bio: TextView? = null

    private var recyclerView: RecyclerView? = null
    private var _binding: FragmentArtistDetailBinding? = null
    private lateinit var model : RetrofitApiCall
    private lateinit var artistDetailViewModel: ArtistDetailViewModel
    private lateinit var artistDetailAdapter: ArtistDetailAdapter
    private val binding get() = _binding!!
    var tracks: MutableList<Track> = mutableListOf()
    lateinit var artist: ArtistInfo

    var artists: MutableList<Artist> = mutableListOf()
    lateinit var  sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            artist_name = requireArguments().getString("artist_name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistDetailAdapter = ArtistDetailAdapter(tracks)
        sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE) ?: return

        with (sharedPref.edit()) {
            putString("artist", artist_name)
            apply()
        }

        recyclerView = binding.recycleView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = artistDetailAdapter

        name = view.findViewById(R.id.name)
        artist_image = view.findViewById(R.id.artist_image)
        bio = view.findViewById(R.id.bio)

        artistDetailViewModel = ViewModelProvider(this)[ArtistDetailViewModel::class.java]
        model = RetrofitApiCall()
        artistDetailViewModel.getArtistTopTracksList(model)
        artistDetailViewModel.getInfoList(model)

        setLiveDataListeners()
    }

    private fun setLiveDataListeners() {
        artistDetailViewModel.tracksList.observe(viewLifecycleOwner, Observer { it
            setAdapterInfo(it)
        })

        artistDetailViewModel.info.observe(viewLifecycleOwner, Observer { it
            init(it)
        })
    }

    private fun setAdapterInfo(it: List<Track>) {
        tracks.addAll(it)
        artistDetailAdapter.notifyDataSetChanged()
    }

    private fun init(artistInfo: ArtistInfo){
        name!!.text = artistInfo.name
        bio!!.text = artistInfo.bio.summary
        Glide.with(artist_image!!.context).load(artistInfo.image[0].text).into(artist_image!!)
    }
}