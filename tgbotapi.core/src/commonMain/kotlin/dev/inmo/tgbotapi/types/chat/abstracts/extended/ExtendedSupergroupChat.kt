package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat

interface ExtendedSupergroupChat : SupergroupChat, ExtendedGroupChat {
    val slowModeDelay: Long?
    val stickerSetName: StickerSetName?
    val canSetStickerSet: Boolean
}
