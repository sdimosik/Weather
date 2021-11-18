package com.sdimosikvip.weather.screens

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.appComponent
import com.sdimosikvip.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onStart() {
        super.onStart()

        val navHost = findNavController(R.id.navHost)
        val navInflater = navHost.navInflater
        val mainGraph = navInflater.inflate(R.navigation.main_nav_graph)
        navHost.graph = mainGraph
    }
}
