package com.bliss.questionsapp.questions.commons.model

import java.time.LocalDateTime

class QuestionResponse(
    val title: String,
    val imageUrl: String,
    val thumbUrl: String,
    val publishedAt: LocalDateTime,
    val choices: List<Choice>
)
