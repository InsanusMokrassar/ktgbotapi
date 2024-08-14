package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.types.UntilDate
import korlibs.time.DateTime
import kotlinx.serialization.Serializable

@Serializable(ChatMemberSerializer::class)
sealed interface MemberChatMember : ChatMember, UntilDate {
    val until: DateTime?
        get() = untilDate ?.asDate
}
