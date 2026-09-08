package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.alternativeTextField
import dev.inmo.tgbotapi.types.anchorNameField
import dev.inmo.tgbotapi.types.bankCardNumberField
import dev.inmo.tgbotapi.types.buttonField
import dev.inmo.tgbotapi.types.botCommandFullField
import dev.inmo.tgbotapi.types.cashtagField
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.customEmojiIdField
import dev.inmo.tgbotapi.types.dateTimeFormatField
import dev.inmo.tgbotapi.types.emailAddressField
import dev.inmo.tgbotapi.types.expressionField
import dev.inmo.tgbotapi.types.hashtagField
import dev.inmo.tgbotapi.types.internalUserLinkBeginning
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.phoneNumberField
import dev.inmo.tgbotapi.types.referenceNameField
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.unixTimeField
import dev.inmo.tgbotapi.types.urlField
import dev.inmo.tgbotapi.types.userField
import dev.inmo.tgbotapi.types.usernameField
import dev.inmo.tgbotapi.utils.extensions.toHtml
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A [RichMessageButton] embedded in [RichText].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextbutton">RichTextButton</a>
 */
@Serializable
data class RichTextButton(
    @SerialName(buttonField)
    val button: RichMessageButton
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = button.text.rawText
    override val markdown: String = markdown(button)
    override val html: String = html(button)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "button"
        fun markdown(button: RichMessageButton): String = button.toRichMarkup(button.text.markdown)
        fun html(button: RichMessageButton): String = button.toRichMarkup(button.text.html)
    }
}

/**
 * A bold [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextbold">RichTextBold</a>
 */
@Serializable
data class RichTextBold(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "bold"
        fun markdown(text: RichText): String = "**${text.markdown}**"
        fun html(text: RichText): String = "<b>${text.html}</b>"
    }
}

/**
 * An italicized [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextitalic">RichTextItalic</a>
 */
@Serializable
data class RichTextItalic(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "italic"
        fun markdown(text: RichText): String = "*${text.markdown}*"
        fun html(text: RichText): String = "<i>${text.html}</i>"
    }
}

/**
 * An underlined [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextunderline">RichTextUnderline</a>
 */
@Serializable
data class RichTextUnderline(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "underline"
        fun markdown(text: RichText): String = "<u>${text.markdown}</u>"
        fun html(text: RichText): String = "<u>${text.html}</u>"
    }
}

/**
 * A strikethrough [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextstrikethrough">RichTextStrikethrough</a>
 */
@Serializable
data class RichTextStrikethrough(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "strikethrough"
        fun markdown(text: RichText): String = "~~${text.markdown}~~"
        fun html(text: RichText): String = "<s>${text.html}</s>"
    }
}

/**
 * A [RichTextEntity] covered by a spoiler.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextspoiler">RichTextSpoiler</a>
 */
@Serializable
data class RichTextSpoiler(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "spoiler"
        fun markdown(text: RichText): String = "||${text.markdown}||"
        fun html(text: RichText): String = "<tg-spoiler>${text.html}</tg-spoiler>"
    }
}

/**
 * A subscript [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextsubscript">RichTextSubscript</a>
 */
@Serializable
data class RichTextSubscript(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "subscript"
        fun markdown(text: RichText): String = "<sub>${text.markdown}</sub>"
        fun html(text: RichText): String = "<sub>${text.html}</sub>"
    }
}

/**
 * A superscript [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextsuperscript">RichTextSuperscript</a>
 */
@Serializable
data class RichTextSuperscript(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "superscript"
        fun markdown(text: RichText): String = "<sup>${text.markdown}</sup>"
        fun html(text: RichText): String = "<sup>${text.html}</sup>"
    }
}

/**
 * A marked [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextmarked">RichTextMarked</a>
 */
@Serializable
data class RichTextMarked(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "marked"
        fun markdown(text: RichText): String = "==${text.markdown}=="
        fun html(text: RichText): String = "<mark>${text.html}</mark>"
    }
}

/**
 * A monowidth [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextcode">RichTextCode</a>
 */
@Serializable
data class RichTextCode(
    @SerialName(textField)
    val text: RichText
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "code"
        fun markdown(text: RichText): String = "`${text.rawText}`"
        fun html(text: RichText): String = "<code>${text.html}</code>"
    }
}

/**
 * A formatted date and time [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextdatetime">RichTextDateTime</a>
 */
@Serializable
data class RichTextDateTime(
    @SerialName(textField)
    val text: RichText,
    @SerialName(unixTimeField)
    val unixTime: TelegramDate,
    @SerialName(dateTimeFormatField)
    val dateTimeFormat: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, unixTime, dateTimeFormat)
    override val html: String = html(text, unixTime, dateTimeFormat)
    override val isValidRichMessageButtonText: Boolean = text.isValidRichMessageButtonText

    companion object {
        const val TYPE = "date_time"
        fun markdown(text: RichText, unixTime: TelegramDate, dateTimeFormat: String): String =
            "![${text.markdown}](tg://time?unix=${unixTime.date}&format=$dateTimeFormat)"
        fun html(text: RichText, unixTime: TelegramDate, dateTimeFormat: String): String =
            "<tg-time unix=\"${unixTime.date}\" format=\"$dateTimeFormat\">${text.html}</tg-time>"
    }
}

/**
 * A mention of a Telegram user by their identifier.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtexttextmention">RichTextTextMention</a>
 */
@Serializable
data class RichTextTextMention(
    @SerialName(textField)
    val text: RichText,
    @SerialName(userField)
    val user: User
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, user)
    override val html: String = html(text, user)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "text_mention"
        fun markdown(text: RichText, user: User): String =
            "[${text.markdown}]($internalUserLinkBeginning${user.id.chatId.long})"
        fun html(text: RichText, user: User): String =
            "<a href=\"$internalUserLinkBeginning${user.id.chatId.long}\">${text.html}</a>"
    }
}

/**
 * A custom emoji [RichTextEntity].
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextcustomemoji">RichTextCustomEmoji</a>
 */
@Serializable
data class RichTextCustomEmoji(
    @SerialName(customEmojiIdField)
    val customEmojiId: CustomEmojiId,
    @SerialName(alternativeTextField)
    val alternativeText: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = alternativeText
    override val markdown: String = markdown(customEmojiId, alternativeText)
    override val html: String = html(customEmojiId, alternativeText)
    override val isValidRichMessageButtonText: Boolean = true

    companion object {
        const val TYPE = "custom_emoji"
        fun markdown(customEmojiId: CustomEmojiId, alternativeText: String): String =
            "![${alternativeText.escapeRichMarkdown()}](tg://emoji?id=${customEmojiId.string})"
        fun html(customEmojiId: CustomEmojiId, alternativeText: String): String =
            "<tg-emoji emoji-id=\"${customEmojiId.string}\">${alternativeText.toHtml()}</tg-emoji>"
    }
}

/**
 * A mathematical expression in LaTeX format.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextmathematicalexpression">RichTextMathematicalExpression</a>
 */
@Serializable
data class RichTextMathematicalExpression(
    @SerialName(expressionField)
    val expression: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = expression
    override val markdown: String = markdown(expression)
    override val html: String = html(expression)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "mathematical_expression"
        fun markdown(expression: String): String = "\$$expression\$"
        fun html(expression: String): String = "<tg-math>$expression</tg-math>"
    }
}

/**
 * A [RichTextEntity] with a link.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtexturl">RichTextUrl</a>
 */
@Serializable
data class RichTextUrl(
    @SerialName(textField)
    val text: RichText,
    @SerialName(urlField)
    val url: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, url)
    override val html: String = html(text, url)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "url"
        fun markdown(text: RichText, url: String): String = "[${text.markdown}]($url)"
        fun html(text: RichText, url: String): String = "<a href=\"$url\">${text.html}</a>"
    }
}

/**
 * A [RichTextEntity] with an email address.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextemailaddress">RichTextEmailAddress</a>
 */
@Serializable
data class RichTextEmailAddress(
    @SerialName(textField)
    val text: RichText,
    @SerialName(emailAddressField)
    val emailAddress: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, emailAddress)
    override val html: String = html(text, emailAddress)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "email_address"
        fun markdown(text: RichText, emailAddress: String): String = "[${text.markdown}](mailto:$emailAddress)"
        fun html(text: RichText, emailAddress: String): String = "<a href=\"mailto:$emailAddress\">${text.html}</a>"
    }
}

/**
 * A [RichTextEntity] with a phone number.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextphonenumber">RichTextPhoneNumber</a>
 */
@Serializable
data class RichTextPhoneNumber(
    @SerialName(textField)
    val text: RichText,
    @SerialName(phoneNumberField)
    val phoneNumber: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, phoneNumber)
    override val html: String = html(text, phoneNumber)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "phone_number"
        fun markdown(text: RichText, phoneNumber: String): String = "[${text.markdown}](tel:$phoneNumber)"
        fun html(text: RichText, phoneNumber: String): String = "<a href=\"tel:$phoneNumber\">${text.html}</a>"
    }
}

/**
 * A [RichTextEntity] with a bank card number.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextbankcardnumber">RichTextBankCardNumber</a>
 */
@Serializable
data class RichTextBankCardNumber(
    @SerialName(textField)
    val text: RichText,
    @SerialName(bankCardNumberField)
    val bankCardNumber: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "bank_card_number"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = text.html
    }
}

/**
 * A mention by a username.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextmention">RichTextMention</a>
 */
@Serializable
data class RichTextMention(
    @SerialName(textField)
    val text: RichText,
    @SerialName(usernameField)
    val username: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "mention"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = text.html
    }
}

/**
 * A hashtag.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtexthashtag">RichTextHashtag</a>
 */
@Serializable
data class RichTextHashtag(
    @SerialName(textField)
    val text: RichText,
    @SerialName(hashtagField)
    val hashtag: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "hashtag"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = text.html
    }
}

/**
 * A cashtag.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextcashtag">RichTextCashtag</a>
 */
@Serializable
data class RichTextCashtag(
    @SerialName(textField)
    val text: RichText,
    @SerialName(cashtagField)
    val cashtag: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "cashtag"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = text.html
    }
}

/**
 * A bot command.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextbotcommand">RichTextBotCommand</a>
 */
@Serializable
data class RichTextBotCommand(
    @SerialName(textField)
    val text: RichText,
    @SerialName(botCommandFullField)
    val botCommand: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "bot_command"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = text.html
    }
}

/**
 * An anchor.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextanchor">RichTextAnchor</a>
 */
@Serializable
data class RichTextAnchor(
    @SerialName(nameField)
    val name: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = ""
    override val markdown: String = markdown(name)
    override val html: String = html(name)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "anchor"
        fun markdown(name: String): String = "<a name=\"$name\"></a>"
        fun html(name: String): String = "<a name=\"$name\"></a>"
    }
}

/**
 * A link to an anchor.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextanchorlink">RichTextAnchorLink</a>
 */
@Serializable
data class RichTextAnchorLink(
    @SerialName(textField)
    val text: RichText,
    @SerialName(anchorNameField)
    val anchorName: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, anchorName)
    override val html: String = html(text, anchorName)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "anchor_link"
        fun markdown(text: RichText, anchorName: String): String = "[${text.markdown}](#$anchorName)"
        fun html(text: RichText, anchorName: String): String = "<a href=\"#$anchorName\">${text.html}</a>"
    }
}

/**
 * A reference.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextreference">RichTextReference</a>
 */
@Serializable
data class RichTextReference(
    @SerialName(textField)
    val text: RichText,
    @SerialName(nameField)
    val name: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, name)
    override val html: String = html(text, name)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "reference"
        fun markdown(text: RichText, name: String): String = "<tg-reference name=\"$name\">${text.markdown}</tg-reference>"
        fun html(text: RichText, name: String): String = "<tg-reference name=\"$name\">${text.html}</tg-reference>"
    }
}

/**
 * A link to a reference.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtextreferencelink">RichTextReferenceLink</a>
 */
@Serializable
data class RichTextReferenceLink(
    @SerialName(textField)
    val text: RichText,
    @SerialName(referenceNameField)
    val referenceName: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val rawText: String = text.rawText
    override val markdown: String = markdown(text, referenceName)
    override val html: String = html(text, referenceName)
    override val isValidRichMessageButtonText: Boolean = false

    companion object {
        const val TYPE = "reference_link"
        fun markdown(text: RichText, referenceName: String): String = "[${text.markdown}](#$referenceName)"
        fun html(text: RichText, referenceName: String): String = "<a href=\"#$referenceName\">${text.html}</a>"
    }
}
