package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class MainScreen : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val search = view.findViewById<Button>(R.id.search)
        val artists = view.findViewById<Button>(R.id.artists)
        val tracks = view.findViewById<Button>(R.id.tracks)


        search.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreen_to_search)
        }

        artists.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreen_to_topArtists)
        }

        tracks.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreen_to_topTracks)
        }


    }




}