package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.chat.User

/**
 * [DslMarker] for the rich message builders, so the inner [RichTextBuilder], [RichBlocksBuilder] and
 * [RichBlockListBuilder] scopes do not leak their receivers into each other.
 */
@DslMarker
annotation class RichTextDsl

/**
 * Builder of a single [RichText]. Each call appends a part; [build] returns a [RichTextPlain]/[RichTextEntity] when there
 * is exactly one part, a [RichTextGroup] otherwise.
 */
@RichTextDsl
class RichTextBuilder {
    private val parts = mutableListOf<RichText>()

    /** Appends an already built [RichText]. */
    fun add(richText: RichText) {
        parts.add(richText)
    }

    /** Appends an already built [RichText]. */
    operator fun RichText.unaryPlus() = add(this)

    /** Plain, non-formatted text. */
    fun plain(text: String) = add(RichTextPlain(text))

    fun bold(text: String) = add(RichTextBold(RichTextPlain(text)))
    fun bold(block: RichTextBuilder.() -> Unit) = add(RichTextBold(buildRichText(block)))

    fun italic(text: String) = add(RichTextItalic(RichTextPlain(text)))
    fun italic(block: RichTextBuilder.() -> Unit) = add(RichTextItalic(buildRichText(block)))

    fun underline(text: String) = add(RichTextUnderline(RichTextPlain(text)))
    fun underline(block: RichTextBuilder.() -> Unit) = add(RichTextUnderline(buildRichText(block)))

    fun strikethrough(text: String) = add(RichTextStrikethrough(RichTextPlain(text)))
    fun strikethrough(block: RichTextBuilder.() -> Unit) = add(RichTextStrikethrough(buildRichText(block)))

    fun spoiler(text: String) = add(RichTextSpoiler(RichTextPlain(text)))
    fun spoiler(block: RichTextBuilder.() -> Unit) = add(RichTextSpoiler(buildRichText(block)))

    fun subscript(text: String) = add(RichTextSubscript(RichTextPlain(text)))
    fun subscript(block: RichTextBuilder.() -> Unit) = add(RichTextSubscript(buildRichText(block)))

    fun superscript(text: String) = add(RichTextSuperscript(RichTextPlain(text)))
    fun superscript(block: RichTextBuilder.() -> Unit) = add(RichTextSuperscript(buildRichText(block)))

    fun marked(text: String) = add(RichTextMarked(RichTextPlain(text)))
    fun marked(block: RichTextBuilder.() -> Unit) = add(RichTextMarked(buildRichText(block)))

    fun code(text: String) = add(RichTextCode(RichTextPlain(text)))
    fun code(block: RichTextBuilder.() -> Unit) = add(RichTextCode(buildRichText(block)))

    fun dateTime(unixTime: Long, dateTimeFormat: String, text: String) =
        add(RichTextDateTime(RichTextPlain(text), unixTime, dateTimeFormat))
    fun dateTime(unixTime: Long, dateTimeFormat: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextDateTime(buildRichText(block), unixTime, dateTimeFormat))

    fun textMention(user: User, text: String) = add(RichTextTextMention(RichTextPlain(text), user))
    fun textMention(user: User, block: RichTextBuilder.() -> Unit) = add(RichTextTextMention(buildRichText(block), user))

    fun customEmoji(customEmojiId: CustomEmojiId, alternativeText: String) =
        add(RichTextCustomEmoji(customEmojiId, alternativeText))

    fun mathematicalExpression(expression: String) = add(RichTextMathematicalExpression(expression))

    fun url(url: String, text: String) = add(RichTextUrl(RichTextPlain(text), url))
    fun url(url: String, block: RichTextBuilder.() -> Unit) = add(RichTextUrl(buildRichText(block), url))

    fun email(emailAddress: String, text: String) = add(RichTextEmailAddress(RichTextPlain(text), emailAddress))
    fun email(emailAddress: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextEmailAddress(buildRichText(block), emailAddress))

    fun phone(phoneNumber: String, text: String) = add(RichTextPhoneNumber(RichTextPlain(text), phoneNumber))
    fun phone(phoneNumber: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextPhoneNumber(buildRichText(block), phoneNumber))

    fun bankCard(bankCardNumber: String, text: String) = add(RichTextBankCardNumber(RichTextPlain(text), bankCardNumber))
    fun bankCard(bankCardNumber: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextBankCardNumber(buildRichText(block), bankCardNumber))

    fun mention(username: String, text: String) = add(RichTextMention(RichTextPlain(text), username))
    fun mention(username: String, block: RichTextBuilder.() -> Unit) = add(RichTextMention(buildRichText(block), username))

    fun hashtag(hashtag: String, text: String) = add(RichTextHashtag(RichTextPlain(text), hashtag))
    fun hashtag(hashtag: String, block: RichTextBuilder.() -> Unit) = add(RichTextHashtag(buildRichText(block), hashtag))

    fun cashtag(cashtag: String, text: String) = add(RichTextCashtag(RichTextPlain(text), cashtag))
    fun cashtag(cashtag: String, block: RichTextBuilder.() -> Unit) = add(RichTextCashtag(buildRichText(block), cashtag))

    fun botCommand(botCommand: String, text: String) = add(RichTextBotCommand(RichTextPlain(text), botCommand))
    fun botCommand(botCommand: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextBotCommand(buildRichText(block), botCommand))

    fun anchor(name: String) = add(RichTextAnchor(name))

    fun anchorLink(anchorName: String, text: String) = add(RichTextAnchorLink(RichTextPlain(text), anchorName))
    fun anchorLink(anchorName: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextAnchorLink(buildRichText(block), anchorName))

    fun reference(name: String, text: String) = add(RichTextReference(RichTextPlain(text), name))
    fun reference(name: String, block: RichTextBuilder.() -> Unit) = add(RichTextReference(buildRichText(block), name))

    fun referenceLink(referenceName: String, text: String) =
        add(RichTextReferenceLink(RichTextPlain(text), referenceName))
    fun referenceLink(referenceName: String, block: RichTextBuilder.() -> Unit) =
        add(RichTextReferenceLink(buildRichText(block), referenceName))

    fun build(): RichText = when (parts.size) {
        0 -> RichTextGroup(emptyList())
        1 -> parts.single()
        else -> RichTextGroup(parts.toList())
    }
}

/**
 * Builder of [RichBlockListItem]s used inside [RichBlocksBuilder.list].
 */
@RichTextDsl
class RichBlockListBuilder {
    private val items = mutableListOf<RichBlockListItem>()

    fun item(
        label: String,
        hasCheckbox: Boolean? = null,
        isChecked: Boolean? = null,
        value: Int? = null,
        labelType: String? = null,
        block: RichBlocksBuilder.() -> Unit
    ) {
        items.add(RichBlockListItem(label, buildRichBlocks(block), hasCheckbox, isChecked, value, labelType))
    }

    fun item(label: String, text: String) {
        items.add(RichBlockListItem(label, listOf(RichBlockParagraph(RichTextPlain(text)))))
    }

    fun build(): List<RichBlockListItem> = items.toList()
}

/**
 * Builder of a [List] of [RichBlock]s - the root of the rich message DSL. Text-bearing and container blocks have their
 * own DSL functions; file/cell-heavy blocks (media, collage, slideshow, table, map) can be appended with [add] / unary
 * plus.
 */
@RichTextDsl
class RichBlocksBuilder {
    private val blocks = mutableListOf<RichBlock>()

    /** Appends an already built [RichBlock]. */
    fun add(block: RichBlock) {
        blocks.add(block)
    }

    /** Appends an already built [RichBlock]. */
    operator fun RichBlock.unaryPlus() = add(this)

    fun paragraph(text: String) = add(RichBlockParagraph(RichTextPlain(text)))
    fun paragraph(block: RichTextBuilder.() -> Unit) = add(RichBlockParagraph(buildRichText(block)))

    fun heading(size: Int, text: String) = add(RichBlockSectionHeading(RichTextPlain(text), size))
    fun heading(size: Int, block: RichTextBuilder.() -> Unit) = add(RichBlockSectionHeading(buildRichText(block), size))

    fun preformatted(text: String, language: String? = null) = add(RichBlockPreformatted(RichTextPlain(text), language))

    fun footer(text: String) = add(RichBlockFooter(RichTextPlain(text)))
    fun footer(block: RichTextBuilder.() -> Unit) = add(RichBlockFooter(buildRichText(block)))

    fun divider() = add(RichBlockDivider())

    fun mathematicalExpression(expression: String) = add(RichBlockMathematicalExpression(expression))

    fun anchor(name: String) = add(RichBlockAnchor(name))

    fun thinking(text: String) = add(RichBlockThinking(RichTextPlain(text)))
    fun thinking(block: RichTextBuilder.() -> Unit) = add(RichBlockThinking(buildRichText(block)))

    fun list(block: RichBlockListBuilder.() -> Unit) = add(RichBlockList(RichBlockListBuilder().apply(block).build()))

    fun blockQuotation(credit: RichText? = null, block: RichBlocksBuilder.() -> Unit) =
        add(RichBlockBlockQuotation(buildRichBlocks(block), credit))

    fun pullQuotation(credit: RichText? = null, block: RichTextBuilder.() -> Unit) =
        add(RichBlockPullQuotation(buildRichText(block), credit))

    fun details(summary: RichText, isOpen: Boolean? = null, block: RichBlocksBuilder.() -> Unit) =
        add(RichBlockDetails(summary, buildRichBlocks(block), isOpen))

    fun details(summary: String, isOpen: Boolean? = null, block: RichBlocksBuilder.() -> Unit) =
        details(RichTextPlain(summary), isOpen, block)

    fun build(): List<RichBlock> = blocks.toList()
}

/** Builds a [RichText] using the [RichTextBuilder] DSL. */
fun buildRichText(block: RichTextBuilder.() -> Unit): RichText = RichTextBuilder().apply(block).build()

/** Builds a [List] of [RichBlock]s using the [RichBlocksBuilder] DSL. */
fun buildRichBlocks(block: RichBlocksBuilder.() -> Unit): List<RichBlock> = RichBlocksBuilder().apply(block).build()

/** Builds a [RichTextInfo] using the [RichBlocksBuilder] DSL. */
fun buildRichTextInfo(isRtl: Boolean? = null, block: RichBlocksBuilder.() -> Unit): RichTextInfo =
    RichTextInfo(buildRichBlocks(block), isRtl)
