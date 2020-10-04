package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBot

interface PossiblySentViaBotCommonMessage<T: MessageContent> : CommonMessage<T>, PossiblySentViaBot
