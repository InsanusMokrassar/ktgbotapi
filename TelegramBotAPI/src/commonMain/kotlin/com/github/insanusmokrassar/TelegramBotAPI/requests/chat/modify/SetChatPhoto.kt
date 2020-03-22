package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJson
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetChatPhoto (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    val photo: MultipartFile = throw IllegalArgumentException("Unfortunately, this type of objects can't be parsed automatically")
): ChatRequest, MultipartRequest<Boolean> {
    override fun method(): String = "setChatPhoto"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val mediaMap: Map<String, MultipartFile> = mapOf(photoField to photo)
    override val paramsJson: JsonObject = toJson(serializer())
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.setChatPhoto(
    chatId: ChatIdentifier,
    photo: MultipartFile
) = execute(SetChatPhoto(chatId, photo))

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.setChatPhoto(
    chat: PublicChat,
    photo: MultipartFile
) = setChatPhoto(chat.id, photo)
