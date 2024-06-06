package com.ekik.feature_splash.navigation

import androidx.navigation.ActivityNavigator

interface SplashNavigation {
    fun navigateToIntro()
    fun navigateToHome()
    fun navigateToIntroActivity(): ActivityNavigator.Destination
    fun navigateToHomeActivity(isNeedFinish: Boolean = false): ActivityNavigator.Destination
}