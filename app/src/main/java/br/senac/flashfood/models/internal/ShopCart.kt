package br.senac.flashfood.models.internal

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class ShopCart (
    val idRestaurant: UUID,
    val products: HashMap<UUID, ArrayList<Product>>? = HashMap()
)