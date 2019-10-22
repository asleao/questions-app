package com.bliss.questionsapp.questions.details.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionDetailFragmentBinding
import com.bliss.questionsapp.questions.details.ui.adapters.ChoicesAdapter
import com.bliss.questionsapp.questions.details.viewmodel.QuestionDetailViewModel
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionDetailFragment : Fragment() {

    private val viewModel: QuestionDetailViewModel by viewModel()
    private val args by navArgs<QuestionDetailFragmentArgs>()
    private lateinit var binding: QuestionDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getKoin().setProperty(QUESTIONDETAIL_ARGUMENT, args.questionId)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.question.observe(this, Observer { question ->
            binding.rvChoices.adapter = ChoicesAdapter(question.choices) { choice ->
                Toast.makeText(requireContext(), choice.title, Toast.LENGTH_LONG).show()
            }
        })
        setupErrorObserver()
    }

    private fun setupErrorObserver() {
        viewModel.error.observe(this, Observer { error ->
            viewModel.showLoading(false)
            viewModel.hasConnectionProblems(true)
            viewModel.showLoading(false)
            viewModel.changeErrorMessage("${error.title}\n${error.message}")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.question_detail_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupTryAgainClickListener(binding.iBaseLayout.btTryAgain)
        setupRecyclerView(binding.rvChoices)
        return binding.root
    }

    private fun setupRecyclerView(rvChoices: RecyclerView) {
        val layoutManager = LinearLayoutManager(requireContext())
        val divider = DividerItemDecoration(
            rvChoices.context,
            layoutManager.orientation
        )
        rvChoices.layoutManager = layoutManager
        rvChoices.addItemDecoration(divider)
    }

    private fun setupTryAgainClickListener(btTryAgain: Button) {
        btTryAgain.setOnClickListener {
            viewModel.tryAgain()
        }
    }

    companion object {
        const val QUESTIONDETAIL_ARGUMENT = "QUESTIONDETAIL_ARGUMENT"
    }

}
