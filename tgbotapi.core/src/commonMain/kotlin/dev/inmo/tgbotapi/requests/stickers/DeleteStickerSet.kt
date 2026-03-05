package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteStickerSet(
    @SerialName(nameField)
    val name: StickerSetName
) : SimpleRequest<Unit> {
    override fun method(): String = "deleteStickerSet"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
