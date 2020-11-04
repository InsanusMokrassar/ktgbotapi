package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat

interface ExtendedSupergroupChat : SupergroupChat, ExtendedGroupChat {
    val slowModeDelay: Long?
    val stickerSetName: StickerSetName?
    val canSetStickerSet: Boolean
    val linkedChannelChatId: ChatId?
    val location: ChatLocation?
}
