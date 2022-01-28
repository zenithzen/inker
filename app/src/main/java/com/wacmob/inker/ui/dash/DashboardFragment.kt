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
    private var perfomanceList: MutableList<Perfomance> =  arrayListOf()
    private var recyclerBaseModelList: MutableList<RecyclerBaseModel> =  arrayListOf()
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
        adapter= RecyclerItemAdapter(requireContext())
        binding.dashData.setHasFixedSize(true)
        binding.dashData.adapter=adapter

        adapter.differ.submitList(recyclerBaseModelList)
        adapter.notifyDataSetChanged()

    }

    private fun setUpData() {
        levelDataList?.add(LevelData(1,R.drawable.level_item1))
        levelDataList?.add(LevelData(2,R.drawable.level_item2))
        levelDataList?.add(LevelData(3,R.drawable.level_item3))
        levelDataList?.add(LevelData(4,R.drawable.level_item4))
        levelDataList?.add(LevelData(5,R.drawable.level_item5))

        perfomanceList?.add(Perfomance("Badges", R.drawable.perform_item1, "150", "#FFE2EC"))
        perfomanceList?.add(Perfomance("Streak", R.drawable.perform_item1, "150", "#CEFFE5"))

        perfomanceList?.add(Perfomance("Shop", R.drawable.perform_item1, "150", "#FFE2EC"))
        perfomanceList?.add(Perfomance("1000 gal", R.drawable.perform_item1, "", "#CEFFE5"))

        recyclerBaseModelList?.add(RecyclerBaseModel(1, 1, "Performance", levelDataList, null))
        //recyclerBaseModelList?.add(RecyclerBaseModel(2, 2, "Performance", null, perfomanceList))
        recyclerBaseModelList?.add(RecyclerBaseModel(3, 1, "Performance", levelDataList, null))



    }
}