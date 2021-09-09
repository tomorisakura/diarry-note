package com.grevi.diarry.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.grevi.diarry.R
import com.grevi.diarry.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initHostFragment()
    }

    private fun initHostFragment() = with(binding) {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.intro_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.splashFragment -> setToolbarVisible(false)
                R.id.viewPagerFragment -> setToolbarVisible(false)
                R.id.homeFragment -> setToolbarVisible(true)
                R.id.formFragment -> setVisible(true)
                R.id.taskFragment -> setToolbarVisible(true)
            }
        }
    }

    private fun setToolbarVisible(status: Boolean, title: String? = null) = with(binding) {
        if (status) {
            appBarLayout.visibility = View.VISIBLE
            toolbar.title = title
        } else {
            binding.appBarLayout.visibility = View.GONE
        }
    }
}