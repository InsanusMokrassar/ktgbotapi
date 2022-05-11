package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

data class MyChatMemberUpdatedUpdate(
    override val updateId: UpdateIdentifier,
    override val data: ChatMemberUpdated
) : ChatMemberUpdatedUpdate
