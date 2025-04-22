package dev.inmo.tgbotapi.webapps.invoice

external interface InvoiceClosedInfo {
    val url: String
    val status: String
}

val String.statusTyped
    get() =
        when (this) {
            InvoiceStatus.Paid.name -> InvoiceStatus.Paid
            InvoiceStatus.Cancelled.name -> InvoiceStatus.Cancelled
            InvoiceStatus.Failed.name -> InvoiceStatus.Failed
            InvoiceStatus.Pending.name -> InvoiceStatus.Pending
            else -> InvoiceStatus.Unknown(this)
        }

val InvoiceClosedInfo.statusTyped
    get() = status.statusTyped
