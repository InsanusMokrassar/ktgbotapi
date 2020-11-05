package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedOutput
import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput
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
interface MediaGroupMemberInputMedia : InputMedia, CaptionedOutput, TextedOutput {
    @Deprecated("Will be removed in next major release")
    override val caption: String?
        get() = text
    fun serialize(format: StringFormat): String
}

interface AudioMediaGroupMemberInputMedia: MediaGroupMemberInputMedia
interface DocumentMediaGroupMemberInputMedia: MediaGroupMemberInputMedia

@Serializable(MediaGroupMemberInputMediaSerializer::class)
interface VisualMediaGroupMemberInputMedia : MediaGroupMemberInputMedia
