package com.wacmob.inker.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.R
import com.wacmob.inker.databinding.RecycleItem2Binding
import com.wacmob.inker.databinding.RecyclerItem3Binding
import com.wacmob.inker.models.Perfomance

/*
class SettingAdapter {
}*/


class SettingAdapter(val context: Context) :
    RecyclerView.Adapter<SettingAdapter.ViewHolderNew>() {

    inner class ViewHolderNew(binding: RecyclerItem3Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    val differCallback = object : DiffUtil.ItemCallback<Perfomance>() {
        override fun areItemsTheSame(oldItem: Perfomance, newItem: Perfomance): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Perfomance, newItem: Perfomance): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Perfomance>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNew {
        val recycleItem2Binding: RecyclerItem3Binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycler_item_3, parent, false)
        return ViewHolderNew(recycleItem2Binding)
    }

    override fun onBindViewHolder(holder: ViewHolderNew, position: Int) {
        println("@DATA"+"ENTER")
        val data = differ.currentList[position]
        val itemBinding = holder.itemBinding
        itemBinding.item = data
        itemBinding.mainLayout.background.setColorFilter(Color.parseColor(data.color), PorterDuff.Mode.SRC_OVER)

        if (position %2!=0)
        {


            // itemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.reycler_item_bg2))
        }else
        {
            //  itemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.recycler_item_bg1))
        }
    }

    override fun getItemCount(): Int {
        println("@DATA"+differ.currentList.size)
        return differ.currentList.size
    }


}