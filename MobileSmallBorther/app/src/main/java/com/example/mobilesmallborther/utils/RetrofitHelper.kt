package com.example.mobilesmallborther.utils

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    // 10.0.2.2 pour utilisation sur émulateur et 5216 pour le port http de l'API
    private val baseUrl:String = "http://10.0.2.2:5216/api/v1/"
    private var authCookie: String? = null

    fun newInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .cookieJar(object: CookieJar {
                private val cookieStore = HashMap<String, List<Cookie>>()
                override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                    cookieStore[url.host()] = cookies
                    cookies.forEach {
                        if(it.name() == "CookieSuper") {
                            setAuthCookie(it.value())
                        }
                    }
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    val cookies = cookieStore[url.host()]
                    return cookies ?:ArrayList()
                }
            })
            .addInterceptor(CookieInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setAuthCookie(cookie: String) {
        authCookie = cookie
    }

    fun getAuthCookie(): String? {
        return authCookie
    }
}