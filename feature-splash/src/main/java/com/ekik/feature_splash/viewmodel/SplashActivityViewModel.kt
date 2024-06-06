package com.ekik.feature_splash.viewmodel

import com.ekik.core_ui.base.BaseActivityViewModel
import com.ekik.feature_splash.navigation.SplashNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    internal val navigation: SplashNavigation
): BaseActivityViewModel()