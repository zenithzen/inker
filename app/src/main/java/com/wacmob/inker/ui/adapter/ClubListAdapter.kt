package com.wacmob.inker.ui.adapter

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.R
import com.wacmob.inker.databinding.ItemClubListBinding
import com.wacmob.inker.listeners.RecyclerSelectorListener
import com.wacmob.inker.models.DataXXX
import com.wacmob.inker.utils.hide
import com.wacmob.inker.utils.show

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.wacmob.inker.utils.px
import android.graphics.ColorMatrixColorFilter
import androidx.compose.ui.graphics.Color


class ClubListAdapter(val context: Context, val listener: RecyclerSelectorListener) :
    RecyclerView.Adapter<ClubListAdapter.ClubViewHolder>() {

    inner class ClubViewHolder(binding: ItemClubListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val itemBinding = binding


    }

    val differCallback = object : DiffUtil.ItemCallback<DataXXX>() {
        override fun areItemsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<DataXXX>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val itemClubListBinding: ItemClubListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                com.wacmob.inker.R.layout.item_club_list, parent, false)
        return ClubViewHolder(itemClubListBinding)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val data = differ.currentList[position]

        // holder.setIsRecyclable(false)
        val itemBinding = holder.itemBinding
        itemBinding.normalLayout.show()
        itemBinding.largeLayout.hide()
        if (data.isSelected) {
            setUnlocked(itemBinding.bronzItem)

            /*  holder.itemBinding.bronzItem.requestLayout()
              holder.itemBinding.bronzItem.layoutParams.width = 68.px
              holder.itemBinding.bronzItem.layoutParams.height = 66.px
              holder.itemBinding.bronzItem.scaleType = ImageView.ScaleType.FIT_XY*/
            println("@SEL" + "YES-" + position)
            val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv)
            itemBinding.root.startAnimation(anim)
            anim.fillAfter = true

            itemBinding.bronzText.setTextColor(context.resources.getColor(R.color.black))
            /* itemBinding.root.requestFocus()
             itemBinding.normalLayout.hide()
             itemBinding.largeLayout.show()*/

        } else {
            setLocked(itemBinding.bronzItem)
            itemBinding.bronzText.setTextColor(context.resources.getColor(R.color.default_color))
            /* holder.itemBinding.bronzItem.requestLayout()
             holder.itemBinding.bronzItem.layoutParams.width = 52.px
             holder.itemBinding.bronzItem.layoutParams.height = 48.px
             holder.itemBinding.bronzItem.scaleType = ImageView.ScaleType.FIT_XY*/
            val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.scale_out_tv)
            itemBinding.root.startAnimation(anim)
            anim.fillAfter = true

            /*  itemBinding.normalLayout.show()
              itemBinding.largeLayout.hide()*/
        }
        /*  if (position!=0) {
              itemBinding.bronzItem.getDrawable()
                  .setColorFilter(0x76ffffff, PorterDuff.Mode.MULTIPLY)
          }*/

        /*  itemBinding.root.setOnFocusChangeListener { focusedView, hasFocus ->

              if (hasFocus) {
                  // run scale animation and make it bigger
                  val anim: Animation =
                      AnimationUtils.loadAnimation(context, com.wacmob.inker.R.anim.scale_in_tv)
                  itemBinding.root.startAnimation(anim)
                  anim.fillAfter = true
              } else {
                  // run scale animation and make it smaller
                  val anim: Animation =
                      AnimationUtils.loadAnimation(context, com.wacmob.inker.R.anim.scale_out_tv)
                  itemBinding.root.startAnimation(anim)
                  anim.fillAfter = true
              }

          }*/


        itemBinding.item = data
        itemBinding.position = position
        itemBinding.listener = listener

      //  println("@XX"+position+"--"+itemBinding.bronzItem.measuredWidth+"--"+itemBinding.bronzItem.measuredHeight)

    }

    fun setLocked(v: ImageView) {
        val matrix = ColorMatrix()
        matrix.setSaturation(0F)
        val cf = ColorMatrixColorFilter(matrix)
        v.colorFilter = cf
        v.imageAlpha = 128
    }

    fun setUnlocked(v: ImageView) {
        v.colorFilter = null
        v.imageAlpha = 255
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}