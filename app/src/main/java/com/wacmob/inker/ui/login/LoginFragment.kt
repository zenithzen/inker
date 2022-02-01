package com.wacmob.inker.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.util.Util
import com.wacmob.inker.R
import com.wacmob.inker.databinding.FragmentLoginBinding
import com.wacmob.inker.preferences.PreferenceHandler
import com.wacmob.inker.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService
import android.app.Activity
import android.app.ProgressDialog

import android.view.WindowManager
import android.widget.EditText
import androidx.lifecycle.Observer
import com.wacmob.inker.baseresult.BaseResult
import com.wacmob.inker.models.LoginRequest
import com.wacmob.inker.models.LoginResponse
import com.wacmob.inker.ui.auth.AuthActivity
import com.wacmob.inker.utils.*
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    @Inject
    lateinit var stylishToastyUtils: StylishToastyUtils
    private var mProgressDialog: ProgressDialog? = null
    private var navController: NavController? = null

    private val viewModel: AuthViewModel by viewModels()
    private val binding: FragmentLoginBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this

        viewModel.getLoginData.observe(viewLifecycleOwner,
            Observer { it: BaseResult<LoginResponse> ->
                when (it.status) {
                    BaseResult.Status.SUCCESS -> {
                        hideLoading()

                        if (it?.data?.data != null) {

                            val bundle: Bundle =
                                bundleOf("number" to binding.mobileNumber.text.toString(),
                                    "country_code" to "+91",
                                    "user_code" to it.data?.data?.user_code)


                            findNavController().navigate(R.id.action_loginFragment_to_otpFragment,
                                bundle)
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

        binding.mobileNumber.setOnTouchListener(View.OnTouchListener { arg0, arg1 ->
            if (binding.mobileNumber.text.toString().isEmpty()) {
                binding.countryCode.hide()
                binding.view.hide()
                binding.editTextLayout.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.edittext_valid_bg)
                binding.errorText.hide()
            }
            false
        })
        binding.mobileNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {

                    /* if (isPhoneNumberValid(s.toString())) {*/
                    binding.countryCode.show()
                    binding.view.show()
                    binding.editTextLayout.background = ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.edittext_valid_bg)
                    binding.errorText.hide()
                    /* } else {
                         binding.countryCode.show()
                         binding.view.show()
                         binding.editTextLayout.background = ContextCompat.getDrawable(
                             requireActivity(),
                             R.drawable.edittext_error_bg)
                         binding.errorText.show()
                     }*/
                } else {
                    binding.countryCode.hide()
                    binding.view.hide()
                    binding.editTextLayout.background =
                        ContextCompat.getDrawable(requireActivity(), R.drawable.edittext_valid_bg)
                    binding.errorText.hide()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (binding.mobileNumber.text.toString().isNotEmpty()) {
            binding.mobileNumber.requestFocus()
            showKeyboard()
        }

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.signUpLayout -> {
                var bundle = bundleOf("isLoginApiFailed" to "false")
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment,
                    bundle)

            }
            binding.continueBtn -> {
                hideKeyboard()
                //viewModel.getLogin()

                if (binding.mobileNumber.text.toString()
                        .isNotEmpty() && isPhoneNumberValid(binding.mobileNumber.text.toString())
                ) {
                    //  findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
                    viewModel.getLogin(LoginRequest(binding.mobileNumber.text.toString(),
                        resources.getString(R.string.country_code2)))
                } else {

                    binding.countryCode.show()
                    binding.view.show()
                    binding.editTextLayout.background = ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.edittext_error_bg)
                    binding.errorText.show()
                }
            }
        }

    }

/*    fun hideKeyboard(context: Context) {
        try {
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            if (context.currentFocus != null && context.currentFocus!!
                    .windowToken != null
            ) {
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    context.currentFocus!!.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }*/

    fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
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

}