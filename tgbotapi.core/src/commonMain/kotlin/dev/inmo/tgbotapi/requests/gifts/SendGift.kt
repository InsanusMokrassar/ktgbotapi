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
    val userId: UserId? = null,
    @SerialName(chatIdField)
    val chatId: ChatIdentifier? = null,
    @SerialName(giftIdField)
    val giftId: GiftId,
    @SerialName(textField)
    override val text: String? = null,
    @SerialName(textParseModeField)
    override val parseMode: ParseMode?,
    @SerialName(textEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(payToUpgradeField)
    val upgradableToUnique: Boolean = false
) : SimpleRequest<Boolean>, TextedOutput {
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.let {
            text ?.let { _ ->
                it.asTextSources(text)
            }
        }
    }

    override fun method(): String = "sendGift"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()


    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    @Deprecated("Use factory function `toUser` instead", ReplaceWith("toUser(userId, giftId, text, parseMode, upgradableToUnique)"))
    constructor(
        userId: UserId,
        giftId: GiftId,
        text: String,
        parseMode: ParseMode?,
        upgradableToUnique: Boolean = false
    ) : this(
        userId = userId,
        giftId = giftId,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        upgradableToUnique = upgradableToUnique
    )

    @Deprecated("Use factory function `toUser` instead", ReplaceWith("toUser(userId, giftId, textSources, upgradableToUnique)"))
    constructor(
        userId: UserId,
        giftId: GiftId,
        textSources: TextSourcesList? = null,
        upgradableToUnique: Boolean = false,
    ) : this(
        userId = userId,
        giftId = giftId,
        text = textSources ?.makeSourceString(),
        parseMode = null,
        rawEntities = textSources ?.toRawMessageEntities(),
        upgradableToUnique = upgradableToUnique
    )

    companion object {
        fun toUser(
            userId: UserId,
            giftId: GiftId,
            text: String,
            parseMode: ParseMode?,
            upgradableToUnique: Boolean = false
        ) = SendGift(
            userId = userId,
            giftId = giftId,
            text = text,
            parseMode = parseMode,
            rawEntities = null,
            upgradableToUnique = upgradableToUnique
        )
        fun toUser(
            userId: UserId,
            giftId: GiftId,
            textSources: TextSourcesList? = null,
            upgradableToUnique: Boolean = false,
        ) = SendGift(
            userId = userId,
            giftId = giftId,
            text = textSources ?.makeSourceString(),
            parseMode = null,
            rawEntities = textSources ?.toRawMessageEntities(),
            upgradableToUnique = upgradableToUnique
        )
        fun toChat(
            chatId: ChatIdentifier,
            giftId: GiftId,
            text: String,
            parseMode: ParseMode?,
            upgradableToUnique: Boolean = false
        ) = SendGift(
            chatId = chatId,
            giftId = giftId,
            text = text,
            parseMode = parseMode,
            rawEntities = null,
            upgradableToUnique = upgradableToUnique
        )
        fun toChat(
            chatId: ChatIdentifier,
            giftId: GiftId,
            textSources: TextSourcesList? = null,
            upgradableToUnique: Boolean = false,
        ) = SendGift(
            chatId = chatId,
            giftId = giftId,
            text = textSources ?.makeSourceString(),
            parseMode = null,
            rawEntities = textSources ?.toRawMessageEntities(),
            upgradableToUnique = upgradableToUnique
        )
    }
}
