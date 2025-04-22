package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.types.InvoicePayload
import dev.inmo.tgbotapi.types.ShippingOptionId
import dev.inmo.tgbotapi.types.payments.OrderInfo
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val PreCheckoutQuery.total_amount: Long
    get() = amount

@RiskFeature(RawFieldsUsageWarning)
val PreCheckoutQuery.invoice_payload: InvoicePayload
    get() = invoicePayload

@RiskFeature(RawFieldsUsageWarning)
val PreCheckoutQuery.shipping_option_id: ShippingOptionId?
    get() = shippingOptionId

@RiskFeature(RawFieldsUsageWarning)
val PreCheckoutQuery.order_info: OrderInfo?
    get() = orderInfo
