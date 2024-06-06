package com.ekik.core_ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.ActivityNavigator

abstract class BaseActivityViewModel: ViewModel() {
    protected var scenario = ""
    protected var origin = ""

    private val _navigationCommand = MutableLiveData<Event<NavigationCommand>>()
    val navigationCommand: LiveData<Event<NavigationCommand>> get() = _navigationCommand

    fun navigateTo(direction: ActivityNavigator.Destination) {
        _navigationCommand.value = Event(NavigationCommand.NavigateTo(direction))
    }

    fun back() {
        _navigationCommand.value = Event(NavigationCommand.Back)
    }
}