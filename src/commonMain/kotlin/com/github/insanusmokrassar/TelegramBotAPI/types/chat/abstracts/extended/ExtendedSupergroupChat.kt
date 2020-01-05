package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.StickerSetName
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.SupergroupChat

interface ExtendedSupergroupChat : SupergroupChat, ExtendedGroupChat {
    val slowModeDelay: Long?
    val stickerSetName: StickerSetName?
    val canSetStickerSet: Boolean
}
