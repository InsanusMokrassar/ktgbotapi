package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.webapps.invoice.InvoiceClosedInfo

typealias EventHandler = WebApp.() -> Unit
typealias ViewportChangedEventHandler = WebApp.(ViewportChangedData) -> Unit
typealias InvoiceClosedEventHandler = WebApp.(InvoiceClosedInfo) -> Unit
typealias PopupClosedEventHandler = WebApp.(String?) -> Unit
