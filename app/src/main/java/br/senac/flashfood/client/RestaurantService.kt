package br.senac.flashfood.client

import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface RestaurantService {

    @GET("restaurant")
    fun getAll() : Observable<Response<List<RestaurantResponseDTO>>>

    @GET("restaurant/{ID}")
    fun getRestaurantAndMenu(@Path("ID") id: UUID) : Observable<Response<RestaurantResponseDTO>>

    @GET("restaurant/product/{ID}")
    fun getRestaurantByProductId(@Path("ID") id: UUID) : Observable<Response<RestaurantResponseDTO>>

}