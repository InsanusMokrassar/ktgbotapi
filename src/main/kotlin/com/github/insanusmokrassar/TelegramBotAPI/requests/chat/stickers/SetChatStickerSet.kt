package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatStickerSet(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(stickerSetNameField)
    val stickerSetName: String
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "setChatStickerSet"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
}
