package br.senac.flashfood.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.PurchaseController
import br.senac.flashfood.databinding.ActivityPaymentBinding
import br.senac.flashfood.utils.ui.toastShow
import androidx.lifecycle.Observer
import br.senac.flashfood.constants.PaymentTypeConstants
import br.senac.flashfood.context.UserShopCartsContext
import br.senac.flashfood.models.dto.purchase.PurchaseRequestDTO
import java.util.*
import kotlin.collections.ArrayList

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    private lateinit var idRestaurant: UUID

    private val PURCHASE_CONTROLLER = PurchaseController()

    private var mUUIDResp = MutableLiveData<UUID>()

    private var mResult = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()

        binding.buttonSendPayment.setOnClickListener {
            PURCHASE_CONTROLLER.purchase(getPurchaseRequestDTO(), mResult, mUUIDResp)
        }

        mResult.observe(this, Observer<Boolean> {
            if(!it) toastShow(this, "Erro para efetuar pagamento", Toast.LENGTH_LONG)
        })

        mUUIDResp.observe(this, Observer {
            it?.let {
                finishAffinity()
                    val INTENT = Intent(this, PaymentFinishActivity::class.java)
                        .putExtra(ExtraConstants.COD_PURCHASE.value, it.toString())
                    startActivity(INTENT)
            }
        })
    }

    fun getPurchaseRequestDTO(): PurchaseRequestDTO {

        var paymentTypeConstants =
            if(binding.cardRadioButton.isChecked) PaymentTypeConstants.CARD
            else  PaymentTypeConstants.CASH

        val prodUUIDs = ArrayList<UUID>()
        UserShopCartsContext.getListProductsByRestaurant(this.idRestaurant).forEach {
            prodUUIDs.add(it.id)
        }

        return PurchaseRequestDTO(
            this.idRestaurant,
            paymentTypeConstants,
            prodUUIDs
        )
    }

    fun getExtras() {
        idRestaurant = UUID.fromString(intent.getStringExtra(ExtraConstants.RESTAURANT_ID.value).orEmpty())
    }
}