package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.senac.flashfood.R
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.context.UserShopCartsContext
import br.senac.flashfood.databinding.ActivityProductInfoBinding
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import br.senac.flashfood.models.internal.Product
import br.senac.flashfood.models.internal.ShopCart
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

class ProductInfoActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityProductInfoBinding

    private var qtd = 0;

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var idRestaurant: UUID
    private lateinit var idProduct: UUID
    private var price: Double = 0.0

    private lateinit var listProducts: ArrayList<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()

        binding.txtProductNameInfo.text = name
        binding.txtDescProductInfo.text = description
        binding.txtValueProductInfo.text = """R$ $price"""

        binding.btnAdd.setOnClickListener {
            qtd++
            binding.txtQtd.text = qtd.toString()
            listProducts.add(Product(idProduct, name, price))
        }

        binding.btnSub.setOnClickListener {
            if(qtd > 0) {
                qtd--
                binding.txtQtd.text = qtd.toString()
                if(listProducts.size > 0)
                    listProducts.removeAt(0)
            }
        }

        binding.btnBackMenu.setOnClickListener { finish() }
    }

    fun getExtras() {
        name = intent.getStringExtra(ExtraConstants.PRODUCT_NAME.value).orEmpty()
        description = intent.getStringExtra(ExtraConstants.PRODUCT_DESC.value).orEmpty()
        price = intent.getDoubleExtra(ExtraConstants.PRODUCT_PRICE.value, 0.0)
        idRestaurant = UUID.fromString(intent.getStringExtra(ExtraConstants.RESTAURANT_ID.value).orEmpty())
        idProduct = UUID.fromString(intent.getStringExtra(ExtraConstants.PRODUCT_ID.value).orEmpty())
        listProducts = createShopCartIfNotFound(idRestaurant, idProduct)
    }

    fun createShopCartIfNotFound(idRestaurant: UUID, idProduct: UUID): ArrayList<Product> {

        var shopCart = UserShopCartsContext.getShopCart(idRestaurant)

        if (shopCart  == null) {
            val SHOP_CART = ShopCart(idRestaurant = idRestaurant)
            SHOP_CART.products?.put(idProduct, ArrayList())
            UserShopCartsContext.setShopCart(SHOP_CART)
            shopCart = UserShopCartsContext.getShopCart(idRestaurant)
        } else {
            if(shopCart?.products?.get(idProduct) == null)
                shopCart.products?.put(idProduct, ArrayList())
        }

        return shopCart?.products?.get(idProduct)!!
    }
}