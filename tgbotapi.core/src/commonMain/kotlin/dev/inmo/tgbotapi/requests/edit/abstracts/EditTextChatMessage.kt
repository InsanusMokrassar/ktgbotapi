package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.abstracts.TextedOutput

interface EditTextChatMessage : TextedOutput {
    override val text: String
}
