package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.SearchAdapter
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.models.Artist
import com.example.myapplication.network.RetrofitApiCall
import com.example.myapplication.viewmodel.SearchViewModel


class Search : Fragment() {

    private var recyclerView: RecyclerView? = null
    lateinit var name: String
    private var _binding: FragmentSearchBinding? = null
    private lateinit var model : RetrofitApiCall
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    private val binding get() = _binding!!
    var artists: MutableList<Artist> = mutableListOf()
    lateinit var  sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var search: SearchView = view.findViewById(R.id.searchView)
        sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE) ?: return

        searchAdapter = SearchAdapter(artists)

        recyclerView = binding.recycleView
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = searchAdapter

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        search.setOnClickListener { search.onActionViewExpanded() }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }
            override fun onQueryTextChange(s: String): Boolean {
                artists.clear()
                if(s.equals("")){
                    recyclerView?.visibility = View.GONE
                } else{
                    recyclerView?.visibility = View.VISIBLE
                    with (sharedPref.edit()) {
                        putString("artist", s)
                        apply()
                    }

                    model = RetrofitApiCall()
                    searchViewModel.getSearchArtistList(model)

                    setLiveDataListeners()
                }
                return false
            }
        })
    }

    private fun setLiveDataListeners() {
        searchViewModel.artistsList.observe(viewLifecycleOwner, Observer { it
            setAdapterInfo(it)
        })
    }

    private fun setAdapterInfo(it: List<Artist>) {
        artists.addAll(it)
        searchAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}