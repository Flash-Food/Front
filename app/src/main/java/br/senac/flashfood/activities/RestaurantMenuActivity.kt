package br.senac.flashfood.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.R
import br.senac.flashfood.constants.CategoryProductConstants
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.RestaurantController
import br.senac.flashfood.databinding.ActivityRestaurantMenuBinding
import br.senac.flashfood.databinding.CardCategoryProductBinding
import br.senac.flashfood.databinding.CardProductBinding
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import br.senac.flashfood.utils.ui.toastShow
import java.util.*

class RestaurantMenuActivity : AppCompatActivity() {

    private val RESTAURANT_CONTROLLER = RestaurantController()

    private var mListProducts = MutableLiveData<RestaurantResponseDTO?>()

    private var mGetAllResult = MutableLiveData<Boolean>()

    private lateinit var restauratId: UUID

    private lateinit var nameRestaurant: String

    private lateinit var descriptionRestaurant: String

    private lateinit var binding: ActivityRestaurantMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantMenuBinding.inflate(layoutInflater)

        getExtras()

        binding.txtNameRest.text = nameRestaurant

        binding.txtDescRest.text = descriptionRestaurant

        binding.fltbShopCart?.setOnClickListener {
            val INTENT = Intent(this, ShopCartActivity::class.java)
            INTENT.putExtra(ExtraConstants.RESTAURANT_ID.value, restauratId.toString())
            startActivity(INTENT)
        }

        binding.btnBackRestaurants.setOnClickListener {
            finish()
        }

        RESTAURANT_CONTROLLER.getRestaurantAndMenu(restauratId, mListProducts, mGetAllResult)

        mGetAllResult.observe(this, Observer<Boolean> {
            if (!it) toastShow(this, "Erro para listar os produtos!", Toast.LENGTH_LONG)
        })

        mListProducts.observe(this, Observer {
            it?.let { showCardProducts(it) }
        })

        setContentView(binding.root)
    }

    fun getExtras() {
        nameRestaurant = intent.getStringExtra(ExtraConstants.RESTAURANT_NAME.value).orEmpty()
        descriptionRestaurant =
            intent.getStringExtra(ExtraConstants.RESTAURANT_DESC.value).orEmpty()
        restauratId  = UUID.fromString(intent.getStringExtra(ExtraConstants.RESTAURANT_ID.value).orEmpty())
    }


    fun showCardProducts(restaurants: RestaurantResponseDTO) {

        val SALTY = restaurants.menu.productsList.filter { it.category == CategoryProductConstants.SALTY }

        val CANDY = restaurants.menu.productsList.filter { it.category ==  CategoryProductConstants.CANDY}

        val DRINK = restaurants.menu.productsList.filter { it.category == CategoryProductConstants.DRINK }

        createMenuAndCategory(SALTY, "Salgados")
        createMenuAndCategory(CANDY, "Doces")
        createMenuAndCategory(DRINK, "Bebidas")

    }

    fun createTitle(name: String): CardView {
        val TITLE = CardCategoryProductBinding.inflate(layoutInflater)
        TITLE.txtCategory.text = name
        return TITLE.root
    }

    fun createMenuAndCategory(products: List<ProductResponseDTO>, categoryName: String) {

        var counter = 0

        products.forEach {

            val NAME = it.name
            val DESC = it.description
            val PRICE = it.price
            val RESTAURANT_ID = restauratId
            val PRODUCT_ID = it.id

            if(counter == 0) {
                binding.containerProductsMenu.addView(createTitle(categoryName))
                counter++;
            }

            val PRODUCT_CARD = CardProductBinding.inflate(layoutInflater)

            PRODUCT_CARD.root.setOnClickListener {
                val INTENT = Intent(this, ProductInfoActivity::class.java)
                INTENT.putExtra(ExtraConstants.PRODUCT_NAME.value, NAME)
                INTENT.putExtra(ExtraConstants.PRODUCT_DESC.value, DESC)
                INTENT.putExtra(ExtraConstants.PRODUCT_PRICE.value, PRICE)
                INTENT.putExtra(ExtraConstants.PRODUCT_ID.value, PRODUCT_ID.toString())
                INTENT.putExtra(ExtraConstants.RESTAURANT_ID.value, RESTAURANT_ID.toString())
                startActivity(INTENT)
            }

            PRODUCT_CARD.txtNameProduct.text = NAME
            PRODUCT_CARD.txtPrice.text = """R$ $PRICE"""

            binding.containerProductsMenu.addView(PRODUCT_CARD.root)
        }
    }



}


