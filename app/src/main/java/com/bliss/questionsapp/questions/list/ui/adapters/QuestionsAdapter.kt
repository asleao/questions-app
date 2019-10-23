package com.bliss.questionsapp.questions.list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionItemBinding
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.squareup.picasso.Picasso

class QuestionsAdapter(
    private val questions: List<QuestionResponse>,
    private val questionAction: (QuestionResponse) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(layoutInflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position], questionAction)
    }

    class QuestionViewHolder(private val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: QuestionResponse, questionAction: (QuestionResponse) -> Unit) {
            binding.question = question
            setupQuestionThumbnail(question)
            binding.clQuestionItem.setOnClickListener {
                questionAction.invoke(question)
            }
        }

        private fun setupQuestionThumbnail(question: QuestionResponse) {
            Picasso.get()
                .load(question.thumbUrl)
                .error(R.drawable.no_photo)
                .into(binding.ivQuestionThumbnail)
        }
    }
}