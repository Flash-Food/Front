package br.senac.flashfood.activities

import android.content.Intent
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

        binding.btnPayment.setOnClickListener {
            startActivity(
                Intent(this, PaymentActivity::class.java)
                    .putExtra(ExtraConstants.RESTAURANT_ID.value, idRestaurant.toString())
            )
        }

        getExtras()

        setProductsInContainer(
            UserShopCartsContext.getListProductsByRestaurant(idRestaurant)
        )

    }


    fun getExtras() {
        idRestaurant = UUID.fromString(intent.getStringExtra(ExtraConstants.RESTAURANT_ID.value).orEmpty())
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