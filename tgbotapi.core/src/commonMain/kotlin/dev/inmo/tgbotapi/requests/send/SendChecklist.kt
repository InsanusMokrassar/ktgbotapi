package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.types.AllowPaidBroadcast
import dev.inmo.tgbotapi.abstracts.types.DisableNotification
import dev.inmo.tgbotapi.abstracts.types.OptionallyWithEffectId
import dev.inmo.tgbotapi.abstracts.types.ProtectContent
import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup
import dev.inmo.tgbotapi.abstracts.types.WithReplyParameters
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendChatMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendContentMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.message.content.GameContent
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<ChecklistContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendChecklist (
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(checklistField)
    val checklist: Checklist.Input,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendChatMessageRequest<ContentMessage<ChecklistContent>>,
    WithReplyParameters,
    DisableNotification,
    ProtectContent,
    OptionallyWithEffectId,
    WithBusinessConnectionId,
    WithReplyMarkup {
    constructor(
        chatId: BusinessChatId,
        checklist: Checklist.Input,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        checklist = checklist,
        businessConnectionId = chatId.businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    override fun method(): String = "sendChecklist"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<ChecklistContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
