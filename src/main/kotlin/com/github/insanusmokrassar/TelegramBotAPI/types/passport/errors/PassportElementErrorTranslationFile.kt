package com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportElementErrorTranslationFile (
    @SerialName(sourceField)
    override val source: String,
    @SerialName(typeField)
    override val type: String,
    @SerialName(messageField)
    override val message: String,

    @SerialName(fileHashField)
    val fileHash: Base64

) : PassportElementError