package br.senac.flashfood.models.dto.restaurant

import br.senac.flashfood.constants.CategoryProduct
import java.util.*

data class ProductResponseDTO (
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val category: CategoryProduct
)