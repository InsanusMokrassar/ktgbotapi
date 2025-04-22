package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.types.InvoicePayload
import dev.inmo.tgbotapi.types.payments.ShippingAddress
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val ShippingQuery.invoice_payload: InvoicePayload
    get() = invoicePayload

@RiskFeature(RawFieldsUsageWarning)
val ShippingQuery.shipping_address: ShippingAddress
    get() = shippingAddress
