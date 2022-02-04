package com.wacmob.inker.ui.otp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.wacmob.inker.R
import com.wacmob.inker.databinding.FragmentLoginBinding
import com.wacmob.inker.databinding.FragmentOtpBinding
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.viewmodels.AuthViewModel
import com.wacmob.inker.viewmodels.OtpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.view.View.OnFocusChangeListener

import android.view.View.OnTouchListener
import android.widget.EditText
import com.wacmob.inker.ui.main.MainActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.listeners.AutoCheckListener
import com.wacmob.inker.models.OtpSubmitRequest
import com.wacmob.inker.utils.*


@AndroidEntryPoint
class OtpFragment : Fragment(), AutoCheckListener {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    private var mobileNumber: String? = null
    private var countryCode: String? = null
    private var userCode: String? = null
    lateinit var timer: CountDownTimer
    private var mProgressDialog: ProgressDialog? = null
    private val isFirstTime = true
    lateinit var rootView: View
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
        rootView = inflater.inflate(R.layout.fragment_otp, container, false)


        return rootView
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mobileNumber.text = "$countryCode $mobileNumber"
        startTimer(30000, 1000)

        setFocus(binding.eT1)
        setFocus(binding.eT2)
        setFocus(binding.eT3)
        setFocus(binding.eT4)
        setFocus(binding.eT5)
        setFocus(binding.eT6)
        binding.eT1.requestFocus()
        showKeyboard()


        binding.eT1.addTextChangedListener(GenericTextWatcher(binding.eT1, binding.eT2, this))
        binding.eT2.addTextChangedListener(GenericTextWatcher(binding.eT2, binding.eT3, this))
        binding.eT3.addTextChangedListener(GenericTextWatcher(binding.eT3, binding.eT4, this))
        binding.eT4.addTextChangedListener(GenericTextWatcher(binding.eT4, binding.eT5, this))
        binding.eT5.addTextChangedListener(GenericTextWatcher(binding.eT5, binding.eT6, this))
        binding.eT6.addTextChangedListener(GenericTextWatcher(binding.eT6, null, this))

        binding.eT1.setOnKeyListener(GenericKeyEvent(binding.eT1, null))
        binding.eT2.setOnKeyListener(GenericKeyEvent(binding.eT2, binding.eT1))
        binding.eT3.setOnKeyListener(GenericKeyEvent(binding.eT3, binding.eT2))
        binding.eT4.setOnKeyListener(GenericKeyEvent(binding.eT4, binding.eT3))
        binding.eT5.setOnKeyListener(GenericKeyEvent(binding.eT5, binding.eT4))
        binding.eT6.setOnKeyListener(GenericKeyEvent(binding.eT6, binding.eT5))

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

                    setSuccessBackground()
                    hideLoading()
                    /*   binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                           R.color.valid_border_color))*/
                    timer.cancel()
                    if (it?.data?.data != null && it?.data?.code == 200) {

                        preferenceHandler.mobileNumber = it?.data?.data?.user?.mobile.toString()
                        preferenceHandler.countryCode =
                            it?.data?.data?.user?.phone_country_code.toString()
                        preferenceHandler.userCode = it?.data?.data?.user?.user_code.toString()
                        preferenceHandler.userName = it?.data?.data?.user?.user_code.toString()
                        preferenceHandler.userToken = it?.data?.data?.access_token.toString()
                        println("@USERID" + it?.data?.data?.user?.id.toString())
                        preferenceHandler.userId = it?.data?.data?.user?.id.toString()
                        preferenceHandler.isLogged = true



                        Intent(requireContext(), MainActivity::class.java).apply {
                            startActivity(this)

                        }
                        requireActivity().finish()
                    }

                }
                BaseResult.Status.ERROR -> {

                    setErrorBackground()
                    hideLoading()
                    // binding.eT6.requestFocus()
                    // showKeyboard()
                    /*   binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
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
        /*   binding.firstPinView.addTextChangedListener(object : TextWatcher
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
                    binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                        R.color.borderDefault))
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
        hideKeyboard()
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


    class GenericTextWatcher internal constructor(
        private val currentView: View,
        private val nextView: View?, private val listener: AutoCheckListener,
    ) : TextWatcher {

        override fun afterTextChanged(editable: Editable) {

            val text = editable.toString()


            when (currentView.id) {
                R.id.eT1 -> if (text.length == 1) nextView?.requestFocus()
                R.id.eT2 -> if (text.length == 1) nextView?.requestFocus()
                R.id.eT3 -> if (text.length == 1) nextView?.requestFocus()
                R.id.eT4 -> if (text.length == 1) nextView?.requestFocus()
                R.id.eT5 -> if (text.length == 1) nextView?.requestFocus()

            }
            println("@ENTER" + "afterTextChanged")
            listener.checkAllFieldsFilled()
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int,
        ) {
            println("@ENTER" + "beforeTextChanged")
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int,
        ) {
            println("@ENTER" + "onTextChanged")

        }

    }

    class GenericKeyEvent internal constructor(
        private val currentView: EditText,
        private val previousView: EditText?,
    ) : View.OnKeyListener {
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {

            if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.eT1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView?.text = null
                previousView?.requestFocus()
                return true
            }
            return false
        }


    }

    override fun checkAllFieldsFilled() {
        if (binding.errorText2.isVisible) {
            binding.errorText2.hide()
        }
        if (isOtpViewFilled()) {
            hideKeyboard()
            clearFocus()
            val otpString =
                binding.eT1.text.toString() + binding.eT2.text.toString() + binding.eT3.text.toString() + binding.eT4.text.toString() + binding.eT5.text.toString() + binding.eT6.text.toString()
            viewModel.submitOtp(OtpSubmitRequest(otpString,
                userCode,
                mobileNumber,
                "91"))
        }
    }


    fun clearFocus() {
        binding.eT1.clearFocus()
        binding.eT2.clearFocus()
        binding.eT3.clearFocus()
        binding.eT4.clearFocus()
        binding.eT5.clearFocus()
        binding.eT6.clearFocus()
    }

    private fun setFocus(view: EditText) {
        view.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setSelectorBackground()


            }
        }
    }


    private fun setSelectorBackground() {
        binding.eT1.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
        binding.eT2.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
        binding.eT3.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
        binding.eT4.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
        binding.eT5.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
        binding.eT6.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.otp_selector)
    }

    private fun setSuccessBackground() {
        binding.eT1.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
        binding.eT2.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
        binding.eT3.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
        binding.eT4.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
        binding.eT5.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
        binding.eT6.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_yellow_box)
    }


    private fun setErrorBackground() {
        binding.eT1.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
        binding.eT2.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
        binding.eT3.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
        binding.eT4.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
        binding.eT5.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
        binding.eT6.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.drawable_error_box)
    }


    fun isOtpViewFilled(): Boolean {
        if (binding.eT1.text.toString().isEmpty()) {
            return false
        } else if (binding.eT2.text.toString().isEmpty()) {
            return false
        } else if (binding.eT3.text.toString().isEmpty()) {
            return false
        } else if (binding.eT4.text.toString().isEmpty()) {
            return false
        } else if (binding.eT5.text.toString().isEmpty()) {
            return false
        } else if (binding.eT6.text.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }


    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }
}