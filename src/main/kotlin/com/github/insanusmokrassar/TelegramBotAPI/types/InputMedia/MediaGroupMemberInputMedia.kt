package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import kotlinx.serialization.StringFormat

interface MediaGroupMemberInputMedia: InputMedia {
    fun serialize(format: StringFormat): String
    val arguments: Map<String, Any?>
}
