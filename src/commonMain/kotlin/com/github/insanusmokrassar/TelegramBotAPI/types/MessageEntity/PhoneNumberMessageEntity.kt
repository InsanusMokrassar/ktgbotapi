package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.PhoneNumberTextSource
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneHTML
import com.github.insanusmokrassar.TelegramBotAPI.utils.phoneMarkdown

data class PhoneNumberMessageEntity(
    override val offset: Int,
    override val length: Int,
    override val sourceString: String
) : MessageEntity, TextSource by PhoneNumberTextSource(sourceString)
