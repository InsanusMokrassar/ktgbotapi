import com.github.matfax.klassindex.KlassIndex
import dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery
import dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.InputMedia.InputMedia
import dev.inmo.tgbotapi.types.actions.BotAction
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlin.reflect.KClass

fun printlnInstanceSubclassesFuns(kclass: KClass<*>) {
    KlassIndex.getSubclasses(Message::class).forEach { subclass ->
        println("inline fun ${kclass.simpleName}.as${subclass.simpleName}(): ${subclass.simpleName}? = this as? ${subclass.simpleName}")
    }
}

fun printlnInstancesSubclassesFuns(kclass: KClass<*>, subclasses: Set<KClass<*>>): List<Pair<String, String>> {
    return subclasses.map { subclass ->
        "${subclass.qualifiedName}" to "inline fun ${kclass.simpleName}.as${subclass.simpleName}(): ${subclass.simpleName}? = this as? ${subclass.simpleName}"
    }
}

val result = mutableMapOf<KClass<*>, Set<KClass<*>>>()


fun main() {
    result[dev.inmo.tgbotapi.types.chat.abstracts.Chat::class] =
        setOf(dev.inmo.tgbotapi.types.Bot::class,
            dev.inmo.tgbotapi.types.CommonBot::class,
            dev.inmo.tgbotapi.types.CommonUser::class,
            dev.inmo.tgbotapi.types.ExtendedBot::class,
            dev.inmo.tgbotapi.types.User::class,
            dev.inmo.tgbotapi.types.chat.ChannelChatImpl::class,
            dev.inmo.tgbotapi.types.chat.GroupChatImpl::class,
            dev.inmo.tgbotapi.types.chat.PrivateChatImpl::class,
            dev.inmo.tgbotapi.types.chat.SupergroupChatImpl::class,
            dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.GroupChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.PublicChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.SuperPublicChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.UnknownChatType::class,
            dev.inmo.tgbotapi.types.chat.abstracts.UsernameChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedChannelChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedGroupChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedPrivateChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedPublicChat::class,
            dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedSupergroupChat::class,
            dev.inmo.tgbotapi.types.chat.extended.ExtendedChannelChatImpl::class,
            dev.inmo.tgbotapi.types.chat.extended.ExtendedGroupChatImpl::class,
            dev.inmo.tgbotapi.types.chat.extended.ExtendedPrivateChatImpl::class,
            dev.inmo.tgbotapi.types.chat.extended.ExtendedSupergroupChatImpl::class)
    result[dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery::class] =
        setOf(dev.inmo.tgbotapi.types.CallbackQuery.DataCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.GameShortNameCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.InlineMessageIdCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.InlineMessageIdDataCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.InlineMessageIdGameShortNameCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.MessageCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.MessageDataCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.MessageGameShortNameCallbackQuery::class,
            dev.inmo.tgbotapi.types.CallbackQuery.UnknownCallbackQueryType::class)
    result[dev.inmo.tgbotapi.types.message.abstracts.Message::class] =
        setOf(dev.inmo.tgbotapi.types.message.AnonymousGroupMessageImpl::class,
            dev.inmo.tgbotapi.types.message.ChannelEventMessage::class,
            dev.inmo.tgbotapi.types.message.ChannelMediaGroupMessage::class,
            dev.inmo.tgbotapi.types.message.ChannelMessageImpl::class,
            dev.inmo.tgbotapi.types.message.CommonGroupEventMessage::class,
            dev.inmo.tgbotapi.types.message.CommonGroupMessageImpl::class,
            dev.inmo.tgbotapi.types.message.CommonMediaGroupMessage::class,
            dev.inmo.tgbotapi.types.message.CommonSupergroupEventMessage::class,
            dev.inmo.tgbotapi.types.message.FromChannelGroupMessageImpl::class,
            dev.inmo.tgbotapi.types.message.PrivateMessageImpl::class,
            dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.ChannelMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.CommonGroupMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.CommonMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.ContentMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.FromChannelGroupMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.GroupEventMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.GroupMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.PossiblyEditedMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.PossiblyForwardedMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.PossiblyPaymentMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.PrivateMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.PublicMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.SignedMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.SupergroupEventMessage::class,
            dev.inmo.tgbotapi.types.message.abstracts.UnknownMessageType::class,
            dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage::class)
    result[dev.inmo.tgbotapi.types.actions.BotAction::class] =
        setOf(dev.inmo.tgbotapi.types.actions.FindLocationAction::class,
            dev.inmo.tgbotapi.types.actions.RecordAudioAction::class,
            dev.inmo.tgbotapi.types.actions.RecordVideoAction::class,
            dev.inmo.tgbotapi.types.actions.RecordVideoNoteAction::class,
            dev.inmo.tgbotapi.types.actions.TypingAction::class,
            dev.inmo.tgbotapi.types.actions.UploadAudioAction::class,
            dev.inmo.tgbotapi.types.actions.UploadDocumentAction::class,
            dev.inmo.tgbotapi.types.actions.UploadPhotoAction::class,
            dev.inmo.tgbotapi.types.actions.UploadVideoAction::class,
            dev.inmo.tgbotapi.types.actions.UploadVideoNoteAction::class)
    result[dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery::class] =
        setOf(dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery::class,
            dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery::class)
    result[dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent::class] =
        setOf(dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputContactMessageContent::class,
            dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputLocationMessageContent::class,
            dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputTextMessageContent::class,
            dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputVenueMessageContent::class)
    result[dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult::class] =
        setOf(dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultArticle::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultAudioImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultContact::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultDocumentImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGame::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultGifImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultLocation::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultMpeg4GifImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultPhotoImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultStickerCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVenue::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVideoImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceCachedImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.InlineQueryResultVoiceImpl::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.DescribedInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.FileInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.OptionallyTitledInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.SizedInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbSizedInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.ThumbedWithMimeTypeInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.TitledInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.UrlInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.WithInputMessageContentInlineQueryResult::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudio::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.InlineQueryResultAudioCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocument::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.InlineQueryResultDocumentCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGif::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.InlineQueryResultGifCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4Gif::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.InlineQueryResultMpeg4GifCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhoto::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.InlineQueryResultPhotoCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideo::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.InlineQueryResultVideoCommon::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoice::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCached::class,
            dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.InlineQueryResultVoiceCommon::class)
    result[dev.inmo.tgbotapi.types.ChatMember.abstracts.ChatMember::class] =
        setOf(dev.inmo.tgbotapi.types.ChatMember.AdministratorChatMemberImpl::class,
            dev.inmo.tgbotapi.types.ChatMember.CreatorChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.KickedChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.LeftChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.MemberChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.RestrictedChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.abstracts.AdministratorChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.abstracts.BannedChatMember::class,
            dev.inmo.tgbotapi.types.ChatMember.abstracts.SpecialRightsChatMember::class)
    result[dev.inmo.tgbotapi.types.InputMedia.InputMedia::class] =
        setOf(dev.inmo.tgbotapi.types.InputMedia.AudioMediaGroupMemberInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.DocumentMediaGroupMemberInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.DuratedInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.InputMediaAnimation::class,
            dev.inmo.tgbotapi.types.InputMedia.InputMediaAudio::class,
            dev.inmo.tgbotapi.types.InputMedia.InputMediaDocument::class,
            dev.inmo.tgbotapi.types.InputMedia.InputMediaPhoto::class,
            dev.inmo.tgbotapi.types.InputMedia.InputMediaVideo::class,
            dev.inmo.tgbotapi.types.InputMedia.MediaGroupMemberInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.SizedInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.ThumbedInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.TitledInputMedia::class,
            dev.inmo.tgbotapi.types.InputMedia.VisualMediaGroupMemberInputMedia::class)
    result[dev.inmo.tgbotapi.types.update.abstracts.Update::class] =
        setOf(dev.inmo.tgbotapi.types.update.CallbackQueryUpdate::class,
            dev.inmo.tgbotapi.types.update.ChannelPostUpdate::class,
            dev.inmo.tgbotapi.types.update.ChosenInlineResultUpdate::class,
            dev.inmo.tgbotapi.types.update.EditChannelPostUpdate::class,
            dev.inmo.tgbotapi.types.update.EditMessageUpdate::class,
            dev.inmo.tgbotapi.types.update.InlineQueryUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.ChannelPostMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.EditChannelPostMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.EditMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.EditMessageMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MessageMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate::class,
            dev.inmo.tgbotapi.types.update.MessageUpdate::class,
            dev.inmo.tgbotapi.types.update.PollAnswerUpdate::class,
            dev.inmo.tgbotapi.types.update.PollUpdate::class,
            dev.inmo.tgbotapi.types.update.PreCheckoutQueryUpdate::class,
            dev.inmo.tgbotapi.types.update.ShippingQueryUpdate::class,
            dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate::class,
            dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate::class,
            dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate::class,
            dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate::class)
    result[dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile::class] =
        setOf(dev.inmo.tgbotapi.types.files.AnimationFile::class,
            dev.inmo.tgbotapi.types.files.AudioFile::class,
            dev.inmo.tgbotapi.types.files.DocumentFile::class,
            dev.inmo.tgbotapi.types.files.File::class,
            dev.inmo.tgbotapi.types.files.PathedFile::class,
            dev.inmo.tgbotapi.types.files.PhotoSize::class,
            dev.inmo.tgbotapi.types.files.Sticker::class,
            dev.inmo.tgbotapi.types.files.VideoFile::class,
            dev.inmo.tgbotapi.types.files.VideoNoteFile::class,
            dev.inmo.tgbotapi.types.files.VoiceFile::class,
            dev.inmo.tgbotapi.types.files.abstracts.MimedMediaFile::class,
            dev.inmo.tgbotapi.types.files.abstracts.PlayableMediaFile::class,
            dev.inmo.tgbotapi.types.files.abstracts.SizedMediaFile::class,
            dev.inmo.tgbotapi.types.files.abstracts.ThumbedMediaFile::class)
    result[dev.inmo.tgbotapi.types.buttons.KeyboardMarkup::class] =
        setOf(dev.inmo.tgbotapi.types.buttons.ForceReply::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup::class,
            dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup::class,
            dev.inmo.tgbotapi.types.buttons.ReplyKeyboardRemove::class)
    result[dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton::class] =
        setOf(dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackGameInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.LoginURLInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.PayInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryCurrentChatInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.URLInlineKeyboardButton::class,
            dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.UnknownInlineKeyboardButton::class)
    result[dev.inmo.tgbotapi.types.polls.Poll::class] =
        setOf(dev.inmo.tgbotapi.types.polls.MultipleAnswersPoll::class,
            dev.inmo.tgbotapi.types.polls.QuizPoll::class,
            dev.inmo.tgbotapi.types.polls.RegularPoll::class,
            dev.inmo.tgbotapi.types.polls.UnknownPollType::class)
    val importsToFuns = result.keys.flatMap {
        printlnInstancesSubclassesFuns(it, result.getValue(it))
    }
    importsToFuns.forEach { println("import ${it.first}") }
    println()
    importsToFuns.forEach { println(it.second) }
//    printlnInstanceSubclassesFuns(Message::class)
//    printlnInstanceSubclassesFuns(Chat::class)
//    printlnInstanceSubclassesFuns(CallbackQuery::class)
//    printlnInstanceSubclassesFuns(KeyboardMarkup::class)
//    printlnInstanceSubclassesFuns(BotAction::class)
//    printlnInstanceSubclassesFuns(InlineKeyboardButton::class)
//    printlnInstanceSubclassesFuns(ChatMember::class)
//    printlnInstanceSubclassesFuns(TelegramMediaFile::class)
//    printlnInstanceSubclassesFuns(InlineQuery::class)
//    printlnInstanceSubclassesFuns(InlineQueryResult::class)
//    printlnInstanceSubclassesFuns(InputMessageContent::class)
//    printlnInstanceSubclassesFuns(InputMedia::class)
//    printlnInstanceSubclassesFuns(Poll::class)
//    printlnInstanceSubclassesFuns(Update::class)
}