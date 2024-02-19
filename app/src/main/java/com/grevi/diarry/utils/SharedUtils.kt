package com.grevi.diarry.utils

import android.content.Context

class SharedUtils(context: Context) {

    private val appContext = context

    companion object {
        const val STARTED_KEY = "started_key"
    }

    internal fun setStartedKey() {
        appContext.getSharedPreferences(STARTED_KEY, Context.MODE_PRIVATE).let { sp ->
            sp.edit().let {
                it.putBoolean(STARTED_KEY, true)
                it.apply()
            }
        }
    }
    internal fun getStartedKey() : Boolean = appContext.getSharedPreferences(STARTED_KEY, Context.MODE_PRIVATE).getBoolean(STARTED_KEY, false)
}