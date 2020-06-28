package com.example.nomspots.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nomspots.R
import com.example.nomspots.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_trending, container, false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}