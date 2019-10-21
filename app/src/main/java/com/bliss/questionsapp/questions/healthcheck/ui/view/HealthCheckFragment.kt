package com.bliss.questionsapp.questions.healthcheck.ui.view

import android.app.AlertDialog
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
            if (healthStatus.status == "OK") {
                val action =
                    HealthCheckFragmentDirections.actionHealthCheckFragmentToQuestionListFragment()
                findNavController().navigate(action)
            } else {
                viewModel.hasConnectionProblems(true)
            }
        })
    }

    private fun setupErrorObserver() {
        viewModel.error.observe(this, Observer { error ->
            viewModel.showLoading(false)
            AlertDialog.Builder(context)
                .setTitle(error.title)
                .setMessage(error.message)
                .setPositiveButton(R.string.dialog_ok, null)
                .create()
                .show()
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
        setupClickListeners(binding.iBaseLayout.btTryAgain)
        return binding.root
    }

    private fun setupClickListeners(btTryAgain: Button) {
        btTryAgain.setOnClickListener {
            viewModel.tryAgain()
        }
    }
}
