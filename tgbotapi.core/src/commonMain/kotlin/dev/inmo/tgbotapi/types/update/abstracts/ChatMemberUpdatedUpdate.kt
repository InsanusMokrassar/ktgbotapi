package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.types.ChatMemberUpdated

interface ChatMemberUpdatedUpdate : Update {
    override val data: ChatMemberUpdated
}
