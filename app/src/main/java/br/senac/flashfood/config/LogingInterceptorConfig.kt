package br.senac.flashfood.config

import android.util.Log
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.context.UserContext
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object LogingInterceptorConfig  {

    fun getLoginInterceprtor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return loggingInterceptor
    }

}