package com.wacmob.inker.ui.otp

import android.content.Intent
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
import com.wacmob.inker.ui.main.MainActivity


@AndroidEntryPoint
class OtpFragment : Fragment() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler

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
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueBtn.setOnClickListener {

            //binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),R.color.valid_border_color))
            Intent(requireContext(),MainActivity::class.java).apply {
                startActivity(this)

            }
            requireActivity().finish()

        }

          binding.change.setOnClickListener {

            binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),R.color.error_color))
        }
       binding.firstPinView.addTextChangedListener(object :TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                   binding.firstPinView.setLineColor(ContextCompat.getColor(requireContext(),R.color.borderDefault))


           }

           override fun afterTextChanged(p0: Editable?) {

           }


       })


    }
}