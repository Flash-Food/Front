package br.senac.flashfood.config

import android.util.Log
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.context.UserContext
import okhttp3.Interceptor
import okhttp3.Response

object InterceptorConfig : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        if(UserContext.token.isNotEmpty()) {
            val prefix = ApiConstants.PREFIX.value
            val authVal = UserContext.token
            request = request.newBuilder()
                .addHeader(ApiConstants.HEADER_NAME_AUTH.value, "${prefix} ${authVal}")
                .build()
            return chain.proceed(request)
        } else {
            val response = chain.proceed(request)
            UserContext.token = response.header(ApiConstants.HEADER_NAME_AUTH.value).orEmpty()
            return response
        }

    }
}