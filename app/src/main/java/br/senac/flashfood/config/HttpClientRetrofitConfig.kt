package br.senac.flashfood.config

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpClientRetrofitConfig {


    private fun configure() = OkHttpClient().newBuilder()
        .addInterceptor(LogingInterceptorConfig.getLoginInterceprtor())
        .addInterceptor(InterceptorConfig)
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    fun getHttpClient() = configure()

}