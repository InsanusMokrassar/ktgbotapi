package com.github.insanusmokrassar.TelegramBotAPI.requests.chat.modify

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ChatRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeMapper

@Serializable
data class SetChatPhoto (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Transient
    val photo: MultipartFile = throw IllegalArgumentException("Unfortunately, this type of objects can't be parsed automatically")
): ChatRequest, MultipartRequest<Boolean> {
    override fun method(): String = "setChatPhoto"
    override fun resultSerializer(): KSerializer<Boolean> = BooleanSerializer
    override val mediaMap: Map<String, MultipartFile> = mapOf(photoField to photo)
    override val paramsJson: JsonObject = JsonTreeMapper().writeTree(this, serializer()).jsonObject
}
