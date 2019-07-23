package com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportElementErrorUnspecified (
    @SerialName(sourceField)
    override val source: String,
    @SerialName(typeField)
    override val type: String,
    @SerialName(messageField)
    override val message: String,

    @SerialName(elementHashField)
    val elementHash: Base64

) : PassportElementError