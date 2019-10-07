package es.npatarino.android.gotchallenge.houses.presentation.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_row.view.*

class GoTHouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivBackground: ImageView = itemView.ivBackground
    val tvName: TextView = itemView.tvName
}