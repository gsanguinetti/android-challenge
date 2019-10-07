package es.npatarino.android.gotchallenge.base.presentation

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import es.npatarino.android.gotchallenge.R

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(imageUrl :String, defaultDrawableKey :String? = null) {
    if(imageUrl.isNotEmpty()) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(this)
    }
}