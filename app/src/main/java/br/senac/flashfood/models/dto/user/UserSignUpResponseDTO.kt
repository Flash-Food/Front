package br.senac.flashfood.models.dto.user

import java.util.*

data class UserSignUpResponseDTO (
    val id: UUID,
    val name: String,
    val email: String,
    val cpf: String,
    val phoneNumber: String,
    val password: String
)