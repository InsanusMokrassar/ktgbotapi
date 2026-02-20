package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftBackdrop
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftColors
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftModel
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftSymbol
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Gift.Companion::class)
sealed interface Gift {
    val id: GiftId?
    val publisherChat: PreviewChat?
    val isPremium: Boolean
    @Serializable(Regular.Companion::class)
    sealed interface Regular : Gift {
        override val id: GiftId
        val sticker: Sticker
        val starCount: Int
        val totalCount: Int?
        val upgradeStarCount: Int?
        val remainingCount: Int?
        val personalTotalCount: Int?
        val personalRemainingCount: Int?
        val hasColors: Boolean
        val background: GiftBackground?
        val uniqueGiftVariantCount: Int?

        /**
         * Represents a [Gift](https://core.telegram.org/bots/api#gift) for fields of unlimited gifts
         */
        @Serializable
        data class Unlimited(
            @SerialName(idField)
            override val id: GiftId,
            @SerialName(stickerField)
            override val sticker: Sticker,
            @SerialName(starCountField)
            override val starCount: Int,
            @SerialName(upgradeStarCountField)
            override val upgradeStarCount: Int? = null,
            @SerialName(publisherChatField)
            override val publisherChat: PreviewChat? = null,
            @SerialName(personalTotalCountField)
            override val personalTotalCount: Int? = null,
            @SerialName(personalRemainingCountField)
            override val personalRemainingCount: Int? = null,
            @SerialName(isPremiumField)
            override val isPremium: Boolean = false,
            @SerialName(hasColorsField)
            override val hasColors: Boolean = false,
            @SerialName(backgroundField)
            override val background: GiftBackground? = null,
            @SerialName(uniqueGiftVariantCountField)
            override val uniqueGiftVariantCount: Int? = null,
        ) : Regular {
            override val totalCount: Int?
                get() = null
            override val remainingCount: Int?
                get() = null
        }

        /**
         * Represents a [Gift](https://core.telegram.org/bots/api#gift) for fields of limited gifts
         */
        @Serializable
        data class Limited(
            @SerialName(idField)
            override val id: GiftId,
            @SerialName(stickerField)
            override val sticker: Sticker,
            @SerialName(starCountField)
            override val starCount: Int,
            @SerialName(totalCountField)
            override val totalCount: Int,
            @SerialName(remainingCountField)
            override val remainingCount: Int,
            @SerialName(upgradeStarCountField)
            override val upgradeStarCount: Int? = null,
            @SerialName(publisherChatField)
            override val publisherChat: PreviewChat? = null,
            @SerialName(personalTotalCountField)
            override val personalTotalCount: Int? = null,
            @SerialName(personalRemainingCountField)
            override val personalRemainingCount: Int? = null,
            @SerialName(isPremiumField)
            override val isPremium: Boolean = false,
            @SerialName(hasColorsField)
            override val hasColors: Boolean = false,
            @SerialName(backgroundField)
            override val background: GiftBackground? = null,
            @SerialName(uniqueGiftVariantCountField)
            override val uniqueGiftVariantCount: Int? = null,
        ) : Regular

        companion object : KSerializer<Regular> {
            @Serializable
            @Suppress("propertyName")
            private data class RegularGiftSurrogate(
                val id: GiftId,
                val sticker: Sticker,
                val star_count: Int,
                val total_count: Int? = null,
                val remaining_count: Int? = null,
                val upgrade_star_count: Int? = null,
                val publisher_chat: PreviewChat? = null,
                val personal_total_count: Int? = null,
                val personal_remaining_count: Int? = null,
                val is_premium: Boolean = false,
                val has_colors: Boolean = false,
                val background: GiftBackground? = null,
                val unique_gift_variant_count: Int? = null,
            )

            override val descriptor: SerialDescriptor
                get() = RegularGiftSurrogate.serializer().descriptor

            override fun deserialize(decoder: Decoder): Regular {
                val surrogate = RegularGiftSurrogate.serializer().deserialize(decoder)

                return if (surrogate.total_count != null && surrogate.remaining_count != null) {
                    Limited(
                        id = surrogate.id,
                        sticker = surrogate.sticker,
                        starCount = surrogate.star_count,
                        totalCount = surrogate.total_count,
                        remainingCount = surrogate.remaining_count,
                        upgradeStarCount = surrogate.upgrade_star_count,
                        publisherChat = surrogate.publisher_chat,
                        personalTotalCount = surrogate.personal_total_count,
                        personalRemainingCount = surrogate.personal_remaining_count,
                        isPremium = surrogate.is_premium,
                        hasColors = surrogate.has_colors,
                        background = surrogate.background,
                        uniqueGiftVariantCount = surrogate.unique_gift_variant_count
                    )
                } else {
                    Unlimited(
                        id = surrogate.id,
                        sticker = surrogate.sticker,
                        starCount = surrogate.star_count,
                        upgradeStarCount = surrogate.upgrade_star_count,
                        publisherChat = surrogate.publisher_chat,
                        personalTotalCount = surrogate.personal_total_count,
                        personalRemainingCount = surrogate.personal_remaining_count,
                        isPremium = surrogate.is_premium,
                        hasColors = surrogate.has_colors,
                        background = surrogate.background,
                        uniqueGiftVariantCount = surrogate.unique_gift_variant_count
                    )
                }
            }

            override fun serialize(encoder: Encoder, value: Regular) {
                val surrogate = RegularGiftSurrogate(
                    id = value.id,
                    sticker = value.sticker,
                    star_count = value.starCount,
                    total_count = value.totalCount,
                    remaining_count = value.remainingCount,
                    upgrade_star_count = value.upgradeStarCount,
                    publisher_chat = value.publisherChat,
                    personal_total_count = value.personalTotalCount,
                    personal_remaining_count = value.personalRemainingCount,
                    is_premium = value.isPremium,
                    has_colors = value.hasColors,
                    background = value.background,
                    unique_gift_variant_count = value.uniqueGiftVariantCount
                )
                RegularGiftSurrogate.serializer().serialize(encoder, surrogate)
            }
        }
    }

    /**
     * Represents a [UniqueGift](https://core.telegram.org/bots/api#uniquegift) from telegram bots api
     */
    @Serializable
    data class Unique(
        @SerialName(giftIdField)
        override val id: GiftId? = null,
        @SerialName(baseNameField)
        val baseName: String,
        @SerialName(nameField)
        val name: UniqueGiftName,
        @SerialName(numberField)
        val number: Int,
        @SerialName(modelField)
        val model: UniqueGiftModel,
        @SerialName(symbolField)
        val symbol: UniqueGiftSymbol,
        @SerialName(backdropField)
        val backdrop: UniqueGiftBackdrop,
        @SerialName(isPremiumField)
        override val isPremium: Boolean = false,
        @SerialName(isBurnedField)
        val isBurned: Boolean = false,
        @SerialName(isFromBlockchainField)
        val isFromBlockchain: Boolean = false,
        @SerialName(colorsField)
        val colors: UniqueGiftColors? = null,
        @SerialName(publisherChatField)
        override val publisherChat: PreviewChat? = null,
    ) : Gift

    companion object : KSerializer<Gift> {
        @Serializable
        @Suppress("unused", "propertyName")
        private class GiftSurrogate(
            // regular gift fields
            val id: GiftId?,
            val sticker: Sticker?,
            val star_count: Int?,
            val total_count: Int? = null,
            val remaining_count: Int? = null,
            val upgrade_star_count: Int? = null,
            val personal_total_count: Int? = null,
            val personal_remaining_count: Int? = null,
            val is_premium: Boolean = false,
            val has_colors: Boolean = false,
            val background: GiftBackground? = null,
            val unique_gift_variant_count: Int? = null,
            // unique gift fields
            val base_name: String? = null,
            val name: UniqueGiftName? = null,
            val number: Int? = null,
            val model: UniqueGiftModel? = null,
            val symbol: UniqueGiftSymbol? = null,
            val backdrop: UniqueGiftBackdrop? = null,
            val is_from_blockchain: Boolean = false,
            val colors: UniqueGiftColors? = null,
            val publisher_chat: PreviewChat? = null,
        )

        override val descriptor: SerialDescriptor
            get() = GiftSurrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): Gift {
            val surrogate = GiftSurrogate.serializer().deserialize(decoder)

            return if (surrogate.base_name != null && surrogate.name != null && surrogate.number != null && surrogate.model != null && surrogate.symbol != null && surrogate.backdrop != null) {
                Unique(
                    id = surrogate.id,
                    baseName = surrogate.base_name,
                    name = surrogate.name,
                    number = surrogate.number,
                    model = surrogate.model,
                    symbol = surrogate.symbol,
                    backdrop = surrogate.backdrop,
                    publisherChat = surrogate.publisher_chat,
                    isFromBlockchain = surrogate.is_from_blockchain,
                    isPremium = surrogate.is_premium,
                    colors = surrogate.colors
                )
            } else {
                decoder.decodeSerializableValue(Regular.serializer())
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: Gift
        ) {
            val surrogate = GiftSurrogate(
                id = value.id,
                sticker = (value as? Regular)?.sticker,
                star_count = (value as? Regular)?.starCount,
                total_count = (value as? Regular.Limited)?.totalCount,
                remaining_count = (value as? Regular.Limited)?.remainingCount,
                upgrade_star_count = (value as? Regular)?.upgradeStarCount,
                personal_total_count = (value as? Regular)?.personalTotalCount,
                personal_remaining_count = (value as? Regular)?.personalRemainingCount,
                is_premium = (value as? Regular)?.isPremium ?: (value as? Unique)?.isPremium ?: false,
                has_colors = (value as? Regular)?.hasColors ?: false,
                background = (value as? Regular)?.background,
                unique_gift_variant_count = (value as? Regular)?.uniqueGiftVariantCount,
                base_name = (value as? Unique)?.baseName,
                name = (value as? Unique)?.name,
                number = (value as? Unique)?.number,
                model = (value as? Unique)?.model,
                symbol = (value as? Unique)?.symbol,
                backdrop = (value as? Unique)?.backdrop,
                is_from_blockchain = (value as? Unique)?.isFromBlockchain ?: false,
                colors = (value as? Unique)?.colors,
                publisher_chat = value.publisherChat,
            )
            GiftSurrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
