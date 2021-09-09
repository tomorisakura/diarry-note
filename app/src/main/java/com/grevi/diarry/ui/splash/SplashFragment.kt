package com.grevi.diarry.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.grevi.diarry.utils.SharedUtils
import com.grevi.diarry.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding
    private lateinit var navController : NavController
    private lateinit var sharedUtils: SharedUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedUtils = SharedUtils(requireContext())
        navController = Navigation.findNavController(view)
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedUtils.getStartedKey()) {
                SplashFragmentDirections.actionSplashFragmentToHomeFragment().also {
                    navController.navigate(it)
                }
            } else {
                SplashFragmentDirections.actionSplashFragmentToViewPagerFragment().apply {
                    navController.navigate(this)
                }
            }
        }, DURATION)
    }

    companion object {
        const val DURATION = 2000L
    }
}