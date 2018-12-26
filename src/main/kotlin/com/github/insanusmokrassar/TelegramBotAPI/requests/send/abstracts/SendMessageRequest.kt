package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.DisableNotification
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.types.ReplyMessageId

interface SendMessageRequest<T: Any> : SendChatMessageRequest<T>, ReplyMessageId, DisableNotification
