package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val CallbackQuery.message: ContentMessage<MessageContent>?
    get() = asMessageCallbackQuery() ?.message
@RiskFeature(RawFieldsUsageWarning)
val CallbackQuery.inline_message_id: InlineMessageId?
    get() = asInlineMessageIdCallbackQuery() ?.inlineMessageId
@RiskFeature(RawFieldsUsageWarning)
val CallbackQuery.data: String?
    get() = asDataCallbackQuery() ?.data
@RiskFeature(RawFieldsUsageWarning)
val CallbackQuery.game_short_name: String?
    get() = asGameShortNameCallbackQuery() ?.gameShortName
