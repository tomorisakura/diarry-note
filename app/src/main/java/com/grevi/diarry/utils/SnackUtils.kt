package com.grevi.diarry.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snackBar(view: View, msg : String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
}