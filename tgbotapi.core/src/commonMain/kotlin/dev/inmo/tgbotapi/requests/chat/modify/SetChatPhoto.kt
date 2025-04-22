package dev.inmo.tgbotapi.requests.chat.modify

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.toJson
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject

@Serializable
data class SetChatPhoto(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @Transient
    val photo: MultipartFile = throw IllegalArgumentException("Unfortunately, this type of objects can't be parsed automatically"),
) : ChatRequest, MultipartRequest<Boolean> {
    override fun method(): String = "setChatPhoto"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    @Transient
    override val mediaMap: Map<String, MultipartFile> = mapOf(photoField to photo)

    @Transient
    override val paramsJson: JsonObject = toJson(serializer())
}
