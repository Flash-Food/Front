package br.senac.flashfood.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.senac.flashfood.R
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.databinding.ActivityPaymentFinishBinding
import java.util.*

class PaymentFinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentFinishBinding

    private lateinit var codPurchase: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()

        binding.buttonBackToMenu.setOnClickListener {
            finishAffinity()
            startActivity(
                Intent(this,BottomNavigationActivity::class.java)
            )
        }

        binding.txtCodPurchase.text = codPurchase.toString()
    }

    fun getExtras() {
        codPurchase = UUID.fromString(intent.getStringExtra(ExtraConstants.COD_PURCHASE.value).orEmpty())
    }
}