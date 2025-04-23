package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(KeyboardButtonRequestUsers.Companion::class)
@ClassCastsIncluded
sealed interface KeyboardButtonRequestUsers {
    val requestId: RequestId
    val isBot: Boolean?
    val isPremium: Boolean?
    val maxCount: Int
    val requestName: Boolean?
    val requestUsername: Boolean?
    val requestPhoto: Boolean?

    @Serializable
    data class Any(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(userIsPremiumField)
        override val isPremium: Boolean? = null,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first,
        @SerialName(requestNameField)
        override val requestName: Boolean? = null,
        @SerialName(requestUsernameField)
        override val requestUsername: Boolean? = null,
        @SerialName(requestPhotoField)
        override val requestPhoto: Boolean? = null,
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean? = null
    }

    @Serializable
    data class Common(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(userIsPremiumField)
        override val isPremium: Boolean? = null,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first,
        @SerialName(requestNameField)
        override val requestName: Boolean? = null,
        @SerialName(requestUsernameField)
        override val requestUsername: Boolean? = null,
        @SerialName(requestPhotoField)
        override val requestPhoto: Boolean? = null,
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = false
    }

    @Serializable
    data class Bot(
        @SerialName(requestIdField)
        override val requestId: RequestId,
        @SerialName(maxQuantityField)
        override val maxCount: Int = keyboardButtonRequestUserLimit.first,
        @SerialName(requestNameField)
        override val requestName: Boolean? = null,
        @SerialName(requestUsernameField)
        override val requestUsername: Boolean? = null,
        @SerialName(requestPhotoField)
        override val requestPhoto: Boolean? = null,
    ) : KeyboardButtonRequestUsers {
        @SerialName(userIsBotField)
        @EncodeDefault
        override val isBot: Boolean = true
        override val isPremium: Boolean?
            get() = null
    }

    companion object : KSerializer<KeyboardButtonRequestUsers> {
        @Serializable
        private data class Surrogate(
            @SerialName(requestIdField)
            val requestId: RequestId,
            @SerialName(userIsBotField)
            val userIsBot: Boolean? = null,
            @SerialName(userIsPremiumField)
            val userIsPremium: Boolean? = null,
            @SerialName(maxQuantityField)
            val maxCount: Int = keyboardButtonRequestUserLimit.first,
            @SerialName(requestNameField)
            val requestName: Boolean? = null,
            @SerialName(requestUsernameField)
            val requestUsername: Boolean? = null,
            @SerialName(requestPhotoField)
            val requestPhoto: Boolean? = null,
        )

        private val realSerializer = Surrogate.serializer()

        override val descriptor: SerialDescriptor = realSerializer.descriptor

        override fun deserialize(decoder: Decoder): KeyboardButtonRequestUsers {
            val surrogate = realSerializer.deserialize(decoder)

            return when (surrogate.userIsBot) {
                true ->
                    Bot(
                        requestId = surrogate.requestId,
                        maxCount = surrogate.maxCount,
                        requestName = surrogate.requestName,
                        requestUsername = surrogate.requestUsername,
                        requestPhoto = surrogate.requestPhoto,
                    )
                false ->
                    Common(
                        requestId = surrogate.requestId,
                        isPremium = surrogate.userIsPremium,
                        maxCount = surrogate.maxCount,
                        requestName = surrogate.requestName,
                        requestUsername = surrogate.requestUsername,
                        requestPhoto = surrogate.requestPhoto,
                    )
                null ->
                    Any(
                        requestId = surrogate.requestId,
                        isPremium = surrogate.userIsPremium,
                        maxCount = surrogate.maxCount,
                        requestName = surrogate.requestName,
                        requestUsername = surrogate.requestUsername,
                        requestPhoto = surrogate.requestPhoto,
                    )
            }
        }

        override fun serialize(
            encoder: Encoder,
            value: KeyboardButtonRequestUsers,
        ) {
            realSerializer.serialize(
                encoder,
                Surrogate(
                    requestId = value.requestId,
                    userIsBot = value.isBot,
                    userIsPremium = (value as? Common) ?.isPremium,
                    maxCount = value.maxCount,
                    requestName = value.requestName,
                    requestUsername = value.requestUsername,
                    requestPhoto = value.requestPhoto,
                ),
            )
        }
    }
}
