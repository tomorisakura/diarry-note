package com.grevi.diarry.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.grevi.diarry.databinding.FragmentSplashBinding
import com.grevi.diarry.ui.base.BaseFragment
import com.grevi.diarry.utils.SharedUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    @Inject
    lateinit var sharedUtils: SharedUtils

    override fun viewBindingInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSplash()
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedUtils.getStartedKey()) {
                SplashFragmentDirections.actionSplashFragmentToHomeFragment().also {
                    getNavController().navigate(it)
                }
            } else {
                SplashFragmentDirections.actionSplashFragmentToViewPagerFragment().apply {
                    getNavController().navigate(this)
                }
            }
        }, DURATION)
    }

    companion object {
        const val DURATION = 2000L
    }
}