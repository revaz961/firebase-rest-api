package com.example.apiauthentication.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible(visible:Boolean){
    if(visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun View.snackBar(message:String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}