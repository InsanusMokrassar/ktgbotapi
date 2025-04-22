package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumTopic(
    @SerialName(messageThreadIdField)
    val messageThreadId: MessageThreadId,
    @SerialName(nameField)
    val name: String,
    @SerialName(iconColorField)
    val color: RGBColor,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId? = null,
) {
    companion object {
        val CYAN = RGBColor(0x6FB9F0)
        val YELLOW = RGBColor(0xffd67e)
        val PURPLE = RGBColor(0xCB86DB)
        val GREEN = RGBColor(0x8EEE98)
        val PINK = RGBColor(0xFF93B2)
        val RED = RGBColor(0xFB6F5F)
    }
}
