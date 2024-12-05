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
    val remainingCount: Int?

    @Serializable
    data class Unlimited(
        @SerialName(idField)
        override val id: GiftId,
        @SerialName(stickerField)
        override val sticker: Sticker,
        @SerialName(starCountField)
        override val starCount: Int
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
    ) : Gift

    companion object : KSerializer<Gift> {
        @Serializable
        private data class GiftSurrogate(
            val id: GiftId,
            val sticker: Sticker,
            val star_count: Int,
            val total_count: Int? = null,
            val remaining_count: Int? = null,
        )

        override val descriptor: SerialDescriptor
            get() = GiftSurrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): Gift {
            val surrogate = GiftSurrogate.serializer().deserialize(decoder)

            return if (surrogate.total_count != null && surrogate.remaining_count != null) {
                Limited(
                    surrogate.id,
                    surrogate.sticker,
                    surrogate.star_count,
                    surrogate.total_count,
                    surrogate.remaining_count
                )
            } else {
                Unlimited(
                    surrogate.id,
                    surrogate.sticker,
                    surrogate.star_count,
                )
            }
        }

        override fun serialize(encoder: Encoder, value: Gift) {
            val surrogate = GiftSurrogate(
                value.id,
                value.sticker,
                value.starCount,
                value.totalCount,
                value.remainingCount
            )
        }
    }
}
