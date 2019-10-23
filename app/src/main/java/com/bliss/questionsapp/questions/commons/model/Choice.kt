package com.bliss.questionsapp.questions.commons.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Choice(
    @field:Json(name = "choice")
    val title: String,
    @field:Json(name = "votes")
    val votes: Int
) : Parcelable
