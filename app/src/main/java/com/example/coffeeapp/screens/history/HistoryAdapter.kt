package com.example.coffeeapp.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.database.CoffeeEntity
import com.example.coffeeapp.databinding.CoffeeHistoryListItemBinding

class HistoryAdapter : ListAdapter<CoffeeEntity, HistoryAdapter.ViewHolder>(CoffeeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    //ViewHolder
    class ViewHolder(private val binding: CoffeeHistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind each view widget to their respective view
        fun bind(current: CoffeeEntity) {
            binding.coffeeEntity = current
            binding.executePendingBindings()
        }

        // inflate layout from viewHolder
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CoffeeHistoryListItemBinding.inflate(
                    layoutInflater, parent, false,
                )
                return ViewHolder(binding)
            }
        }
    }
}

class CoffeeDiffCallBack : DiffUtil.ItemCallback<CoffeeEntity>() {
    override fun areItemsTheSame(oldItem: CoffeeEntity, newItem: CoffeeEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoffeeEntity, newItem: CoffeeEntity): Boolean {
        return oldItem == newItem
    }

}