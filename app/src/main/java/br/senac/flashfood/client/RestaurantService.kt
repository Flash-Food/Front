package br.senac.flashfood.client

import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantService {

    @GET("restaurant")
    fun getAll() : Observable<Response<List<RestaurantResponseDTO>>>

}