package com.sdimosikvip.weather.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val apiToken: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter("appid", apiToken)
            .build()

        val requestBuilder = original.newBuilder()
            .url(httpUrl)

        return chain.proceed(requestBuilder.build())
    }
}
