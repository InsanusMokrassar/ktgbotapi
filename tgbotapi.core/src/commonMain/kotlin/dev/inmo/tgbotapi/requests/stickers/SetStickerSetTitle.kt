package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class SetStickerSetTitle(
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(titleField)
    val title: String,
) : StickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerSetTitle"
}
