package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.*
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.message.content.StaticLocationContent
import dev.inmo.tgbotapi.types.rich.InputRichMessageHTML
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.json.*
import kotlin.test.*

class CallbackEphemeralSendTest {
    private val callbackTarget = EphemeralMessageParameters(
        callbackQueryId = CallbackQueryId("pressed-button"),
        replaceCallbackQueryMessage = true
    )

    @Test
    fun everyEphemeralSendMethodCanUseOnlyTheCallbackDestination() {
        val file = FileId("existing-file")
        val upload = byteArrayOf(1, 2, 3).asMultipartFile("photo.jpg")
        val photoRequest = SendPhoto(photo = upload, ephemeralMessageParameters = callbackTarget)
        val requests = listOf<Request<*>>(
            SendTextMessage(text = "Hello", ephemeralMessageParameters = callbackTarget),
            SendRichMessage(richMessage = InputRichMessageHTML("<p>Hello</p>"), ephemeralMessageParameters = callbackTarget),
            SendAnimation(animation = file, ephemeralMessageParameters = callbackTarget),
            SendAudio(audio = file, ephemeralMessageParameters = callbackTarget),
            SendContact(phoneNumber = "+1234", firstName = "Contact", ephemeralMessageParameters = callbackTarget),
            SendDocument(document = file, ephemeralMessageParameters = callbackTarget),
            SendLivePhoto(livePhoto = file, photo = file, ephemeralMessageParameters = callbackTarget),
            SendLocation(latitude = 1.0, longitude = 2.0, ephemeralMessageParameters = callbackTarget),
            photoRequest,
            SendSticker(sticker = file, ephemeralMessageParameters = callbackTarget),
            SendVenue(latitude = 1.0, longitude = 2.0, title = "Venue", address = "Street", ephemeralMessageParameters = callbackTarget),
            SendVideo(video = file, ephemeralMessageParameters = callbackTarget),
            SendVideoNote(videoNote = file, ephemeralMessageParameters = callbackTarget),
            SendVoice(voice = file, ephemeralMessageParameters = callbackTarget)
        )
        val expectedParameters = buildJsonObject {
            put("callback_query_id", "pressed-button")
            put("replace_callback_query_message", true)
        }
        for (request in requests) {
            val payload = when (request) {
                is MultipartRequest<*> -> request.paramsJson
                is SimpleRequest<*> -> request.json()
                else -> error("Unexpected request type for ${request.method()}")
            }
            assertFalse("chat_id" in payload, request.method())
            assertEquals(expectedParameters, payload["ephemeral_message_parameters"], request.method())
        }
        assertEquals(setOf(upload.fileId), assertIs<MultipartRequest<*>>(photoRequest).mediaMap.keys)
        assertEquals(callbackTarget, Json.decodeFromJsonElement(EphemeralMessageParameters.serializer(), expectedParameters))
    }

    @Test
    fun missingChatRequiresACallbackWithoutAnExplicitReceiver() {
        val receiver = ChatId(RawChatId(37L))
        assertFailsWith<IllegalArgumentException> { EphemeralMessageParameters() }
        assertFailsWith<IllegalArgumentException> {
            Json.decodeFromString(SendTextMessage.serializer(), """{"text":"missing destination"}""")
        }
        assertFailsWith<IllegalArgumentException> {
            SendPhoto(photo = FileId("photo"), ephemeralMessageParameters = EphemeralMessageParameters(receiver))
        }
        assertFailsWith<IllegalArgumentException> {
            SendTextMessage(text = "missing chat", ephemeralMessageParameters = callbackTarget.copy(receiverUserId = receiver))
        }
    }

    @Test
    fun bothVideoDestinationsPreserveAllMultipartAttachments() {
        val video = byteArrayOf(1).asMultipartFile("video.mp4")
        val thumbnail = byteArrayOf(2).asMultipartFile("thumbnail.jpg")
        val cover = byteArrayOf(3).asMultipartFile("cover.jpg")
        val chat = ChatIdWithThreadId(RawChatId(18L), MessageThreadId(7L))
        val recipient = 19L.toChatId()
        val chatUpload = assertIs<MultipartRequest<*>>(
            SendVideo(chat, video, thumbnail = thumbnail, cover = cover, receiverUserId = recipient)
        )
        val callbackUpload = assertIs<MultipartRequest<*>>(
            SendVideo(callbackTarget, video, thumbnail = thumbnail, cover = cover)
        )
        for (upload in listOf(chatUpload, callbackUpload)) {
            assertEquals(setOf(video.fileId, thumbnail.fileId, cover.fileId), upload.mediaMap.keys)
        }
        assertEquals(JsonPrimitive(18L), chatUpload.paramsJson[chatIdField])
        assertEquals(JsonPrimitive(7L), chatUpload.paramsJson[messageThreadIdField])
        assertEquals(buildJsonObject { put(receiverUserIdField, 19L) }, chatUpload.paramsJson[ephemeralMessageParametersField])
        assertFalse(chatIdField in callbackUpload.paramsJson)
        assertFalse(messageThreadIdField in callbackUpload.paramsJson)
        assertEquals(JsonPrimitive("pressed-button"), callbackUpload.paramsJson.getValue(ephemeralMessageParametersField).jsonObject[callbackQueryIdField])
    }

    @Test
    fun ephemeralLocationsUseTheStaticWireForm() {
        val recipientChat = EphemeralChatId(RawChatId(1L), 2L.toChatId())
        assertFailsWith<IllegalArgumentException> {
            SendLiveLocation(recipientChat, latitude = 1.0, longitude = 2.0, livePeriod = 60)
        }
        assertFailsWith<IllegalArgumentException> {
            Json.decodeFromString(
                SendLocation.Companion,
                """{"chat_id":37,"latitude":1.0,"longitude":2.0,"live_period":60,"ephemeral_message_parameters":{"callback_query_id":"pressed-button"}}"""
            )
        }
        val request = Json.decodeFromString(
            SendLocation.Companion,
            """{"latitude":1.0,"longitude":2.0,"live_period":0,"ephemeral_message_parameters":{"callback_query_id":"pressed-button"}}"""
        )
        assertIs<SendLocation.Static>(request)
        assertEquals(CallbackQueryId("pressed-button"), request.ephemeralMessageParameters?.callbackQueryId)
    }

    @Test
    fun venueReceiptPreservesTheVenueWhenTelegramAlsoReturnsItsLocation() {
        val location = """{"latitude":1.0,"longitude":2.0}"""
        val receipt = nonstrictJsonFormat.parseToJsonElement("""
            {
                "message_id":0,"date":1,
                "chat":{"id":42,"type":"private","first_name":"Recipient"},
                "from":{"id":55,"is_bot":true,"first_name":"Bot"},
                "location":$location,
                "venue":{"location":$location,"title":"Test venue","address":"Test address"}
            }
        """).jsonObject
        val request = SendVenue(
            latitude = 1.0, longitude = 2.0, title = "Test venue", address = "Test address",
            ephemeralMessageParameters = callbackTarget
        )
        val place = nonstrictJsonFormat.decodeFromJsonElement(request.resultDeserializer, receipt).content.venue

        assertEquals(request.title, place.title)
        assertEquals(request.address, place.address)
        val locationRequest = SendLocation(latitude = 1.0, longitude = 2.0, ephemeralMessageParameters = callbackTarget)
        val coordinates = nonstrictJsonFormat.decodeFromJsonElement(locationRequest.resultDeserializer, JsonObject(receipt - "venue")).content
        assertEquals(place.location, assertIs<StaticLocationContent>(coordinates).location)
    }
}
