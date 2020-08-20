package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import kotlinx.serialization.Serializable
import kotlinx.serialization.StringFormat

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface MediaGroupMemberInputMedia : InputMedia, CaptionedOutput {
    fun serialize(format: StringFormat): String
    @Deprecated("Marked as deprecated for removal in future updates", level = DeprecationLevel.ERROR)
    val arguments: Map<String, Any?>
}
