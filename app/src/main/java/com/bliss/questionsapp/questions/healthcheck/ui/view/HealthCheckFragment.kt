package com.bliss.questionsapp.questions.healthcheck.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.HealthCheckFragmentBinding
import com.bliss.questionsapp.questions.healthcheck.viewmodel.HealthCheckViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HealthCheckFragment : Fragment() {

    private val viewModel: HealthCheckViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        setupHealthObserver()
        setupErrorObserver()
    }

    private fun setupHealthObserver() {
        viewModel.health.observe(this, Observer { healthStatus ->
            viewModel.showLoading(false)
            if (healthStatus.status == resources.getString(R.string.status_ok)) {
                val action =
                    HealthCheckFragmentDirections.actionHealthCheckFragmentToQuestionListFragment()
                findNavController().navigate(action)
            } else {
                viewModel.hasConnectionProblems(true)
                viewModel.changeErrorMessage(resources.getString(R.string.health_problem))
            }
        })
    }

    private fun setupErrorObserver() {
        viewModel.error.observe(this, Observer { error ->
            viewModel.showLoading(false)
            viewModel.hasConnectionProblems(true)
            viewModel.showLoading(false)
            viewModel.changeErrorMessage("${error.title}\n\n${error.message}")
        })
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
        setupTryAgainClickListener(binding.iBaseLayout.btTryAgain)
        return binding.root
    }

    private fun setupTryAgainClickListener(btTryAgain: Button) {
        btTryAgain.setOnClickListener {
            viewModel.tryAgain()
        }
    }
}
