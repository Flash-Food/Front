package br.senac.flashfood.config

import android.util.Log
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.context.UserContext
import okhttp3.Interceptor
import okhttp3.Response

object InterceptorResponseConfig : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var response = chain.proceed(chain.request())

        if(!response.header(ApiConstants.HEADER_NAME_AUTH.value).isNullOrEmpty())
            UserContext.token = response.header(ApiConstants.HEADER_NAME_AUTH.value).orEmpty()

        return response
    }
}