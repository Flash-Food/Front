package br.senac.flashfood.controller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.client.RestaurantService
import br.senac.flashfood.config.RetrofitConfig
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RestaurantController {

    private val retrofit = RetrofitConfig.getRetrofit()

    private val service = retrofit.create(RestaurantService::class.java)

    fun getAll(restaurants: MutableLiveData<List<RestaurantResponseDTO?>>, result: MutableLiveData<Boolean>) {

        service.getAll().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(
                {value ->
                    if(value.isSuccessful) {
                        value.body()?.let { restaurants.postValue(it) }
                        result.postValue(true)
                    }
                    else {
                        result.postValue(false)
                    }
                },{error ->
                    Log.e("ERROR_GET_ALL", error?.message.toString())
                    result.postValue(false)
                }
            )
    }

}