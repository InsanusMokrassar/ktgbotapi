package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance

inline fun Flow<AccessibleMessage>.businessMessages() = filterIsInstance<BusinessContentMessage<*>>()
inline fun <reified T : MessageContent> Flow<ContentMessage<T>>.businessMessagesWithType() = filterIsInstance<BusinessContentMessage<T>>()
inline fun Flow<ContentMessage<*>>.allBusinessMessages() = businessMessagesWithType()
