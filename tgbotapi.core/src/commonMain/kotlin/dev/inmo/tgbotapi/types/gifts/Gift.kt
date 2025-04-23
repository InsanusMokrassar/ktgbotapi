package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.gifts.unique.UniqueGiftBackdrop
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
    @Serializable(Regular.Companion::class)
    sealed interface Regular : Gift {
        val id: GiftId
        val sticker: Sticker
        val starCount: Int
        val totalCount: Int?
        val upgradeStarCount: Int?
        val remainingCount: Int?

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
        ) : Regular {
            override val totalCount: Int?
                get() = null
            override val remainingCount: Int?
                get() = null
        }

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
                    )
                } else {
                    Unlimited(
                        id = surrogate.id,
                        sticker = surrogate.sticker,
                        starCount = surrogate.star_count,
                        upgradeStarCount = surrogate.upgrade_star_count,
                    )
                }
            }

            override fun serialize(
                encoder: Encoder,
                value: Regular,
            ) {
                val surrogate = RegularGiftSurrogate(
                    id = value.id,
                    sticker = value.sticker,
                    star_count = value.starCount,
                    total_count = value.totalCount,
                    remaining_count = value.remainingCount,
                    upgrade_star_count = value.upgradeStarCount,
                )
                RegularGiftSurrogate.serializer().serialize(encoder, surrogate)
            }
        }
    }

    @Serializable
    data class Unique(
        @SerialName(baseNameField)
        val baseName: String,
        @SerialName(nameField)
        val name: String,
        @SerialName(numberField)
        val number: Int,
        @SerialName(modelField)
        val model: UniqueGiftModel,
        @SerialName(symbolField)
        val symbol: UniqueGiftSymbol,
        @SerialName(backdropField)
        val backdrop: UniqueGiftBackdrop,
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
            // unique gift fields
            val base_name: String? = null,
            val name: String? = null,
            val number: Int? = null,
            val model: UniqueGiftModel? = null,
            val symbol: UniqueGiftSymbol? = null,
            val backdrop: UniqueGiftBackdrop? = null,
        )

        override val descriptor: SerialDescriptor
            get() = GiftSurrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): Gift {
            val surrogate = GiftSurrogate.serializer().deserialize(decoder)

            return if (surrogate.base_name != null && surrogate.name != null && surrogate.number != null && surrogate.model != null && surrogate.symbol != null && surrogate.backdrop != null) {
                Unique(
                    baseName = surrogate.base_name,
                    name = surrogate.name,
                    number = surrogate.number,
                    model = surrogate.model,
                    symbol = surrogate.symbol,
                    backdrop = surrogate.backdrop,
                )
            } else {
                decoder.decodeSerializableValue(Regular.serializer())
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: Gift,
        ) {
            val surrogate = GiftSurrogate(
                id = (value as? Regular)?.id,
                sticker = (value as? Regular)?.sticker,
                star_count = (value as? Regular)?.starCount,
                total_count = (value as? Regular.Limited)?.totalCount,
                remaining_count = (value as? Regular.Limited)?.remainingCount,
                upgrade_star_count = (value as? Regular)?.upgradeStarCount,
                base_name = (value as? Unique)?.baseName,
                name = (value as? Unique)?.name,
                number = (value as? Unique)?.number,
                model = (value as? Unique)?.model,
                symbol = (value as? Unique)?.symbol,
                backdrop = (value as? Unique)?.backdrop,
            )
            GiftSurrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
