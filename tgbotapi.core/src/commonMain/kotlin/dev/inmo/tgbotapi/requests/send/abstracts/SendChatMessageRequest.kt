package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.OptionalChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest

/** A request targeting a chat, including a chat inferred from an ephemeral callback query. */
interface SendChatMessageRequest<T: Any> : SimpleRequest<T>, OptionalChatRequest
