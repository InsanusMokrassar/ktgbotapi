package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.GroupEvent

interface GroupEventMessage<T : GroupEvent> : ChatEventMessage<T>, FromUserMessage
