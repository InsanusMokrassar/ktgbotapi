package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class SendGift internal constructor(
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(giftIdField)
    val giftId: GiftId,
    @SerialName(textField)
    override val text: String,
    @SerialName(textParseModeField)
    override val parseMode: ParseMode?,
    @SerialName(textEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
) : SimpleRequest<Boolean>, TextedOutput {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text)
    }

    override fun method(): String = "sendGift"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()


    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    constructor(
        userId: UserId,
        giftId: GiftId,
        text: String,
        parseMode: ParseMode?
    ) : this(userId, giftId, text, parseMode, null)

    constructor(
        userId: UserId,
        giftId: GiftId,
        textSources: TextSourcesList,
    ) : this(userId, giftId, textSources.makeSourceString(), null, textSources.toRawMessageEntities())
}
