package com.ekik.feature_splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ekik.core_ui.base.BaseFragment
import com.ekik.feature_splash.R
import com.ekik.feature_splash.databinding.FragmentSplashBinding
import com.ekik.feature_splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun subscribeUi() {

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