package dev.inmo.tgbotapi.webapps.invoice

sealed interface InvoiceStatus {
    val name: String
    object Paid : InvoiceStatus { override val name: String = "paid" }
    object Cancelled : InvoiceStatus { override val name: String = "cancelled" }
    object Failed : InvoiceStatus { override val name: String = "failed" }
    object Pending : InvoiceStatus { override val name: String = "pending" }
    value class Unknown(override val name: String) : InvoiceStatus
}
