package dev.inmo.tgbotapi.requests.gifts

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.monthCountField
import dev.inmo.tgbotapi.types.starCountField
import dev.inmo.tgbotapi.types.textEntitiesField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.textParseModeField
import dev.inmo.tgbotapi.types.userIdField
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class GiftPremiumSubscription internal constructor(
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(monthCountField)
    val monthCount: Int,
    @SerialName(starCountField)
    val starCount: Int,
    @SerialName(textField)
    override val text: String? = null,
    @SerialName(textParseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(textEntitiesField)
    private val rawEntities: RawMessageEntities? = null,
) : SimpleRequest<Boolean>, TextedOutput {
    override val textSources: TextSourcesList? by lazy {
        rawEntities?.let {
            text?.let { _ ->
                it.asTextSources(text)
            }
        }
    }

    override fun method(): String = "sendPremiumGift"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    constructor(
        userId: UserId,
        monthCount: Int,
        starCount: Int,
        text: String,
        parseMode: ParseMode?
    ) : this(
        userId = userId,
        monthCount = monthCount,
        starCount = starCount,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
    )

    constructor(
        userId: UserId,
        monthCount: Int,
        starCount: Int,
        textSources: TextSourcesList? = null
    ) : this(
        userId = userId,
        monthCount = monthCount,
        starCount = starCount,
        text = textSources?.makeSourceString(),
        parseMode = null,
        rawEntities = textSources?.toRawMessageEntities()
    )
}