package com.wacmob.inker.ui.dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.wacmob.inker.R
import com.wacmob.inker.databinding.FragmentDashboardBinding
import com.wacmob.inker.models.LevelData
import com.wacmob.inker.models.Perfomance
import com.wacmob.inker.models.RecyclerBaseModel
import com.wacmob.inker.ui.adapter.RecyclerItemAdapter
import com.wacmob.inker.utils.showToast
import com.wacmob.inker.viewmodels.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val binding: FragmentDashboardBinding by viewBinding()
    private val viewModel: DashBoardViewModel by viewModels()
    private var levelDataList: MutableList<LevelData> = arrayListOf()
    private var perfomanceList: MutableList<Perfomance> = arrayListOf()
    private var settingList: MutableList<Perfomance> = arrayListOf()
    private var recyclerBaseModelList: MutableList<RecyclerBaseModel> = arrayListOf()
    lateinit var adapter: RecyclerItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = RecyclerItemAdapter(requireContext())
        binding.dashData.setHasFixedSize(true)
        binding.dashData.adapter = adapter

        adapter.differ.submitList(recyclerBaseModelList)
        adapter.notifyDataSetChanged()

    }

    private fun setUpData() {
        binding.cueProgressBar.progressMax= 100F
        binding.cueProgressBar.progress=34F
        binding.flashProgressBar.progressMax= 100F
        binding.flashProgressBar.progress=34F

        levelDataList?.add(LevelData(1, R.drawable.level_item4))
        levelDataList?.add(LevelData(2, R.drawable.level_item2))
        levelDataList?.add(LevelData(3, R.drawable.level_item5))
        levelDataList?.add(LevelData(4, R.drawable.level_item4))
        levelDataList?.add(LevelData(5, R.drawable.level_item5))
       /* levelDataList?.add(LevelData(6, R.drawable.level_item5))
        levelDataList?.add(LevelData(7, R.drawable.level_item5))
        levelDataList?.add(LevelData(8, R.drawable.level_item5))
        levelDataList?.add(LevelData(9, R.drawable.level_item5))*/

        perfomanceList?.add(Perfomance(1, "Badges", R.drawable.perform_item1, "150", "#FFE2EC"))
        perfomanceList?.add(Perfomance(2, "Streak", R.drawable.perform_item2, "150", "#CEFFE5"))

        perfomanceList?.add(Perfomance(3, "Shop", R.drawable.perform_item3, "150", "#FFE2EC"))
        perfomanceList?.add(Perfomance(4, "1000 gal", R.drawable.perform_item4, "", "#CEFFE5"))
        settingList?.add(Perfomance(5, "Video Playlist", R.drawable.setting_1, "", "#FFDEB1"))
        settingList?.add(Perfomance(6, "Preference Settings", R.drawable.setting2, "", "#B9F5FF"))
        settingList?.add(Perfomance(7, "Certificates", R.drawable.setting2, "", "#DEDEDE"))



        recyclerBaseModelList?.add(RecyclerBaseModel(1, 1, "Performance", levelDataList, null))
      /*  recyclerBaseModelList?.add(RecyclerBaseModel(2, 2, "Performance", null, perfomanceList))
        recyclerBaseModelList?.add(RecyclerBaseModel(3, 1, "Performance", levelDataList, null))
        recyclerBaseModelList?.add(RecyclerBaseModel(4, 2, "Performance", null, perfomanceList))
        recyclerBaseModelList?.add(RecyclerBaseModel(8, 1, "Performance", levelDataList, null))
        recyclerBaseModelList?.add(RecyclerBaseModel(9, 3, "Performance", null, settingList))*/


    }
}