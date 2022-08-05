@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink
import dev.inmo.tgbotapi.requests.send.payments.SendInvoice
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.queries.callback.*
import dev.inmo.tgbotapi.types.chat.member.*
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.*
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.Bot
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember
import dev.inmo.tgbotapi.types.chat.member.BannedChatMember
import dev.inmo.tgbotapi.types.chat.member.ChatMember
import dev.inmo.tgbotapi.types.chat.member.MemberChatMember
import dev.inmo.tgbotapi.types.chat.member.SpecialRightsChatMember
import dev.inmo.tgbotapi.types.dice.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMember
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.types.passport.*
import dev.inmo.tgbotapi.types.passport.decrypted.*
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import dev.inmo.tgbotapi.types.passport.encrypted.*
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.types.update.media_group.*
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
inline fun <T> Any.ifFromUser(block: (FromUser) -> T) = fromUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.fromUserOrNull(): FromUser? = this as? FromUser

@PreviewFeature
inline fun Any.fromUserOrThrow(): FromUser = this as FromUser

@PreviewFeature
inline fun <T> Any.ifWithUser(block: (WithUser) -> T) = withUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.withUserOrNull(): WithUser? = this as? WithUser

@PreviewFeature
inline fun Any.withUserOrThrow(): WithUser = this as WithUser

@PreviewFeature
inline fun <T> Any.ifWithOptionalLanguageCode(block: (WithOptionalLanguageCode) -> T) =
    withOptionalLanguageCodeOrNull()?.let(block)

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrNull(): WithOptionalLanguageCode? = this as? WithOptionalLanguageCode

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrThrow(): WithOptionalLanguageCode = this as WithOptionalLanguageCode
