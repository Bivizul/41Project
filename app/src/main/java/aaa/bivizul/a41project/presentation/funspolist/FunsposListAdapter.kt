package aaa.bivizul.a41project.presentation.funspolist

import aaa.bivizul.a41project.databinding.ItemFunsposBinding
import aaa.bivizul.a41project.domain.model.Funspos
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Funspos>() {
    override fun areItemsTheSame(oldItem: Funspos, newItem: Funspos) =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Funspos, newItem: Funspos) =
        oldItem == newItem
}

class FunsposListAdapter :
    ListAdapter<Funspos, FunsposListAdapter.FunsposViewHolder>(DIFF_CALLBACK) {

    inner class FunsposViewHolder(val binding: ItemFunsposBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunsposViewHolder {
        val binding = ItemFunsposBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FunsposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FunsposViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            textViewTitle.text = item.funspotit
            textViewDescription.text = item.funspodesc
        }
    }

}