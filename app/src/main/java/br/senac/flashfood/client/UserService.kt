package br.senac.flashfood.client

import br.senac.flashfood.models.dto.user.UserLoginRequestDTO
import br.senac.flashfood.models.dto.user.UserSignUpRequestDTO
import br.senac.flashfood.models.dto.user.UserSignUpResponseDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("user/login")
    fun login(@Body user: UserLoginRequestDTO) : Observable<Response<Void>>

    @POST("user/signup")
    fun signup(@Body user: UserSignUpRequestDTO) : Observable<Response<UserSignUpResponseDTO>>
}