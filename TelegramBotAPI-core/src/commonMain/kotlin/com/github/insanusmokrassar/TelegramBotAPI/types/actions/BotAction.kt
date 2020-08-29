package com.github.insanusmokrassar.TelegramBotAPI.types.actions

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Use BotAction objects realisations to notify user about bot actions
 */
@Serializable(BotActionSerializer::class)
sealed class BotAction {
    abstract val actionName: String
}

@Serializer(BotAction::class)
internal object BotActionSerializer: KSerializer<BotAction> {
    override fun serialize(encoder: Encoder, value: BotAction) {
        encoder.encodeString(value.actionName)
    }

    override fun deserialize(decoder: Decoder): BotAction {
        return when (val actionName = decoder.decodeString()) {
            TypingAction.actionName -> TypingAction
            UploadPhotoAction.actionName -> UploadPhotoAction
            RecordVideoAction.actionName -> RecordVideoAction
            UploadVideoAction.actionName -> UploadVideoAction
            RecordAudioAction.actionName -> RecordAudioAction
            UploadAudioAction.actionName -> UploadAudioAction
            UploadDocumentAction.actionName -> UploadDocumentAction
            FindLocationAction.actionName -> FindLocationAction
            RecordVideoNoteAction.actionName -> RecordVideoNoteAction
            UploadVideoNoteAction.actionName -> UploadVideoNoteAction
            else -> throw IllegalStateException("Unknown action type: $actionName")
        }
    }
}

/**
 * Will notify user that bot is "typing" something
 */
@Serializable(BotActionSerializer::class)
object TypingAction : BotAction() {
    override val actionName: String = "typing"
}

/**
 * Will notify user that bot is uploading some photo
 */
@Serializable(BotActionSerializer::class)
object UploadPhotoAction : BotAction() {
    override val actionName: String = "upload_photo"
}

/**
 * Will notify user that bot is recording some video
 */
@Serializable(BotActionSerializer::class)
object RecordVideoAction : BotAction() {
    override val actionName: String = "record_video"
}

/**
 * Will notify user that bot is uploading some photo
 */
@Serializable(BotActionSerializer::class)
object UploadVideoAction : BotAction() {
    override val actionName: String = "upload_video"
}

/**
 * Will notify user that bot is recording some audio
 */
@Serializable(BotActionSerializer::class)
object RecordAudioAction : BotAction() {
    override val actionName: String = "record_audio"
}

/**
 * Will notify user that bot is uploading some audio
 */
@Serializable(BotActionSerializer::class)
object UploadAudioAction : BotAction() {
    override val actionName: String = "upload_audio"
}

/**
 * Will notify user that bot is uploading some document
 */
@Serializable(BotActionSerializer::class)
object UploadDocumentAction : BotAction() {
    override val actionName: String = "upload_document"
}

/**
 * Will notify user that bot is trying to find location
 */
@Serializable(BotActionSerializer::class)
object FindLocationAction : BotAction() {
    override val actionName: String = "find_location"
}

/**
 * Will notify user that bot is recording video note
 */
@Serializable(BotActionSerializer::class)
object RecordVideoNoteAction : BotAction() {
    override val actionName: String = "record_video_note"
}

/**
 * Will notify user that bot is uploading video note
 */
@Serializable(BotActionSerializer::class)
object UploadVideoNoteAction : BotAction() {
    override val actionName: String = "upload_video_note"
}
