package com.bliss.questionsapp.questions.commons.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class QuestionResponse(
    @field:Json(name = "question")
    val title: String,
    @field:Json(name = "image_url")
    val imageUrl: String,
    @field:Json(name = "thumb_url")
    val thumbUrl: String,
    @field:Json(name = "choices")
    val choices: List<Choice>
):Parcelable
