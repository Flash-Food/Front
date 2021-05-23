package br.senac.flashfood.config

import android.util.Log
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.context.UserContext
import okhttp3.Interceptor
import okhttp3.Response

object InterceptorRequestConfig : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        if(UserContext.token.isNotEmpty()) {
            val authVal = UserContext.token
            request = request.newBuilder()
                .addHeader(ApiConstants.HEADER_NAME_AUTH.value, "${authVal}")
                .build()
        }

        return chain.proceed(request)
    }
}