package com.ekik.core_navigation.navigation

import android.content.Context
import com.ekik.core_navigation.base.BaseNavigation
import com.ekik.feature_home.ui.HomeActivity
import com.ekik.feature_intro.ui.navigation.IntroNavigation
import javax.inject.Inject

class IntroNavigationImpl @Inject constructor(private val context: Context):
    BaseNavigation(context), IntroNavigation {
    override fun navigateToHome() = navigateDestination(HomeActivity::class.java, needFinish = true)
}