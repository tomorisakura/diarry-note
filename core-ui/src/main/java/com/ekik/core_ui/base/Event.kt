package com.ekik.core_ui.base

class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getNavigationIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekNavigation(): T = content
}