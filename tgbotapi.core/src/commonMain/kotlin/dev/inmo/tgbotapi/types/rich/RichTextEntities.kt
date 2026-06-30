package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.alternativeTextField
import dev.inmo.tgbotapi.types.anchorNameField
import dev.inmo.tgbotapi.types.bankCardNumberField
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

    override val markdown: String
        get() = "**${text.markdown}**"
    override val html: String
        get() = "<b>${text.html}</b>"

    companion object {
        const val TYPE = "bold"
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

    override val markdown: String
        get() = "*${text.markdown}*"
    override val html: String
        get() = "<i>${text.html}</i>"

    companion object {
        const val TYPE = "italic"
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

    override val markdown: String
        get() = "<u>${text.markdown}</u>"
    override val html: String
        get() = "<u>${text.html}</u>"

    companion object {
        const val TYPE = "underline"
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

    override val markdown: String
        get() = "~~${text.markdown}~~"
    override val html: String
        get() = "<s>${text.html}</s>"

    companion object {
        const val TYPE = "strikethrough"
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

    override val markdown: String
        get() = "||${text.markdown}||"
    override val html: String
        get() = "<tg-spoiler>${text.html}</tg-spoiler>"

    companion object {
        const val TYPE = "spoiler"
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

    override val markdown: String
        get() = "<sub>${text.markdown}</sub>"
    override val html: String
        get() = "<sub>${text.html}</sub>"

    companion object {
        const val TYPE = "subscript"
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

    override val markdown: String
        get() = "<sup>${text.markdown}</sup>"
    override val html: String
        get() = "<sup>${text.html}</sup>"

    companion object {
        const val TYPE = "superscript"
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

    override val markdown: String
        get() = "==${text.markdown}=="
    override val html: String
        get() = "<mark>${text.html}</mark>"

    companion object {
        const val TYPE = "marked"
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

    override val markdown: String
        get() = "`${text.source}`"
    override val html: String
        get() = "<code>${text.html}</code>"

    companion object {
        const val TYPE = "code"
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
    val unixTime: Long,
    @SerialName(dateTimeFormatField)
    val dateTimeFormat: String
) : RichTextEntity {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String
        get() = "![${text.markdown}](tg://time?unix=$unixTime&format=$dateTimeFormat)"
    override val html: String
        get() = "<tg-time unix=\"$unixTime\" format=\"$dateTimeFormat\">${text.html}</tg-time>"

    companion object {
        const val TYPE = "date_time"
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

    override val markdown: String
        get() = "[${text.markdown}]($internalUserLinkBeginning${user.id.chatId.long})"
    override val html: String
        get() = "<a href=\"$internalUserLinkBeginning${user.id.chatId.long}\">${text.html}</a>"

    companion object {
        const val TYPE = "text_mention"
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

    override val markdown: String
        get() = "![${alternativeText.escapeRichMarkdown()}](tg://emoji?id=${customEmojiId.string})"
    override val html: String
        get() = "<tg-emoji emoji-id=\"${customEmojiId.string}\">${alternativeText.toHtml()}</tg-emoji>"

    companion object {
        const val TYPE = "custom_emoji"
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

    override val markdown: String
        get() = "\$$expression\$"
    override val html: String
        get() = "<tg-math>$expression</tg-math>"

    companion object {
        const val TYPE = "mathematical_expression"
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

    override val markdown: String
        get() = "[${text.markdown}]($url)"
    override val html: String
        get() = "<a href=\"$url\">${text.html}</a>"

    companion object {
        const val TYPE = "url"
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

    override val markdown: String
        get() = "[${text.markdown}](mailto:$emailAddress)"
    override val html: String
        get() = "<a href=\"mailto:$emailAddress\">${text.html}</a>"

    companion object {
        const val TYPE = "email_address"
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

    override val markdown: String
        get() = "[${text.markdown}](tel:$phoneNumber)"
    override val html: String
        get() = "<a href=\"tel:$phoneNumber\">${text.html}</a>"

    companion object {
        const val TYPE = "phone_number"
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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = text.html

    companion object {
        const val TYPE = "bank_card_number"
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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = text.html

    companion object {
        const val TYPE = "mention"
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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = text.html

    companion object {
        const val TYPE = "hashtag"
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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = text.html

    companion object {
        const val TYPE = "cashtag"
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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = text.html

    companion object {
        const val TYPE = "bot_command"
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

    override val markdown: String
        get() = "<a name=\"$name\"></a>"
    override val html: String
        get() = "<a name=\"$name\"></a>"

    companion object {
        const val TYPE = "anchor"
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

    override val markdown: String
        get() = "[${text.markdown}](#$anchorName)"
    override val html: String
        get() = "<a href=\"#$anchorName\">${text.html}</a>"

    companion object {
        const val TYPE = "anchor_link"
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

    override val markdown: String
        get() = "<tg-reference name=\"$name\">${text.markdown}</tg-reference>"
    override val html: String
        get() = "<tg-reference name=\"$name\">${text.html}</tg-reference>"

    companion object {
        const val TYPE = "reference"
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

    override val markdown: String
        get() = "[${text.markdown}](#$referenceName)"
    override val html: String
        get() = "<a href=\"#$referenceName\">${text.html}</a>"

    companion object {
        const val TYPE = "reference_link"
    }
}
