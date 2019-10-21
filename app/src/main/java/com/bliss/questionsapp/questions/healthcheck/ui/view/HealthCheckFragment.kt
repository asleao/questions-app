package com.bliss.questionsapp.questions.healthcheck.ui.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.HealthCheckFragmentBinding
import com.bliss.questionsapp.questions.healthcheck.viewmodel.HealthCheckViewModel

class HealthCheckFragment : Fragment() {

    private lateinit var viewModel: HealthCheckViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HealthCheckViewModel::class.java)
    }

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
