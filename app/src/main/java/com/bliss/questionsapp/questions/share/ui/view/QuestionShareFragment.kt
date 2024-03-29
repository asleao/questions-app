package com.bliss.questionsapp.questions.share.ui.view

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
import androidx.navigation.fragment.navArgs
import com.bliss.questionsapp.R
import com.bliss.questionsapp.core.network.BUSINESS_LOGIC_ERROR_CODE
import com.bliss.questionsapp.core.network.GENERIC_MSG_ERROR_TITLE
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.utils.hideSoftInput
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
        setupObservers()
    }

    private fun setupObservers() {
        setupLoadingObserver()
        setupShareObserver()
        setupErrorObserver()
    }

    private fun setupLoadingObserver() {
        viewModel.loading.observe(this, Observer {
            requireActivity().hideSoftInput()
        })

    }

    private fun setupShareObserver() {
        viewModel.share.observe(this, Observer { share ->
            viewModel.showLoading(false)
            if (share.status == resources.getString(R.string.status_ok)) {
                showSuccessDialog()
            } else {
                showFailDialog()
            }
        })
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(context)
            .setTitle(resources.getString(R.string.share_success_dialog_title))
            .setMessage(resources.getString(R.string.share_success_dialog_message))
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { _, _ ->
                findNavController().popBackStack()
            }
            .create()
            .show()
    }

    private fun showFailDialog() {
        AlertDialog.Builder(context)
            .setTitle(resources.getString(R.string.share_fail_dialog_title))
            .setMessage(resources.getString(R.string.share_fail_dialog_message))
            .setCancelable(false)
            .setPositiveButton(R.string.ok, null)
            .create()
            .show()
    }
    private fun setupErrorObserver() {
        viewModel.error.observe(this, Observer { error ->
            viewModel.showLoading(false)
            if (error.codErro == BUSINESS_LOGIC_ERROR_CODE) {
                showBusinessLogicErrorDialog(error)
            } else {
                viewModel.hasConnectionProblems(true)
                viewModel.changeErrorMessage("${error.title}\n\n${error.message}")
            }
        })
    }

    private fun showBusinessLogicErrorDialog(error: Error) {
        AlertDialog.Builder(context)
            .setTitle(GENERIC_MSG_ERROR_TITLE)
            .setMessage(error.message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok, null)
            .create()
            .show()
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
        setupClickListeners(binding)
        return binding.root
    }

    private fun setupClickListeners(binding: QuestionShareFragmentBinding) {
        setupTryAgainClickListener(binding.iBaseLayout.btTryAgain)
        setupShareClickListener(binding.btShare)
    }

    private fun setupTryAgainClickListener(btTryAgain: Button) {
        btTryAgain.setOnClickListener {
            viewModel.tryAgain()
        }
    }

    private fun setupShareClickListener(btShare: Button) {
        btShare.setOnClickListener {
            viewModel.shareQuestion()
        }
    }

    companion object {
        const val QUESTIONSHARE_ARGUMENT = "QUESTIONSHARE_ARGUMENT"
    }
}
