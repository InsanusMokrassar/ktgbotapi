package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.stickers.abstracts.StickerAction
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Suppress("unused")
@Serializable
data class SetStickerKeywords (
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(stickerField)
    override val sticker: FileId,
    @SerialName(keywordsField)
    val keywords: List<String>
) : StickerAction<Boolean> {
    constructor(sticker: FileId, vararg keywords: String) : this(sticker, keywords.toList())

    init {
        require(keywords.size !in keywordsInStickerLimit) {
            "Keywords list size should be in range $keywordsInStickerLimit, but was ${keywords.size}"
        }
        keywords.forEach {
            require(it.length in stickerKeywordLengthLimit) {
                "Keyword length should be in range $stickerKeywordLengthLimit, but was ${it.length} (word \"$it\")"
            }
        }
    }

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerKeywords"
}
