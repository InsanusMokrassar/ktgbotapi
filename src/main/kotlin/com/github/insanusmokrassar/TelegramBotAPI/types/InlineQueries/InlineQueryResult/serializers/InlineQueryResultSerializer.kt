package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.serializers

import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import kotlinx.serialization.*
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@Serializer(InlineQueryResult::class)
object InlineQueryResultSerializer :
    KSerializer<InlineQueryResult> {
    override fun serialize(encoder: Encoder, obj: InlineQueryResult) {
        when(obj) {
            is InlineQueryResultArticle -> InlineQueryResultArticle.serializer().serialize(encoder, obj)
            is InlineQueryResultAudioCachedImpl -> InlineQueryResultAudioCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultAudioImpl -> InlineQueryResultAudioImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultContact -> InlineQueryResultContact.serializer().serialize(encoder, obj)
            is InlineQueryResultDocumentCachedImpl -> InlineQueryResultDocumentCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultDocumentImpl -> InlineQueryResultDocumentImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultGame -> InlineQueryResultGame.serializer().serialize(encoder, obj)
            is InlineQueryResultGifCachedImpl -> InlineQueryResultGifCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultGifImpl -> InlineQueryResultGifImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultLocation -> InlineQueryResultLocation.serializer().serialize(encoder, obj)
            is InlineQueryResultMpeg4GifCachedImpl -> InlineQueryResultMpeg4GifCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultMpeg4GifImpl -> InlineQueryResultMpeg4GifImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultPhotoCachedImpl -> InlineQueryResultPhotoCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultPhotoImpl -> InlineQueryResultPhotoImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultStickerCached -> InlineQueryResultStickerCached.serializer().serialize(encoder, obj)
            is InlineQueryResultVenue -> InlineQueryResultVenue.serializer().serialize(encoder, obj)
            is InlineQueryResultVideoCachedImpl -> InlineQueryResultVideoCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultVideoImpl -> InlineQueryResultVideoImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultVoiceCachedImpl -> InlineQueryResultVoiceCachedImpl.serializer().serialize(encoder, obj)
            is InlineQueryResultVoiceImpl -> InlineQueryResultVoiceImpl.serializer().serialize(encoder, obj)
        }
    }

    override fun deserialize(decoder: Decoder): InlineQueryResult {
        throw NotImplementedException()
    }
}