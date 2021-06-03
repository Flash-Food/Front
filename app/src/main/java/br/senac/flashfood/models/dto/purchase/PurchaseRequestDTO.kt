package br.senac.flashfood.models.dto.purchase

import br.senac.flashfood.constants.PaymentTypeConstants
import java.util.*

data class PurchaseRequestDTO (
    val restaurantId: UUID,
    val paymentType: PaymentTypeConstants,
    val products:  List<UUID>
)