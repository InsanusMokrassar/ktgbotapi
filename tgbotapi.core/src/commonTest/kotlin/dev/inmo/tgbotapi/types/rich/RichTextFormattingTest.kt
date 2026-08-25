package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CopyTextButtonData
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChat as SwitchInlineQueryChosenChatParameters
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class RichTextFormattingTest {
    @Test
    fun bold() {
        val entity = RichTextBold(RichTextPlain("x"))
        assertEquals("**x**", entity.markdown)
        assertEquals("<b>x</b>", entity.html)
    }

    @Test
    fun italic() {
        val entity = RichTextItalic(RichTextPlain("x"))
        assertEquals("*x*", entity.markdown)
        assertEquals("<i>x</i>", entity.html)
    }

    @Test
    fun underline() {
        val entity = RichTextUnderline(RichTextPlain("x"))
        assertEquals("<u>x</u>", entity.markdown)
        assertEquals("<u>x</u>", entity.html)
    }

    @Test
    fun strikethrough() {
        val entity = RichTextStrikethrough(RichTextPlain("x"))
        assertEquals("~~x~~", entity.markdown)
        assertEquals("<s>x</s>", entity.html)
    }

    @Test
    fun spoiler() {
        val entity = RichTextSpoiler(RichTextPlain("x"))
        assertEquals("||x||", entity.markdown)
        assertEquals("<tg-spoiler>x</tg-spoiler>", entity.html)
    }

    @Test
    fun subscript() {
        val entity = RichTextSubscript(RichTextPlain("x"))
        assertEquals("<sub>x</sub>", entity.markdown)
        assertEquals("<sub>x</sub>", entity.html)
    }

    @Test
    fun superscript() {
        val entity = RichTextSuperscript(RichTextPlain("x"))
        assertEquals("<sup>x</sup>", entity.markdown)
        assertEquals("<sup>x</sup>", entity.html)
    }

    @Test
    fun marked() {
        val entity = RichTextMarked(RichTextPlain("x"))
        assertEquals("==x==", entity.markdown)
        assertEquals("<mark>x</mark>", entity.html)
    }

    @Test
    fun code() {
        val entity = RichTextCode(RichTextPlain("x"))
        assertEquals("`x`", entity.markdown)
        assertEquals("<code>x</code>", entity.html)
    }

    @Test
    fun dateTime() {
        val entity = RichTextDateTime(RichTextPlain("now"), TelegramDate(1647531900L), "wDT")
        assertEquals("![now](tg://time?unix=1647531900&format=wDT)", entity.markdown)
        assertEquals("<tg-time unix=\"1647531900\" format=\"wDT\">now</tg-time>", entity.html)
    }

    @Test
    fun textMention() {
        val entity = RichTextTextMention(RichTextPlain("John"), CommonUser(ChatId(RawChatId(12345L)), "John"))
        assertEquals("[John](tg://user?id=12345)", entity.markdown)
        assertEquals("<a href=\"tg://user?id=12345\">John</a>", entity.html)
    }

    @Test
    fun customEmoji() {
        val entity = RichTextCustomEmoji(CustomEmojiId("555"), "alt")
        assertEquals("![alt](tg://emoji?id=555)", entity.markdown)
        assertEquals("<tg-emoji emoji-id=\"555\">alt</tg-emoji>", entity.html)
    }

    @Test
    fun mathematicalExpression() {
        val entity = RichTextMathematicalExpression("x^2")
        assertEquals("\$x^2\$", entity.markdown)
        assertEquals("<tg-math>x^2</tg-math>", entity.html)
    }

    @Test
    fun url() {
        val entity = RichTextUrl(RichTextPlain("link"), "https://t.me/")
        assertEquals("[link](https://t.me/)", entity.markdown)
        assertEquals("<a href=\"https://t.me/\">link</a>", entity.html)
    }

    @Test
    fun emailAddress() {
        val entity = RichTextEmailAddress(RichTextPlain("mail"), "a@b.com")
        assertEquals("[mail](mailto:a@b.com)", entity.markdown)
        assertEquals("<a href=\"mailto:a@b.com\">mail</a>", entity.html)
    }

    @Test
    fun phoneNumber() {
        val entity = RichTextPhoneNumber(RichTextPlain("call"), "+123")
        assertEquals("[call](tel:+123)", entity.markdown)
        assertEquals("<a href=\"tel:+123\">call</a>", entity.html)
    }

    @Test
    fun bankCardNumber() {
        val entity = RichTextBankCardNumber(RichTextPlain("4242 4242 4242 4242"), "4242424242424242")
        assertEquals("4242 4242 4242 4242", entity.markdown)
        assertEquals("4242 4242 4242 4242", entity.html)
    }

    @Test
    fun mention() {
        val entity = RichTextMention(RichTextPlain("@user"), "user")
        assertEquals("@user", entity.markdown)
        assertEquals("@user", entity.html)
    }

    @Test
    fun hashtag() {
        val entity = RichTextHashtag(RichTextPlain("#tag"), "tag")
        assertEquals("\\#tag", entity.markdown)
        assertEquals("#tag", entity.html)
    }

    @Test
    fun cashtag() {
        val entity = RichTextCashtag(RichTextPlain("\$USD"), "USD")
        assertEquals("\\\$USD", entity.markdown)
        assertEquals("\$USD", entity.html)
    }

    @Test
    fun botCommand() {
        val entity = RichTextBotCommand(RichTextPlain("/start"), "start")
        assertEquals("/start", entity.markdown)
        assertEquals("/start", entity.html)
    }

    @Test
    fun anchor() {
        val entity = RichTextAnchor("top")
        assertEquals("<a name=\"top\"></a>", entity.markdown)
        assertEquals("<a name=\"top\"></a>", entity.html)
    }

    @Test
    fun anchorLink() {
        val entity = RichTextAnchorLink(RichTextPlain("go"), "top")
        assertEquals("[go](#top)", entity.markdown)
        assertEquals("<a href=\"#top\">go</a>", entity.html)
    }

    @Test
    fun reference() {
        val entity = RichTextReference(RichTextPlain("ref"), "note1")
        assertEquals("<tg-reference name=\"note1\">ref</tg-reference>", entity.markdown)
        assertEquals("<tg-reference name=\"note1\">ref</tg-reference>", entity.html)
    }

    @Test
    fun referenceLink() {
        val entity = RichTextReferenceLink(RichTextPlain("see"), "note1")
        assertEquals("[see](#note1)", entity.markdown)
        assertEquals("<a href=\"#note1\">see</a>", entity.html)
    }

    @Test
    fun nestedGroupRecursesIntoInnerEntities() {
        val entity = RichTextBold(
            RichTextGroup(
                listOf(
                    RichTextPlain("a "),
                    RichTextItalic(RichTextPlain("b"))
                )
            )
        )
        assertEquals("**a *b***", entity.markdown)
        assertEquals("<b>a <i>b</i></b>", entity.html)
    }

    @Test
    fun plainMarkdownEscapesSpecialCharacters() {
        assertEquals("a\\*b\\_c", RichTextPlain("a*b_c").markdown)
        assertEquals("\\[x\\]", RichTextPlain("[x]").markdown)
    }

    @Test
    fun plainHtmlEscapesAngleBrackets() {
        assertEquals("a&lt;b", RichTextPlain("a<b").html)
    }

    @Test
    fun buttonHtmlEscapesContentExactlyOnce() {
        val button = RichTextButton(RichMessageButton.Disabled(RichTextPlain("<&>")))
        assertEquals("<tg-button type=\"disabled\">&lt;&amp;&gt;</tg-button>", button.html)
    }

    @Test
    fun buttonAttributesEscapeRichMarkupMetacharacters() {
        val rawAttribute = "a&\"<>"
        val escapedAttribute = "a&amp;&quot;&lt;&gt;"
        val text = RichTextPlain("Button")
        val buttonsWithExpectedAttributes = listOf(
            RichMessageButton.Url(text, rawAttribute) to
                "type=\"url\" url=\"$escapedAttribute\"",
            RichMessageButton.CallbackData(text, rawAttribute) to
                "type=\"callback_data\" data=\"$escapedAttribute\"",
            RichMessageButton.WebApp(text, WebAppInfo(rawAttribute)) to
                "type=\"web_app\" url=\"$escapedAttribute\"",
            RichMessageButton.LoginUrl(
                text,
                LoginURL(rawAttribute, rawAttribute, requestWriteAccess = true)
            ) to "type=\"login_url\" url=\"$escapedAttribute\" forward-text=\"$escapedAttribute\" " +
                "request-write-access",
            RichMessageButton.SwitchInlineQuery(text, rawAttribute) to
                "type=\"switch_inline_query\" query=\"$escapedAttribute\"",
            RichMessageButton.SwitchInlineQueryCurrentChat(text, rawAttribute) to
                "type=\"switch_inline_query_current_chat\" query=\"$escapedAttribute\"",
            RichMessageButton.SwitchInlineQueryChosenChat(
                text,
                SwitchInlineQueryChosenChatParameters(rawAttribute, allowUsers = true)
            ) to "type=\"switch_inline_query_chosen_chat\" query=\"$escapedAttribute\" allow-user-chats",
            RichMessageButton.CopyText(text, CopyTextButtonData(rawAttribute)) to
                "type=\"copy_text\" text=\"$escapedAttribute\""
        )

        buttonsWithExpectedAttributes.forEach { (button, expectedAttributes) ->
            val expectedMarkup = "<tg-button $expectedAttributes>Button</tg-button>"
            assertEquals(expectedMarkup, RichTextButton(button).html)
            assertEquals(expectedMarkup, RichTextButton(button).markdown)
        }
    }

    @Test
    fun rawTextExtractsPlainText() {
        assertEquals("x", RichTextBold(RichTextPlain("x")).rawText)
        assertEquals("alt", RichTextCustomEmoji(CustomEmojiId("1"), "alt").rawText)
        assertEquals("e", RichTextMathematicalExpression("e").rawText)
        assertEquals(
            "a b",
            RichTextGroup(listOf(RichTextPlain("a "), RichTextBold(RichTextPlain("b")))).rawText
        )
    }
}
