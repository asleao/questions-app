package com.bliss.questionsapp.core.network.retrofit.model

import com.squareup.moshi.Json

data class Error(
    val codErro: Int = -1,
    val title: String = "",
    @field:Json(name = "status")
    val message: String = ""
) {
    fun isValid(): Boolean = codErro != -1 && title.isNotEmpty() && message.isNotEmpty()
}