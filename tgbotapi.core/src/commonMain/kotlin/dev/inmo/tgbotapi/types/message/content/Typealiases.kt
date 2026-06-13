package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage

typealias TextedMessage = ChatContentMessage<TextedContent>

typealias InvoiceMessage = ChatContentMessage<InvoiceContent>
typealias VenueMessage = ChatContentMessage<VenueContent>
typealias GameMessage = ChatContentMessage<GameContent>
typealias DiceMessage = ChatContentMessage<DiceContent>
typealias ContactMessage = ChatContentMessage<ContactContent>
typealias PollMessage = ChatContentMessage<PollContent>
typealias TextMessage = ChatContentMessage<TextContent>
typealias StoryMessage = ChatContentMessage<StoryContent>

typealias LocationMessage = ChatContentMessage<LocationContent>
typealias LiveLocationMessage = ChatContentMessage<LiveLocationContent>
typealias StaticLocationMessage = ChatContentMessage<StaticLocationContent>

typealias MediaCollectionMessage<T> = ChatContentMessage<MediaCollectionContent<T>>
typealias MediaMessage = ChatContentMessage<MediaContent>
typealias VideoNoteMessage = ChatContentMessage<VideoNoteContent>
typealias StickerMessage = ChatContentMessage<StickerContent>
typealias TextedMediaMessage = ChatContentMessage<TextedMediaContent>
typealias VoiceMessage = ChatContentMessage<VoiceContent>
typealias MediaGroupMessage<T> = ChatContentMessage<MediaGroupContent<T>>
typealias AudioMediaGroupMessage = ChatContentMessage<AudioMediaGroupPartContent>
typealias AudioMessage = ChatContentMessage<AudioContent>
typealias DocumentMediaGroupMessage = ChatContentMessage<DocumentMediaGroupPartContent>
typealias DocumentMessage = ChatContentMessage<DocumentContent>
typealias VisualMediaGroupMessage = ChatContentMessage<VisualMediaGroupPartContent>
typealias VideoMessage = ChatContentMessage<VideoContent>
typealias LivePhotoMessage = ChatContentMessage<LivePhotoContent>
typealias PhotoMessage = ChatContentMessage<PhotoContent>
typealias AnimationMessage = ChatContentMessage<AnimationContent>
typealias ScheduledGiveawayContentMessage = ChatContentMessage<GiveawayContent>
typealias GiveawayPublicResultsContentMessage = ChatContentMessage<GiveawayPublicResultsContent>
typealias PaidMediaInfoContentMessage = ChatContentMessage<PaidMediaInfoContent>
typealias ChecklistMessage = ChatContentMessage<ChecklistContent>


