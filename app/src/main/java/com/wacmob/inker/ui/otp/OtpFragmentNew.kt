package com.wacmob.inker.ui.otp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.wacmob.inker.R
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.databinding.FragmentOtpBinding
import com.wacmob.inker.models.OtpSubmitRequest
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.ui.main.MainActivity
import com.wacmob.inker.utils.DialogUtils
import com.wacmob.inker.utils.findNavController
import com.wacmob.inker.utils.hide
import com.wacmob.inker.utils.show
import com.wacmob.inker.viewmodels.OtpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OtpFragmentNew : Fragment() {


    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private var mobileNumber: String? = null
    private var countryCode: String? = null
    private var userCode: String? = null
    lateinit var timer: CountDownTimer
    private var mProgressDialog: ProgressDialog? = null
    private val isFirstTime = true
    private val viewModel: OtpViewModel by viewModels()
    private val binding: FragmentOtpBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    timer.cancel()

                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        mobileNumber = arguments?.getString("number").toString()
        countryCode = arguments?.getString("country_code").toString()
        userCode = arguments?.getString("user_code").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mobileNumber.text = "$countryCode $mobileNumber"
        startTimer(30000, 1000)
        binding.continueBtn.setOnClickListener {

            //binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),R.color.valid_border_color))
            Intent(requireContext(), MainActivity::class.java).apply {
                startActivity(this)

            }
            requireActivity().finish()

        }


        viewModel.getOtpResponse.observe(viewLifecycleOwner, {

            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                  /*  hideLoading()
                    binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                        R.color.valid_border_color))*/
                    timer.cancel()
                    if (it?.data?.data != null && it?.data?.code == 200) {
                        preferenceHandler.mobileNumber = it?.data?.data?.user?.mobile.toString()
                        preferenceHandler.countryCode =
                            it?.data?.data?.user?.phone_country_code.toString()
                        preferenceHandler.userCode = it?.data?.data?.user?.user_code.toString()
                        preferenceHandler.userName = it?.data?.data?.user?.user_code.toString()
                        preferenceHandler.userToken = it?.data?.data?.access_token.toString()
                        preferenceHandler.isLogged = true



                        Intent(requireContext(), MainActivity::class.java).apply {
                            startActivity(this)

                        }
                        requireActivity().finish()
                    }

                }
                BaseResult.Status.ERROR -> {

                    hideLoading()
                  /*  binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                        R.color.error_color))*/
                    binding.errorText2.show()

                }
                BaseResult.Status.LOADING -> {
                    showLoading()

                }
            }

        })




        binding.change.setOnClickListener {
            timer.cancel()

            findNavController().navigateUp()
            /* binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                 R.color.error_color))*/
        }
   /*     binding.firstPinView.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.errorText2.isVisible)
                {
                    binding.errorText2.hide()
                }
                if (p0.toString().length == 6) {
                    *//* binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                         R.color.valid_border_color))
                     *//**//*  binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                          R.color.error_color))*//**//*
                    timer.cancel()

                    Intent(requireContext(), MainActivity::class.java).apply {
                        startActivity(this)

                    }
                    requireActivity().finish()*//*
                    viewModel.submitOtp(OtpSubmitRequest(p0.toString(),
                        userCode,
                        mobileNumber,
                        "91"))

                } else {
                 *//*   binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                        R.color.borderDefault))*//*
                }


            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })
*/

    }


    private fun startTimer(totalTime: Long, counterTime: Long) {
        timer = object : CountDownTimer(totalTime, counterTime) {

            override fun onTick(millisUntilFinished: Long) {
                val countTimer = millisUntilFinished / 1000

                if (countTimer < 10) {
                    binding.timer.text = "00:0" + millisUntilFinished / 1000

                } else {
                    binding.timer.text = "00:" + millisUntilFinished / 1000
                }


            }

            override fun onFinish() {
                binding.timer.text = "00:00"
            }
        }.start()

    }

    override fun onDestroyView() {
        // timer.cancel()
        super.onDestroyView()
    }

    override fun onDestroy() {

        super.onDestroy()


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