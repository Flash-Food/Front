package br.senac.flashfood.models.dto.user

data class UserSignUpRequestDTO (
    val name: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phoneNumber: String
)