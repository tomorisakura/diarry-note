package com.ekik.core_ui.base

import androidx.navigation.ActivityNavigator

sealed class NavigationCommand {
    data class NavigateTo(val destination: ActivityNavigator.Destination): NavigationCommand()
    data object Back : NavigationCommand()
}