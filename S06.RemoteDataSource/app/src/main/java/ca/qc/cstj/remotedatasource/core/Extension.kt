package ca.qc.cstj.remotedatasource.core

import android.widget.ImageView

fun ImageView.loadFromResource(resourceName: String) {
    val resId = resources.getIdentifier(resourceName, "drawable", this.context.packageName)
    setImageResource(resId)
}