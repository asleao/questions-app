package com.bliss.questionsapp.questions.commons.di

import com.bliss.questionsapp.questions.details.di.questionDetailModule
import com.bliss.questionsapp.questions.healthcheck.di.healthCheckModule

val questionsModules = listOf(healthCheckModule, questionDetailModule)