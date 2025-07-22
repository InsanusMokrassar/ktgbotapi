package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import dev.inmo.tgbotapi.types.TelegramDate
import korlibs.time.DateTime
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatMemberSerializer::class)
sealed interface SubscriptionMemberChatMember : MemberChatMember, UntilDate {
    override val untilDate: TelegramDate
    val until: DateTime
        get() = untilDate.asDate
}
