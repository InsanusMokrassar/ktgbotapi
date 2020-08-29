package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.*

internal val argumentsFormatter by lazy {
    Json {
        encodeDefaults = true
    }
}
internal fun <T> T.buildArguments(withSerializer: SerializationStrategy<T>) = argumentsFormatter.encodeToJsonElement(
    withSerializer,
    this
)

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface MediaGroupMemberInputMedia : InputMedia, CaptionedOutput {
    fun serialize(format: StringFormat): String
}
