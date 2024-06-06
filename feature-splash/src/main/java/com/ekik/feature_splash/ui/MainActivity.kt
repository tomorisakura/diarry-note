package com.ekik.feature_splash.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.ekik.core_ui.base.BaseActivity
import com.ekik.feature_splash.R
import com.ekik.feature_splash.databinding.ActivityMainBinding
import com.ekik.feature_splash.viewmodel.SplashActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<SplashActivityViewModel>()

    override var navHostContainerId: Int = R.id.splash_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeNavigation(viewModel)
    }

    fun navigateToIntroActivity() = with(viewModel) {
        finish()
        navigateTo(navigation.navigateToIntroActivity())
    }

    fun navigateToHome() = with(viewModel) {
        finish()
        navigateTo(navigation.navigateToHomeActivity(isNeedFinish = true))
    }
}