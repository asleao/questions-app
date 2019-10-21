package com.bliss.questionsapp.questions.list.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bliss.questionsapp.R
import com.bliss.questionsapp.questions.list.viewmodel.QuestionListViewModel

class QuestionListFragment : Fragment() {

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
