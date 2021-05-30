package br.senac.flashfood.config

import android.util.Log
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.context.UserContext
import br.senac.flashfood.utils.internal.SharedUtils
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.HTTP

object InterceptorResponseConfig : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val ERRO_CODE = 403

        var response = chain.proceed(chain.request())

        if(!response.header(ApiConstants.HEADER_NAME_AUTH.value).isNullOrEmpty() && response.code() != ERRO_CODE)
            UserContext.token = response.header(ApiConstants.HEADER_NAME_AUTH.value).orEmpty()
        else if(response.code() == ERRO_CODE) {
            throw SecurityException(response.code().toString())
        }

        return response
    }
}