package com.wacmob.inker.ui.leaderbord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.ImageView
import androidx.fragment.app.viewModels

import com.wacmob.inker.databinding.FragmentLeaderBordBinding
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.viewmodels.LeaderBordViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.R
import android.graphics.Color
import android.graphics.Typeface

import androidx.core.content.res.ResourcesCompat
import com.wacmob.inker.utils.TextDrawable
import com.wacmob.inker.utils.hide
import com.wacmob.inker.utils.show


@AndroidEntryPoint
class LeaderBordFragment : Fragment(), View.OnClickListener {
    private val viewModel: LeaderBordViewModel by viewModels()


    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    val binding: FragmentLeaderBordBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.wacmob.inker.R.layout.fragment_leader_bord, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this



        try {
            val density = resources.displayMetrics.density
            val tf = Typeface.createFromAsset(context?.assets, "bai_jamjuree_bold.ttf")

            binding.pic.setImageDrawable(TextDrawable.builder()
                .beginConfig()
                .textColor(Color.parseColor("#2F8D5B"))
                .useFont(tf)
                .fontSize(((17) * density).toInt())
                .toUpperCase()
                .endConfig()
                .buildRound("A",
                    Color.parseColor("#D0FFE6")))

        } catch (e: Exception) {
            println("@XD" + e.message)
        }


    }


    override fun onClick(p0: View?) {
        when (p0) {
            binding.bronzeLayout -> {
                binding.bronzeLayout.hide()
                binding.bronzeLayoutLarge.show()
                binding.silverLayoutLarge.hide()
                binding.silverLayout.show()
                binding.goldLayout.show()
                binding.goldLayoutLarge.hide()

            }

            binding.silverLayout -> {
                binding.bronzeLayout.show()
                binding.bronzeLayoutLarge.hide()
                binding.silverLayoutLarge.show()
                binding.silverLayout.hide()
                binding.goldLayout.show()
                binding.goldLayoutLarge.hide()

            }

            binding.goldLayout -> {
                binding.bronzeLayout.show()
                binding.bronzeLayoutLarge.hide()
                binding.silverLayoutLarge.hide()
                binding.silverLayout.show()
                binding.goldLayout.hide()
                binding.goldLayoutLarge.show()

            }
        }
    }
}