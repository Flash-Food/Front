package br.senac.flashfood.client

import br.senac.flashfood.models.dto.purchase.PurchaseRequestDTO
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface PurchaseService {

    @POST("purchase")
    fun purchase(@Body purchase: PurchaseRequestDTO): Observable<Response<UUID>>

    @GET("purchase/{COD}")
    fun getProducts(@Path("COD") cod: UUID): Observable<Response<List<ProductResponseDTO>>>

}