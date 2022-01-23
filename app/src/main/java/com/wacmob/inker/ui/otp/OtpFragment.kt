package com.wacmob.inker.ui.otp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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








@AndroidEntryPoint
class OtpFragment : Fragment() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler

    private val isFirstTime=true
    private val viewModel: OtpViewModel by viewModels()
    private val binding: FragmentOtpBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.firstPinView.isEnabled = false
        binding.firstPinView.isFocusable = false*/






    }
}