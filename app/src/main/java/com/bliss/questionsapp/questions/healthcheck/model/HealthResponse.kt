package com.bliss.questionsapp.questions.healthcheck.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HealthResponse(val status: String):Parcelable
