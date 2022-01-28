package com.wacmob.inker.ui.otp

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
import com.wacmob.inker.utils.showToast
import com.wacmob.inker.viewmodels.AuthViewModel
import com.wacmob.inker.viewmodels.OtpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.view.View.OnFocusChangeListener

import android.view.View.OnTouchListener
import com.wacmob.inker.ui.main.MainActivity


@AndroidEntryPoint
class OtpFragment : Fragment() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler
    lateinit var timer: CountDownTimer
    private val isFirstTime = true
    private val viewModel: OtpViewModel by viewModels()
    private val binding: FragmentOtpBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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

        startTimer(30000, 1000)
        binding.continueBtn.setOnClickListener {

            //binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),R.color.valid_border_color))
            Intent(requireContext(), MainActivity::class.java).apply {
                startActivity(this)

            }
            requireActivity().finish()

        }

        binding.change.setOnClickListener {

            binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                R.color.error_color))
        }
        binding.firstPinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0.toString().length == 6) {
                    binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                        R.color.valid_border_color))
                    /*  binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),
                          R.color.error_color))*/
                    timer.cancel()

                    Intent(requireContext(), MainActivity::class.java).apply {
                        startActivity(this)

                    }
                    requireActivity().finish()
                } else {
                    binding.firstPinView.setLineColor( ContextCompat.getColor(requireContext(),
                        R.color.borderDefault))
                }


            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })


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
}