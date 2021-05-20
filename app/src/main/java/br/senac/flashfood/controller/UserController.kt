package br.senac.flashfood.controller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.client.UserService
import br.senac.flashfood.config.RetrofitConfig
import br.senac.flashfood.models.dto.UserLoginRequestDTO
import br.senac.flashfood.models.dto.UserSignUpRequestDTO
import io.reactivex.rxjava3.schedulers.Schedulers

class UserController {

    private val retrofit = RetrofitConfig.getRetrofit()

    private val service = retrofit.create(UserService::class.java)


    fun login(user: UserLoginRequestDTO, result: MutableLiveData<Boolean>) {

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

    fun signup(user: UserSignUpRequestDTO, result: MutableLiveData<Boolean>) {

        service.signup(user).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(
                {value ->
                    if(value.isSuccessful) result.postValue(true)
                    else result.postValue(false)
                },
                { error ->
                    Log.e("ERROR_SIGNUP", error.message.toString())
                    result.postValue(false)
                })
    }
}