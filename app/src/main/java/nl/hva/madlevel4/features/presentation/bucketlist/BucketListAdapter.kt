package nl.hva.madlevel4.features.presentation.bucketlist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_item_row.view.*
import nl.hva.madlevel4.R
import nl.hva.madlevel4.features.data.models.BucketListItem

class BucketListAdapter(
        private val longClickListener: (BucketListItem) -> Unit
) : ListAdapter<BucketListItem, BucketListAdapter.ViewHolder>(BucketListItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), longClickListener)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: BucketListItem, longClickListener: (BucketListItem) -> Unit) = with(view) {
            checkBox.setOnClickListener {
                if (checkBox.isChecked) {
                    strikeTrough(view.textViewTitle)
                    strikeTrough(view.textViewDescription)
                } else {
                    reset(view.textViewTitle)
                    reset(view.textViewDescription)
                }
            }
            textViewTitle.text = item.title
            textViewDescription.text = item.description
            setOnLongClickListener {
                longClickListener(item)
                true
            }
        }

        private fun strikeTrough(textView: TextView) = with(textView) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        private fun reset(textView: TextView) = with(textView) {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    class BucketListItemDiffCallback : DiffUtil.ItemCallback<BucketListItem>() {
        override fun areItemsTheSame(oldItem: BucketListItem, newItem: BucketListItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: BucketListItem, newItem: BucketListItem): Boolean {
            return oldItem == newItem
        }
    }

}