package com.wacmob.inker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.R
import com.wacmob.inker.databinding.RecyclerBaseItemBinding
import com.wacmob.inker.models.RecyclerBaseModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.wacmob.inker.utils.hide
import com.wacmob.inker.utils.show


class RecyclerItemAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerItemAdapter.BaseViewHolder>() {

    inner class BaseViewHolder(binding: RecyclerBaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    val differCallback = object : DiffUtil.ItemCallback<RecyclerBaseModel>() {
        override fun areItemsTheSame(
            oldItem: RecyclerBaseModel,
            newItem: RecyclerBaseModel,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecyclerBaseModel,
            newItem: RecyclerBaseModel,
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<RecyclerBaseModel>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val recyclerBaseItemBinding: RecyclerBaseItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycler_base_item, parent, false)
        return BaseViewHolder(recyclerBaseItemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = differ.currentList[position]
        val binding = holder.itemBinding
        if (data.level == 2) {
            binding.title.show()
            binding.title.text = data.titleData


        } else {
            binding.baseRecyclerView.setHasFixedSize(true)
            binding.baseRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                true)
            val adapter = LevelAdapter(context)
            binding.baseRecyclerView.adapter = adapter
            adapter.differ.submitList(data.levelList)
            adapter.notifyDataSetChanged()
            binding.title.hide()
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}