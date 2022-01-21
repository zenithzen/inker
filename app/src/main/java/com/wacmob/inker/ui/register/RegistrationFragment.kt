package com.wacmob.inker.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.wacmob.inker.R
import com.wacmob.inker.databinding.FragmentLoginBinding
import com.wacmob.inker.databinding.FragmentRegistrationBinding
import com.wacmob.inker.viewmodels.AuthViewModel
import com.wacmob.inker.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.wacmob.inker.utils.*


@AndroidEntryPoint
class RegistrationFragment : Fragment(), View.OnClickListener {
    private val viewModel: RegistrationViewModel by viewModels()
    private val binding: FragmentRegistrationBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLoginFailed = arguments?.getString("isLoginApiFailed")

        if (isLoginFailed=="true")
        {
            binding.errorLayout.show()
        }else{
            binding.errorLayout.hide()
        }
        binding.listener = this
        binding.firstName.setOnTouchListener(OnTouchListener { arg0, arg1 ->
            // if (binding.firstName.text.toString().isEmpty()) {
            binding.editTextLayout1.background = ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.edittext_valid_bg)

            binding.editTextLayout2.background = ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.edittext_default_bg)
            if (binding.firstName.text.toString().isNotEmpty()) {
                firstNameEditTextInAppValidation(binding.firstName.text.toString())
            }
            // }
            false
        })
        binding.mobileNumber.setOnTouchListener(OnTouchListener { arg0, arg1 ->
            //   if (binding.mobileNumber.text.toString().isEmpty()) {
            binding.editTextLayout2.background = ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.edittext_valid_bg)
            binding.editTextLayout1.background = ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.edittext_default_bg)
            if (binding.mobileNumber.text.toString().isNotEmpty()) {
                mobileNumberEditTextInAppValidation(binding.mobileNumber.text.toString())
            }
            //  }
            false
        })

        binding.mobileNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mobileNumberEditTextInAppValidation(s.toString())

            }
        })



        binding.firstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                firstNameEditTextInAppValidation(s.toString())
            }
        })
    }

    fun stringMatcher(string: String): Boolean {
        try {


            if (string.contains("_")) {
                val split: List<String> = string.split("_")
                if (split[0].isNotEmpty() && split[1].isNotEmpty()) {
                    return true
                } else {
                    false
                }
            } else {
                return false
            }
            return false
        } catch (e: Exception) {
            return false
            e.printStackTrace()
        }
    }

    fun firstNameEditTextInAppValidation(s: String) {
        if (s != null && s.isNotEmpty()) {
            if (stringMatcher(s.toString())) {
                binding.editTextLayout1.background =
                    ContextCompat.getDrawable(requireActivity(),
                        R.drawable.edittext_valid_bg)
                binding.errorText.hide()
            } else {
                binding.editTextLayout1.background =
                    ContextCompat.getDrawable(requireActivity(),
                        R.drawable.edittext_error_bg)
                binding.errorText.show()
            }
        } else {
            binding.editTextLayout1.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.edittext_valid_bg)
            binding.errorText.hide()
        }
    }

    fun mobileNumberEditTextInAppValidation(s: String) {
        if (s != null && s.isNotEmpty()) {
            if (isPhoneNumberValid(s.toString())) {
                binding.countryCode.show()
                binding.view.show()
                binding.editTextLayout2.background = ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.edittext_valid_bg)
                binding.errorText2.hide()
            } else {
                binding.countryCode.show()
                binding.view.show()
                binding.editTextLayout2.background = ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.edittext_error_bg)
                binding.errorText2.show()
            }
        } else {
            binding.countryCode.hide()
            binding.view.hide()
            binding.editTextLayout2.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.edittext_valid_bg)
            binding.errorText2.hide()
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.signInLayout -> {
                findNavController().navigateUp()

            }
        }
    }
}