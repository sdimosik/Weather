package com.sdimosikvip.weather.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isAttached = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        if (isAttached) return

        val navHost = findNavController(R.id.navHost)
        val navInflater = navHost.navInflater
        val mainGraph = navInflater.inflate(R.navigation.main_nav_graph)
        navHost.graph = mainGraph
    }
}