package com.github.insanusmokrassar.TelegramBotAPI.types.actions

import kotlinx.serialization.*

@Serializable(BotActionSerializer::class)
sealed class BotAction {
    abstract val actionName: String
}

@Serializer(BotAction::class)
internal object BotActionSerializer: KSerializer<BotAction> {
    override fun serialize(encoder: Encoder, obj: BotAction) {
        encoder.encodeString(obj.actionName)
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
            else -> throw IllegalStateException("Unknown action type: $actionName")
        }
    }
}

/**
 * Use BotAction objects realisations to notify user about bot actions
 */

@Serializable(BotActionSerializer::class)
object TypingAction : BotAction() {
    override val actionName: String = "typing"
}

@Serializable(BotActionSerializer::class)
object UploadPhotoAction : BotAction() {
    override val actionName: String = "upload_photo"
}

@Serializable(BotActionSerializer::class)
object RecordVideoAction : BotAction() {
    override val actionName: String = "record_video"
}

@Serializable(BotActionSerializer::class)
object UploadVideoAction : BotAction() {
    override val actionName: String = "upload_video"
}

@Serializable(BotActionSerializer::class)
object RecordAudioAction : BotAction() {
    override val actionName: String = "record_audio"
}

@Serializable(BotActionSerializer::class)
object UploadAudioAction : BotAction() {
    override val actionName: String = "upload_audio"
}

@Serializable(BotActionSerializer::class)
object UploadDocumentAction : BotAction() {
    override val actionName: String = "upload_document"
}

@Serializable(BotActionSerializer::class)
object FindLocationAction : BotAction() {
    override val actionName: String = "find_location"
}
