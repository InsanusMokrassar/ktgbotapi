package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

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
interface MediaGroupMemberInputMedia : InputMedia, TextedOutput {
    fun serialize(format: StringFormat): String
}

interface AudioMediaGroupMemberInputMedia: MediaGroupMemberInputMedia
interface DocumentMediaGroupMemberInputMedia: MediaGroupMemberInputMedia

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface VisualMediaGroupMemberInputMedia : MediaGroupMemberInputMedia
