package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import kotlinx.serialization.*
import kotlinx.serialization.properties.Properties

internal fun <T> T.buildArguments(withSerializer: SerializationStrategy<T>) = Properties.encodeToMap(
    withSerializer,
    this
)

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface MediaGroupMemberInputMedia : InputMedia, CaptionedOutput {
    fun serialize(format: StringFormat): String
    val arguments: Map<String, Any?>
}
