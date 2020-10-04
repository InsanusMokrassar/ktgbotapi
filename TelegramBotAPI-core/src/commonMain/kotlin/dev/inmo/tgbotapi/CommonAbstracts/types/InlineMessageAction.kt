package dev.inmo.tgbotapi.CommonAbstracts.types

import dev.inmo.tgbotapi.types.InlineMessageIdentifier

interface InlineMessageAction {
    val inlineMessageId: InlineMessageIdentifier
}