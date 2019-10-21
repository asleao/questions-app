package com.bliss.questionsapp.core.network.retrofit

import com.bliss.questionsapp.BuildConfig
import com.bliss.questionsapp.core.network.HOST
import com.bliss.questionsapp.core.network.SCHEME
import com.squareup.moshi.Moshi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceGenerator {

    private val moshiFactory = Moshi.Builder().build()

    private val builder = Retrofit.Builder()
        .baseUrl(buildUrl())
        .addConverterFactory(MoshiConverterFactory.create(moshiFactory))

    private var retrofit = builder.build()

    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(
        serviceClass: Class<S>
    ): S {
        if (!httpClient.interceptors().contains(
                logging
            ) && BuildConfig.DEBUG
        ) {
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()
        }

        return retrofit.create(serviceClass)
    }

    private fun buildUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .build()
    }
}
