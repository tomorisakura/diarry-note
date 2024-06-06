package com.ekik.feature_splash.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_splash.R
import com.ekik.feature_splash.databinding.FragmentSplashBinding
import com.ekik.feature_splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isHasOptionMenu = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSplash()
    }

    private fun initSplash() = with(viewModel) {
        runDelay {
            (activity as MainActivity).apply {
                if (sharedUtils.getStartedKey()) {
                    navigateToHome()
                } else navigateToIntroActivity()
            }
        }
    }
}