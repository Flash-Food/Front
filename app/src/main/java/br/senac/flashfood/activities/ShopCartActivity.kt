package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.senac.flashfood.R
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.context.UserShopCartsContext
import br.senac.flashfood.databinding.ActivityShopCartBinding
import br.senac.flashfood.databinding.CardProductBinding
import br.senac.flashfood.models.internal.Product
import java.util.*
import kotlin.collections.ArrayList

class ShopCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopCartBinding

    private lateinit var idRestaurant: UUID

    private var valueFinal: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtValueFinal.text = valueFinal.toString()

        binding.btnBackMenuTheOfShopCart.setOnClickListener {
            finish()
        }

        getExtras()

        setProductsInContainer(
            getListProducts(idRestaurant)
        )

    }


    fun getExtras() {
        idRestaurant = UUID.fromString(intent.getStringExtra(ExtraConstants.RESTAURANT_ID.value).orEmpty())
    }

    fun getListProducts(idRestaurant: UUID): ArrayList<Product> {
        val SHOP_CART = UserShopCartsContext.getShopCart(idRestaurant)
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

    fun setProductsInContainer(products: ArrayList<Product>) {
        products.forEach {
            val CARD_PRODUCT = CardProductBinding.inflate(layoutInflater)

            CARD_PRODUCT.txtNameProduct.text = it.name
            CARD_PRODUCT.txtPrice.text = it.price.toString()

            valueFinal += it.price

            binding.containerProductsCart.addView(CARD_PRODUCT.root)
        }

        binding.txtValueFinal.text = """R$ $valueFinal"""
    }
}