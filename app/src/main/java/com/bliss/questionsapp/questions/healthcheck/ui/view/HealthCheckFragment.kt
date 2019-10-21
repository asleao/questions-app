package com.bliss.questionsapp.questions.healthcheck.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.HealthCheckFragmentBinding
import com.bliss.questionsapp.questions.healthcheck.viewmodel.HealthCheckViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HealthCheckFragment : Fragment() {

    private val viewModel: HealthCheckViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<HealthCheckFragmentBinding>(
            inflater,
            R.layout.health_check_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}
