package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ArtistAdapter
import com.example.myapplication.adapters.SearchAdapter
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.models.Artist
import com.example.myapplication.models.SearchResponse
import retrofit2.Call


class Search : Fragment() {

    private var recyclerView: RecyclerView? = null
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var artist: MutableList<Artist> = mutableListOf()
        var searchAdapter: SearchAdapter = SearchAdapter(artist)
        var search: SearchView = view.findViewById(R.id.searchView)


        recyclerView = view.findViewById(R.id.recycleView)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = searchAdapter

        search.setOnClickListener { search.onActionViewExpanded() }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                artist.clear()
                if(s.equals("")){
                    recyclerView?.visibility = View.GONE
                } else{
                    recyclerView?.visibility = View.VISIBLE
                    val response = RetrofitInstance.api.getSearch(s)
                    response.enqueue(
                        object : retrofit2.Callback<SearchResponse> {
                            override fun onResponse(
                                call: retrofit2.Call<SearchResponse>,
                                response: retrofit2.Response<SearchResponse>
                            ) {
                                if (response.body() != null) {
                                    val searchResponse = (response.body())!!
                                    artist.addAll(searchResponse.results.artistmatches.artist)
                                    searchAdapter.notifyDataSetChanged()
                                    Log.d("andrea", artist.toString())
                                }
                            }

                            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                                Toast.makeText(context, "Failed $t", Toast.LENGTH_LONG).show()
                            }
                        },
                    )
                }

                return false
            }

        })

    }
}