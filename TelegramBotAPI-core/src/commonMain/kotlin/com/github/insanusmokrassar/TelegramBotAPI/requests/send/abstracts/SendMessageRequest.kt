package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.DisableNotification
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMessageId

interface SendMessageRequest<T: Any> : SendChatMessageRequest<T>, ReplyMessageId, DisableNotification
