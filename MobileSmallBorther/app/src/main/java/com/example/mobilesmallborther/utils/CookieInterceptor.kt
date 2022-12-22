package com.example.mobilesmallborther.utils

import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cookie = RetrofitHelper.getAuthCookie()
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Cookie", "CookieSuper=${cookie}")
            .build()
        return chain.proceed(modifiedRequest)
    }

}