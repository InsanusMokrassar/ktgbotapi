package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.stickers.abstracts.OwnerStickerSetAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerSetAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetStickerEmojiList (
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(emojiListField)
    val emojis: List<String>
) : StickerAction<Boolean> {
    constructor(sticker: FileId, vararg emojis: String) : this(sticker, emojis.toList())

    init {
        require(emojis.size !in emojisInStickerLimit) {
            "Emojis size should be in range $emojisInStickerLimit, but was ${emojis.size}"
        }
    }

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerEmojiList"
}
