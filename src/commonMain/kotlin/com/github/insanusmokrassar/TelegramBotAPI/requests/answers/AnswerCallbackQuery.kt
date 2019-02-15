package com.github.insanusmokrassar.TelegramBotAPI.requests.answers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.CallbackQuery
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class AnswerCallbackQuery(
    @SerialName(callbackQueryIdField)
    val callbackQueryId: CallbackQueryIdentifier,
    @SerialName(textField)
    @Optional
    val text: String? = null,
    @SerialName(showAlertField)
    @Optional
    val showAlert: Boolean? = null,
    @SerialName(urlField)
    @Optional
    val url: String? = null,
    @SerialName(cachedTimeField)
    @Optional
    val cachedTimeSeconds: Int? = null
) : SimpleRequest<Boolean> {
    override fun method(): String = "answerCallbackQuery"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}

fun CallbackQuery.createAnswer(
    text: String? = null,
    showAlert: Boolean? = null,
    url: String? = null,
    cachedTimeSeconds: Int? = null
): AnswerCallbackQuery = AnswerCallbackQuery(id, text, showAlert, url, cachedTimeSeconds)
