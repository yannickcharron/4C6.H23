package ca.qc.cstj.mvvm.core

import android.annotation.SuppressLint
import android.widget.ImageView

@SuppressLint("DiscouragedApi")
fun ImageView.loadFromResource(resourceName: String) {
    val resId = resources.getIdentifier(resourceName, "drawable", context.packageName)
    setImageResource(resId)
}