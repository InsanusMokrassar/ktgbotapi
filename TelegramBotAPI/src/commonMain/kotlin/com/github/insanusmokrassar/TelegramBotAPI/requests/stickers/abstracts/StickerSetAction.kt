package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface StickerSetAction : SimpleRequest<Boolean> {
    val userId: UserId
    val name: String
    val emojis: String // must be more than one
    val maskPosition: MaskPosition?

    override val resultDeserializer: KSerializer<Boolean>
        get() = Boolean.serializer()
}