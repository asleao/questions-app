package com.bliss.questionsapp.questions.share.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionShareFragmentBinding
import com.bliss.questionsapp.questions.share.viewmodel.QuestionShareViewModel
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionShareFragment : Fragment() {

    private val viewModel: QuestionShareViewModel by viewModel()
    private val args by navArgs<QuestionShareFragmentArgs>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getKoin().setProperty(QUESTIONSHARE_ARGUMENT, args.questionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<QuestionShareFragmentBinding>(
            inflater,
            R.layout.question_share_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        const val QUESTIONSHARE_ARGUMENT = "QUESTIONSHARE_ARGUMENT"
    }
}
