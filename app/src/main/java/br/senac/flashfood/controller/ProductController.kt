package br.senac.flashfood.controller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.client.ProductService
import br.senac.flashfood.config.RetrofitConfig
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductController {

    private val retrofit = RetrofitConfig.getRetrofit()

    private val service = retrofit.create(ProductService::class.java)

    fun findByName(products: MutableLiveData<List<ProductResponseDTO>>, result: MutableLiveData<Boolean>, name: String) {
        service.findByName(name).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(
                {value ->
                    if(value.isSuccessful) {
                        value.body()?.let { products.postValue(it) }
                        result.postValue(true)
                    }
                    else {
                        result.postValue(false)
                    }
                },{error ->
                    Log.e("ERROR_FIND_BY_NAME_PROD", error?.message.toString())
                    result.postValue(throw SecurityException(error.message.toString()))
                }
            )
    }


}