package br.senac.flashfood.models.dto

data class UserSignUpResponseDTO (
    val id: String,
    val name: String,
    val email: String,
    val cpf: String,
    val phoneNumber: String,
    val password: String
)