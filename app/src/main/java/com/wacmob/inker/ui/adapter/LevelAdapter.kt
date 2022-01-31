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
import com.wacmob.inker.models.LevelData

class LevelAdapter(val context: Context) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    inner class LevelViewHolder(binding: RecycleItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val item1Binding = binding
    }

    val differCallback = object : DiffUtil.ItemCallback<LevelData>() {
        override fun areItemsTheSame(oldItem: LevelData, newItem: LevelData): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: LevelData, newItem: LevelData): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<LevelData>) {
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
        data.images?.let { itemBinding.cornerIV.setImageResource(it) }
    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }
}