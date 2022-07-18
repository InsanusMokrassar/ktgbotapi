package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage

typealias ContactMessage = CommonMessage<ContactContent>
typealias VenueMessage = CommonMessage<VenueContent>
typealias PollMessage = CommonMessage<PollContent>
typealias DiceMessage = CommonMessage<DiceContent>
typealias GameMEssage = CommonMessage<GameContent>
typealias TextMessage = CommonMessage<TextContent>
typealias LocationMessage = CommonMessage<LocationContent>
typealias PhotoMessage = CommonMessage<PhotoContent>
typealias VideoMessage = CommonMessage<VideoContent>
typealias AudioMessage = CommonMessage<AudioContent>
typealias DocumentMessage = CommonMessage<DocumentContent>
typealias VoiceMessage = CommonMessage<VoiceContent>
typealias VideoNoteMessage = CommonMessage<VideoNoteContent>
typealias AnimationMessage = CommonMessage<AnimationContent>
typealias StickerMessage = CommonMessage<StickerContent>
typealias InvoiceMessage = CommonMessage<InvoiceContent>
typealias MediaCollectionMessage<T> = CommonMessage<MediaCollectionContent<T>>