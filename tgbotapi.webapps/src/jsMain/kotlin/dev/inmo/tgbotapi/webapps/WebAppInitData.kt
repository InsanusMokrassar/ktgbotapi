package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.MilliSeconds
import dev.inmo.tgbotapi.types.WebAppQueryId

external interface WebAppInitData {
    val queryId: WebAppQueryId?

    val user: WebAppUser?
    val receiver: WebAppUser?

    @JsName("start_param")
    val startParam: String?

    @JsName("auth_date")
    val authDate: MilliSeconds

    val hash: String
}
