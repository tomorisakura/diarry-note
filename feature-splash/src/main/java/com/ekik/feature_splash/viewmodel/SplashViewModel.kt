package com.ekik.feature_splash.viewmodel

import androidx.lifecycle.ViewModel
import com.ekik.core_common.utils.SharedUtils
import com.ekik.feature_splash.navigation.SplashNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    internal val sharedUtils: SharedUtils,
    internal val navigation: SplashNavigation
): ViewModel()