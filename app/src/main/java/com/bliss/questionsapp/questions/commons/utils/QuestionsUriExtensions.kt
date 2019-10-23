package com.bliss.questionsapp.questions.commons.utils

import android.net.Uri

fun Int.buildQuestionUri(): String {
    val builder = Uri.Builder()
    builder.scheme("blissrecruitment")
        .path("//questions")
        .appendQueryParameter("question_filter", this.toString())
    return builder.toString()
}