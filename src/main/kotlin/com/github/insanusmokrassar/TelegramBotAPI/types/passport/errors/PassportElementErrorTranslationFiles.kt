package com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportElementErrorTranslationFiles (
    @SerialName(sourceField)
    override val source: String,
    @SerialName(typeField)
    override val type: String,
    @SerialName(messageField)
    override val message: String,

    @SerialName(fileHashesField)
    val fileHashes: List<Base64>

) : PassportElementError