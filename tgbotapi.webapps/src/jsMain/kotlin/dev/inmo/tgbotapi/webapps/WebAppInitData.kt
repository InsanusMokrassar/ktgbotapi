package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.*

external interface WebAppInitData {
    @JsName("query_id")
    val queryId: WebAppQueryId?

    val user: WebAppUser?
    val receiver: WebAppUser?

    @JsName("start_param")
    val startParam: String?

    @JsName("auth_date")
    val authDate: MilliSeconds

    @JsName("can_send_after")
    val canSendAfter: MilliSeconds

    val chat: WebAppChat

    val hash: String
}
