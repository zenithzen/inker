package com.wacmob.inker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.R
import com.wacmob.inker.databinding.RecycleItem1Binding
import com.wacmob.inker.models.Badge
import com.wacmob.inker.models.LevelData

class LevelAdapter(val context: Context) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    inner class LevelViewHolder(binding: RecycleItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val item1Binding = binding
    }

    val differCallback = object : DiffUtil.ItemCallback<Badge>() {
        override fun areItemsTheSame(oldItem: Badge, newItem: Badge): Boolean {
            return oldItem.badge_image_full_url == newItem.badge_image_full_url

        }

        override fun areContentsTheSame(oldItem: Badge, newItem: Badge): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<Badge>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val recycleItem1Binding: RecycleItem1Binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycle_item1, parent, false)
        return LevelViewHolder(recycleItem1Binding)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val data = differ.currentList[position]
        val itemBinding = holder.item1Binding
        itemBinding.item = data

    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }
}