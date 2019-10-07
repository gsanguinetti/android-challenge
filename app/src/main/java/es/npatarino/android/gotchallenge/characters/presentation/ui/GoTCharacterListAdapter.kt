package es.npatarino.android.gotchallenge.characters.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.base.presentation.DelayedOnQueryTextListener
import es.npatarino.android.gotchallenge.base.presentation.loadImage
import es.npatarino.android.gotchallenge.characters.domain.model.GoTCharacter

class GoTCharacterListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var characters: List<GoTCharacter>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onQueryCharacter: ((query: String) -> Unit)? = null
    var onCharacterSelected: ((character: GoTCharacter, ivBackground: ImageView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SEARCH_VIEW_TYPE ->
                GoTSearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_search_row,
                        parent, false))
            EMPTY_VIEW_TYPE -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_empty_row,
                    parent, false))
            else ->
                GoTCharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_row,
                        parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            position == 0 -> {
                holder as GoTSearchViewHolder
                holder.svQuery.setOnQueryTextListener(object : DelayedOnQueryTextListener() {
                    override fun onDelayedQueryTextChange(query: String) {
                        onQueryCharacter?.invoke(query)
                    }
                })
            }
            characters.isNullOrEmpty() && position == 1 -> Unit
            else -> {
                holder as GoTCharacterViewHolder
                characters!![position - 1].let { character ->
                    holder.ivBackground.loadImage(character.imageUrl)
                    holder.tvName.text = character.name
                    holder.itemView.setOnClickListener {
                        onCharacterSelected?.invoke(character, holder.ivBackground)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> SEARCH_VIEW_TYPE
            characters.isNullOrEmpty() && position == 1 -> EMPTY_VIEW_TYPE
            else -> CHARACTER_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        return if (characters.isNullOrEmpty()) 2 else characters!!.size + 1
    }

    companion object {
        private const val SEARCH_VIEW_TYPE = 0
        private const val CHARACTER_VIEW_TYPE = 1
        private const val EMPTY_VIEW_TYPE = 2
    }

}