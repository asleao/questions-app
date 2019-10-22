package com.bliss.questionsapp.questions.details.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bliss.questionsapp.databinding.ChoicesItemBinding
import com.bliss.questionsapp.questions.commons.model.Choice

class ChoicesAdapter(
    private val choices: List<Choice>,
    private val choiceAction: (Choice) -> Unit
) : RecyclerView.Adapter<ChoicesAdapter.ChoicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChoicesItemBinding.inflate(layoutInflater, parent, false)
        return ChoicesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return choices.size
    }

    override fun onBindViewHolder(holder: ChoicesViewHolder, position: Int) {
        holder.bind(choices[position], choiceAction)
    }

    class ChoicesViewHolder(private val binding: ChoicesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(choice: Choice, choiceAction: (Choice) -> Unit) {
            binding.choice = choice
            binding.ivThumbsup.setOnClickListener {
                val votedUpChoice = choice.copy(votes = choice.votes.plus(1))
                binding.choice = votedUpChoice
                choiceAction.invoke(votedUpChoice)
            }
            binding.ivThumbsdown.setOnClickListener {
                val votedDownChoice = choice.copy(votes = choice.votes.minus(1))
                binding.choice = votedDownChoice
                choiceAction.invoke(votedDownChoice)
            }
        }
    }
}