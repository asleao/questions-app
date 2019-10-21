package com.bliss.questionsapp.commons.healthcheck.ui.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bliss.questionsapp.R
import com.bliss.questionsapp.commons.healthcheck.viewmodel.HealthCheckViewModel

class HealthCheckFragment : Fragment() {

    companion object {
        fun newInstance() = HealthCheckFragment()
    }

    private lateinit var viewModel: HealthCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.health_check_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HealthCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
