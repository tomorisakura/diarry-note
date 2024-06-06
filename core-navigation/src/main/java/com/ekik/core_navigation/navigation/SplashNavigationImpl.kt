package com.ekik.core_navigation.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.ActivityNavigator
import com.ekik.core_navigation.base.BaseNavigation
import com.ekik.feature_home.ui.HomeActivity
import com.ekik.feature_intro.ui.IntroActivity
import com.ekik.feature_splash.navigation.SplashNavigation
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(private val context: Context) : BaseNavigation(context), SplashNavigation {
    override fun navigateToIntro() {
        navigateDestination(IntroActivity::class.java, needFinish = true)
    }

    override fun navigateToHome() {
        navigateDestination(HomeActivity::class.java, needFinish = true)
    }

    override fun navigateToIntroActivity(): ActivityNavigator.Destination {
        return ActivityNavigator(context).createDestination().setIntent(
            Intent(context, IntroActivity::class.java)
        )
    }

    override fun navigateToHomeActivity(isNeedFinish: Boolean): ActivityNavigator.Destination {
        return ActivityNavigator(context).createDestination().setIntent(
            Intent(context, HomeActivity::class.java)
        )
    }
}