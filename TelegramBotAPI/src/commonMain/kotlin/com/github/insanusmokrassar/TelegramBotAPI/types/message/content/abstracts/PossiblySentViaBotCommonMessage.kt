package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.CommonMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.PossiblySentViaBot

interface PossiblySentViaBotCommonMessage<T: MessageContent> : CommonMessage<T>, PossiblySentViaBot
