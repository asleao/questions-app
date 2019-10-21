package com.bliss.questionsapp.questions.details.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionDetailFragmentBinding
import com.bliss.questionsapp.questions.details.viewmodel.QuestionDetailViewModel
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionDetailFragment : Fragment() {

    private val viewModel: QuestionDetailViewModel by viewModel()
    private val args by navArgs<QuestionDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getKoin().setProperty(QUESTIONDETAIL_ARGUMENT, args.questionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<QuestionDetailFragmentBinding>(
            inflater,
            R.layout.question_detail_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        const val QUESTIONDETAIL_ARGUMENT = "QUESTIONDETAIL_ARGUMENT"
    }

}
