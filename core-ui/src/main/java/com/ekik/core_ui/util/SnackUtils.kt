package com.ekik.core_ui.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(msg : String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}