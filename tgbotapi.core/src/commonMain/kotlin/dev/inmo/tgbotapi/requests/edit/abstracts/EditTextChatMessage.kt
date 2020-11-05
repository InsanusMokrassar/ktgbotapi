package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput

interface EditTextChatMessage : TextedOutput {
    override val text: String
}