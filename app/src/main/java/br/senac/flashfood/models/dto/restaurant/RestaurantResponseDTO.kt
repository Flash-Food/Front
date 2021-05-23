package br.senac.flashfood.models.dto.restaurant

import java.util.*

data class RestaurantResponseDTO (
    val id: UUID,
    val name: String,
    val phoneNumber: String,
    val description: String,
    val menu: MenuResponseDTO
)