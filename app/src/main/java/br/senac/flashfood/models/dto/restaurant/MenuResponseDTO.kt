package br.senac.flashfood.models.dto.restaurant

import java.util.*

data class MenuResponseDTO (
    val id: UUID,
    val productsList: List<ProductResponseDTO>
)