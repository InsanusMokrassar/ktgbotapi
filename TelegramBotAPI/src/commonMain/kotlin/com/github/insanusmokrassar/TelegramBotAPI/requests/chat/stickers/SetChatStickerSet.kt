package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.stickers

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class SetChatStickerSet(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(stickerSetNameField)
    val stickerSetName: StickerSetName
): ChatRequest, SimpleRequest<Boolean> {
    override fun method(): String = "setChatStickerSet"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
