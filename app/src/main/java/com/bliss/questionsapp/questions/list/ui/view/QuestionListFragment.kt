package com.bliss.questionsapp.questions.list.ui.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bliss.questionsapp.R
import com.bliss.questionsapp.questions.list.viewmodel.QuestionListViewModel

class QuestionListFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionListFragment()
    }

    private lateinit var viewModel: QuestionListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
