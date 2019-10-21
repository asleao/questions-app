package com.bliss.questionsapp

import android.app.Application
import com.bliss.questionsapp.questions.healthcheck.di.healthCheckModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class QuestionsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuestionsApplication)
            modules(healthCheckModule)
        }
    }
}