package com.bliss.questionsapp.core.utils

import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    val emailRegex = "[A-Z0-9a-z.%&'+_|-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
    val pattern = Pattern.compile(emailRegex)
    return this.isNotEmpty() && pattern.matcher(this).find()
}