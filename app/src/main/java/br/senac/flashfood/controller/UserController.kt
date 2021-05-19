package br.senac.flashfood.controller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.client.UserService
import br.senac.flashfood.config.RetrofitConfig
import br.senac.flashfood.constants.ApiConstants
import br.senac.flashfood.models.dto.UserLoginRequestDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserController {

    private val retrofit = RetrofitConfig.getRetrofit()

    fun login(user: UserLoginRequestDTO, result: MutableLiveData<Boolean>) {

        val service = retrofit.create(UserService::class.java)

        service.login(user).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(
                {value ->
                    if(value.isSuccessful) result.postValue(true)
                    else result.postValue(false)
                },
                {error ->
                    Log.e("ERROR_LOGIN", error.message.toString())
                    result.postValue(false)
                })
    }
}