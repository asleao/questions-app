package com.bliss.questionsapp.questions.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionListFragmentBinding
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.bliss.questionsapp.questions.list.ui.adapters.QuestionsAdapter
import com.bliss.questionsapp.questions.list.viewmodel.QuestionListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionListFragment : Fragment() {

    private val viewModel: QuestionListViewModel by viewModel()
    private lateinit var binding: QuestionListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }
    override fun onResume() {
        super.onResume()
        viewModel.questions.value?.let { questions ->
            setupQuestionsList(questions)
        }
    }

    private fun setupObservers() {
        setupQuestionsObserver()
        setupErrorObserver()
    }

    private fun setupQuestionsObserver() {
        viewModel.questions.observe(this, Observer { questions ->
            setupQuestionsList(questions)
        })
    }

    private fun setupQuestionsList(questions: List<QuestionResponse>) {
        binding.rvQuestions.adapter = QuestionsAdapter(questions) { question ->
            val action =
                QuestionListFragmentDirections.actionQuestionListFragmentToQuestionDetailFragment(
                    question.id
                )
            findNavController().navigate(action)
        }
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.question_list_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupRecyclerView(binding.rvQuestions)
        return binding.root
    }

    private fun setupRecyclerView(rvQuestions: RecyclerView) {
        val layoutManager = LinearLayoutManager(requireContext())
        val divider = DividerItemDecoration(
            rvQuestions.context,
            layoutManager.orientation
        )
        rvQuestions.layoutManager = layoutManager
        rvQuestions.addItemDecoration(divider)
    }
}
