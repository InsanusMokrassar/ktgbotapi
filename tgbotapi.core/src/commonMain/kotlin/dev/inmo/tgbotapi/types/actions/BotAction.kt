package dev.inmo.tgbotapi.types.actions

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Use BotAction objects realisations to notify user about bot actions
 */
@Serializable(BotActionSerializer::class)
@ClassCastsIncluded
sealed interface BotAction {
    val actionName: String
}

object BotActionSerializer: KSerializer<BotAction> {
    override val descriptor: SerialDescriptor = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: BotAction) {
        encoder.encodeString(value.actionName)
    }

    override fun deserialize(decoder: Decoder): BotAction {
        return when (val actionName = decoder.decodeString()) {
            TypingAction.actionName -> TypingAction
            UploadPhotoAction.actionName -> UploadPhotoAction
            RecordVideoAction.actionName -> RecordVideoAction
            UploadVideoAction.actionName -> UploadVideoAction
            RecordVoiceAction.actionName -> RecordVoiceAction
            UploadVoiceAction.actionName -> UploadVoiceAction
            UploadDocumentAction.actionName -> UploadDocumentAction
            FindLocationAction.actionName -> FindLocationAction
            RecordVideoNoteAction.actionName -> RecordVideoNoteAction
            UploadVideoNoteAction.actionName -> UploadVideoNoteAction
            ChooseStickerAction.actionName -> ChooseStickerAction
            else -> CustomBotAction(actionName)
        }
    }
}

/**
 * Will notify user that bot is "typing" something
 */
@Serializable(BotActionSerializer::class)
object TypingAction : BotAction {
    override val actionName: String = "typing"
}
inline val typing
    get() = TypingAction
inline fun BotAction.asTyping() = this as? TypingAction

/**
 * Will notify user that bot is uploading some photo
 */
@Serializable(BotActionSerializer::class)
object UploadPhotoAction : BotAction {
    override val actionName: String = "upload_photo"
}
inline val uploadPhoto
    get() = UploadPhotoAction
inline fun BotAction.asUploadPhoto() = this as? UploadPhotoAction

/**
 * Will notify user that bot is recording some video
 */
@Serializable(BotActionSerializer::class)
object RecordVideoAction : BotAction {
    override val actionName: String = "record_video"
}
inline val recordVideo
    get() = RecordVideoAction
inline fun BotAction.asRecordVideo() = this as? RecordVideoAction

/**
 * Will notify user that bot is uploading some photo
 */
@Serializable(BotActionSerializer::class)
object UploadVideoAction : BotAction {
    override val actionName: String = "upload_video"
}
inline val uploadVideo
    get() = UploadVideoAction
inline fun BotAction.asUploadVideo() = this as? UploadVideoAction

/**
 * Will notify user that bot is recording some audio
 */
@Serializable(BotActionSerializer::class)
object RecordVoiceAction : BotAction {
    override val actionName: String = "record_voice"
}
inline val recordVoice
    get() = RecordVoiceAction
inline fun BotAction.asRecordVoice() = this as? RecordVoiceAction

/**
 * Will notify user that bot is uploading some audio
 */
@Serializable(BotActionSerializer::class)
object UploadVoiceAction : BotAction {
    override val actionName: String = "upload_voice"
}
inline val uploadVoice
    get() = UploadVoiceAction
inline fun BotAction.asUploadVoice() = this as? UploadVoiceAction

/**
 * Will notify user that bot is uploading some document
 */
@Serializable(BotActionSerializer::class)
object UploadDocumentAction : BotAction {
    override val actionName: String = "upload_document"
}
inline val uploadDocument
    get() = UploadDocumentAction
inline fun BotAction.asUploadDocument() = this as? UploadDocumentAction

/**
 * Will notify user that bot is trying to find location
 */
@Serializable(BotActionSerializer::class)
object FindLocationAction : BotAction {
    override val actionName: String = "find_location"
}
inline val findLocation
    get() = FindLocationAction
inline fun BotAction.asFindLocation() = this as? FindLocationAction

/**
 * Will notify user that bot is recording video note
 */
@Serializable(BotActionSerializer::class)
object RecordVideoNoteAction : BotAction {
    override val actionName: String = "record_video_note"
}
inline val recordVideoNote
    get() = RecordVideoNoteAction
inline fun BotAction.asRecordVideoNote() = this as? RecordVideoNoteAction

/**
 * Will notify user that bot is uploading video note
 */
@Serializable(BotActionSerializer::class)
object UploadVideoNoteAction : BotAction {
    override val actionName: String = "upload_video_note"
}
inline val uploadVideoNote
    get() = UploadVideoNoteAction
inline fun BotAction.asUploadVideoNote() = this as? UploadVideoNoteAction

/**
 * Will notify user that bot is uploading video note
 */
@Serializable(BotActionSerializer::class)
object ChooseStickerAction : BotAction {
    override val actionName: String = "choose_sticker"
}
inline val chooseSticker
    get() = ChooseStickerAction
inline fun BotAction.asChooseStickerAction() = this as? ChooseStickerAction

@Serializable(BotActionSerializer::class)
@Warning("Use this action only in case you are pretty sure that there are no other action for your needs")
class CustomBotAction @RiskFeature("Usage of this action may lead to errors") constructor(
    override val actionName: String
) : BotAction
