package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.PurchaseController
import br.senac.flashfood.databinding.ActivityPurchaseDetailsBinding
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import androidx.lifecycle.Observer
import br.senac.flashfood.databinding.CardProductBinding
import br.senac.flashfood.utils.ui.toastShow
import java.util.*

class PurchaseDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseDetailsBinding

    private lateinit var nameRestaurant: String

    private lateinit var codPurchase: UUID

    private var totalValue = "0.0"

    private lateinit var datePurchase: String

    private var mListProducts = MutableLiveData<List<ProductResponseDTO>>()

    private var mResult = MutableLiveData<Boolean>()

    private val PURCHASE_CONTROLLER = PurchaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBackSignup2.setOnClickListener {
            finish()
        }

        getExtras()

        PURCHASE_CONTROLLER.getByCod(mListProducts, mResult, codPurchase)

        binding.txtValuePurchaseDetails.text = totalValue
        binding.txtRestaurantNamePurchase.text = nameRestaurant
        binding.txtCodPurchaseDetails.text = codPurchase.toString()
        binding.txtDatePurchaseDetails.text = datePurchase

        mResult.observe(this, Observer {
            if(!it) toastShow(this, "Erro para listar produtos", Toast.LENGTH_LONG)
        })

        mListProducts.observe(this,  Observer{
            it?.let { showProducts(it) }
        })

    }

    fun getExtras() {
        nameRestaurant = intent.getStringExtra(ExtraConstants.RESTAURANT_NAME.value).orEmpty()
        codPurchase  = UUID.fromString(intent.getStringExtra(ExtraConstants.COD_PURCHASE.value).orEmpty())
        totalValue = intent.getStringExtra(ExtraConstants.TOTAL_VALUE_PURCHASE.value).orEmpty()
        datePurchase = intent.getStringExtra(ExtraConstants.PURCHASE_DATE.value).orEmpty()
    }

    fun showProducts(products: List<ProductResponseDTO>) {

        products.forEach {
            val CARD_PRODUCT = CardProductBinding.inflate(layoutInflater)

            CARD_PRODUCT.txtNameProduct.text = it.name
            CARD_PRODUCT.txtPrice.text = it.price.toString()

            binding.containerPurchaseDetails.addView(CARD_PRODUCT.root)
        }

    }
}