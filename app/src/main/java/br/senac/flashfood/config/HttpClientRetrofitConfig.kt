package br.senac.flashfood.config

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpClientRetrofitConfig {


    private fun configure() = OkHttpClient().newBuilder()
        .addInterceptor(LogingInterceptorConfig.getLoginInterceprtor())
        .addInterceptor(InterceptorRequestConfig)
        .addInterceptor(InterceptorResponseConfig)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getHttpClient() = configure()

}