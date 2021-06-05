package br.senac.flashfood.controller

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.client.PurchaseService
import br.senac.flashfood.config.RetrofitConfig
import br.senac.flashfood.models.dto.purchase.PurchaseRequestDTO
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class PurchaseController {

    private val retrofit = RetrofitConfig.getRetrofit()

    private val service = retrofit.create(PurchaseService::class.java)

    fun purchase(purchaseRequestDTO: PurchaseRequestDTO, result: MutableLiveData<Boolean>, uuid: MutableLiveData<UUID>) {
        service.purchase(purchaseRequestDTO).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe(
                {value ->
                    if(value.isSuccessful) {
                        value.body()?.let { uuid.postValue(it) }
                        result.postValue(true)
                    }
                    else {
                        result.postValue(false)
                    }
                },{error ->
                    Log.e("ERROR_PURCHASE", error?.message.toString())
                    result.postValue(throw SecurityException(error.message.toString()))
                }
            )
    }


    fun getByCod(products: MutableLiveData<List<ProductResponseDTO>>, result: MutableLiveData<Boolean>, uuid: UUID) {
        service.getProducts(uuid).subscribeOn(Schedulers.io())
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
                    Log.e("ERROR_PURCHASE", error?.message.toString())
                    result.postValue(throw SecurityException(error.message.toString()))
                }
            )
    }


}