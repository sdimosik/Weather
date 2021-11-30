package com.sdimosikvip.weather.screens

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animDrawable: AnimationDrawable
    private var isAttached = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        window.statusBarColor = Color.TRANSPARENT

        animDrawable = binding.root.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(2500)
        animDrawable.setExitFadeDuration(5000)
    }

    override fun onStart() {
        super.onStart()

        if (!isAttached) {
            val navHost = findNavController(R.id.navHost)
            val navInflater = navHost.navInflater
            val mainGraph = navInflater.inflate(R.navigation.main_nav_graph)
            navHost.graph = mainGraph
            isAttached = true
        }
    }

    override fun onResume() {
        super.onResume()
        animDrawable.start()
    }
}
