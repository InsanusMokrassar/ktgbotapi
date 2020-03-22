package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.ContactContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<ContactContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

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
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<ContactContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<ContactContent>>
{
    constructor(
        chatId: ChatIdentifier,
        contact: Contact,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        replyMarkup: KeyboardMarkup? = null
    ): this(
        chatId,
        contact.phoneNumber,
        contact.firstName,
        contact.lastName,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    override fun method(): String = "sendContact"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<ContactContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun Contact.toRequest(
    chatId: ChatIdentifier,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): SendContact = SendContact(
    chatId,
    this,
    disableNotification,
    replyToMessageId,
    replyMarkup
)
