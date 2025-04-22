package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.message.abstracts.InaccessibleMessage

data class InaccessibleMessageGameShortNameCallbackQuery(
    override val id: CallbackQueryId,
    override val from: CommonUser,
    override val chatInstance: String,
    override val message: InaccessibleMessage,
    override val gameShortName: String,
) : GameShortNameCallbackQuery, InaccessibleMessageCallbackQuery
