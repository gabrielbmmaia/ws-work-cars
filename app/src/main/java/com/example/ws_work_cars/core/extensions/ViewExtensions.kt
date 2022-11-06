package com.example.ws_work_cars.core.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.enabled(){
    isVisible = true
}

fun View.disabled(){
    isVisible = false
}
