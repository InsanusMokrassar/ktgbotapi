package com.github.insanusmokrassar.TelegramBotAPI.types.passport.errors

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.fileNameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassportElementErrorDataField (
    @SerialName(sourceField)
    override val source: String,
    @SerialName(typeField)
    override val type: String,
    @SerialName(messageField)
    override val message: String,

    @SerialName(fileNameField)
    val fileName: String,
    @SerialName(dataHashField)
    val dataHash: Base64

) : PassportElementError