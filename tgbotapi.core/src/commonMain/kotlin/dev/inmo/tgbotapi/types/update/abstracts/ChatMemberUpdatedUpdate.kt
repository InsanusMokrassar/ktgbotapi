package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated

interface ChatMemberUpdatedUpdate : Update {
    override val data: ChatMemberUpdated
}
