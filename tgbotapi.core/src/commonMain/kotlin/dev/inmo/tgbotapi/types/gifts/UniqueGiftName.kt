package dev.inmo.tgbotapi.types.gifts

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents a name for a unique gift, encapsulating its value as a single, immutable string.
 */
@Serializable
@JvmInline
value class UniqueGiftName(val value: String) {
    val nftLink
        get() = "https://t.me/nft/$value"
}
