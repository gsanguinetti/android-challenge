package es.npatarino.android.gotchallenge.houses.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.base.presentation.loadImage
import es.npatarino.android.gotchallenge.houses.domain.model.GoTHouse

class GoTHouseAdapter: RecyclerView.Adapter<GoTHouseViewHolder>() {

    var houses: List<GoTHouse>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onHouseSelected: ((houseId: String, houseName: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoTHouseViewHolder {
        return GoTHouseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: GoTHouseViewHolder, position: Int) {
        houses?.let {
            with(it[position]) {
                holder.ivBackground.loadImage(houseImageUrl)
                holder.tvName.text = houseName
                holder.itemView.setOnClickListener { onHouseSelected?.invoke(houseId, houseName) }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (houses.isNullOrEmpty()) 0 else houses!!.size
    }
}