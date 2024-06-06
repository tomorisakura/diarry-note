package com.ekik.core_navigation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.ActivityNavigator

abstract class BaseNavigation(private val context: Context) {
    protected fun <T>navigateDestination(activityClass: Class<T>, args: Bundle? = null, needFinish: Boolean = false) {
        ActivityNavigator(context).apply {
            val destination = createDestination().setIntent(Intent(context, activityClass))
            navigate(destination, args, null, null)
        }
    }
}