package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendContentMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.ContactContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<ContactContent>> =
    TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendContact(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(phoneNumberField)
    val phoneNumber: String,
    @SerialName(firstNameField)
    val firstName: String,
    @SerialName(lastNameField)
    val lastName: String? = null,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null,
) : SendContentMessageRequest<ContentMessage<ContactContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<ContactContent>> {
    constructor(
        chatId: ChatIdentifier,
        contact: Contact,
        threadId: MessageThreadId? = chatId.threadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null,
    ) : this(
        chatId,
        contact.phoneNumber,
        contact.firstName,
        contact.lastName,
        threadId,
        businessConnectionId,
        disableNotification,
        protectContent,
        allowPaidBroadcast,
        effectId,
        replyParameters,
        replyMarkup,
    )

    override fun method(): String = "sendContact"

    override val resultDeserializer: DeserializationStrategy<ContentMessage<ContactContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun Contact.toRequest(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): SendContact =
    SendContact(
        chatId = chatId,
        contact = this,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )
