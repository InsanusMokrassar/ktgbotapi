package com.github.insanusmokrassar.TelegramBotAPI.requests.passport

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.errorsField
import com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors.PassportElementError
import com.github.insanusmokrassar.TelegramBotAPI.types.userIdField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
data class SetPassportDataErrors (
    @SerialName(userIdField)
    val usedId: UserId,
    @SerialName(errorsField)
    val errors: List<PassportElementError>
) : SimpleRequest<Boolean> {
    override fun method(): String = "setPassportDataErrors"
    override fun resultSerializer(): KSerializer<Boolean> = Boolean.serializer()
}