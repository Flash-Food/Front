package br.senac.flashfood.models.dto.purchase

import br.senac.flashfood.constants.PaymentTypeConstants
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import java.util.*

data class PurchaseResponseDTO (
    val codPurchase: UUID,
    val restaurant: RestaurantResponseDTO,
    val purchaseDate: Date,
    val totalValue: Double,
    val paymentType: PaymentTypeConstants,
    val products:  List<ProductResponseDTO>
)