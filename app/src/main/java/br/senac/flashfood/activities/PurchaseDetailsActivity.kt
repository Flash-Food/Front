package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.flashfood.R
import br.senac.flashfood.databinding.ActivityPaymentFinishBinding
import br.senac.flashfood.databinding.ActivityPurchaseDetailsBinding

class PurchaseDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}