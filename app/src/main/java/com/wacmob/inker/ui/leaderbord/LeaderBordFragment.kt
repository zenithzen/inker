package com.wacmob.inker.ui.leaderbord

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels

import com.wacmob.inker.databinding.FragmentLeaderBordBinding
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.viewmodels.LeaderBordViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.graphics.Color
import android.graphics.Typeface

import androidx.recyclerview.widget.LinearLayoutManager
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.listeners.RecyclerSelectorListener
import com.wacmob.inker.models.DataXXX
import com.wacmob.inker.ui.adapter.ClubListAdapter
import com.wacmob.inker.utils.*
import androidx.recyclerview.widget.RecyclerView
import com.wacmob.inker.models.Leaderboard
import com.wacmob.inker.ui.adapter.LeaderBordListAdapter


@AndroidEntryPoint
class LeaderBordFragment : Fragment(), View.OnClickListener, RecyclerSelectorListener {
    private val viewModel: LeaderBordViewModel by viewModels()


    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private lateinit var adapter: ClubListAdapter
    var clubList: ArrayList<DataXXX>? = null
    var bgColorList: ArrayList<String>? = arrayListOf()
    var textColorList: ArrayList<String>? = arrayListOf()

    lateinit var leaderBordList: ArrayList<Leaderboard>
    lateinit var leaderBoardAdapter: LeaderBordListAdapter

    var selectedPos = 0;
    val binding: FragmentLeaderBordBinding by viewBinding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bgColorList?.add("#AFD9FF")
        bgColorList?.add( "#D0FFE6")
        bgColorList?.add( "#F4D0FF")
        bgColorList?.add( "#FFDED0")
        textColorList?.add("#0094D5")
        textColorList?.add("#2F8D5B")
        textColorList?.add("#9F60B2")
        textColorList?.add("#AC6A4E")

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
        setUpRecyclerView()

        binding.clubList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                for (i in clubList?.indices!!) {
                    if (clubList?.get(i)?.isSelected == true) {
                        binding.clubList.post { adapter.notifyItemChanged(i) }
                    }
                }
            }
        })
        viewModel.getClubListLiveData.observe(viewLifecycleOwner, {

            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    //  println("@SUCCESS" + "YES"+it.data?.data?.size)
                    if (it?.data?.data != null) {
                        if (it?.data?.data.isNotEmpty()) {
                            clubList = arrayListOf()
                            clubList?.addAll(it?.data?.data)

                            viewModel.getLeaderBordData(preferenceHandler.userId)


                        }
                    }


                }
                BaseResult.Status.ERROR -> {
                    //println("@SUCCESS" + "ERR" + it.message)

                }

                BaseResult.Status.LOADING -> {
                    //println("@SUCCESS" + "LOADING")

                }
            }
        })

        viewModel.getLeaderBordData.observe(viewLifecycleOwner, {
            when (it.status) {
                BaseResult.Status.SUCCESS -> {

                    if (it?.data?.data != null) {
                        if (it?.data?.data?.leaderboard != null && it?.data?.data?.leaderboard.isNotEmpty()) {

                            try {
                                var selectedPos=0;
                                binding.leaderList.setHasFixedSize(true)
                                leaderBoardAdapter = LeaderBordListAdapter(requireContext(),
                                    preferenceHandler.userId.toInt(),bgColorList,textColorList)
                                binding.leaderList.adapter = leaderBoardAdapter
                                leaderBordList = arrayListOf()
                                leaderBordList.addAll(it?.data?.data?.leaderboard)
                                if (it?.data?.data?.club != null && clubList != null && clubList?.isNotEmpty() == true) {
                                    for (i in clubList?.indices!!) {
                                        clubList?.get(i)?.isSelected =
                                            clubList?.get(i)?.id == it?.data?.data?.club.id


                                      /*  if( clubList?.get(i)?.id == it?.data?.data?.club.id)
                                        {
                                            selectedPos=i
                                        }*/
                                    }
                                }

                                adapter.differ.submitList(clubList)
                                adapter.notifyDataSetChanged()
                               /* binding.clubList.scrollToPosition(selectedPos)*/

                                leaderBoardAdapter.differ.submitList(leaderBordList)
                                leaderBoardAdapter.notifyDataSetChanged()
                            } catch (e: Exception) {
                                println("@ERRXXR" + e.message)
                            }


                        }
                    }
                }

                BaseResult.Status.ERROR -> {
                    showToast("ERROR")

                }
                BaseResult.Status.LOADING -> {

                }
            }


        })

        viewModel.getClubList()

        try {
            /* binding.circularStatusView.setPortionsCount(3)
             binding.circularStatusView.setPortionColorForIndex(0,Color.parseColor("#E80000"))
             binding.circularStatusView.setPortionColorForIndex(1,Color.parseColor("#9F60B2"))
             binding.circularStatusView.setPortionColorForIndex(2,Color.parseColor("#00B17A"))*/

        } catch (e: Exception) {
            println("@EXP" + e.message)
        }


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

    private fun setUpRecyclerView() {
        binding.clubList.itemAnimator = null
        binding.clubList.setHasFixedSize(true)
        binding.clubList.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,
            false)

        /*binding.clubList.addItemDecoration(EqualSpacingItemDecoration(32,
            EqualSpacingItemDecoration.HORIZONTAL))*/
        adapter = ClubListAdapter(requireContext(), this)
        binding.clubList.adapter = adapter

    }


    override fun onClick(p0: View?) {

    }

    override fun onItemSelect(position: Int) {

        /* if (clubList != null && clubList?.isNotEmpty() == true) {
             for (i in clubList?.indices!!) {
                 clubList?.get(i)?.isSelected = i == position
             }
             if (position != selectedPos) {
                 clubList?.get(selectedPos)?.isSelected = false
                 adapter.differ.submitList(clubList)
                 adapter.notifyItemChanged(position)
                 adapter.notifyItemChanged(selectedPos)
             }
             if (position >= 2) {
                 // clubList?.size?.minus(1)?.let { binding.clubList.scrollToPosition(it) }
                 val sizeSpan = clubList?.size
                 if (sizeSpan != null && position < sizeSpan.minus(1)) {
                     binding.clubList.scrollToPosition(position + 1)
                 }
             }

             selectedPos = position
         }*/
    }
}