package com.wacmob.inker.ui.otp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


@AndroidEntryPoint
class OtpFragment : Fragment() {
    @Inject
    lateinit var preferenceHandler: PreferenceHandler


    private val viewModel: OtpViewModel by viewModels()
    private val binding: FragmentOtpBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  binding.firstPinView.onTextCha
        binding.firstPinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
              //  showToast(s.toString())
                if (s!=null && s.isNotEmpty())
                {
                    if (s.length==6)
                    {
                        binding.firstPinView.setLineColor(ContextCompat.getColor(requireActivity(),R.color.valid_border_color))
                    }else
                    {
                        binding.firstPinView.setLineColor(ContextCompat.getColor(requireActivity(),R.color.borderDefault))

                    }
                }

            }

            override fun afterTextChanged(s: Editable) {}
        })


    }
}