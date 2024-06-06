package com.ekik.feature_intro.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ekik.core_common.utils.SharedUtils
import com.ekik.feature_intro.ui.navigation.IntroNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val sharedUtils: SharedUtils,
    private val navigation: IntroNavigation
): ViewModel() {

    fun navigateToHome() {
        sharedUtils.setStartedKey()
        navigation.navigateToHome()
    }
}