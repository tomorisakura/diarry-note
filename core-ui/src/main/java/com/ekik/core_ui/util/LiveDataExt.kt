package com.ekik.core_ui.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeOrNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner) {
        it?.let(observer)
    }
}