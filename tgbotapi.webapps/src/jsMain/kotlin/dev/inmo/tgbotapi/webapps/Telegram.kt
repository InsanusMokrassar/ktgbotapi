package dev.inmo.tgbotapi.webapps

import org.w3c.dom.Window

external interface Telegram {
    val WebApp: WebApp
}

val Window.Telegram
    get() = asDynamic().Telegram.unsafeCast<Telegram>()
