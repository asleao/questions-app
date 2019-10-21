package com.bliss.questionsapp.questions.details.ui.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bliss.questionsapp.R
import com.bliss.questionsapp.questions.details.viewmodel.QuestionDetailViewModel

class QuestionDetailFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionDetailFragment()
    }

    private lateinit var viewModel: QuestionDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
