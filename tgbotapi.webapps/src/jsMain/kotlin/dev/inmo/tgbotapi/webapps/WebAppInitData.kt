package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChatType

external interface WebAppInitData {
    @JsName("query_id")
    val queryId: WebAppQueryId?

    val user: WebAppUser?
    val receiver: WebAppUser?

    val chat: WebAppChat?

    @JsName("chat_type")
    val chatType: ChatType?

    @JsName("chat_instance")
    val chatInstance: String?

    @JsName("start_param")
    val startParam: String?

    @JsName("auth_date")
    val authDate: MilliSeconds

    @JsName("can_send_after")
    val canSendAfter: MilliSeconds?

    val hash: String

    val signature: String
}
