package dev.inmo.tgbotapi.webapps.invoice

external interface InvoiceClosedInfo {
    val url: String
    val status: String
}

val InvoiceClosedInfo.statusTyped
    get() = when (status) {
        InvoiceStatus.Paid.name -> InvoiceStatus.Paid
        InvoiceStatus.Cancelled.name -> InvoiceStatus.Cancelled
        InvoiceStatus.Failed.name -> InvoiceStatus.Failed
        InvoiceStatus.Pending.name -> InvoiceStatus.Pending
        else -> InvoiceStatus.Unknown(status)
    }

