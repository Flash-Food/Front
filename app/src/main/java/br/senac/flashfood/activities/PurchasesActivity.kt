package br.senac.flashfood.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.UserController
import br.senac.flashfood.databinding.ActivityPurchasesBinding
import br.senac.flashfood.databinding.CardPurchaseBinding
import br.senac.flashfood.models.dto.purchase.PurchaseResponseDTO
import br.senac.flashfood.utils.ui.toastShow
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class PurchasesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchasesBinding

    private var mListPurchases = MutableLiveData<List<PurchaseResponseDTO>>()

    private var mResult = MutableLiveData<Boolean>()

    private val USER_CONTROLLER = UserController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        USER_CONTROLLER.purchases(mListPurchases, mResult)

        mListPurchases.observe(this, Observer {
            it?.let { showPurchases(it) }
        })

        mResult.observe(this, Observer {
            if(!it) toastShow(this, "Erro para listar compras", Toast.LENGTH_LONG)
        })
    }

    fun showPurchases(purchases: List<PurchaseResponseDTO>) {
        purchases.forEach {
            val CARD_PURCHASE = CardPurchaseBinding.inflate(layoutInflater)

            val COD_PURCHASE = it.codPurchase.toString()
            val RESTAURANT_NAME = it.restaurant.name
            val TOTAL_VALUE = it.totalValue.toString()
            val PURCHASE_DATE = SimpleDateFormat("dd/MM/yyyy").format(it.purchaseDate)

            CARD_PURCHASE.txtRestaurantPurchase.text = RESTAURANT_NAME
            CARD_PURCHASE.txtDatePurchase.text = PURCHASE_DATE
            CARD_PURCHASE.txtPricePurchase.text = TOTAL_VALUE

            CARD_PURCHASE.root.setOnClickListener {
                val INTENT = Intent(this, PurchaseDetailsActivity::class.java)
                    .putExtra(ExtraConstants.COD_PURCHASE.value, COD_PURCHASE)
                    .putExtra(ExtraConstants.RESTAURANT_NAME.value, RESTAURANT_NAME)
                    .putExtra(ExtraConstants.TOTAL_VALUE_PURCHASE.value, TOTAL_VALUE)
                    .putExtra(ExtraConstants.PURCHASE_DATE.value, PURCHASE_DATE)
                startActivity(INTENT)
            }

            binding.containerPurchases.addView(CARD_PURCHASE.root)
        }
    }
}