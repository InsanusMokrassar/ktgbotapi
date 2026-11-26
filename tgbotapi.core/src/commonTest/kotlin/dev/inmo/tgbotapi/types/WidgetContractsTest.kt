package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.answers.AnswerWidgetQuery
import dev.inmo.tgbotapi.requests.edit.widgets.*
import dev.inmo.tgbotapi.requests.send.SendSharedWidget
import dev.inmo.tgbotapi.requests.send.SendWidget
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContentSerializer
import dev.inmo.tgbotapi.types.chat.ChatType
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.boldTextSource
import dev.inmo.tgbotapi.types.payments.ton.TonAddress
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.UpdateSerializerWithoutSerialization
import dev.inmo.tgbotapi.types.widgets.*
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.test.*

class WidgetContractsTest {
    private val html = "<button>Open</button>"
    private val chatId = ChatId(RawChatId(42))
    private val sharedId = WidgetId("shared-widget")
    private val user = """{"id":7,"is_bot":false,"first_name":"Alice"}"""
    private val chat = """{"id":42,"type":"private","first_name":"Alice"}"""
    private val json = Json { encodeDefaults = false }

    @Test
    fun sendAndInlineInputsUseTheSameFlatWidgetFields() {
        val input = InputWidgetMessageContent(
            html, listOf(boldTextSource("open")), width = 220, height = 150,
            publicData = "public", chatData = "private", showCaptionAboveMedia = true,
            allowQueries = true, isShared = true
        )
        val sent = SendWidget(chatId, input, threadId = MessageThreadId(17), protectContent = true)
        val sentJson = json.encodeToJsonElement(SendWidget.serializer(), sent).jsonObject
        val inlineJson = json.encodeToJsonElement(InputMessageContentSerializer, input).jsonObject

        assertEquals("sendWidget", sent.method())
        assertEquals(42, sentJson.getValue("chat_id").jsonPrimitive.int)
        assertEquals(17, sentJson.getValue("message_thread_id").jsonPrimitive.int)
        assertEquals(true, sentJson.getValue("protect_content").jsonPrimitive.boolean)
        assertEquals(inlineJson, JsonObject(sentJson - setOf("chat_id", "message_thread_id", "protect_content")))
        assertEquals(html, inlineJson.getValue("html").jsonPrimitive.content)
        assertEquals("open", inlineJson.getValue("caption").jsonPrimitive.content)
        assertEquals("bold", inlineJson.getValue("caption_entities").jsonArray.single().jsonObject.getValue("type").jsonPrimitive.content)
        assertEquals(setOf("html", "width", "height", "public_data", "chat_data", "caption", "caption_entities",
            "show_caption_above_media", "allow_queries", "is_shared"), inlineJson.keys)
        assertEquals(sent, json.decodeFromJsonElement(SendWidget.serializer(), sentJson))

        val shared = InputSharedWidgetMessageContent(sharedId, chatData = "private")
        val sharedJson = json.encodeToJsonElement(InputMessageContentSerializer, shared).jsonObject
        assertEquals(setOf("widget_id", "chat_data"), sharedJson.keys)
        val resend = SendSharedWidget(chatId, shared)
        assertEquals("sendSharedWidget", resend.method())
        assertEquals(sharedId.string, json.encodeToJsonElement(SendSharedWidget.serializer(), resend).jsonObject.getValue("widget_id").jsonPrimitive.content)
    }

    @Test
    fun widgetMessagesPreserveCaptionsExternalRepliesAndResendIdentity() {
        val payload = """{"message_id":10,"date":100,"chat":$chat,"from":$user,"widget":{"html":"X","id":"shared-widget","chat_data":"local"},"caption":"hi","caption_entities":[{"type":"bold","offset":0,"length":2}],"show_caption_above_media":true}"""
        val message = nonstrictJsonFormat.decodeFromString(SendWidget(chatId, html).resultDeserializer, payload)
        val content = message.content
        assertEquals(Widget("X", id = sharedId, chatData = "local"), content.widget)
        assertEquals(listOf(boldTextSource("hi")), content.textSources)
        assertTrue(content.showCaptionAboveMedia)
        val resend = assertIs<SendSharedWidget>(content.createResend(chatId))
        assertEquals(sharedId, resend.widgetId)
        assertEquals("hi", resend.text)
        assertEquals("local", resend.chatData)

        val external = nonstrictJsonFormat.decodeFromString(
            ReplyInfo.External.serializer(),
            """{"origin":{"type":"hidden_user","date":100,"sender_user_name":"Alice"},"widget":{"html":"other"}}"""
        )
        assertEquals(Widget("other"), assertIs<ReplyInfo.External.Content.Simple>(external).content)
        val recreated = assertIs<SendWidget>(WidgetContent(Widget("new")).createResend(chatId))
        assertEquals("new", recreated.html)
        assertNull(recreated.text)
    }

    @Test
    fun routeDirectInlineAndForwardedWidgetQueries() {
        fun update(name: String, fields: String) = nonstrictJsonFormat.decodeFromString(
            UpdateSerializerWithoutSerialization,
            """{"update_id":9,"$name":{"id":"q","from":$user,"data":"click",$fields}}"""
        )
        assertTrue(UPDATE_WIDGET_QUERY in ALL_UPDATES_LIST)
        assertTrue(UPDATE_SHARED_WIDGET_QUERY in ALL_UPDATES_LIST)
        val direct = assertIs<WidgetQueryUpdate>(update(UPDATE_WIDGET_QUERY,
            """"chat_instance":"group","chat":$chat,"message_id":2,"type":{"type":"data"}""")).data
        assertEquals(chatId, direct.chat?.id)
        assertEquals(MessageId(2), direct.messageId)
        assertEquals(WidgetQueryType.Data(), direct.type)

        val cardData = json.decodeFromString(WidgetQueryType.serializer(),
            """{"type":"data","wallet_card":{"address":"0:abc","amount":"0","stickers":[]}}""")
        assertEquals(TonAddress("0:abc"), assertIs<WidgetQueryType.Data>(cardData).walletCard?.address)

        val wallet = """{"address":"0:123","amount":"1000000000000000000000","public_key":"abcd","state_init":"AB=="}"""
        val inline = assertIs<WidgetQueryUpdate>(update(UPDATE_WIDGET_QUERY,
            """"chat_instance":"group","inline_message_id":"inline","chat_type":"sender","type":{"type":"sign_data","wallet":$wallet,"sign_data":"signed-json"}""")).data
        assertEquals(InlineMessageId("inline"), inline.inlineMessageId)
        assertEquals(ChatType.Sender, inline.chatType)
        val signed = assertIs<WidgetQueryType.SignData>(inline.type)
        assertEquals("signed-json", signed.signData)
        assertEquals(TonAddress("0:123"), signed.wallet.address)
        assertEquals("1000000000000000000000", signed.wallet.amount)

        val shared = assertIs<SharedWidgetQueryUpdate>(update(UPDATE_SHARED_WIDGET_QUERY,
            """"widget_id":"shared-widget","type":{"type":"sent_transaction","wallet":$wallet,"msg_hash":"hash"}""")).data
        assertEquals(sharedId, shared.widgetId)
        assertEquals("hash", assertIs<WidgetQueryType.SentTransaction>(shared.type).msgHash)
        for (type in listOf(direct.type, cardData, signed, shared.type)) {
            assertEquals(type, json.decodeFromString(WidgetQueryType.serializer(), json.encodeToString(WidgetQueryType.serializer(), type)))
        }
        val signedWire = nonstrictJsonFormat.encodeToJsonElement(WidgetQueryType.SignData.serializer(), signed).jsonObject
        assertEquals(setOf("type", "wallet", "sign_data"), signedWire.keys)
        assertEquals(signed, nonstrictJsonFormat.decodeFromJsonElement(WidgetQueryType.SignData.serializer(), signedWire))
        assertFailsWith<SerializationException> { json.decodeFromJsonElement(WidgetQueryType.Data.serializer(), signedWire) }
        assertFailsWith<SerializationException> { json.decodeFromJsonElement(WidgetQueryType.SentTransaction.serializer(), signedWire) }
        assertFailsWith<SerializationException> { json.decodeFromString(WidgetQueryType.serializer(), """{"type":"sign_data","sign_data":"signed"}""") }
        val unknown = assertIs<UnknownUpdate>(update(UPDATE_WIDGET_QUERY,
            """"chat_instance":"group","type":{"type":"future","extra":42}"""))
        val rawType = unknown.rawJson.jsonObject.getValue(UPDATE_WIDGET_QUERY).jsonObject.getValue("type").jsonObject
        assertEquals(42, rawType.getValue("extra").jsonPrimitive.int)
    }

    @Test
    fun editAndAnswerKeepEmptyAndOmittedDataDistinct() {
        val query = AnswerWidgetQuery(WidgetQueryId("q"), "")
        assertEquals("answerWidgetQuery", query.method())
        assertEquals("""{"widget_query_id":"q","data":""}""", json.encodeToString(query))
        val chatEdit = EditChatMessageWidgetData(chatId, MessageId(7), publicData = "")
        val chatJson = json.encodeToJsonElement(EditChatMessageWidgetData.serializer(), chatEdit).jsonObject
        assertEquals(setOf("chat_id", "message_id", "public_data"), chatJson.keys)
        assertEquals("", chatJson.getValue("public_data").jsonPrimitive.content)
        val inlineEdit = EditInlineMessageWidgetData(InlineMessageId("inline"), chatData = "")
        assertEquals("""{"inline_message_id":"inline","chat_data":""}""", json.encodeToString(inlineEdit))
        assertEquals("editMessageWidgetData", chatEdit.method())
        assertEquals(chatEdit.method(), inlineEdit.method())
        val sharedEdit = EditSharedWidgetData(sharedId, "", 5)
        assertEquals("editSharedWidgetData", sharedEdit.method())
        assertEquals("""{"widget_id":"shared-widget","public_data":"","cache_time":5}""", json.encodeToString(sharedEdit))
        assertEquals(Unit, json.decodeFromString(sharedEdit.resultDeserializer, "true"))
    }
}
