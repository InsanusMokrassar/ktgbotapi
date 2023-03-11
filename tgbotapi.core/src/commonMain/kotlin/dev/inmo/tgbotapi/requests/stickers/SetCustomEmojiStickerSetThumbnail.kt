package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class SetCustomEmojiStickerSetThumbnail (
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(customEmojiIdField)
    val customEmojiId: CustomEmojiId
) : StickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setCustomEmojiStickerSetThumbnail"
}
