package dev.inmo.tgbotapi.requests.edit.location.live

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

@Serializable
data class EditInlineMessageLiveLocation(
    @SerialName(inlineMessageIdField)
    override val inlineMessageId: InlineMessageId,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
    @SerialName(headingField)
    override val heading: Degrees? = null,
    @SerialName(proximityAlertRadiusField)
    override val proximityAlertRadius: Meters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
) : EditInlineMessage, EditReplyMessage, EditLocationMessage {
    override fun method(): String = editMessageLiveLocationMethod

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (horizontalAccuracy != null && horizontalAccuracy !in horizontalAccuracyLimit) {
            throwRangeError("horizontalAccuracy", horizontalAccuracyLimit, horizontalAccuracy)
        }
    }
}
