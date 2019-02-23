package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.Serializable
import kotlinx.serialization.StringFormat

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface MediaGroupMemberInputMedia: InputMedia {
    fun serialize(format: StringFormat): String
    val arguments: Map<String, Any?>
}
