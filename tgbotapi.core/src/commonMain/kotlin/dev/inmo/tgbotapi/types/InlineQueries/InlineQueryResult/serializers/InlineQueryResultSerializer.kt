package dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.serializers

import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@RiskFeature
object InlineQueryResultSerializer : KSerializer<InlineQueryResult> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(InlineQueryResult::class.toString(), PolymorphicKind.OPEN)
    override fun serialize(encoder: Encoder, value: InlineQueryResult) {
        when(value) {
            is InlineQueryResultArticle -> InlineQueryResultArticle.serializer().serialize(encoder, value)
            is InlineQueryResultAudioCachedImpl -> InlineQueryResultAudioCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultAudioImpl -> InlineQueryResultAudioImpl.serializer().serialize(encoder, value)
            is InlineQueryResultContact -> InlineQueryResultContact.serializer().serialize(encoder, value)
            is InlineQueryResultDocumentCachedImpl -> InlineQueryResultDocumentCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultDocumentImpl -> InlineQueryResultDocumentImpl.serializer().serialize(encoder, value)
            is InlineQueryResultGame -> InlineQueryResultGame.serializer().serialize(encoder, value)
            is InlineQueryResultGifCachedImpl -> InlineQueryResultGifCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultGifImpl -> InlineQueryResultGifImpl.serializer().serialize(encoder, value)
            is InlineQueryResultLocation -> InlineQueryResultLocation.serializer().serialize(encoder, value)
            is InlineQueryResultMpeg4GifCachedImpl -> InlineQueryResultMpeg4GifCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultMpeg4GifImpl -> InlineQueryResultMpeg4GifImpl.serializer().serialize(encoder, value)
            is InlineQueryResultPhotoCachedImpl -> InlineQueryResultPhotoCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultPhotoImpl -> InlineQueryResultPhotoImpl.serializer().serialize(encoder, value)
            is InlineQueryResultStickerCached -> InlineQueryResultStickerCached.serializer().serialize(encoder, value)
            is InlineQueryResultVenue -> InlineQueryResultVenue.serializer().serialize(encoder, value)
            is InlineQueryResultVideoCachedImpl -> InlineQueryResultVideoCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultVideoImpl -> InlineQueryResultVideoImpl.serializer().serialize(encoder, value)
            is InlineQueryResultVoiceCachedImpl -> InlineQueryResultVoiceCachedImpl.serializer().serialize(encoder, value)
            is InlineQueryResultVoiceImpl -> InlineQueryResultVoiceImpl.serializer().serialize(encoder, value)
        }
    }

    override fun deserialize(decoder: Decoder): InlineQueryResult {
        throw NotImplementedError("Object can't be deserialized")
    }
}
