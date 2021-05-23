package br.senac.flashfood.models.dto.user

data class UserLoginRequestDTO (
    val username: String,
    val password: String
)