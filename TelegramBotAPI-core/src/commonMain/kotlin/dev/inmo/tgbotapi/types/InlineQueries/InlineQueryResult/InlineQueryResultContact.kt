package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult

import dev.inmo.tgbotapi.CommonAbstracts.CommonContactData
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQueryResultContact(
    @SerialName(idField)
    override val id: InlineQueryIdentifier,
    @SerialName(phoneNumberField)
    override val phoneNumber: String,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String? = null,
    @SerialName(vcardField)
    override val vcard: String? = null,
    @SerialName(thumbUrlField)
    override val thumbUrl: String? = null,
    @SerialName(thumbWidthField)
    override val thumbWidth: Int? = null,
    @SerialName(thumbHeightField)
    override val thumbHeight: Int? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null,
    @SerialName(inputMessageContentField)
    override val inputMessageContent: InputMessageContent? = null
) : InlineQueryResult,
    CommonContactData,
    WithInputMessageContentInlineQueryResult,
    ThumbedInlineQueryResult,
    ThumbSizedInlineQueryResult
{
    override val type: String = "contact"
}
