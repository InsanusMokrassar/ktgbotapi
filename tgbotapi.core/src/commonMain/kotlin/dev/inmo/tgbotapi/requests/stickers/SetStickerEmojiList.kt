package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Suppress("unused")
@Serializable
data class SetStickerEmojiList (
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(emojiListField)
    val emojis: List<String>
) : StickerAction<Unit> {
    constructor(sticker: FileId, vararg emojis: String) : this(sticker, emojis.toList())

    init {
        require(emojis.size !in emojisInStickerLimit) {
            "Emojis size should be in range $emojisInStickerLimit, but was ${emojis.size}"
        }
    }

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerEmojiList"
}
