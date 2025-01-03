package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.Sticker
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Gift.Companion::class)
sealed interface Gift {
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
        override val upgradeStarCount: Int? = null
    ) : Gift {
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
    ) : Gift

    companion object : KSerializer<Gift> {
        @Serializable
        private data class GiftSurrogate(
            val id: GiftId,
            val sticker: Sticker,
            val star_count: Int,
            val total_count: Int? = null,
            val remaining_count: Int? = null,
            val upgrade_star_count: Int? = null,
        )

        override val descriptor: SerialDescriptor
            get() = GiftSurrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): Gift {
            val surrogate = GiftSurrogate.serializer().deserialize(decoder)

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

        override fun serialize(encoder: Encoder, value: Gift) {
            val surrogate = GiftSurrogate(
                id = value.id,
                sticker = value.sticker,
                star_count = value.starCount,
                total_count = value.totalCount,
                remaining_count = value.remainingCount,
                upgrade_star_count = value.upgradeStarCount
            )
            GiftSurrogate.serializer().serialize(encoder, surrogate)
        }
    }
}
