package dev.inmo.tgbotapi.types.update

import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

data class MyChatMemberUpdatedUpdate(
    override val updateId: UpdateId,
    override val data: ChatMemberUpdated
) : ChatMemberUpdatedUpdate
