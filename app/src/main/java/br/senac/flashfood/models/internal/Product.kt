package br.senac.flashfood.models.internal

import java.util.*

data class Product (
    val id: UUID,
    val name: String,
    val price: Double
)