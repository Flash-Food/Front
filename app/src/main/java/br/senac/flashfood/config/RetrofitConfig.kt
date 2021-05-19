package br.senac.flashfood.config

import br.senac.flashfood.constants.ApiConstants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private fun config() = Retrofit.Builder()
        .client(HttpClientRetrofitConfig.getHttpClient())
        .baseUrl(ApiConstants.BASE_URL.value)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofit() = config()
}