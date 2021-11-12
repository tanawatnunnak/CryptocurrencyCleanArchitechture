package com.tanawatnunnak.cryptocurrencycleanarchitechture.common

import android.app.Application
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

fun View.setVisibility(isVisible: Boolean) {
    when (isVisible) {
        true -> visible()
        else -> gone()
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun RecyclerView.ViewHolder.getColor(colorId: Int): Int {
    val context = itemView.context
    return ContextCompat.getColor(context, colorId)
}

fun AndroidViewModel.getString(stringId: Int): String{
    return getApplication<Application>().getString(stringId)
}