package br.senac.flashfood.client

import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("product")
    fun findByName(@Query("name") name: String): Observable<Response<List<ProductResponseDTO>>>

}