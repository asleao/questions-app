package com.bliss.questionsapp.questions.list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bliss.questionsapp.R
import com.bliss.questionsapp.databinding.QuestionItemBinding
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.squareup.picasso.Picasso
import java.util.*

class QuestionsAdapter(
    private val questions: List<QuestionResponse>,
    private val questionAction: (QuestionResponse) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>(), Filterable {

    var filteredQuestions = questions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(layoutInflater, parent, false)
        return QuestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredQuestions.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(filteredQuestions[position], questionAction)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence): FilterResults {
                filteredQuestions = questions.filter { question ->
                    query.toString().toLowerCase(Locale.getDefault()) in question.title.toLowerCase(Locale.getDefault())
                }
                val filteredResults = FilterResults()
                filteredResults.values = filteredQuestions

                return filteredResults
            }

            override fun publishResults(query: CharSequence, results: FilterResults) {
                filteredQuestions = results.values as List<QuestionResponse>
                notifyDataSetChanged()
            }
        }
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