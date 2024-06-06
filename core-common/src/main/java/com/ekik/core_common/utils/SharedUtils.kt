package com.ekik.core_common.utils

import android.content.Context
import javax.inject.Inject

class SharedUtils @Inject constructor(private val context: Context) {
    companion object {
        const val STARTED_KEY = "started_key"
    }

    fun setStartedKey() {
        context.getSharedPreferences(STARTED_KEY, Context.MODE_PRIVATE).let { sp ->
            sp.edit().let {
                it.putBoolean(STARTED_KEY, true)
                it.apply()
            }
        }
    }

    fun getStartedKey() : Boolean = context.getSharedPreferences(STARTED_KEY, Context.MODE_PRIVATE).getBoolean(
        STARTED_KEY, false)
}