package br.senac.flashfood.context

import br.senac.flashfood.models.internal.Product
import br.senac.flashfood.models.internal.ShopCart
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object UserShopCartsContext {
    private val SHOP_CARTS = HashMap<UUID, ShopCart>()

    fun setShopCart(shopCart: ShopCart) { SHOP_CARTS.put(shopCart.idRestaurant, shopCart) }


    fun getShopCart(idRestaurant: UUID) = SHOP_CARTS.get(idRestaurant)

    fun getListProductsByRestaurant(id: UUID): ArrayList<Product> {
        val SHOP_CART = this.getShopCart(id)
        if(SHOP_CART != null) {
            val PRODUCTS = ArrayList<Product>()
            SHOP_CART.products?.forEach {
                it.value.forEach {
                    PRODUCTS.add(it)
                }
            }
            return PRODUCTS
        } else return ArrayList()
    }
}