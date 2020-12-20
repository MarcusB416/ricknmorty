package io.threethirtythree.ricknmorty.view.recyclerview
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import io.threethirtythree.ricknmorty.R
import io.threethirtythree.ricknmorty.database.Character
import io.threethirtythree.ricknmorty.view.recyclerview.CharacterListAdapter.CharacterViewHolder

/** Adapter that binds our characters to viewholders in our recyclerview. */
class CharacterListAdapter : ListAdapter<Character, CharacterViewHolder>(CharacterComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val charImage: ImageView = itemView.findViewById(R.id.char_image)
        private val charName: TextView = itemView.findViewById(R.id.char_name)
        private val charSpecies: TextView = itemView.findViewById(R.id.char_species)
        private val charChip: Chip = itemView.findViewById(R.id.char_chip)
        fun bind(character: Character?) {
            character?.apply {
                charImage.setImageResource(R.drawable.ic_baseline_add_24)
                charName.text = name
                charSpecies.text = species
            }
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                val view: View = from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CharacterViewHolder(view)
            }
        }
    }

    class CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
    }
}