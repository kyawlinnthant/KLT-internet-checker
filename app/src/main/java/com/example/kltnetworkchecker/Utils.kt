package com.example.kltnetworkchecker

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.view.Gravity
import android.view.View

import android.widget.FrameLayout

fun AppCompatActivity.displaySnackTop(
    msg: String = "Just an Activity Snack",
    duration: Int = Snackbar.LENGTH_SHORT
):Snackbar {

    val snack  = Snackbar.make(findViewById(android.R.id.content)!!, msg, duration)
    val view: View = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snack.show()
    return snack
}

