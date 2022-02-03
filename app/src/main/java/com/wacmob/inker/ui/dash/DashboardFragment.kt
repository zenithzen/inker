package com.wacmob.inker.ui.dash

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.compose.ui.text.toUpperCase
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.wacmob.inker.R
import com.wacmob.inker.databinding.FragmentDashboardBinding
import com.wacmob.inker.models.LevelData
import com.wacmob.inker.models.Perfomance
import com.wacmob.inker.models.RecyclerBaseModel
import com.wacmob.inker.ui.adapter.RecyclerItemAdapter
import com.wacmob.inker.viewmodels.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.ui.adapter.LevelAdapter
import com.wacmob.inker.utils.*
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : Fragment(), View.OnClickListener {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private val binding: FragmentDashboardBinding by viewBinding()
    private var mProgressDialog: ProgressDialog? = null
    private val viewModel: DashBoardViewModel by viewModels()
    private var levelDataList: MutableList<LevelData> = arrayListOf()
    private var perfomanceList: MutableList<Perfomance> = arrayListOf()
    private var settingList: MutableList<Perfomance> = arrayListOf()
    private var recyclerBaseModelList: MutableList<RecyclerBaseModel> = arrayListOf()
    lateinit var adapter: LevelAdapter


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
        binding.listener = this

        viewModel.getDashBordData.observe(viewLifecycleOwner, {
            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    hideLoading()
                    if (it?.data?.data != null) {

                        val data = it?.data?.data
                        if (data.badges_count != null) {
                            binding.count.text = data.badges_count.toString()
                            binding.badgeLayout.show()
                        } else {
                            binding.badgeLayout.hide()
                        }

                        if (data.streak_count != null) {
                            binding.steakcount.text = data.streak_count.toString()
                            binding.steakLayout.show()
                        } else {
                            binding.steakLayout.hide()
                        }
                        if (data.total_points != null) {
                            binding.emptyTitle.text = data.total_points.toString() + " gal"
                            binding.noTitleLayout.show()
                        } else {
                            binding.noTitleLayout.hide()
                        }


                        if (data.total_cues != null && data.completed_cues_count != null) {
                            binding.cueProgressBar.progressMax = data.total_cues?.toFloat()!!

                            binding.cueProgressBar.progress = data.completed_cues_count?.toFloat()!!
                            binding.cueSubTitle.text = data.completed_cues_count.toString()
                            binding.cueProgressBar.show()
                            binding.cueSubTitle.show()
                        } else {
                            binding.cueProgressBar.hide()
                            binding.cueSubTitle.hide()
                        }

                      if (data.name!=null && data.name.isNotEmpty())
                      {
                          binding.firstLetter.text= data.name.toString()[0].toString().toUpperCase()
                      }

                        if (data.total_flashcards != null && data.completed_flashcards_count != null) {
                            binding.flashProgressBar.show()
                            binding.flashSubTitle.show()
                            binding.flashProgressBar.progressMax =
                                data.total_flashcards?.toFloat()!!
                            binding.flashProgressBar.progress =
                                data.completed_flashcards_count?.toFloat()!!
                            binding.flashSubTitle.text = data.completed_flashcards_count.toString()
                        } else {
                            binding.flashProgressBar.hide()
                            binding.flashSubTitle.hide()
                        }

                        if (data.concept_learned != null) {
                            binding.mechSubTitle.show()
                            binding.mechImage.show()
                            binding.mechSubTitle.text = data.concept_learned.type.toString()

                            Glide.with(requireActivity())
                                .load(data.concept_learned.skill_icon_full_url)
                                .placeholder(R.drawable.placeholder)
                                .timeout(60000)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .error(R.drawable.placeholder)
                                .into(binding.mechImage)
                        } else {
                            binding.mechSubTitle.hide()
                            binding.mechImage.hide()
                        }

                        if (data.badges != null && data.badges.isNotEmpty()) {
                            binding.recyclerDivider.show()
                            binding.dashData.show()
                            adapter = LevelAdapter(requireContext())
                            binding.dashData.setHasFixedSize(true)
                            binding.dashData.layoutManager = LinearLayoutManager(requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false)
                            binding.dashData.adapter = adapter

                            adapter.differ.submitList(data.badges)
                            adapter.notifyDataSetChanged()
                        } else {
                            binding.recyclerDivider.hide()
                            binding.dashData.hide()
                        }
                        if (data.total_points != null) {
                            binding.points.show()
                            binding.gamePoints.text = data.total_points.toString()
                        } else {
                            binding.points.hide()
                        }
                        if (data.hours_spent_on_cue != null) {
                            binding.hours.show()
                            binding.timeSubTitle.show()
                            binding.hours.text = data.hours_spent_on_cue.toString()
                        } else {
                            binding.timeSubTitle.hide()
                            binding.hours.hide()
                        }
                        binding.mainLayout.show()


                    }



                }
                BaseResult.Status.ERROR -> {
                    hideLoading()

                }
                BaseResult.Status.LOADING -> {

                    showLoading()


                }
            }

        })
        viewModel.getDashBordData(preferenceHandler.userId)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {


    }

    private fun setUpData() {


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

    override fun onClick(view: View?) {
        when (view) {
            binding.points -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_leaderBordFragment2)
            }
        }
    }


    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
            mProgressDialog?.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = DialogUtils.showLoadingDialog(requireContext())
    }
}