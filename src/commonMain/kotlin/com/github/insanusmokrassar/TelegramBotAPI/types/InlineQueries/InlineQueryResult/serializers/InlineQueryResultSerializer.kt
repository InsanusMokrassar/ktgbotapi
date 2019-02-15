package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.serializers

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import kotlinx.serialization.*

@Serializer(InlineQueryResult::class)
object InlineQueryResultSerializer :
    KSerializer<InlineQueryResult> {
    override fun serialize(output: Encoder, obj: InlineQueryResult) {
        when(obj) {
            is InlineQueryResultArticle -> InlineQueryResultArticle.serializer().serialize(output, obj)
            is InlineQueryResultAudioCachedImpl -> InlineQueryResultAudioCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultAudioImpl -> InlineQueryResultAudioImpl.serializer().serialize(output, obj)
            is InlineQueryResultContact -> InlineQueryResultContact.serializer().serialize(output, obj)
            is InlineQueryResultDocumentCachedImpl -> InlineQueryResultDocumentCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultDocumentImpl -> InlineQueryResultDocumentImpl.serializer().serialize(output, obj)
            is InlineQueryResultGame -> InlineQueryResultGame.serializer().serialize(output, obj)
            is InlineQueryResultGifCachedImpl -> InlineQueryResultGifCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultGifImpl -> InlineQueryResultGifImpl.serializer().serialize(output, obj)
            is InlineQueryResultLocation -> InlineQueryResultLocation.serializer().serialize(output, obj)
            is InlineQueryResultMpeg4GifCachedImpl -> InlineQueryResultMpeg4GifCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultMpeg4GifImpl -> InlineQueryResultMpeg4GifImpl.serializer().serialize(output, obj)
            is InlineQueryResultPhotoCachedImpl -> InlineQueryResultPhotoCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultPhotoImpl -> InlineQueryResultPhotoImpl.serializer().serialize(output, obj)
            is InlineQueryResultStickerCached -> InlineQueryResultStickerCached.serializer().serialize(output, obj)
            is InlineQueryResultVenue -> InlineQueryResultVenue.serializer().serialize(output, obj)
            is InlineQueryResultVideoCachedImpl -> InlineQueryResultVideoCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultVideoImpl -> InlineQueryResultVideoImpl.serializer().serialize(output, obj)
            is InlineQueryResultVoiceCachedImpl -> InlineQueryResultVoiceCachedImpl.serializer().serialize(output, obj)
            is InlineQueryResultVoiceImpl -> InlineQueryResultVoiceImpl.serializer().serialize(output, obj)
        }
    }

    override fun deserialize(input: Decoder): InlineQueryResult {
        throw NotImplementedError()
    }
}