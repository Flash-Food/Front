package br.senac.flashfood.models.dto.user

import java.util.*

data class UserInfoResponseDTO (
    val id: UUID,
    val name: String,
    val email: String,
    val cpf: String
)