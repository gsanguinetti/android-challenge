package es.npatarino.android.gotchallenge.characters.presentation.ui

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_search_row.view.*

class GoTSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val svQuery: SearchView = itemView.svQuery
}