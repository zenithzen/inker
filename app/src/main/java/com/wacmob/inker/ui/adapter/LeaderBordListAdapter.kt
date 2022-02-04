package com.wacmob.inker.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.R
import com.wacmob.inker.databinding.ItemLeaderboardListBinding
import com.wacmob.inker.models.Leaderboard
import com.wacmob.inker.utils.TextDrawable
import com.wacmob.inker.utils.invisible
import com.wacmob.inker.utils.show


class LeaderBordListAdapter(
    val context: Context,
    val userId: Int,
    val bgColorList: ArrayList<String>?,
   val textColorList: ArrayList<String>?
) :
    RecyclerView.Adapter<LeaderBordListAdapter.LeaderBordViewHolder>() {
    var counter: Int = 0;

    inner class LeaderBordViewHolder(binding: ItemLeaderboardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding

    }

    val differCallback = object : DiffUtil.ItemCallback<Leaderboard>() {
        override fun areItemsTheSame(oldItem: Leaderboard, newItem: Leaderboard): Boolean {
            return oldItem.profile_id == newItem.profile_id
        }

        override fun areContentsTheSame(oldItem: Leaderboard, newItem: Leaderboard): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Leaderboard>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LeaderBordListAdapter.LeaderBordViewHolder {
        val itemLeaderboardListBinding: ItemLeaderboardListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_leaderboard_list, parent, false)
        return LeaderBordViewHolder(itemLeaderboardListBinding)
    }

    override fun onBindViewHolder(
        holder: LeaderBordListAdapter.LeaderBordViewHolder,
        position: Int,
    ) {
       /* println("@COUNTER$counter")*/

println("@COUNTER"+counter+"--"+textColorList?.get(counter)+"---"+bgColorList?.get(counter))
        val data = differ.currentList[position]
        val itemBinding = holder.itemBinding
        val tf2 = Typeface.createFromAsset(context?.assets, "bai_jamjuree_bold.ttf")
        try {
            val density = context.resources.displayMetrics.density
            val tf = Typeface.createFromAsset(context?.assets, "titillium_web_bold.ttf")

            val firstLetter = data.name?.get(0).toString()
            itemBinding.firstLetter.setImageDrawable(TextDrawable.builder()
                .beginConfig()
                .textColor(Color.parseColor(textColorList?.get(counter)))
                .useFont(tf)
                .fontSize(((20) * density).toInt())
                .toUpperCase()
                .endConfig()
                .buildRound(firstLetter,
                    Color.parseColor(bgColorList?.get(counter))))

        } catch (e: Exception) {
            println("@XD" + e.message)
        }

        if (data.profile_id == userId) {
            itemBinding.mainLayout.background =
                ContextCompat.getDrawable(context, R.drawable.drawable_leaderboard)
            itemBinding.name.typeface = tf2
            itemBinding.points.typeface = tf2
        }

        when (data.position) {
            1 -> {
                itemBinding.rankMedal.show()
                itemBinding.rank.invisible()
                itemBinding.rankMedal.setImageResource(R.drawable.first_medal)
            }
            2 -> {
                itemBinding.rankMedal.show()
                itemBinding.rank.invisible()
                itemBinding.rankMedal.setImageResource(R.drawable.second)
            }
            3 -> {
                itemBinding.rankMedal.show()
                itemBinding.rank.invisible()
                itemBinding.rankMedal.setImageResource(R.drawable.third_medal)
            }
            else -> {
                itemBinding.rankMedal.invisible()
                itemBinding.rank.show()
                itemBinding.rank.text = data.position.toString()
            }
        }
        itemBinding.item = data

        if (counter < 3) {
            counter += 1
        } else {
            counter = 0
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}