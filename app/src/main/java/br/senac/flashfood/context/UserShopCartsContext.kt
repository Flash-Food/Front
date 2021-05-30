package br.senac.flashfood.context

import br.senac.flashfood.models.internal.ShopCart
import java.util.*
import kotlin.collections.HashMap

object UserShopCartsContext {
    private val SHOP_CARTS = HashMap<UUID, ShopCart>()

    fun setShopCart(shopCart: ShopCart) { SHOP_CARTS.put(shopCart.idRestaurant, shopCart) }


    fun getShopCart(idRestaurant: UUID) = SHOP_CARTS.get(idRestaurant)
}