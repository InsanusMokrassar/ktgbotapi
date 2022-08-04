@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink
import dev.inmo.tgbotapi.requests.send.payments.SendInvoice
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.queries.callback.*
import dev.inmo.tgbotapi.types.chat.member.*
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.audio.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.document.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.mpeg4gif.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.photo.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.video.*
import dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.results.voice.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.*
import dev.inmo.tgbotapi.types.InlineQueries.query.*
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.Bot
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember
import dev.inmo.tgbotapi.types.chat.member.BannedChatMember
import dev.inmo.tgbotapi.types.chat.member.ChatMember
import dev.inmo.tgbotapi.types.chat.member.MemberChatMember
import dev.inmo.tgbotapi.types.chat.member.SpecialRightsChatMember
import dev.inmo.tgbotapi.types.dice.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMember
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.types.passport.*
import dev.inmo.tgbotapi.types.passport.decrypted.*
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import dev.inmo.tgbotapi.types.passport.encrypted.*
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.types.update.media_group.*
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
inline fun <T> Chat.ifBot(block: (Bot) -> T) = botOrNull()?.let(block)

@PreviewFeature
inline fun Chat.botOrNull(): Bot? = this as? Bot

@PreviewFeature
inline fun Chat.botOrThrow(): Bot = this as Bot

@PreviewFeature
inline fun <T> Chat.ifCommonBot(block: (CommonBot) -> T) = commonBotOrNull()?.let(block)

@PreviewFeature
inline fun Chat.commonBotOrNull(): CommonBot? = this as? CommonBot

@PreviewFeature
inline fun Chat.commonBotOrThrow(): CommonBot = this as CommonBot

@PreviewFeature
inline fun <T> Chat.ifCommonUser(block: (CommonUser) -> T) = commonUserOrNull()?.let(block)

@PreviewFeature
inline fun Chat.commonUserOrNull(): CommonUser? = this as? CommonUser

@PreviewFeature
inline fun Chat.commonUserOrThrow(): CommonUser = this as CommonUser

@PreviewFeature
inline fun <T> Chat.ifExtendedBot(block: (ExtendedBot) -> T) = extendedBotOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedBotOrNull(): ExtendedBot? = this as? ExtendedBot

@PreviewFeature
inline fun Chat.extendedBotOrThrow(): ExtendedBot = this as ExtendedBot

@PreviewFeature
inline fun <T> Chat.ifUser(block: (User) -> T) = userOrNull()?.let(block)

@PreviewFeature
inline fun Chat.userOrNull(): User? = this as? User

@PreviewFeature
inline fun Chat.userOrThrow(): User = this as User

@PreviewFeature
inline fun <T> Chat.ifChannelChat(block: (ChannelChat) -> T) = channelChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.channelChatOrNull(): ChannelChat? = this as? ChannelChat

@PreviewFeature
inline fun Chat.channelChatOrThrow(): ChannelChat = this as ChannelChat

@PreviewFeature
inline fun <T> Chat.ifGroupChat(block: (GroupChat) -> T) = groupChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.groupChatOrNull(): GroupChat? = this as? GroupChat

@PreviewFeature
inline fun Chat.groupChatOrThrow(): GroupChat = this as GroupChat

@PreviewFeature
inline fun <T> Chat.ifPrivateChat(block: (PrivateChat) -> T) = privateChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.privateChatOrNull(): PrivateChat? = this as? PrivateChat

@PreviewFeature
inline fun Chat.privateChatOrThrow(): PrivateChat = this as PrivateChat

@PreviewFeature
inline fun <T> Chat.ifPublicChat(block: (PublicChat) -> T) = publicChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.publicChatOrNull(): PublicChat? = this as? PublicChat

@PreviewFeature
inline fun Chat.publicChatOrThrow(): PublicChat = this as PublicChat

@PreviewFeature
inline fun <T> Chat.ifSuperPublicChat(block: (SuperPublicChat) -> T) = superPublicChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.superPublicChatOrNull(): SuperPublicChat? = this as? SuperPublicChat

@PreviewFeature
inline fun Chat.superPublicChatOrThrow(): SuperPublicChat = this as SuperPublicChat

@PreviewFeature
inline fun <T> Chat.ifSupergroupChat(block: (SupergroupChat) -> T) = supergroupChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.supergroupChatOrNull(): SupergroupChat? = this as? SupergroupChat

@PreviewFeature
inline fun Chat.supergroupChatOrThrow(): SupergroupChat = this as SupergroupChat

@PreviewFeature
inline fun <T> Chat.ifUnknownChatType(block: (UnknownChatType) -> T) = unknownChatTypeOrNull()?.let(block)

@PreviewFeature
inline fun Chat.unknownChatTypeOrNull(): UnknownChatType? = this as? UnknownChatType

@PreviewFeature
inline fun Chat.unknownChatTypeOrThrow(): UnknownChatType = this as UnknownChatType

@PreviewFeature
inline fun <T> Chat.ifUsernameChat(block: (UsernameChat) -> T) = usernameChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.usernameChatOrNull(): UsernameChat? = this as? UsernameChat

@PreviewFeature
inline fun Chat.usernameChatOrThrow(): UsernameChat = this as UsernameChat

@PreviewFeature
inline fun <T> Chat.ifExtendedChannelChat(block: (ExtendedChannelChat) -> T) = extendedChannelChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedChannelChatOrNull(): ExtendedChannelChat? = this as? ExtendedChannelChat

@PreviewFeature
inline fun Chat.extendedChannelChatOrThrow(): ExtendedChannelChat = this as ExtendedChannelChat

@PreviewFeature
inline fun <T> Chat.ifExtendedChat(block: (ExtendedChat) -> T) = extendedChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedChatOrNull(): ExtendedChat? = this as? ExtendedChat

@PreviewFeature
inline fun Chat.extendedChatOrThrow(): ExtendedChat = this as ExtendedChat

@PreviewFeature
inline fun <T> Chat.ifExtendedGroupChat(block: (ExtendedGroupChat) -> T) = extendedGroupChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedGroupChatOrNull(): ExtendedGroupChat? = this as? ExtendedGroupChat

@PreviewFeature
inline fun Chat.extendedGroupChatOrThrow(): ExtendedGroupChat = this as ExtendedGroupChat

@PreviewFeature
inline fun <T> Chat.ifExtendedPrivateChat(block: (ExtendedPrivateChat) -> T) = extendedPrivateChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedPrivateChatOrNull(): ExtendedPrivateChat? = this as? ExtendedPrivateChat

@PreviewFeature
inline fun Chat.extendedPrivateChatOrThrow(): ExtendedPrivateChat = this as ExtendedPrivateChat

@PreviewFeature
inline fun <T> Chat.ifExtendedPublicChat(block: (ExtendedPublicChat) -> T) = extendedPublicChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedPublicChatOrNull(): ExtendedPublicChat? = this as? ExtendedPublicChat

@PreviewFeature
inline fun Chat.extendedPublicChatOrThrow(): ExtendedPublicChat = this as ExtendedPublicChat

@PreviewFeature
inline fun <T> Chat.ifExtendedSupergroupChat(block: (ExtendedSupergroupChat) -> T) =
    extendedSupergroupChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.extendedSupergroupChatOrNull(): ExtendedSupergroupChat? = this as? ExtendedSupergroupChat

@PreviewFeature
inline fun Chat.extendedSupergroupChatOrThrow(): ExtendedSupergroupChat = this as ExtendedSupergroupChat

@PreviewFeature
inline fun <T> Chat.ifPossiblyPremiumChat(block: (PossiblyPremiumChat) -> T) = possiblyPremiumChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.possiblyPremiumChatOrNull(): PossiblyPremiumChat? = this as? PossiblyPremiumChat

@PreviewFeature
inline fun Chat.possiblyPremiumChatOrThrow(): PossiblyPremiumChat = this as PossiblyPremiumChat

@PreviewFeature
inline fun <T> Chat.ifAbleToAddInAttachmentMenuChat(block: (AbleToAddInAttachmentMenuChat) -> T) =
    ableToAddInAttachmentMenuChatOrNull()?.let(block)

@PreviewFeature
inline fun Chat.ableToAddInAttachmentMenuChatOrNull(): AbleToAddInAttachmentMenuChat? =
    this as? AbleToAddInAttachmentMenuChat

@PreviewFeature
inline fun Chat.ableToAddInAttachmentMenuChatOrThrow(): AbleToAddInAttachmentMenuChat =
    this as AbleToAddInAttachmentMenuChat

@PreviewFeature
inline fun <T> CallbackQuery.ifDataCallbackQuery(block: (DataCallbackQuery) -> T) =
    dataCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.dataCallbackQueryOrNull(): DataCallbackQuery? = this as? DataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.dataCallbackQueryOrThrow(): DataCallbackQuery = this as DataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifGameShortNameCallbackQuery(block: (GameShortNameCallbackQuery) -> T) =
    gameShortNameCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.gameShortNameCallbackQueryOrNull(): GameShortNameCallbackQuery? =
    this as? GameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.gameShortNameCallbackQueryOrThrow(): GameShortNameCallbackQuery =
    this as GameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifInlineMessageIdCallbackQuery(block: (InlineMessageIdCallbackQuery) -> T) =
    inlineMessageIdCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdCallbackQueryOrNull(): InlineMessageIdCallbackQuery? =
    this as? InlineMessageIdCallbackQuery

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdCallbackQueryOrThrow(): InlineMessageIdCallbackQuery =
    this as InlineMessageIdCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifInlineMessageIdDataCallbackQuery(block: (InlineMessageIdDataCallbackQuery) -> T) =
    inlineMessageIdDataCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdDataCallbackQueryOrNull(): InlineMessageIdDataCallbackQuery? =
    this as? InlineMessageIdDataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdDataCallbackQueryOrThrow(): InlineMessageIdDataCallbackQuery =
    this as InlineMessageIdDataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifInlineMessageIdGameShortNameCallbackQuery(block: (InlineMessageIdGameShortNameCallbackQuery) -> T) =
    inlineMessageIdGameShortNameCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdGameShortNameCallbackQueryOrNull(): InlineMessageIdGameShortNameCallbackQuery? =
    this as? InlineMessageIdGameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.inlineMessageIdGameShortNameCallbackQueryOrThrow(): InlineMessageIdGameShortNameCallbackQuery =
    this as InlineMessageIdGameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifMessageCallbackQuery(block: (MessageCallbackQuery) -> T) =
    messageCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.messageCallbackQueryOrNull(): MessageCallbackQuery? = this as? MessageCallbackQuery

@PreviewFeature
inline fun CallbackQuery.messageCallbackQueryOrThrow(): MessageCallbackQuery = this as MessageCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifMessageDataCallbackQuery(block: (MessageDataCallbackQuery) -> T) =
    messageDataCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.messageDataCallbackQueryOrNull(): MessageDataCallbackQuery? = this as? MessageDataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.messageDataCallbackQueryOrThrow(): MessageDataCallbackQuery = this as MessageDataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifMessageGameShortNameCallbackQuery(block: (MessageGameShortNameCallbackQuery) -> T) =
    messageGameShortNameCallbackQueryOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.messageGameShortNameCallbackQueryOrNull(): MessageGameShortNameCallbackQuery? =
    this as? MessageGameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.messageGameShortNameCallbackQueryOrThrow(): MessageGameShortNameCallbackQuery =
    this as MessageGameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.ifUnknownCallbackQueryType(block: (UnknownCallbackQueryType) -> T) =
    unknownCallbackQueryTypeOrNull()?.let(block)

@PreviewFeature
inline fun CallbackQuery.unknownCallbackQueryTypeOrNull(): UnknownCallbackQueryType? = this as? UnknownCallbackQueryType

@PreviewFeature
inline fun CallbackQuery.unknownCallbackQueryTypeOrThrow(): UnknownCallbackQueryType = this as UnknownCallbackQueryType

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorDataField(block: (PassportElementErrorDataField) -> T) =
    passportElementErrorDataFieldOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorDataFieldOrNull(): PassportElementErrorDataField? =
    this as? PassportElementErrorDataField

@PreviewFeature
inline fun PassportElementError.passportElementErrorDataFieldOrThrow(): PassportElementErrorDataField =
    this as PassportElementErrorDataField

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorFile(block: (PassportElementErrorFile) -> T) =
    passportElementErrorFileOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorFileOrNull(): PassportElementErrorFile? =
    this as? PassportElementErrorFile

@PreviewFeature
inline fun PassportElementError.passportElementErrorFileOrThrow(): PassportElementErrorFile =
    this as PassportElementErrorFile

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorFiles(block: (PassportElementErrorFiles) -> T) =
    passportElementErrorFilesOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorFilesOrNull(): PassportElementErrorFiles? =
    this as? PassportElementErrorFiles

@PreviewFeature
inline fun PassportElementError.passportElementErrorFilesOrThrow(): PassportElementErrorFiles =
    this as PassportElementErrorFiles

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorFrontSide(block: (PassportElementErrorFrontSide) -> T) =
    passportElementErrorFrontSideOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorFrontSideOrNull(): PassportElementErrorFrontSide? =
    this as? PassportElementErrorFrontSide

@PreviewFeature
inline fun PassportElementError.passportElementErrorFrontSideOrThrow(): PassportElementErrorFrontSide =
    this as PassportElementErrorFrontSide

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorReverseSide(block: (PassportElementErrorReverseSide) -> T) =
    passportElementErrorReverseSideOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorReverseSideOrNull(): PassportElementErrorReverseSide? =
    this as? PassportElementErrorReverseSide

@PreviewFeature
inline fun PassportElementError.passportElementErrorReverseSideOrThrow(): PassportElementErrorReverseSide =
    this as PassportElementErrorReverseSide

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorSelfie(block: (PassportElementErrorSelfie) -> T) =
    passportElementErrorSelfieOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorSelfieOrNull(): PassportElementErrorSelfie? =
    this as? PassportElementErrorSelfie

@PreviewFeature
inline fun PassportElementError.passportElementErrorSelfieOrThrow(): PassportElementErrorSelfie =
    this as PassportElementErrorSelfie

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorTranslationFile(block: (PassportElementErrorTranslationFile) -> T) =
    passportElementErrorTranslationFileOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorTranslationFileOrNull(): PassportElementErrorTranslationFile? =
    this as? PassportElementErrorTranslationFile

@PreviewFeature
inline fun PassportElementError.passportElementErrorTranslationFileOrThrow(): PassportElementErrorTranslationFile =
    this as PassportElementErrorTranslationFile

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorTranslationFiles(block: (PassportElementErrorTranslationFiles) -> T) =
    passportElementErrorTranslationFilesOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorTranslationFilesOrNull(): PassportElementErrorTranslationFiles? =
    this as? PassportElementErrorTranslationFiles

@PreviewFeature
inline fun PassportElementError.passportElementErrorTranslationFilesOrThrow(): PassportElementErrorTranslationFiles =
    this as PassportElementErrorTranslationFiles

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementErrorUnspecified(block: (PassportElementErrorUnspecified) -> T) =
    passportElementErrorUnspecifiedOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementErrorUnspecifiedOrNull(): PassportElementErrorUnspecified? =
    this as? PassportElementErrorUnspecified

@PreviewFeature
inline fun PassportElementError.passportElementErrorUnspecifiedOrThrow(): PassportElementErrorUnspecified =
    this as PassportElementErrorUnspecified

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementFileError(block: (PassportElementFileError) -> T) =
    passportElementFileErrorOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementFileErrorOrNull(): PassportElementFileError? =
    this as? PassportElementFileError

@PreviewFeature
inline fun PassportElementError.passportElementFileErrorOrThrow(): PassportElementFileError =
    this as PassportElementFileError

@PreviewFeature
inline fun <T> PassportElementError.ifPassportElementFilesError(block: (PassportElementFilesError) -> T) =
    passportElementFilesErrorOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportElementFilesErrorOrNull(): PassportElementFilesError? =
    this as? PassportElementFilesError

@PreviewFeature
inline fun PassportElementError.passportElementFilesErrorOrThrow(): PassportElementFilesError =
    this as PassportElementFilesError

@PreviewFeature
inline fun <T> PassportElementError.ifPassportMultipleElementsError(block: (PassportMultipleElementsError) -> T) =
    passportMultipleElementsErrorOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportMultipleElementsErrorOrNull(): PassportMultipleElementsError? =
    this as? PassportMultipleElementsError

@PreviewFeature
inline fun PassportElementError.passportMultipleElementsErrorOrThrow(): PassportMultipleElementsError =
    this as PassportMultipleElementsError

@PreviewFeature
inline fun <T> PassportElementError.ifPassportSingleElementError(block: (PassportSingleElementError) -> T) =
    passportSingleElementErrorOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.passportSingleElementErrorOrNull(): PassportSingleElementError? =
    this as? PassportSingleElementError

@PreviewFeature
inline fun PassportElementError.passportSingleElementErrorOrThrow(): PassportSingleElementError =
    this as PassportSingleElementError

@PreviewFeature
inline fun <T> PassportElementError.ifUnknownPassportElementError(block: (UnknownPassportElementError) -> T) =
    unknownPassportElementErrorOrNull()?.let(block)

@PreviewFeature
inline fun PassportElementError.unknownPassportElementErrorOrNull(): UnknownPassportElementError? =
    this as? UnknownPassportElementError

@PreviewFeature
inline fun PassportElementError.unknownPassportElementErrorOrThrow(): UnknownPassportElementError =
    this as UnknownPassportElementError

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifBankStatement(block: (BankStatement) -> T) = bankStatementOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.bankStatementOrNull(): BankStatement? = this as? BankStatement

@PreviewFeature
inline fun EncryptedPassportElement.bankStatementOrThrow(): BankStatement = this as BankStatement

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifCommonPassport(block: (CommonPassport) -> T) =
    commonPassportOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.commonPassportOrNull(): CommonPassport? = this as? CommonPassport

@PreviewFeature
inline fun EncryptedPassportElement.commonPassportOrThrow(): CommonPassport = this as CommonPassport

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifDriverLicense(block: (DriverLicense) -> T) = driverLicenseOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.driverLicenseOrNull(): DriverLicense? = this as? DriverLicense

@PreviewFeature
inline fun EncryptedPassportElement.driverLicenseOrThrow(): DriverLicense = this as DriverLicense

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEmail(block: (Email) -> T) = emailOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.emailOrNull(): Email? = this as? Email

@PreviewFeature
inline fun EncryptedPassportElement.emailOrThrow(): Email = this as Email

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedAddress(block: (EncryptedAddress) -> T) =
    encryptedAddressOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedAddressOrNull(): EncryptedAddress? = this as? EncryptedAddress

@PreviewFeature
inline fun EncryptedPassportElement.encryptedAddressOrThrow(): EncryptedAddress = this as EncryptedAddress

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPersonalDetails(block: (EncryptedPersonalDetails) -> T) =
    encryptedPersonalDetailsOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPersonalDetailsOrNull(): EncryptedPersonalDetails? =
    this as? EncryptedPersonalDetails

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPersonalDetailsOrThrow(): EncryptedPersonalDetails =
    this as EncryptedPersonalDetails

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifIdentityCard(block: (IdentityCard) -> T) = identityCardOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.identityCardOrNull(): IdentityCard? = this as? IdentityCard

@PreviewFeature
inline fun EncryptedPassportElement.identityCardOrThrow(): IdentityCard = this as IdentityCard

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifInternalPassport(block: (InternalPassport) -> T) =
    internalPassportOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.internalPassportOrNull(): InternalPassport? = this as? InternalPassport

@PreviewFeature
inline fun EncryptedPassportElement.internalPassportOrThrow(): InternalPassport = this as InternalPassport

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifPassport(block: (Passport) -> T) = passportOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.passportOrNull(): Passport? = this as? Passport

@PreviewFeature
inline fun EncryptedPassportElement.passportOrThrow(): Passport = this as Passport

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifPassportRegistration(block: (PassportRegistration) -> T) =
    passportRegistrationOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.passportRegistrationOrNull(): PassportRegistration? = this as? PassportRegistration

@PreviewFeature
inline fun EncryptedPassportElement.passportRegistrationOrThrow(): PassportRegistration = this as PassportRegistration

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifPhoneNumber(block: (PhoneNumber) -> T) = phoneNumberOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.phoneNumberOrNull(): PhoneNumber? = this as? PhoneNumber

@PreviewFeature
inline fun EncryptedPassportElement.phoneNumberOrThrow(): PhoneNumber = this as PhoneNumber

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifRentalAgreement(block: (RentalAgreement) -> T) =
    rentalAgreementOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.rentalAgreementOrNull(): RentalAgreement? = this as? RentalAgreement

@PreviewFeature
inline fun EncryptedPassportElement.rentalAgreementOrThrow(): RentalAgreement = this as RentalAgreement

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifTemporaryRegistration(block: (TemporaryRegistration) -> T) =
    temporaryRegistrationOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.temporaryRegistrationOrNull(): TemporaryRegistration? =
    this as? TemporaryRegistration

@PreviewFeature
inline fun EncryptedPassportElement.temporaryRegistrationOrThrow(): TemporaryRegistration =
    this as TemporaryRegistration

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithTranslatableFilesCollection(block: (EncryptedPassportElementWithTranslatableFilesCollection) -> T) =
    encryptedPassportElementWithTranslatableFilesCollectionOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithTranslatableFilesCollectionOrNull(): EncryptedPassportElementWithTranslatableFilesCollection? =
    this as? EncryptedPassportElementWithTranslatableFilesCollection

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithTranslatableFilesCollectionOrThrow(): EncryptedPassportElementWithTranslatableFilesCollection =
    this as EncryptedPassportElementWithTranslatableFilesCollection

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithTranslatableIDDocument(block: (EncryptedPassportElementWithTranslatableIDDocument) -> T) =
    encryptedPassportElementWithTranslatableIDDocumentOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithTranslatableIDDocumentOrNull(): EncryptedPassportElementWithTranslatableIDDocument? =
    this as? EncryptedPassportElementWithTranslatableIDDocument

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithTranslatableIDDocumentOrThrow(): EncryptedPassportElementWithTranslatableIDDocument =
    this as EncryptedPassportElementWithTranslatableIDDocument

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifUtilityBill(block: (UtilityBill) -> T) = utilityBillOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.utilityBillOrNull(): UtilityBill? = this as? UtilityBill

@PreviewFeature
inline fun EncryptedPassportElement.utilityBillOrThrow(): UtilityBill = this as UtilityBill

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithFilesCollection(block: (EncryptedPassportElementWithFilesCollection) -> T) =
    encryptedPassportElementWithFilesCollectionOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithFilesCollectionOrNull(): EncryptedPassportElementWithFilesCollection? =
    this as? EncryptedPassportElementWithFilesCollection

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithFilesCollectionOrThrow(): EncryptedPassportElementWithFilesCollection =
    this as EncryptedPassportElementWithFilesCollection

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementTranslatable(block: (EncryptedPassportElementTranslatable) -> T) =
    encryptedPassportElementTranslatableOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementTranslatableOrNull(): EncryptedPassportElementTranslatable? =
    this as? EncryptedPassportElementTranslatable

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementTranslatableOrThrow(): EncryptedPassportElementTranslatable =
    this as EncryptedPassportElementTranslatable

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifUnknownEncryptedPassportElement(block: (UnknownEncryptedPassportElement) -> T) =
    unknownEncryptedPassportElementOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.unknownEncryptedPassportElementOrNull(): UnknownEncryptedPassportElement? =
    this as? UnknownEncryptedPassportElement

@PreviewFeature
inline fun EncryptedPassportElement.unknownEncryptedPassportElementOrThrow(): UnknownEncryptedPassportElement =
    this as UnknownEncryptedPassportElement

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithData(block: (EncryptedPassportElementWithData) -> T) =
    encryptedPassportElementWithDataOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithDataOrNull(): EncryptedPassportElementWithData? =
    this as? EncryptedPassportElementWithData

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithDataOrThrow(): EncryptedPassportElementWithData =
    this as EncryptedPassportElementWithData

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithEmail(block: (EncryptedPassportElementWithEmail) -> T) =
    encryptedPassportElementWithEmailOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithEmailOrNull(): EncryptedPassportElementWithEmail? =
    this as? EncryptedPassportElementWithEmail

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithEmailOrThrow(): EncryptedPassportElementWithEmail =
    this as EncryptedPassportElementWithEmail

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithFrontSide(block: (EncryptedPassportElementWithFrontSide) -> T) =
    encryptedPassportElementWithFrontSideOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithFrontSideOrNull(): EncryptedPassportElementWithFrontSide? =
    this as? EncryptedPassportElementWithFrontSide

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithFrontSideOrThrow(): EncryptedPassportElementWithFrontSide =
    this as EncryptedPassportElementWithFrontSide

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithPhoneNumber(block: (EncryptedPassportElementWithPhoneNumber) -> T) =
    encryptedPassportElementWithPhoneNumberOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithPhoneNumberOrNull(): EncryptedPassportElementWithPhoneNumber? =
    this as? EncryptedPassportElementWithPhoneNumber

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithPhoneNumberOrThrow(): EncryptedPassportElementWithPhoneNumber =
    this as EncryptedPassportElementWithPhoneNumber

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithReverseSide(block: (EncryptedPassportElementWithReverseSide) -> T) =
    encryptedPassportElementWithReverseSideOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithReverseSideOrNull(): EncryptedPassportElementWithReverseSide? =
    this as? EncryptedPassportElementWithReverseSide

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithReverseSideOrThrow(): EncryptedPassportElementWithReverseSide =
    this as EncryptedPassportElementWithReverseSide

@PreviewFeature
inline fun <T> EncryptedPassportElement.ifEncryptedPassportElementWithSelfie(block: (EncryptedPassportElementWithSelfie) -> T) =
    encryptedPassportElementWithSelfieOrNull()?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithSelfieOrNull(): EncryptedPassportElementWithSelfie? =
    this as? EncryptedPassportElementWithSelfie

@PreviewFeature
inline fun EncryptedPassportElement.encryptedPassportElementWithSelfieOrThrow(): EncryptedPassportElementWithSelfie =
    this as EncryptedPassportElementWithSelfie

@PreviewFeature
inline fun <T> SecureValue.ifAddressSecureValue(block: (AddressSecureValue) -> T) =
    addressSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.addressSecureValueOrNull(): AddressSecureValue? = this as? AddressSecureValue

@PreviewFeature
inline fun SecureValue.addressSecureValueOrThrow(): AddressSecureValue = this as AddressSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifBankStatementSecureValue(block: (BankStatementSecureValue) -> T) =
    bankStatementSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.bankStatementSecureValueOrNull(): BankStatementSecureValue? = this as? BankStatementSecureValue

@PreviewFeature
inline fun SecureValue.bankStatementSecureValueOrThrow(): BankStatementSecureValue = this as BankStatementSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifCommonPassportSecureValue(block: (CommonPassportSecureValue) -> T) =
    commonPassportSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.commonPassportSecureValueOrNull(): CommonPassportSecureValue? =
    this as? CommonPassportSecureValue

@PreviewFeature
inline fun SecureValue.commonPassportSecureValueOrThrow(): CommonPassportSecureValue = this as CommonPassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifDriverLicenseSecureValue(block: (DriverLicenseSecureValue) -> T) =
    driverLicenseSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.driverLicenseSecureValueOrNull(): DriverLicenseSecureValue? = this as? DriverLicenseSecureValue

@PreviewFeature
inline fun SecureValue.driverLicenseSecureValueOrThrow(): DriverLicenseSecureValue = this as DriverLicenseSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifIdentityCardSecureValue(block: (IdentityCardSecureValue) -> T) =
    identityCardSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.identityCardSecureValueOrNull(): IdentityCardSecureValue? = this as? IdentityCardSecureValue

@PreviewFeature
inline fun SecureValue.identityCardSecureValueOrThrow(): IdentityCardSecureValue = this as IdentityCardSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifIdentityWithReverseSideSecureValue(block: (IdentityWithReverseSideSecureValue) -> T) =
    identityWithReverseSideSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.identityWithReverseSideSecureValueOrNull(): IdentityWithReverseSideSecureValue? =
    this as? IdentityWithReverseSideSecureValue

@PreviewFeature
inline fun SecureValue.identityWithReverseSideSecureValueOrThrow(): IdentityWithReverseSideSecureValue =
    this as IdentityWithReverseSideSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifInternalPassportSecureValue(block: (InternalPassportSecureValue) -> T) =
    internalPassportSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.internalPassportSecureValueOrNull(): InternalPassportSecureValue? =
    this as? InternalPassportSecureValue

@PreviewFeature
inline fun SecureValue.internalPassportSecureValueOrThrow(): InternalPassportSecureValue =
    this as InternalPassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifOtherDocumentsSecureValue(block: (OtherDocumentsSecureValue) -> T) =
    otherDocumentsSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.otherDocumentsSecureValueOrNull(): OtherDocumentsSecureValue? =
    this as? OtherDocumentsSecureValue

@PreviewFeature
inline fun SecureValue.otherDocumentsSecureValueOrThrow(): OtherDocumentsSecureValue = this as OtherDocumentsSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifPassportRegistrationSecureValue(block: (PassportRegistrationSecureValue) -> T) =
    passportRegistrationSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.passportRegistrationSecureValueOrNull(): PassportRegistrationSecureValue? =
    this as? PassportRegistrationSecureValue

@PreviewFeature
inline fun SecureValue.passportRegistrationSecureValueOrThrow(): PassportRegistrationSecureValue =
    this as PassportRegistrationSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifPassportSecureValue(block: (PassportSecureValue) -> T) =
    passportSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.passportSecureValueOrNull(): PassportSecureValue? = this as? PassportSecureValue

@PreviewFeature
inline fun SecureValue.passportSecureValueOrThrow(): PassportSecureValue = this as PassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifPersonalDetailsSecureValue(block: (PersonalDetailsSecureValue) -> T) =
    personalDetailsSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.personalDetailsSecureValueOrNull(): PersonalDetailsSecureValue? =
    this as? PersonalDetailsSecureValue

@PreviewFeature
inline fun SecureValue.personalDetailsSecureValueOrThrow(): PersonalDetailsSecureValue =
    this as PersonalDetailsSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifRentalAgreementSecureValue(block: (RentalAgreementSecureValue) -> T) =
    rentalAgreementSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.rentalAgreementSecureValueOrNull(): RentalAgreementSecureValue? =
    this as? RentalAgreementSecureValue

@PreviewFeature
inline fun SecureValue.rentalAgreementSecureValueOrThrow(): RentalAgreementSecureValue =
    this as RentalAgreementSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifTemporalRegistrationSecureValue(block: (TemporalRegistrationSecureValue) -> T) =
    temporalRegistrationSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.temporalRegistrationSecureValueOrNull(): TemporalRegistrationSecureValue? =
    this as? TemporalRegistrationSecureValue

@PreviewFeature
inline fun SecureValue.temporalRegistrationSecureValueOrThrow(): TemporalRegistrationSecureValue =
    this as TemporalRegistrationSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifUtilityBillSecureValue(block: (UtilityBillSecureValue) -> T) =
    utilityBillSecureValueOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.utilityBillSecureValueOrNull(): UtilityBillSecureValue? = this as? UtilityBillSecureValue

@PreviewFeature
inline fun SecureValue.utilityBillSecureValueOrThrow(): UtilityBillSecureValue = this as UtilityBillSecureValue

@PreviewFeature
inline fun <T> SecureValue.ifSecureValueIdentity(block: (SecureValueIdentity) -> T) =
    secureValueIdentityOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.secureValueIdentityOrNull(): SecureValueIdentity? = this as? SecureValueIdentity

@PreviewFeature
inline fun SecureValue.secureValueIdentityOrThrow(): SecureValueIdentity = this as SecureValueIdentity

@PreviewFeature
inline fun <T> SecureValue.ifSecureValueWithData(block: (SecureValueWithData) -> T) =
    secureValueWithDataOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.secureValueWithDataOrNull(): SecureValueWithData? = this as? SecureValueWithData

@PreviewFeature
inline fun SecureValue.secureValueWithDataOrThrow(): SecureValueWithData = this as SecureValueWithData

@PreviewFeature
inline fun <T> SecureValue.ifSecureValueWithFiles(block: (SecureValueWithFiles) -> T) =
    secureValueWithFilesOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.secureValueWithFilesOrNull(): SecureValueWithFiles? = this as? SecureValueWithFiles

@PreviewFeature
inline fun SecureValue.secureValueWithFilesOrThrow(): SecureValueWithFiles = this as SecureValueWithFiles

@PreviewFeature
inline fun <T> SecureValue.ifSecureValueWithReverseSide(block: (SecureValueWithReverseSide) -> T) =
    secureValueWithReverseSideOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.secureValueWithReverseSideOrNull(): SecureValueWithReverseSide? =
    this as? SecureValueWithReverseSide

@PreviewFeature
inline fun SecureValue.secureValueWithReverseSideOrThrow(): SecureValueWithReverseSide =
    this as SecureValueWithReverseSide

@PreviewFeature
inline fun <T> SecureValue.ifSecureValueWithTranslations(block: (SecureValueWithTranslations) -> T) =
    secureValueWithTranslationsOrNull()?.let(block)

@PreviewFeature
inline fun SecureValue.secureValueWithTranslationsOrNull(): SecureValueWithTranslations? =
    this as? SecureValueWithTranslations

@PreviewFeature
inline fun SecureValue.secureValueWithTranslationsOrThrow(): SecureValueWithTranslations =
    this as SecureValueWithTranslations

@PreviewFeature
inline fun <T> Message.ifAnonymousGroupContentMessageImpl(block: (AnonymousGroupContentMessageImpl<MessageContent>) -> T) =
    anonymousGroupContentMessageImplOrNull()?.let(block)

@PreviewFeature
inline fun Message.anonymousGroupContentMessageImplOrNull(): AnonymousGroupContentMessageImpl<MessageContent>? =
    this as? AnonymousGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.anonymousGroupContentMessageImplOrThrow(): AnonymousGroupContentMessageImpl<MessageContent> =
    this as AnonymousGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun <T> Message.ifChannelContentMessageImpl(block: (UnconnectedFromChannelGroupContentMessageImpl<MessageContent>) -> T) =
    channelContentMessageImplOrNull()?.let(block)

@PreviewFeature
inline fun Message.channelContentMessageImplOrNull(): UnconnectedFromChannelGroupContentMessageImpl<MessageContent>? =
    this as? UnconnectedFromChannelGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.channelContentMessageImplOrThrow(): UnconnectedFromChannelGroupContentMessageImpl<MessageContent> =
    this as UnconnectedFromChannelGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun <T> Message.ifPassportMessage(block: (PassportMessage) -> T) = passportMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.passportMessageOrNull(): PassportMessage? = this as? PassportMessage

@PreviewFeature
inline fun Message.passportMessageOrThrow(): PassportMessage = this as PassportMessage

@PreviewFeature
inline fun <T> Message.ifPrivateContentMessageImpl(block: (PrivateContentMessageImpl<MessageContent>) -> T) =
    privateContentMessageImplOrNull()?.let(block)

@PreviewFeature
inline fun Message.privateContentMessageImplOrNull(): PrivateContentMessageImpl<MessageContent>? =
    this as? PrivateContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.privateContentMessageImplOrThrow(): PrivateContentMessageImpl<MessageContent> =
    this as PrivateContentMessageImpl<MessageContent>

@PreviewFeature
inline fun <T> Message.ifChannelEventMessage(block: (ChannelEventMessage<ChannelEvent>) -> T) =
    channelEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.channelEventMessageOrNull(): ChannelEventMessage<ChannelEvent>? =
    this as? ChannelEventMessage<ChannelEvent>

@PreviewFeature
inline fun Message.channelEventMessageOrThrow(): ChannelEventMessage<ChannelEvent> =
    this as ChannelEventMessage<ChannelEvent>

@PreviewFeature
inline fun <T> Message.ifChannelMediaGroupMessage(block: (ChannelMediaGroupMessage<MediaGroupContent>) -> T) =
    channelMediaGroupMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.channelMediaGroupMessageOrNull(): ChannelMediaGroupMessage<MediaGroupContent>? =
    this as? ChannelMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.channelMediaGroupMessageOrThrow(): ChannelMediaGroupMessage<MediaGroupContent> =
    this as ChannelMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.ifCommonGroupEventMessage(block: (CommonGroupEventMessage<GroupEvent>) -> T) =
    commonGroupEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.commonGroupEventMessageOrNull(): CommonGroupEventMessage<GroupEvent>? =
    this as? CommonGroupEventMessage<GroupEvent>

@PreviewFeature
inline fun Message.commonGroupEventMessageOrThrow(): CommonGroupEventMessage<GroupEvent> =
    this as CommonGroupEventMessage<GroupEvent>

@PreviewFeature
inline fun <T> Message.ifCommonMediaGroupMessage(block: (CommonMediaGroupMessage<MediaGroupContent>) -> T) =
    commonMediaGroupMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.commonMediaGroupMessageOrNull(): CommonMediaGroupMessage<MediaGroupContent>? =
    this as? CommonMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.commonMediaGroupMessageOrThrow(): CommonMediaGroupMessage<MediaGroupContent> =
    this as CommonMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.ifCommonSupergroupEventMessage(block: (CommonSupergroupEventMessage<SupergroupEvent>) -> T) =
    commonSupergroupEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.commonSupergroupEventMessageOrNull(): CommonSupergroupEventMessage<SupergroupEvent>? =
    this as? CommonSupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun Message.commonSupergroupEventMessageOrThrow(): CommonSupergroupEventMessage<SupergroupEvent> =
    this as CommonSupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun <T> Message.ifAnonymousGroupContentMessage(block: (AnonymousGroupContentMessage<MessageContent>) -> T) =
    anonymousGroupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.anonymousGroupContentMessageOrNull(): AnonymousGroupContentMessage<MessageContent>? =
    this as? AnonymousGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.anonymousGroupContentMessageOrThrow(): AnonymousGroupContentMessage<MessageContent> =
    this as AnonymousGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifChannelContentMessage(block: (ChannelContentMessage<MessageContent>) -> T) =
    channelContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.channelContentMessageOrNull(): ChannelContentMessage<MessageContent>? =
    this as? ChannelContentMessage<MessageContent>

@PreviewFeature
inline fun Message.channelContentMessageOrThrow(): ChannelContentMessage<MessageContent> =
    this as ChannelContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifConnectedFromChannelGroupContentMessage(block: (ConnectedFromChannelGroupContentMessage<MessageContent>) -> T) =
    connectedFromChannelGroupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.connectedFromChannelGroupContentMessageOrNull(): ConnectedFromChannelGroupContentMessage<MessageContent>? =
    this as? ConnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.connectedFromChannelGroupContentMessageOrThrow(): ConnectedFromChannelGroupContentMessage<MessageContent> =
    this as ConnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifUnconnectedFromChannelGroupContentMessage(block: (UnconnectedFromChannelGroupContentMessage<MessageContent>) -> T) =
    unconnectedFromChannelGroupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.unconnectedFromChannelGroupContentMessageOrNull(): UnconnectedFromChannelGroupContentMessage<MessageContent>? =
    this as? UnconnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.unconnectedFromChannelGroupContentMessageOrThrow(): UnconnectedFromChannelGroupContentMessage<MessageContent> =
    this as UnconnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifChatEventMessage(block: (ChatEventMessage<ChatEvent>) -> T) =
    chatEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.chatEventMessageOrNull(): ChatEventMessage<ChatEvent>? = this as? ChatEventMessage<ChatEvent>

@PreviewFeature
inline fun Message.chatEventMessageOrThrow(): ChatEventMessage<ChatEvent> = this as ChatEventMessage<ChatEvent>

@PreviewFeature
inline fun <T> Message.ifCommonGroupContentMessage(block: (CommonGroupContentMessage<MessageContent>) -> T) =
    commonGroupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.commonGroupContentMessageOrNull(): CommonGroupContentMessage<MessageContent>? =
    this as? CommonGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.commonGroupContentMessageOrThrow(): CommonGroupContentMessage<MessageContent> =
    this as CommonGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifCommonMessage(block: (CommonMessage<MessageContent>) -> T) = commonMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.commonMessageOrNull(): CommonMessage<MessageContent>? = this as? CommonMessage<MessageContent>

@PreviewFeature
inline fun Message.commonMessageOrThrow(): CommonMessage<MessageContent> = this as CommonMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifContentMessage(block: (ContentMessage<MessageContent>) -> T) =
    contentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.contentMessageOrNull(): ContentMessage<MessageContent>? = this as? ContentMessage<MessageContent>

@PreviewFeature
inline fun Message.contentMessageOrThrow(): ContentMessage<MessageContent> = this as ContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifFromChannelGroupContentMessage(block: (FromChannelGroupContentMessage<MessageContent>) -> T) =
    fromChannelGroupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.fromChannelGroupContentMessageOrNull(): FromChannelGroupContentMessage<MessageContent>? =
    this as? FromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.fromChannelGroupContentMessageOrThrow(): FromChannelGroupContentMessage<MessageContent> =
    this as FromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifGroupEventMessage(block: (GroupEventMessage<GroupEvent>) -> T) =
    groupEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.groupEventMessageOrNull(): GroupEventMessage<GroupEvent>? = this as? GroupEventMessage<GroupEvent>

@PreviewFeature
inline fun Message.groupEventMessageOrThrow(): GroupEventMessage<GroupEvent> = this as GroupEventMessage<GroupEvent>

@PreviewFeature
inline fun <T> Message.ifPrivateEventMessage(block: (PrivateEventMessage<PrivateEvent>) -> T) =
    privateEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.privateEventMessageOrNull(): PrivateEventMessage<PrivateEvent>? =
    this as? PrivateEventMessage<PrivateEvent>

@PreviewFeature
inline fun Message.privateEventMessageOrThrow(): PrivateEventMessage<PrivateEvent> =
    this as PrivateEventMessage<PrivateEvent>

@PreviewFeature
inline fun <T> Message.ifGroupContentMessage(block: (GroupContentMessage<MessageContent>) -> T) =
    groupContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.groupContentMessageOrNull(): GroupContentMessage<MessageContent>? =
    this as? GroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.groupContentMessageOrThrow(): GroupContentMessage<MessageContent> =
    this as GroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifMediaGroupMessage(block: (MediaGroupMessage<MediaGroupContent>) -> T) =
    mediaGroupMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.mediaGroupMessageOrNull(): MediaGroupMessage<MediaGroupContent>? =
    this as? MediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.mediaGroupMessageOrThrow(): MediaGroupMessage<MediaGroupContent> =
    this as MediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.ifPossiblyEditedMessage(block: (PossiblyEditedMessage) -> T) =
    possiblyEditedMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.possiblyEditedMessageOrNull(): PossiblyEditedMessage? = this as? PossiblyEditedMessage

@PreviewFeature
inline fun Message.possiblyEditedMessageOrThrow(): PossiblyEditedMessage = this as PossiblyEditedMessage

@PreviewFeature
inline fun <T> Message.ifPossiblyReplyMessage(block: (PossiblyReplyMessage) -> T) =
    possiblyReplyMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.possiblyReplyMessageOrNull(): PossiblyReplyMessage? = this as? PossiblyReplyMessage

@PreviewFeature
inline fun Message.possiblyReplyMessageOrThrow(): PossiblyReplyMessage = this as PossiblyReplyMessage

@PreviewFeature
inline fun <T> Message.ifPossiblyForwardedMessage(block: (PossiblyForwardedMessage) -> T) =
    possiblyForwardedMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.possiblyForwardedMessageOrNull(): PossiblyForwardedMessage? = this as? PossiblyForwardedMessage

@PreviewFeature
inline fun Message.possiblyForwardedMessageOrThrow(): PossiblyForwardedMessage = this as PossiblyForwardedMessage

@PreviewFeature
inline fun <T> Message.ifPossiblyPaymentMessage(block: (PossiblyPaymentMessage) -> T) =
    possiblyPaymentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.possiblyPaymentMessageOrNull(): PossiblyPaymentMessage? = this as? PossiblyPaymentMessage

@PreviewFeature
inline fun Message.possiblyPaymentMessageOrThrow(): PossiblyPaymentMessage = this as PossiblyPaymentMessage

@PreviewFeature
inline fun <T> Message.ifPrivateContentMessage(block: (PrivateContentMessage<MessageContent>) -> T) =
    privateContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.privateContentMessageOrNull(): PrivateContentMessage<MessageContent>? =
    this as? PrivateContentMessage<MessageContent>

@PreviewFeature
inline fun Message.privateContentMessageOrThrow(): PrivateContentMessage<MessageContent> =
    this as PrivateContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifPublicContentMessage(block: (PublicContentMessage<MessageContent>) -> T) =
    publicContentMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.publicContentMessageOrNull(): PublicContentMessage<MessageContent>? =
    this as? PublicContentMessage<MessageContent>

@PreviewFeature
inline fun Message.publicContentMessageOrThrow(): PublicContentMessage<MessageContent> =
    this as PublicContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifSignedMessage(block: (SignedMessage) -> T) = signedMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.signedMessageOrNull(): SignedMessage? = this as? SignedMessage

@PreviewFeature
inline fun Message.signedMessageOrThrow(): SignedMessage = this as SignedMessage

@PreviewFeature
inline fun <T> Message.ifSupergroupEventMessage(block: (SupergroupEventMessage<SupergroupEvent>) -> T) =
    supergroupEventMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.supergroupEventMessageOrNull(): SupergroupEventMessage<SupergroupEvent>? =
    this as? SupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun Message.supergroupEventMessageOrThrow(): SupergroupEventMessage<SupergroupEvent> =
    this as SupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun <T> Message.ifUnknownMessageType(block: (UnknownMessageType) -> T) = unknownMessageTypeOrNull()?.let(block)

@PreviewFeature
inline fun Message.unknownMessageTypeOrNull(): UnknownMessageType? = this as? UnknownMessageType

@PreviewFeature
inline fun Message.unknownMessageTypeOrThrow(): UnknownMessageType = this as UnknownMessageType

@PreviewFeature
inline fun <T> Message.ifPossiblySentViaBotCommonMessage(block: (PossiblySentViaBotCommonMessage<MessageContent>) -> T) =
    possiblySentViaBotCommonMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.possiblySentViaBotCommonMessageOrNull(): PossiblySentViaBotCommonMessage<MessageContent>? =
    this as? PossiblySentViaBotCommonMessage<MessageContent>

@PreviewFeature
inline fun Message.possiblySentViaBotCommonMessageOrThrow(): PossiblySentViaBotCommonMessage<MessageContent> =
    this as PossiblySentViaBotCommonMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.ifFromUserMessage(block: (FromUserMessage) -> T) = fromUserMessageOrNull()?.let(block)

@PreviewFeature
inline fun Message.fromUserMessageOrNull(): FromUserMessage? = this as? FromUserMessage

@PreviewFeature
inline fun Message.fromUserMessageOrThrow(): FromUserMessage = this as FromUserMessage

@PreviewFeature
inline fun <T> BotAction.ifFindLocationAction(block: (FindLocationAction) -> T) = findLocationActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.findLocationActionOrNull(): FindLocationAction? = this as? FindLocationAction

@PreviewFeature
inline fun BotAction.findLocationActionOrThrow(): FindLocationAction = this as FindLocationAction

@PreviewFeature
inline fun <T> BotAction.ifRecordVoiceAction(block: (RecordVoiceAction) -> T) = recordVoiceActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.recordVoiceActionOrNull(): RecordVoiceAction? = this as? RecordVoiceAction

@PreviewFeature
inline fun BotAction.recordVoiceActionOrThrow(): RecordVoiceAction = this as RecordVoiceAction

@PreviewFeature
inline fun <T> BotAction.ifRecordVideoAction(block: (RecordVideoAction) -> T) = recordVideoActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.recordVideoActionOrNull(): RecordVideoAction? = this as? RecordVideoAction

@PreviewFeature
inline fun BotAction.recordVideoActionOrThrow(): RecordVideoAction = this as RecordVideoAction

@PreviewFeature
inline fun <T> BotAction.ifRecordVideoNoteAction(block: (RecordVideoNoteAction) -> T) =
    recordVideoNoteActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.recordVideoNoteActionOrNull(): RecordVideoNoteAction? = this as? RecordVideoNoteAction

@PreviewFeature
inline fun BotAction.recordVideoNoteActionOrThrow(): RecordVideoNoteAction = this as RecordVideoNoteAction

@PreviewFeature
inline fun <T> BotAction.ifTypingAction(block: (TypingAction) -> T) = typingActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.typingActionOrNull(): TypingAction? = this as? TypingAction

@PreviewFeature
inline fun BotAction.typingActionOrThrow(): TypingAction = this as TypingAction

@PreviewFeature
inline fun <T> BotAction.ifChooseStickerAction(block: (ChooseStickerAction) -> T) =
    chooseStickerActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.chooseStickerActionOrNull(): ChooseStickerAction? = this as? ChooseStickerAction

@PreviewFeature
inline fun BotAction.chooseStickerActionOrThrow(): ChooseStickerAction = this as ChooseStickerAction

@PreviewFeature
inline fun <T> BotAction.ifUploadVoiceAction(block: (UploadVoiceAction) -> T) = uploadVoiceActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.uploadVoiceActionOrNull(): UploadVoiceAction? = this as? UploadVoiceAction

@PreviewFeature
inline fun BotAction.uploadVoiceActionOrThrow(): UploadVoiceAction = this as UploadVoiceAction

@PreviewFeature
inline fun <T> BotAction.ifUploadDocumentAction(block: (UploadDocumentAction) -> T) =
    uploadDocumentActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.uploadDocumentActionOrNull(): UploadDocumentAction? = this as? UploadDocumentAction

@PreviewFeature
inline fun BotAction.uploadDocumentActionOrThrow(): UploadDocumentAction = this as UploadDocumentAction

@PreviewFeature
inline fun <T> BotAction.ifUploadPhotoAction(block: (UploadPhotoAction) -> T) = uploadPhotoActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.uploadPhotoActionOrNull(): UploadPhotoAction? = this as? UploadPhotoAction

@PreviewFeature
inline fun BotAction.uploadPhotoActionOrThrow(): UploadPhotoAction = this as UploadPhotoAction

@PreviewFeature
inline fun <T> BotAction.ifUploadVideoAction(block: (UploadVideoAction) -> T) = uploadVideoActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.uploadVideoActionOrNull(): UploadVideoAction? = this as? UploadVideoAction

@PreviewFeature
inline fun BotAction.uploadVideoActionOrThrow(): UploadVideoAction = this as UploadVideoAction

@PreviewFeature
inline fun <T> BotAction.ifUploadVideoNoteAction(block: (UploadVideoNoteAction) -> T) =
    uploadVideoNoteActionOrNull()?.let(block)

@PreviewFeature
inline fun BotAction.uploadVideoNoteActionOrNull(): UploadVideoNoteAction? = this as? UploadVideoNoteAction

@PreviewFeature
inline fun BotAction.uploadVideoNoteActionOrThrow(): UploadVideoNoteAction = this as UploadVideoNoteAction

@PreviewFeature
inline fun <T> InlineQuery.ifBaseInlineQuery(block: (BaseInlineQuery) -> T) = baseInlineQueryOrNull()?.let(block)

@PreviewFeature
inline fun InlineQuery.baseInlineQueryOrNull(): BaseInlineQuery? =
    this as? BaseInlineQuery

@PreviewFeature
inline fun InlineQuery.baseInlineQueryOrThrow(): BaseInlineQuery =
    this as BaseInlineQuery

@PreviewFeature
inline fun <T> InlineQuery.ifLocationInlineQuery(block: (LocationInlineQuery) -> T) =
    locationInlineQueryOrNull()?.let(block)

@PreviewFeature
inline fun InlineQuery.locationInlineQueryOrNull(): LocationInlineQuery? =
    this as? LocationInlineQuery

@PreviewFeature
inline fun InlineQuery.locationInlineQueryOrThrow(): LocationInlineQuery =
    this as LocationInlineQuery

@PreviewFeature
inline fun <T> InputMessageContent.ifInputContactMessageContent(block: (InputContactMessageContent) -> T) =
    inputContactMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun InputMessageContent.inputContactMessageContentOrNull(): InputContactMessageContent? =
    this as? InputContactMessageContent

@PreviewFeature
inline fun InputMessageContent.inputContactMessageContentOrThrow(): InputContactMessageContent =
    this as InputContactMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.ifInputLocationMessageContent(block: (InputLocationMessageContent) -> T) =
    inputLocationMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun InputMessageContent.inputLocationMessageContentOrNull(): InputLocationMessageContent? =
    this as? InputLocationMessageContent

@PreviewFeature
inline fun InputMessageContent.inputLocationMessageContentOrThrow(): InputLocationMessageContent =
    this as InputLocationMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.ifInputTextMessageContent(block: (InputTextMessageContent) -> T) =
    inputTextMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun InputMessageContent.inputTextMessageContentOrNull(): InputTextMessageContent? =
    this as? InputTextMessageContent

@PreviewFeature
inline fun InputMessageContent.inputTextMessageContentOrThrow(): InputTextMessageContent =
    this as InputTextMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.ifInputVenueMessageContent(block: (InputVenueMessageContent) -> T) =
    inputVenueMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun InputMessageContent.inputVenueMessageContentOrNull(): InputVenueMessageContent? =
    this as? InputVenueMessageContent

@PreviewFeature
inline fun InputMessageContent.inputVenueMessageContentOrThrow(): InputVenueMessageContent =
    this as InputVenueMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.ifInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T) =
    inputInvoiceMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun InputMessageContent.inputInvoiceMessageContentOrNull(): InputInvoiceMessageContent? =
    this as? InputInvoiceMessageContent

@PreviewFeature
inline fun InputMessageContent.inputInvoiceMessageContentOrThrow(): InputInvoiceMessageContent =
    this as InputInvoiceMessageContent

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultArticle(block: (InlineQueryResultArticle) -> T) =
    inlineQueryResultArticleOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultArticleOrNull(): InlineQueryResultArticle? =
    this as? InlineQueryResultArticle

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultArticleOrThrow(): InlineQueryResultArticle =
    this as InlineQueryResultArticle

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultContact(block: (InlineQueryResultContact) -> T) =
    inlineQueryResultContactOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultContactOrNull(): InlineQueryResultContact? =
    this as? InlineQueryResultContact

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultContactOrThrow(): InlineQueryResultContact =
    this as InlineQueryResultContact

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultGame(block: (InlineQueryResultGame) -> T) =
    inlineQueryResultGameOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGameOrNull(): InlineQueryResultGame? = this as? InlineQueryResultGame

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGameOrThrow(): InlineQueryResultGame = this as InlineQueryResultGame

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultLocation(block: (InlineQueryResultLocation) -> T) =
    inlineQueryResultLocationOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultLocationOrNull(): InlineQueryResultLocation? =
    this as? InlineQueryResultLocation

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultLocationOrThrow(): InlineQueryResultLocation =
    this as InlineQueryResultLocation

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultStickerCached(block: (InlineQueryResultStickerCached) -> T) =
    inlineQueryResultStickerCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultStickerCachedOrNull(): InlineQueryResultStickerCached? =
    this as? InlineQueryResultStickerCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultStickerCachedOrThrow(): InlineQueryResultStickerCached =
    this as InlineQueryResultStickerCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVenue(block: (InlineQueryResultVenue) -> T) =
    inlineQueryResultVenueOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVenueOrNull(): InlineQueryResultVenue? = this as? InlineQueryResultVenue

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVenueOrThrow(): InlineQueryResultVenue = this as InlineQueryResultVenue

@PreviewFeature
inline fun <T> InlineQueryResult.ifDescribedInlineQueryResult(block: (DescribedInlineQueryResult) -> T) =
    describedInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.describedInlineQueryResultOrNull(): DescribedInlineQueryResult? =
    this as? DescribedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.describedInlineQueryResultOrThrow(): DescribedInlineQueryResult =
    this as DescribedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifFileInlineQueryResult(block: (FileInlineQueryResult) -> T) =
    fileInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.fileInlineQueryResultOrNull(): FileInlineQueryResult? = this as? FileInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.fileInlineQueryResultOrThrow(): FileInlineQueryResult = this as FileInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifOptionallyTitledInlineQueryResult(block: (OptionallyTitledInlineQueryResult) -> T) =
    optionallyTitledInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.optionallyTitledInlineQueryResultOrNull(): OptionallyTitledInlineQueryResult? =
    this as? OptionallyTitledInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.optionallyTitledInlineQueryResultOrThrow(): OptionallyTitledInlineQueryResult =
    this as OptionallyTitledInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifSizedInlineQueryResult(block: (SizedInlineQueryResult) -> T) =
    sizedInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.sizedInlineQueryResultOrNull(): SizedInlineQueryResult? = this as? SizedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.sizedInlineQueryResultOrThrow(): SizedInlineQueryResult = this as SizedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifThumbSizedInlineQueryResult(block: (ThumbSizedInlineQueryResult) -> T) =
    thumbSizedInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.thumbSizedInlineQueryResultOrNull(): ThumbSizedInlineQueryResult? =
    this as? ThumbSizedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.thumbSizedInlineQueryResultOrThrow(): ThumbSizedInlineQueryResult =
    this as ThumbSizedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifThumbedInlineQueryResult(block: (ThumbedInlineQueryResult) -> T) =
    thumbedInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.thumbedInlineQueryResultOrNull(): ThumbedInlineQueryResult? =
    this as? ThumbedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.thumbedInlineQueryResultOrThrow(): ThumbedInlineQueryResult =
    this as ThumbedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifThumbedWithMimeTypeInlineQueryResult(block: (ThumbedWithMimeTypeInlineQueryResult) -> T) =
    thumbedWithMimeTypeInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.thumbedWithMimeTypeInlineQueryResultOrNull(): ThumbedWithMimeTypeInlineQueryResult? =
    this as? ThumbedWithMimeTypeInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.thumbedWithMimeTypeInlineQueryResultOrThrow(): ThumbedWithMimeTypeInlineQueryResult =
    this as ThumbedWithMimeTypeInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifTitledInlineQueryResult(block: (TitledInlineQueryResult) -> T) =
    titledInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.titledInlineQueryResultOrNull(): TitledInlineQueryResult? =
    this as? TitledInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.titledInlineQueryResultOrThrow(): TitledInlineQueryResult = this as TitledInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifUrlInlineQueryResult(block: (UrlInlineQueryResult) -> T) =
    urlInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.urlInlineQueryResultOrNull(): UrlInlineQueryResult? = this as? UrlInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.urlInlineQueryResultOrThrow(): UrlInlineQueryResult = this as UrlInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifWithInputMessageContentInlineQueryResult(block: (WithInputMessageContentInlineQueryResult) -> T) =
    withInputMessageContentInlineQueryResultOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.withInputMessageContentInlineQueryResultOrNull(): WithInputMessageContentInlineQueryResult? =
    this as? WithInputMessageContentInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.withInputMessageContentInlineQueryResultOrThrow(): WithInputMessageContentInlineQueryResult =
    this as WithInputMessageContentInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultAudio(block: (InlineQueryResultAudio) -> T) =
    inlineQueryResultAudioOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioOrNull(): InlineQueryResultAudio? = this as? InlineQueryResultAudio

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioOrThrow(): InlineQueryResultAudio = this as InlineQueryResultAudio

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultAudioCached(block: (InlineQueryResultAudioCached) -> T) =
    inlineQueryResultAudioCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioCachedOrNull(): InlineQueryResultAudioCached? =
    this as? InlineQueryResultAudioCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioCachedOrThrow(): InlineQueryResultAudioCached =
    this as InlineQueryResultAudioCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultAudioCommon(block: (InlineQueryResultAudioCommon) -> T) =
    inlineQueryResultAudioCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioCommonOrNull(): InlineQueryResultAudioCommon? =
    this as? InlineQueryResultAudioCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultAudioCommonOrThrow(): InlineQueryResultAudioCommon =
    this as InlineQueryResultAudioCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultDocument(block: (InlineQueryResultDocument) -> T) =
    inlineQueryResultDocumentOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentOrNull(): InlineQueryResultDocument? =
    this as? InlineQueryResultDocument

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentOrThrow(): InlineQueryResultDocument =
    this as InlineQueryResultDocument

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultDocumentCached(block: (InlineQueryResultDocumentCached) -> T) =
    inlineQueryResultDocumentCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentCachedOrNull(): InlineQueryResultDocumentCached? =
    this as? InlineQueryResultDocumentCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentCachedOrThrow(): InlineQueryResultDocumentCached =
    this as InlineQueryResultDocumentCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultDocumentCommon(block: (InlineQueryResultDocumentCommon) -> T) =
    inlineQueryResultDocumentCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentCommonOrNull(): InlineQueryResultDocumentCommon? =
    this as? InlineQueryResultDocumentCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultDocumentCommonOrThrow(): InlineQueryResultDocumentCommon =
    this as InlineQueryResultDocumentCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultGif(block: (InlineQueryResultGif) -> T) =
    inlineQueryResultGifOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifOrNull(): InlineQueryResultGif? = this as? InlineQueryResultGif

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifOrThrow(): InlineQueryResultGif = this as InlineQueryResultGif

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultGifCached(block: (InlineQueryResultGifCached) -> T) =
    inlineQueryResultGifCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifCachedOrNull(): InlineQueryResultGifCached? =
    this as? InlineQueryResultGifCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifCachedOrThrow(): InlineQueryResultGifCached =
    this as InlineQueryResultGifCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultGifCommon(block: (InlineQueryResultGifCommon) -> T) =
    inlineQueryResultGifCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifCommonOrNull(): InlineQueryResultGifCommon? =
    this as? InlineQueryResultGifCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultGifCommonOrThrow(): InlineQueryResultGifCommon =
    this as InlineQueryResultGifCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultMpeg4Gif(block: (InlineQueryResultMpeg4Gif) -> T) =
    inlineQueryResultMpeg4GifOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifOrNull(): InlineQueryResultMpeg4Gif? =
    this as? InlineQueryResultMpeg4Gif

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifOrThrow(): InlineQueryResultMpeg4Gif =
    this as InlineQueryResultMpeg4Gif

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultMpeg4GifCached(block: (InlineQueryResultMpeg4GifCached) -> T) =
    inlineQueryResultMpeg4GifCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedOrNull(): InlineQueryResultMpeg4GifCached? =
    this as? InlineQueryResultMpeg4GifCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifCachedOrThrow(): InlineQueryResultMpeg4GifCached =
    this as InlineQueryResultMpeg4GifCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultMpeg4GifCommon(block: (InlineQueryResultMpeg4GifCommon) -> T) =
    inlineQueryResultMpeg4GifCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifCommonOrNull(): InlineQueryResultMpeg4GifCommon? =
    this as? InlineQueryResultMpeg4GifCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultMpeg4GifCommonOrThrow(): InlineQueryResultMpeg4GifCommon =
    this as InlineQueryResultMpeg4GifCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultPhoto(block: (InlineQueryResultPhoto) -> T) =
    inlineQueryResultPhotoOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoOrNull(): InlineQueryResultPhoto? = this as? InlineQueryResultPhoto

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoOrThrow(): InlineQueryResultPhoto = this as InlineQueryResultPhoto

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultPhotoCached(block: (InlineQueryResultPhotoCached) -> T) =
    inlineQueryResultPhotoCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoCachedOrNull(): InlineQueryResultPhotoCached? =
    this as? InlineQueryResultPhotoCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoCachedOrThrow(): InlineQueryResultPhotoCached =
    this as InlineQueryResultPhotoCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultPhotoCommon(block: (InlineQueryResultPhotoCommon) -> T) =
    inlineQueryResultPhotoCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoCommonOrNull(): InlineQueryResultPhotoCommon? =
    this as? InlineQueryResultPhotoCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultPhotoCommonOrThrow(): InlineQueryResultPhotoCommon =
    this as InlineQueryResultPhotoCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVideo(block: (InlineQueryResultVideo) -> T) =
    inlineQueryResultVideoOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoOrNull(): InlineQueryResultVideo? = this as? InlineQueryResultVideo

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoOrThrow(): InlineQueryResultVideo = this as InlineQueryResultVideo

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVideoCached(block: (InlineQueryResultVideoCached) -> T) =
    inlineQueryResultVideoCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoCachedOrNull(): InlineQueryResultVideoCached? =
    this as? InlineQueryResultVideoCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoCachedOrThrow(): InlineQueryResultVideoCached =
    this as InlineQueryResultVideoCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVideoCommon(block: (InlineQueryResultVideoCommon) -> T) =
    inlineQueryResultVideoCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoCommonOrNull(): InlineQueryResultVideoCommon? =
    this as? InlineQueryResultVideoCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVideoCommonOrThrow(): InlineQueryResultVideoCommon =
    this as InlineQueryResultVideoCommon

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVoice(block: (InlineQueryResultVoice) -> T) =
    inlineQueryResultVoiceOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceOrNull(): InlineQueryResultVoice? = this as? InlineQueryResultVoice

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceOrThrow(): InlineQueryResultVoice = this as InlineQueryResultVoice

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVoiceCached(block: (InlineQueryResultVoiceCached) -> T) =
    inlineQueryResultVoiceCachedOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceCachedOrNull(): InlineQueryResultVoiceCached? =
    this as? InlineQueryResultVoiceCached

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceCachedOrThrow(): InlineQueryResultVoiceCached =
    this as InlineQueryResultVoiceCached

@PreviewFeature
inline fun <T> InlineQueryResult.ifInlineQueryResultVoiceCommon(block: (InlineQueryResultVoiceCommon) -> T) =
    inlineQueryResultVoiceCommonOrNull()?.let(block)

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceCommonOrNull(): InlineQueryResultVoiceCommon? =
    this as? InlineQueryResultVoiceCommon

@PreviewFeature
inline fun InlineQueryResult.inlineQueryResultVoiceCommonOrThrow(): InlineQueryResultVoiceCommon =
    this as InlineQueryResultVoiceCommon

@PreviewFeature
inline fun <T> ChatMember.ifOwnerChatMember(block: (OwnerChatMember) -> T) = ownerChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.ownerChatMemberOrNull(): OwnerChatMember? = this as? OwnerChatMember

@PreviewFeature
inline fun ChatMember.ownerChatMemberOrThrow(): OwnerChatMember = this as OwnerChatMember

@PreviewFeature
inline fun <T> ChatMember.ifKickedChatMember(block: (KickedChatMember) -> T) = kickedChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.kickedChatMemberOrNull(): KickedChatMember? = this as? KickedChatMember

@PreviewFeature
inline fun ChatMember.kickedChatMemberOrThrow(): KickedChatMember = this as KickedChatMember

@PreviewFeature
inline fun <T> ChatMember.ifLeftChatMember(block: (LeftChatMember) -> T) = leftChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.leftChatMemberOrNull(): LeftChatMember? = this as? LeftChatMember

@PreviewFeature
inline fun ChatMember.leftChatMemberOrThrow(): LeftChatMember = this as LeftChatMember

@PreviewFeature
inline fun <T> ChatMember.ifMemberChatMember(block: (MemberChatMember) -> T) = memberChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.memberChatMemberOrNull(): MemberChatMember? = this as? MemberChatMember

@PreviewFeature
inline fun ChatMember.memberChatMemberOrThrow(): MemberChatMember = this as MemberChatMember

@PreviewFeature
inline fun <T> ChatMember.ifRestrictedChatMember(block: (RestrictedChatMember) -> T) =
    restrictedChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.restrictedChatMemberOrNull(): RestrictedChatMember? = this as? RestrictedChatMember

@PreviewFeature
inline fun ChatMember.restrictedChatMemberOrThrow(): RestrictedChatMember = this as RestrictedChatMember

@PreviewFeature
inline fun <T> ChatMember.ifAdministratorChatMember(block: (AdministratorChatMember) -> T) =
    administratorChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.administratorChatMemberOrNull(): AdministratorChatMember? = this as? AdministratorChatMember

@PreviewFeature
inline fun ChatMember.administratorChatMemberOrThrow(): AdministratorChatMember = this as AdministratorChatMember

@PreviewFeature
inline fun <T> ChatMember.ifBannedChatMember(block: (BannedChatMember) -> T) = bannedChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.bannedChatMemberOrNull(): BannedChatMember? = this as? BannedChatMember

@PreviewFeature
inline fun ChatMember.bannedChatMemberOrThrow(): BannedChatMember = this as BannedChatMember

@PreviewFeature
inline fun <T> ChatMember.ifSpecialRightsChatMember(block: (SpecialRightsChatMember) -> T) =
    specialRightsChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatMember.specialRightsChatMemberOrNull(): SpecialRightsChatMember? = this as? SpecialRightsChatMember

@PreviewFeature
inline fun ChatMember.specialRightsChatMemberOrThrow(): SpecialRightsChatMember = this as SpecialRightsChatMember

@PreviewFeature
inline fun <T> TelegramMedia.ifAudioMediaGroupMemberTelegramMedia(block: (AudioMediaGroupMemberTelegramMedia) -> T) =
    audioMediaGroupMemberTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.audioMediaGroupMemberTelegramMediaOrNull(): AudioMediaGroupMemberTelegramMedia? =
    this as? AudioMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun TelegramMedia.audioMediaGroupMemberTelegramMediaOrThrow(): AudioMediaGroupMemberTelegramMedia =
    this as AudioMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifDocumentMediaGroupMemberTelegramMedia(block: (DocumentMediaGroupMemberTelegramMedia) -> T) =
    documentMediaGroupMemberTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.documentMediaGroupMemberTelegramMediaOrNull(): DocumentMediaGroupMemberTelegramMedia? =
    this as? DocumentMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun TelegramMedia.documentMediaGroupMemberTelegramMediaOrThrow(): DocumentMediaGroupMemberTelegramMedia =
    this as DocumentMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifDuratedTelegramMedia(block: (DuratedTelegramMedia) -> T) =
    duratedTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.duratedTelegramMediaOrNull(): DuratedTelegramMedia? = this as? DuratedTelegramMedia

@PreviewFeature
inline fun TelegramMedia.duratedTelegramMediaOrThrow(): DuratedTelegramMedia = this as DuratedTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifTelegramMediaAnimation(block: (TelegramMediaAnimation) -> T) =
    telegramMediaAnimationOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.telegramMediaAnimationOrNull(): TelegramMediaAnimation? = this as? TelegramMediaAnimation

@PreviewFeature
inline fun TelegramMedia.telegramMediaAnimationOrThrow(): TelegramMediaAnimation = this as TelegramMediaAnimation

@PreviewFeature
inline fun <T> TelegramMedia.ifTelegramMediaAudio(block: (TelegramMediaAudio) -> T) =
    telegramMediaAudioOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.telegramMediaAudioOrNull(): TelegramMediaAudio? = this as? TelegramMediaAudio

@PreviewFeature
inline fun TelegramMedia.telegramMediaAudioOrThrow(): TelegramMediaAudio = this as TelegramMediaAudio

@PreviewFeature
inline fun <T> TelegramMedia.ifTelegramMediaDocument(block: (TelegramMediaDocument) -> T) =
    telegramMediaDocumentOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.telegramMediaDocumentOrNull(): TelegramMediaDocument? = this as? TelegramMediaDocument

@PreviewFeature
inline fun TelegramMedia.telegramMediaDocumentOrThrow(): TelegramMediaDocument = this as TelegramMediaDocument

@PreviewFeature
inline fun <T> TelegramMedia.ifTelegramMediaPhoto(block: (TelegramMediaPhoto) -> T) =
    telegramMediaPhotoOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.telegramMediaPhotoOrNull(): TelegramMediaPhoto? = this as? TelegramMediaPhoto

@PreviewFeature
inline fun TelegramMedia.telegramMediaPhotoOrThrow(): TelegramMediaPhoto = this as TelegramMediaPhoto

@PreviewFeature
inline fun <T> TelegramMedia.ifTelegramMediaVideo(block: (TelegramMediaVideo) -> T) =
    telegramMediaVideoOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.telegramMediaVideoOrNull(): TelegramMediaVideo? = this as? TelegramMediaVideo

@PreviewFeature
inline fun TelegramMedia.telegramMediaVideoOrThrow(): TelegramMediaVideo = this as TelegramMediaVideo

@PreviewFeature
inline fun <T> TelegramMedia.ifMediaGroupMemberTelegramMedia(block: (MediaGroupMemberTelegramMedia) -> T) =
    mediaGroupMemberTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.mediaGroupMemberTelegramMediaOrNull(): MediaGroupMemberTelegramMedia? =
    this as? MediaGroupMemberTelegramMedia

@PreviewFeature
inline fun TelegramMedia.mediaGroupMemberTelegramMediaOrThrow(): MediaGroupMemberTelegramMedia =
    this as MediaGroupMemberTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifSizedTelegramMedia(block: (SizedTelegramMedia) -> T) =
    sizedTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.sizedTelegramMediaOrNull(): SizedTelegramMedia? = this as? SizedTelegramMedia

@PreviewFeature
inline fun TelegramMedia.sizedTelegramMediaOrThrow(): SizedTelegramMedia = this as SizedTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifThumbedTelegramMedia(block: (ThumbedTelegramMedia) -> T) =
    thumbedTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.thumbedTelegramMediaOrNull(): ThumbedTelegramMedia? = this as? ThumbedTelegramMedia

@PreviewFeature
inline fun TelegramMedia.thumbedTelegramMediaOrThrow(): ThumbedTelegramMedia = this as ThumbedTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifTitledTelegramMedia(block: (TitledTelegramMedia) -> T) =
    titledTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.titledTelegramMediaOrNull(): TitledTelegramMedia? = this as? TitledTelegramMedia

@PreviewFeature
inline fun TelegramMedia.titledTelegramMediaOrThrow(): TitledTelegramMedia = this as TitledTelegramMedia

@PreviewFeature
inline fun <T> TelegramMedia.ifVisualMediaGroupMemberTelegramMedia(block: (VisualMediaGroupMemberTelegramMedia) -> T) =
    visualMediaGroupMemberTelegramMediaOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrNull(): VisualMediaGroupMemberTelegramMedia? =
    this as? VisualMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun TelegramMedia.visualMediaGroupMemberTelegramMediaOrThrow(): VisualMediaGroupMemberTelegramMedia =
    this as VisualMediaGroupMemberTelegramMedia

@PreviewFeature
inline fun <T> Update.ifCallbackQueryUpdate(block: (CallbackQueryUpdate) -> T) = callbackQueryUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.callbackQueryUpdateOrNull(): CallbackQueryUpdate? = this as? CallbackQueryUpdate

@PreviewFeature
inline fun Update.callbackQueryUpdateOrThrow(): CallbackQueryUpdate = this as CallbackQueryUpdate

@PreviewFeature
inline fun <T> Update.ifChannelPostUpdate(block: (ChannelPostUpdate) -> T) = channelPostUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.channelPostUpdateOrNull(): ChannelPostUpdate? = this as? ChannelPostUpdate

@PreviewFeature
inline fun Update.channelPostUpdateOrThrow(): ChannelPostUpdate = this as ChannelPostUpdate

@PreviewFeature
inline fun <T> Update.ifChosenInlineResultUpdate(block: (ChosenInlineResultUpdate) -> T) =
    chosenInlineResultUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.chosenInlineResultUpdateOrNull(): ChosenInlineResultUpdate? = this as? ChosenInlineResultUpdate

@PreviewFeature
inline fun Update.chosenInlineResultUpdateOrThrow(): ChosenInlineResultUpdate = this as ChosenInlineResultUpdate

@PreviewFeature
inline fun <T> Update.ifEditChannelPostUpdate(block: (EditChannelPostUpdate) -> T) =
    editChannelPostUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.editChannelPostUpdateOrNull(): EditChannelPostUpdate? = this as? EditChannelPostUpdate

@PreviewFeature
inline fun Update.editChannelPostUpdateOrThrow(): EditChannelPostUpdate = this as EditChannelPostUpdate

@PreviewFeature
inline fun <T> Update.ifEditMessageUpdate(block: (EditMessageUpdate) -> T) = editMessageUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.editMessageUpdateOrNull(): EditMessageUpdate? = this as? EditMessageUpdate

@PreviewFeature
inline fun Update.editMessageUpdateOrThrow(): EditMessageUpdate = this as EditMessageUpdate

@PreviewFeature
inline fun <T> Update.ifInlineQueryUpdate(block: (InlineQueryUpdate) -> T) = inlineQueryUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.inlineQueryUpdateOrNull(): InlineQueryUpdate? = this as? InlineQueryUpdate

@PreviewFeature
inline fun Update.inlineQueryUpdateOrThrow(): InlineQueryUpdate = this as InlineQueryUpdate

@PreviewFeature
inline fun <T> Update.ifChannelPostMediaGroupUpdate(block: (ChannelPostMediaGroupUpdate) -> T) =
    channelPostMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.channelPostMediaGroupUpdateOrNull(): ChannelPostMediaGroupUpdate? =
    this as? ChannelPostMediaGroupUpdate

@PreviewFeature
inline fun Update.channelPostMediaGroupUpdateOrThrow(): ChannelPostMediaGroupUpdate =
    this as ChannelPostMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifEditChannelPostMediaGroupUpdate(block: (EditChannelPostMediaGroupUpdate) -> T) =
    editChannelPostMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.editChannelPostMediaGroupUpdateOrNull(): EditChannelPostMediaGroupUpdate? =
    this as? EditChannelPostMediaGroupUpdate

@PreviewFeature
inline fun Update.editChannelPostMediaGroupUpdateOrThrow(): EditChannelPostMediaGroupUpdate =
    this as EditChannelPostMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifEditMediaGroupUpdate(block: (EditMediaGroupUpdate) -> T) =
    editMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.editMediaGroupUpdateOrNull(): EditMediaGroupUpdate? = this as? EditMediaGroupUpdate

@PreviewFeature
inline fun Update.editMediaGroupUpdateOrThrow(): EditMediaGroupUpdate = this as EditMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifEditMessageMediaGroupUpdate(block: (EditMessageMediaGroupUpdate) -> T) =
    editMessageMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.editMessageMediaGroupUpdateOrNull(): EditMessageMediaGroupUpdate? =
    this as? EditMessageMediaGroupUpdate

@PreviewFeature
inline fun Update.editMessageMediaGroupUpdateOrThrow(): EditMessageMediaGroupUpdate =
    this as EditMessageMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifMediaGroupUpdate(block: (MediaGroupUpdate) -> T) = mediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.mediaGroupUpdateOrNull(): MediaGroupUpdate? = this as? MediaGroupUpdate

@PreviewFeature
inline fun Update.mediaGroupUpdateOrThrow(): MediaGroupUpdate = this as MediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifMessageMediaGroupUpdate(block: (MessageMediaGroupUpdate) -> T) =
    messageMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.messageMediaGroupUpdateOrNull(): MessageMediaGroupUpdate? = this as? MessageMediaGroupUpdate

@PreviewFeature
inline fun Update.messageMediaGroupUpdateOrThrow(): MessageMediaGroupUpdate = this as MessageMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifSentMediaGroupUpdate(block: (SentMediaGroupUpdate) -> T) =
    sentMediaGroupUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.sentMediaGroupUpdateOrNull(): SentMediaGroupUpdate? = this as? SentMediaGroupUpdate

@PreviewFeature
inline fun Update.sentMediaGroupUpdateOrThrow(): SentMediaGroupUpdate = this as SentMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.ifMessageUpdate(block: (MessageUpdate) -> T) = messageUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.messageUpdateOrNull(): MessageUpdate? = this as? MessageUpdate

@PreviewFeature
inline fun Update.messageUpdateOrThrow(): MessageUpdate = this as MessageUpdate

@PreviewFeature
inline fun <T> Update.ifPollAnswerUpdate(block: (PollAnswerUpdate) -> T) = pollAnswerUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.pollAnswerUpdateOrNull(): PollAnswerUpdate? = this as? PollAnswerUpdate

@PreviewFeature
inline fun Update.pollAnswerUpdateOrThrow(): PollAnswerUpdate = this as PollAnswerUpdate

@PreviewFeature
inline fun <T> Update.ifPollUpdate(block: (PollUpdate) -> T) = pollUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.pollUpdateOrNull(): PollUpdate? = this as? PollUpdate

@PreviewFeature
inline fun Update.pollUpdateOrThrow(): PollUpdate = this as PollUpdate

@PreviewFeature
inline fun <T> Update.ifPreCheckoutQueryUpdate(block: (PreCheckoutQueryUpdate) -> T) =
    preCheckoutQueryUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.preCheckoutQueryUpdateOrNull(): PreCheckoutQueryUpdate? = this as? PreCheckoutQueryUpdate

@PreviewFeature
inline fun Update.preCheckoutQueryUpdateOrThrow(): PreCheckoutQueryUpdate = this as PreCheckoutQueryUpdate

@PreviewFeature
inline fun <T> Update.ifShippingQueryUpdate(block: (ShippingQueryUpdate) -> T) = shippingQueryUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.shippingQueryUpdateOrNull(): ShippingQueryUpdate? = this as? ShippingQueryUpdate

@PreviewFeature
inline fun Update.shippingQueryUpdateOrThrow(): ShippingQueryUpdate = this as ShippingQueryUpdate

@PreviewFeature
inline fun <T> Update.ifBaseEditMessageUpdate(block: (BaseEditMessageUpdate) -> T) =
    baseEditMessageUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.baseEditMessageUpdateOrNull(): BaseEditMessageUpdate? = this as? BaseEditMessageUpdate

@PreviewFeature
inline fun Update.baseEditMessageUpdateOrThrow(): BaseEditMessageUpdate = this as BaseEditMessageUpdate

@PreviewFeature
inline fun <T> Update.ifBaseMessageUpdate(block: (BaseMessageUpdate) -> T) = baseMessageUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.baseMessageUpdateOrNull(): BaseMessageUpdate? = this as? BaseMessageUpdate

@PreviewFeature
inline fun Update.baseMessageUpdateOrThrow(): BaseMessageUpdate = this as BaseMessageUpdate

@PreviewFeature
inline fun <T> Update.ifBaseSentMessageUpdate(block: (BaseSentMessageUpdate) -> T) =
    baseSentMessageUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.baseSentMessageUpdateOrNull(): BaseSentMessageUpdate? = this as? BaseSentMessageUpdate

@PreviewFeature
inline fun Update.baseSentMessageUpdateOrThrow(): BaseSentMessageUpdate = this as BaseSentMessageUpdate

@PreviewFeature
inline fun <T> Update.ifUnknownUpdate(block: (UnknownUpdate) -> T) = unknownUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.unknownUpdateOrNull(): UnknownUpdate? = this as? UnknownUpdate

@PreviewFeature
inline fun Update.unknownUpdateOrThrow(): UnknownUpdate = this as UnknownUpdate

@PreviewFeature
inline fun <T> Update.ifCommonChatMemberUpdatedUpdate(block: (CommonChatMemberUpdatedUpdate) -> T) =
    commonChatMemberUpdatedUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.commonChatMemberUpdatedUpdateOrNull(): CommonChatMemberUpdatedUpdate? =
    this as? CommonChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.commonChatMemberUpdatedUpdateOrThrow(): CommonChatMemberUpdatedUpdate =
    this as CommonChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.ifMyChatMemberUpdatedUpdate(block: (MyChatMemberUpdatedUpdate) -> T) =
    myChatMemberUpdatedUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.myChatMemberUpdatedUpdateOrNull(): MyChatMemberUpdatedUpdate? = this as? MyChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.myChatMemberUpdatedUpdateOrThrow(): MyChatMemberUpdatedUpdate = this as MyChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.ifChatMemberUpdatedUpdate(block: (ChatMemberUpdatedUpdate) -> T) =
    chatMemberUpdatedUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.chatMemberUpdatedUpdateOrNull(): ChatMemberUpdatedUpdate? = this as? ChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.chatMemberUpdatedUpdateOrThrow(): ChatMemberUpdatedUpdate = this as ChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.ifChatJoinRequestUpdate(block: (ChatJoinRequestUpdate) -> T) =
    chatJoinRequestUpdateOrNull()?.let(block)

@PreviewFeature
inline fun Update.chatJoinRequestUpdateOrNull(): ChatJoinRequestUpdate? = this as? ChatJoinRequestUpdate

@PreviewFeature
inline fun Update.chatJoinRequestUpdateOrThrow(): ChatJoinRequestUpdate = this as ChatJoinRequestUpdate

@PreviewFeature
inline fun <T> TelegramMediaFile.ifAnimationFile(block: (AnimationFile) -> T) = animationFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.animationFileOrNull(): AnimationFile? = this as? AnimationFile

@PreviewFeature
inline fun TelegramMediaFile.animationFileOrThrow(): AnimationFile = this as AnimationFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifAudioFile(block: (AudioFile) -> T) = audioFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.audioFileOrNull(): AudioFile? = this as? AudioFile

@PreviewFeature
inline fun TelegramMediaFile.audioFileOrThrow(): AudioFile = this as AudioFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifDocumentFile(block: (DocumentFile) -> T) = documentFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.documentFileOrNull(): DocumentFile? = this as? DocumentFile

@PreviewFeature
inline fun TelegramMediaFile.documentFileOrThrow(): DocumentFile = this as DocumentFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifFile(block: (File) -> T) = fileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.fileOrNull(): File? = this as? File

@PreviewFeature
inline fun TelegramMediaFile.fileOrThrow(): File = this as File

@PreviewFeature
inline fun <T> TelegramMediaFile.ifPathedFile(block: (PathedFile) -> T) = pathedFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.pathedFileOrNull(): PathedFile? = this as? PathedFile

@PreviewFeature
inline fun TelegramMediaFile.pathedFileOrThrow(): PathedFile = this as PathedFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifPhotoSize(block: (PhotoSize) -> T) = photoSizeOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.photoSizeOrNull(): PhotoSize? = this as? PhotoSize

@PreviewFeature
inline fun TelegramMediaFile.photoSizeOrThrow(): PhotoSize = this as PhotoSize

@PreviewFeature
inline fun <T> TelegramMediaFile.ifSticker(block: (Sticker) -> T) = stickerOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.stickerOrNull(): Sticker? = this as? Sticker

@PreviewFeature
inline fun TelegramMediaFile.stickerOrThrow(): Sticker = this as Sticker

@PreviewFeature
inline fun <T> TelegramMediaFile.ifSimpleSticker(block: (SimpleSticker) -> T) = simpleStickerOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.simpleStickerOrNull(): SimpleSticker? = this as? SimpleSticker

@PreviewFeature
inline fun TelegramMediaFile.simpleStickerOrThrow(): SimpleSticker = this as SimpleSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.ifAnimatedSticker(block: (AnimatedSticker) -> T) = animatedStickerOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.animatedStickerOrNull(): AnimatedSticker? = this as? AnimatedSticker

@PreviewFeature
inline fun TelegramMediaFile.animatedStickerOrThrow(): AnimatedSticker = this as AnimatedSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.ifVideoSticker(block: (VideoSticker) -> T) = videoStickerOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.videoStickerOrNull(): VideoSticker? = this as? VideoSticker

@PreviewFeature
inline fun TelegramMediaFile.videoStickerOrThrow(): VideoSticker = this as VideoSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.ifVideoFile(block: (VideoFile) -> T) = videoFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.videoFileOrNull(): VideoFile? = this as? VideoFile

@PreviewFeature
inline fun TelegramMediaFile.videoFileOrThrow(): VideoFile = this as VideoFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifVideoNoteFile(block: (VideoNoteFile) -> T) = videoNoteFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.videoNoteFileOrNull(): VideoNoteFile? = this as? VideoNoteFile

@PreviewFeature
inline fun TelegramMediaFile.videoNoteFileOrThrow(): VideoNoteFile = this as VideoNoteFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifVoiceFile(block: (VoiceFile) -> T) = voiceFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.voiceFileOrNull(): VoiceFile? = this as? VoiceFile

@PreviewFeature
inline fun TelegramMediaFile.voiceFileOrThrow(): VoiceFile = this as VoiceFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifMimedMediaFile(block: (MimedMediaFile) -> T) = mimedMediaFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.mimedMediaFileOrNull(): MimedMediaFile? = this as? MimedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.mimedMediaFileOrThrow(): MimedMediaFile = this as MimedMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifPlayableMediaFile(block: (PlayableMediaFile) -> T) =
    playableMediaFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.playableMediaFileOrNull(): PlayableMediaFile? = this as? PlayableMediaFile

@PreviewFeature
inline fun TelegramMediaFile.playableMediaFileOrThrow(): PlayableMediaFile = this as PlayableMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifSizedMediaFile(block: (SizedMediaFile) -> T) = sizedMediaFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.sizedMediaFileOrNull(): SizedMediaFile? = this as? SizedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.sizedMediaFileOrThrow(): SizedMediaFile = this as SizedMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.ifThumbedMediaFile(block: (ThumbedMediaFile) -> T) =
    thumbedMediaFileOrNull()?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.thumbedMediaFileOrNull(): ThumbedMediaFile? = this as? ThumbedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.thumbedMediaFileOrThrow(): ThumbedMediaFile = this as ThumbedMediaFile

@PreviewFeature
inline fun <T> KeyboardMarkup.ifForceReply(block: (ReplyForce) -> T) = forceReplyOrNull()?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.forceReplyOrNull(): ReplyForce? = this as? ReplyForce

@PreviewFeature
inline fun KeyboardMarkup.forceReplyOrThrow(): ReplyForce = this as ReplyForce

@PreviewFeature
inline fun <T> KeyboardMarkup.ifInlineKeyboardMarkup(block: (InlineKeyboardMarkup) -> T) =
    inlineKeyboardMarkupOrNull()?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.inlineKeyboardMarkupOrNull(): InlineKeyboardMarkup? = this as? InlineKeyboardMarkup

@PreviewFeature
inline fun KeyboardMarkup.inlineKeyboardMarkupOrThrow(): InlineKeyboardMarkup = this as InlineKeyboardMarkup

@PreviewFeature
inline fun <T> KeyboardMarkup.ifReplyKeyboardMarkup(block: (ReplyKeyboardMarkup) -> T) =
    replyKeyboardMarkupOrNull()?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.replyKeyboardMarkupOrNull(): ReplyKeyboardMarkup? = this as? ReplyKeyboardMarkup

@PreviewFeature
inline fun KeyboardMarkup.replyKeyboardMarkupOrThrow(): ReplyKeyboardMarkup = this as ReplyKeyboardMarkup

@PreviewFeature
inline fun <T> KeyboardMarkup.ifReplyKeyboardRemove(block: (ReplyKeyboardRemove) -> T) =
    replyKeyboardRemoveOrNull()?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.replyKeyboardRemoveOrNull(): ReplyKeyboardRemove? = this as? ReplyKeyboardRemove

@PreviewFeature
inline fun KeyboardMarkup.replyKeyboardRemoveOrThrow(): ReplyKeyboardRemove = this as ReplyKeyboardRemove

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifCallbackDataInlineKeyboardButton(block: (CallbackDataInlineKeyboardButton) -> T) =
    callbackDataInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.callbackDataInlineKeyboardButtonOrNull(): CallbackDataInlineKeyboardButton? =
    this as? CallbackDataInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.callbackDataInlineKeyboardButtonOrThrow(): CallbackDataInlineKeyboardButton =
    this as CallbackDataInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifCallbackGameInlineKeyboardButton(block: (CallbackGameInlineKeyboardButton) -> T) =
    callbackGameInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.callbackGameInlineKeyboardButtonOrNull(): CallbackGameInlineKeyboardButton? =
    this as? CallbackGameInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.callbackGameInlineKeyboardButtonOrThrow(): CallbackGameInlineKeyboardButton =
    this as CallbackGameInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifLoginURLInlineKeyboardButton(block: (LoginURLInlineKeyboardButton) -> T) =
    loginURLInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.loginURLInlineKeyboardButtonOrNull(): LoginURLInlineKeyboardButton? =
    this as? LoginURLInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.loginURLInlineKeyboardButtonOrThrow(): LoginURLInlineKeyboardButton =
    this as LoginURLInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifPayInlineKeyboardButton(block: (PayInlineKeyboardButton) -> T) =
    payInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.payInlineKeyboardButtonOrNull(): PayInlineKeyboardButton? =
    this as? PayInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.payInlineKeyboardButtonOrThrow(): PayInlineKeyboardButton =
    this as PayInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifSwitchInlineQueryCurrentChatInlineKeyboardButton(block: (SwitchInlineQueryCurrentChatInlineKeyboardButton) -> T) =
    switchInlineQueryCurrentChatInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrNull(): SwitchInlineQueryCurrentChatInlineKeyboardButton? =
    this as? SwitchInlineQueryCurrentChatInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.switchInlineQueryCurrentChatInlineKeyboardButtonOrThrow(): SwitchInlineQueryCurrentChatInlineKeyboardButton =
    this as SwitchInlineQueryCurrentChatInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifSwitchInlineQueryInlineKeyboardButton(block: (SwitchInlineQueryInlineKeyboardButton) -> T) =
    switchInlineQueryInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.switchInlineQueryInlineKeyboardButtonOrNull(): SwitchInlineQueryInlineKeyboardButton? =
    this as? SwitchInlineQueryInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.switchInlineQueryInlineKeyboardButtonOrThrow(): SwitchInlineQueryInlineKeyboardButton =
    this as SwitchInlineQueryInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifURLInlineKeyboardButton(block: (URLInlineKeyboardButton) -> T) =
    uRLInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.uRLInlineKeyboardButtonOrNull(): URLInlineKeyboardButton? =
    this as? URLInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.uRLInlineKeyboardButtonOrThrow(): URLInlineKeyboardButton =
    this as URLInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifWebAppKeyboardButton(block: (WebAppKeyboardButton) -> T) =
    webAppKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.webAppKeyboardButtonOrNull(): WebAppKeyboardButton? = this as? WebAppKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.webAppKeyboardButtonOrThrow(): WebAppKeyboardButton =
    this as WebAppKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.ifUnknownInlineKeyboardButton(block: (UnknownInlineKeyboardButton) -> T) =
    unknownInlineKeyboardButtonOrNull()?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrNull(): UnknownInlineKeyboardButton? =
    this as? UnknownInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.unknownInlineKeyboardButtonOrThrow(): UnknownInlineKeyboardButton =
    this as UnknownInlineKeyboardButton

@PreviewFeature
inline fun <T> Poll.ifMultipleAnswersPoll(block: (MultipleAnswersPoll) -> T) = multipleAnswersPollOrNull()?.let(block)

@PreviewFeature
inline fun Poll.multipleAnswersPollOrNull(): MultipleAnswersPoll? = this as? MultipleAnswersPoll

@PreviewFeature
inline fun Poll.multipleAnswersPollOrThrow(): MultipleAnswersPoll = this as MultipleAnswersPoll

@PreviewFeature
inline fun <T> Poll.ifQuizPoll(block: (QuizPoll) -> T) = quizPollOrNull()?.let(block)

@PreviewFeature
inline fun Poll.quizPollOrNull(): QuizPoll? = this as? QuizPoll

@PreviewFeature
inline fun Poll.quizPollOrThrow(): QuizPoll = this as QuizPoll

@PreviewFeature
inline fun <T> Poll.ifRegularPoll(block: (RegularPoll) -> T) = regularPollOrNull()?.let(block)

@PreviewFeature
inline fun Poll.regularPollOrNull(): RegularPoll? = this as? RegularPoll

@PreviewFeature
inline fun Poll.regularPollOrThrow(): RegularPoll = this as RegularPoll

@PreviewFeature
inline fun <T> Poll.ifUnknownPollType(block: (UnknownPollType) -> T) = unknownPollTypeOrNull()?.let(block)

@PreviewFeature
inline fun Poll.unknownPollTypeOrNull(): UnknownPollType? = this as? UnknownPollType

@PreviewFeature
inline fun Poll.unknownPollTypeOrThrow(): UnknownPollType = this as UnknownPollType

@PreviewFeature
inline fun <T> ResendableContent.ifContactContent(block: (ContactContent) -> T) = contactContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.contactContentOrNull(): ContactContent? = this as? ContactContent

@PreviewFeature
inline fun ResendableContent.contactContentOrThrow(): ContactContent = this as ContactContent

@PreviewFeature
inline fun <T> ResendableContent.ifDiceContent(block: (DiceContent) -> T) = diceContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.diceContentOrNull(): DiceContent? = this as? DiceContent

@PreviewFeature
inline fun ResendableContent.diceContentOrThrow(): DiceContent = this as DiceContent

@PreviewFeature
inline fun <T> ResendableContent.ifGameContent(block: (GameContent) -> T) = gameContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.gameContentOrNull(): GameContent? = this as? GameContent

@PreviewFeature
inline fun ResendableContent.gameContentOrThrow(): GameContent = this as GameContent

@PreviewFeature
inline fun <T> ResendableContent.ifLocationContent(block: (LocationContent) -> T) = locationContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.locationContentOrNull(): LocationContent? = this as? LocationContent

@PreviewFeature
inline fun ResendableContent.locationContentOrThrow(): LocationContent = this as LocationContent

@PreviewFeature
inline fun <T> ResendableContent.ifLiveLocationContent(block: (LiveLocationContent) -> T) =
    liveLocationContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.liveLocationContentOrNull(): LiveLocationContent? = this as? LiveLocationContent

@PreviewFeature
inline fun ResendableContent.liveLocationContentOrThrow(): LiveLocationContent = this as LiveLocationContent

@PreviewFeature
inline fun <T> ResendableContent.ifStaticLocationContent(block: (StaticLocationContent) -> T) =
    staticLocationContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.staticLocationContentOrNull(): StaticLocationContent? = this as? StaticLocationContent

@PreviewFeature
inline fun ResendableContent.staticLocationContentOrThrow(): StaticLocationContent = this as StaticLocationContent

@PreviewFeature
inline fun <T> ResendableContent.ifPollContent(block: (PollContent) -> T) = pollContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.pollContentOrNull(): PollContent? = this as? PollContent

@PreviewFeature
inline fun ResendableContent.pollContentOrThrow(): PollContent = this as PollContent

@PreviewFeature
inline fun <T> ResendableContent.ifTextContent(block: (TextContent) -> T) = textContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.textContentOrNull(): TextContent? = this as? TextContent

@PreviewFeature
inline fun ResendableContent.textContentOrThrow(): TextContent = this as TextContent

@PreviewFeature
inline fun <T> ResendableContent.ifVenueContent(block: (VenueContent) -> T) = venueContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.venueContentOrNull(): VenueContent? = this as? VenueContent

@PreviewFeature
inline fun ResendableContent.venueContentOrThrow(): VenueContent = this as VenueContent

@PreviewFeature
inline fun <T> ResendableContent.ifAudioMediaGroupContent(block: (AudioMediaGroupContent) -> T) =
    audioMediaGroupContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.audioMediaGroupContentOrNull(): AudioMediaGroupContent? = this as? AudioMediaGroupContent

@PreviewFeature
inline fun ResendableContent.audioMediaGroupContentOrThrow(): AudioMediaGroupContent = this as AudioMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.ifDocumentMediaGroupContent(block: (DocumentMediaGroupContent) -> T) =
    documentMediaGroupContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.documentMediaGroupContentOrNull(): DocumentMediaGroupContent? =
    this as? DocumentMediaGroupContent

@PreviewFeature
inline fun ResendableContent.documentMediaGroupContentOrThrow(): DocumentMediaGroupContent =
    this as DocumentMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.ifMediaCollectionContent(block: (MediaCollectionContent<TelegramMediaFile>) -> T) =
    mediaCollectionContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.mediaCollectionContentOrNull(): MediaCollectionContent<TelegramMediaFile>? =
    this as? MediaCollectionContent<TelegramMediaFile>

@PreviewFeature
inline fun ResendableContent.mediaCollectionContentOrThrow(): MediaCollectionContent<TelegramMediaFile> =
    this as MediaCollectionContent<TelegramMediaFile>

@PreviewFeature
inline fun <T> ResendableContent.ifTextedMediaContent(block: (TextedMediaContent) -> T) =
    textedMediaContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.textedMediaContentOrNull(): TextedMediaContent? =
    this as? TextedMediaContent

@PreviewFeature
inline fun ResendableContent.textedMediaContentOrThrow(): TextedMediaContent =
    this as TextedMediaContent

@PreviewFeature
inline fun <T> ResendableContent.ifMediaContent(block: (MediaContent) -> T) = mediaContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.mediaContentOrNull(): MediaContent? = this as? MediaContent

@PreviewFeature
inline fun ResendableContent.mediaContentOrThrow(): MediaContent = this as MediaContent

@PreviewFeature
inline fun <T> ResendableContent.ifMediaGroupContent(block: (MediaGroupContent) -> T) =
    mediaGroupContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.mediaGroupContentOrNull(): MediaGroupContent? = this as? MediaGroupContent

@PreviewFeature
inline fun ResendableContent.mediaGroupContentOrThrow(): MediaGroupContent = this as MediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.ifMessageContent(block: (MessageContent) -> T) = messageContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.messageContentOrNull(): MessageContent? = this as? MessageContent

@PreviewFeature
inline fun ResendableContent.messageContentOrThrow(): MessageContent = this as MessageContent

@PreviewFeature
inline fun <T> ResendableContent.ifVisualMediaGroupContent(block: (VisualMediaGroupContent) -> T) =
    visualMediaGroupContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.visualMediaGroupContentOrNull(): VisualMediaGroupContent? =
    this as? VisualMediaGroupContent

@PreviewFeature
inline fun ResendableContent.visualMediaGroupContentOrThrow(): VisualMediaGroupContent = this as VisualMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.ifAnimationContent(block: (AnimationContent) -> T) =
    animationContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.animationContentOrNull(): AnimationContent? = this as? AnimationContent

@PreviewFeature
inline fun ResendableContent.animationContentOrThrow(): AnimationContent = this as AnimationContent

@PreviewFeature
inline fun <T> ResendableContent.ifAudioContent(block: (AudioContent) -> T) = audioContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.audioContentOrNull(): AudioContent? = this as? AudioContent

@PreviewFeature
inline fun ResendableContent.audioContentOrThrow(): AudioContent = this as AudioContent

@PreviewFeature
inline fun <T> ResendableContent.ifDocumentContent(block: (DocumentContent) -> T) = documentContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.documentContentOrNull(): DocumentContent? = this as? DocumentContent

@PreviewFeature
inline fun ResendableContent.documentContentOrThrow(): DocumentContent = this as DocumentContent

@PreviewFeature
inline fun <T> ResendableContent.ifPhotoContent(block: (PhotoContent) -> T) = photoContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.photoContentOrNull(): PhotoContent? = this as? PhotoContent

@PreviewFeature
inline fun ResendableContent.photoContentOrThrow(): PhotoContent = this as PhotoContent

@PreviewFeature
inline fun <T> ResendableContent.ifStickerContent(block: (StickerContent) -> T) = stickerContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.stickerContentOrNull(): StickerContent? = this as? StickerContent

@PreviewFeature
inline fun ResendableContent.stickerContentOrThrow(): StickerContent = this as StickerContent

@PreviewFeature
inline fun <T> ResendableContent.ifVideoContent(block: (VideoContent) -> T) = videoContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.videoContentOrNull(): VideoContent? = this as? VideoContent

@PreviewFeature
inline fun ResendableContent.videoContentOrThrow(): VideoContent = this as VideoContent

@PreviewFeature
inline fun <T> ResendableContent.ifVideoNoteContent(block: (VideoNoteContent) -> T) =
    videoNoteContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.videoNoteContentOrNull(): VideoNoteContent? = this as? VideoNoteContent

@PreviewFeature
inline fun ResendableContent.videoNoteContentOrThrow(): VideoNoteContent = this as VideoNoteContent

@PreviewFeature
inline fun <T> ResendableContent.ifVoiceContent(block: (VoiceContent) -> T) = voiceContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.voiceContentOrNull(): VoiceContent? = this as? VoiceContent

@PreviewFeature
inline fun ResendableContent.voiceContentOrThrow(): VoiceContent = this as VoiceContent

@PreviewFeature
inline fun <T> ResendableContent.ifInvoiceContent(block: (InvoiceContent) -> T) = invoiceContentOrNull()?.let(block)

@PreviewFeature
inline fun ResendableContent.invoiceContentOrNull(): InvoiceContent? = this as? InvoiceContent

@PreviewFeature
inline fun ResendableContent.invoiceContentOrThrow(): InvoiceContent = this as InvoiceContent

@PreviewFeature
inline fun <T> TextSource.ifMultilevelTextSource(block: (MultilevelTextSource) -> T) =
    multilevelTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.multilevelTextSourceOrNull(): MultilevelTextSource? = this as? MultilevelTextSource

@PreviewFeature
inline fun TextSource.multilevelTextSourceOrThrow(): MultilevelTextSource = this as MultilevelTextSource

@PreviewFeature
inline fun <T> TextSource.ifBoldTextSource(block: (BoldTextSource) -> T) = boldTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.boldTextSourceOrNull(): BoldTextSource? = this as? BoldTextSource

@PreviewFeature
inline fun TextSource.boldTextSourceOrThrow(): BoldTextSource = this as BoldTextSource

@PreviewFeature
inline fun <T> TextSource.ifBotCommandTextSource(block: (BotCommandTextSource) -> T) =
    botCommandTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.botCommandTextSourceOrNull(): BotCommandTextSource? = this as? BotCommandTextSource

@PreviewFeature
inline fun TextSource.botCommandTextSourceOrThrow(): BotCommandTextSource = this as BotCommandTextSource

@PreviewFeature
inline fun <T> TextSource.ifCashTagTextSource(block: (CashTagTextSource) -> T) = cashTagTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.cashTagTextSourceOrNull(): CashTagTextSource? = this as? CashTagTextSource

@PreviewFeature
inline fun TextSource.cashTagTextSourceOrThrow(): CashTagTextSource = this as CashTagTextSource

@PreviewFeature
inline fun <T> TextSource.ifCodeTextSource(block: (CodeTextSource) -> T) = codeTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.codeTextSourceOrNull(): CodeTextSource? = this as? CodeTextSource

@PreviewFeature
inline fun TextSource.codeTextSourceOrThrow(): CodeTextSource = this as CodeTextSource

@PreviewFeature
inline fun <T> TextSource.ifEMailTextSource(block: (EMailTextSource) -> T) = eMailTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.eMailTextSourceOrNull(): EMailTextSource? = this as? EMailTextSource

@PreviewFeature
inline fun TextSource.eMailTextSourceOrThrow(): EMailTextSource = this as EMailTextSource

@PreviewFeature
inline fun <T> TextSource.ifHashTagTextSource(block: (HashTagTextSource) -> T) = hashTagTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.hashTagTextSourceOrNull(): HashTagTextSource? = this as? HashTagTextSource

@PreviewFeature
inline fun TextSource.hashTagTextSourceOrThrow(): HashTagTextSource = this as HashTagTextSource

@PreviewFeature
inline fun <T> TextSource.ifItalicTextSource(block: (ItalicTextSource) -> T) = italicTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.italicTextSourceOrNull(): ItalicTextSource? = this as? ItalicTextSource

@PreviewFeature
inline fun TextSource.italicTextSourceOrThrow(): ItalicTextSource = this as ItalicTextSource

@PreviewFeature
inline fun <T> TextSource.ifMentionTextSource(block: (MentionTextSource) -> T) = mentionTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.mentionTextSourceOrNull(): MentionTextSource? = this as? MentionTextSource

@PreviewFeature
inline fun TextSource.mentionTextSourceOrThrow(): MentionTextSource = this as MentionTextSource

@PreviewFeature
inline fun <T> TextSource.ifPhoneNumberTextSource(block: (PhoneNumberTextSource) -> T) =
    phoneNumberTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.phoneNumberTextSourceOrNull(): PhoneNumberTextSource? = this as? PhoneNumberTextSource

@PreviewFeature
inline fun TextSource.phoneNumberTextSourceOrThrow(): PhoneNumberTextSource = this as PhoneNumberTextSource

@PreviewFeature
inline fun <T> TextSource.ifPreTextSource(block: (PreTextSource) -> T) = preTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.preTextSourceOrNull(): PreTextSource? = this as? PreTextSource

@PreviewFeature
inline fun TextSource.preTextSourceOrThrow(): PreTextSource = this as PreTextSource

@PreviewFeature
inline fun <T> TextSource.ifRegularTextSource(block: (RegularTextSource) -> T) = regularTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.regularTextSourceOrNull(): RegularTextSource? = this as? RegularTextSource

@PreviewFeature
inline fun TextSource.regularTextSourceOrThrow(): RegularTextSource = this as RegularTextSource

@PreviewFeature
inline fun <T> TextSource.ifStrikethroughTextSource(block: (StrikethroughTextSource) -> T) =
    strikethroughTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.strikethroughTextSourceOrNull(): StrikethroughTextSource? = this as? StrikethroughTextSource

@PreviewFeature
inline fun TextSource.strikethroughTextSourceOrThrow(): StrikethroughTextSource = this as StrikethroughTextSource

@PreviewFeature
inline fun <T> TextSource.ifTextLinkTextSource(block: (TextLinkTextSource) -> T) =
    textLinkTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.textLinkTextSourceOrNull(): TextLinkTextSource? = this as? TextLinkTextSource

@PreviewFeature
inline fun TextSource.textLinkTextSourceOrThrow(): TextLinkTextSource = this as TextLinkTextSource

@PreviewFeature
inline fun <T> TextSource.ifTextMentionTextSource(block: (TextMentionTextSource) -> T) =
    textMentionTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.textMentionTextSourceOrNull(): TextMentionTextSource? = this as? TextMentionTextSource

@PreviewFeature
inline fun TextSource.textMentionTextSourceOrThrow(): TextMentionTextSource = this as TextMentionTextSource

@PreviewFeature
inline fun <T> TextSource.ifURLTextSource(block: (URLTextSource) -> T) = uRLTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.uRLTextSourceOrNull(): URLTextSource? = this as? URLTextSource

@PreviewFeature
inline fun TextSource.uRLTextSourceOrThrow(): URLTextSource = this as URLTextSource

@PreviewFeature
inline fun <T> TextSource.ifUnderlineTextSource(block: (UnderlineTextSource) -> T) =
    underlineTextSourceOrNull()?.let(block)

@PreviewFeature
inline fun TextSource.underlineTextSourceOrNull(): UnderlineTextSource? = this as? UnderlineTextSource

@PreviewFeature
inline fun TextSource.underlineTextSourceOrThrow(): UnderlineTextSource = this as UnderlineTextSource

@PreviewFeature
inline fun <T> DiceAnimationType.ifBasketballDiceAnimationType(block: (BasketballDiceAnimationType) -> T) =
    basketballDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.basketballDiceAnimationTypeOrNull(): BasketballDiceAnimationType? =
    this as? BasketballDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.basketballDiceAnimationTypeOrThrow(): BasketballDiceAnimationType =
    this as BasketballDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifBowlingDiceAnimationType(block: (BowlingDiceAnimationType) -> T) =
    bowlingDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.bowlingDiceAnimationTypeOrNull(): BowlingDiceAnimationType? =
    this as? BowlingDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.bowlingDiceAnimationTypeOrThrow(): BowlingDiceAnimationType =
    this as BowlingDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifCubeDiceAnimationType(block: (CubeDiceAnimationType) -> T) =
    cubeDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.cubeDiceAnimationTypeOrNull(): CubeDiceAnimationType? = this as? CubeDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.cubeDiceAnimationTypeOrThrow(): CubeDiceAnimationType = this as CubeDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifCustomDiceAnimationType(block: (CustomDiceAnimationType) -> T) =
    customDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.customDiceAnimationTypeOrNull(): CustomDiceAnimationType? =
    this as? CustomDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.customDiceAnimationTypeOrThrow(): CustomDiceAnimationType = this as CustomDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifDartsDiceAnimationType(block: (DartsDiceAnimationType) -> T) =
    dartsDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.dartsDiceAnimationTypeOrNull(): DartsDiceAnimationType? = this as? DartsDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.dartsDiceAnimationTypeOrThrow(): DartsDiceAnimationType = this as DartsDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifFootballDiceAnimationType(block: (FootballDiceAnimationType) -> T) =
    footballDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.footballDiceAnimationTypeOrNull(): FootballDiceAnimationType? =
    this as? FootballDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.footballDiceAnimationTypeOrThrow(): FootballDiceAnimationType =
    this as FootballDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.ifSlotMachineDiceAnimationType(block: (SlotMachineDiceAnimationType) -> T) =
    slotMachineDiceAnimationTypeOrNull()?.let(block)

@PreviewFeature
inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrNull(): SlotMachineDiceAnimationType? =
    this as? SlotMachineDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.slotMachineDiceAnimationTypeOrThrow(): SlotMachineDiceAnimationType =
    this as SlotMachineDiceAnimationType

@PreviewFeature
inline fun <T> ChatEvent.ifChannelChatCreated(block: (ChannelChatCreated) -> T) = channelChatCreatedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.channelChatCreatedOrNull(): ChannelChatCreated? = this as? ChannelChatCreated

@PreviewFeature
inline fun ChatEvent.channelChatCreatedOrThrow(): ChannelChatCreated = this as ChannelChatCreated

@PreviewFeature
inline fun <T> ChatEvent.ifDeleteChatPhoto(block: (DeleteChatPhoto) -> T) = deleteChatPhotoOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.deleteChatPhotoOrNull(): DeleteChatPhoto? = this as? DeleteChatPhoto

@PreviewFeature
inline fun ChatEvent.deleteChatPhotoOrThrow(): DeleteChatPhoto = this as DeleteChatPhoto

@PreviewFeature
inline fun <T> ChatEvent.ifGroupChatCreated(block: (GroupChatCreated) -> T) = groupChatCreatedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.groupChatCreatedOrNull(): GroupChatCreated? = this as? GroupChatCreated

@PreviewFeature
inline fun ChatEvent.groupChatCreatedOrThrow(): GroupChatCreated = this as GroupChatCreated

@PreviewFeature
inline fun <T> ChatEvent.ifLeftChatMember(block: (LeftChatMember) -> T) = leftChatMemberOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.leftChatMemberOrNull(): LeftChatMember? = this as? LeftChatMember

@PreviewFeature
inline fun ChatEvent.leftChatMemberOrThrow(): LeftChatMember = this as LeftChatMember

@PreviewFeature
inline fun <T> ChatEvent.ifMessageAutoDeleteTimerChanged(block: (MessageAutoDeleteTimerChanged) -> T) =
    messageAutoDeleteTimerChangedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.messageAutoDeleteTimerChangedOrNull(): MessageAutoDeleteTimerChanged? =
    this as? MessageAutoDeleteTimerChanged

@PreviewFeature
inline fun ChatEvent.messageAutoDeleteTimerChangedOrThrow(): MessageAutoDeleteTimerChanged =
    this as MessageAutoDeleteTimerChanged

@PreviewFeature
inline fun <T> ChatEvent.ifNewChatMembers(block: (NewChatMembers) -> T) = newChatMembersOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.newChatMembersOrNull(): NewChatMembers? = this as? NewChatMembers

@PreviewFeature
inline fun ChatEvent.newChatMembersOrThrow(): NewChatMembers = this as NewChatMembers

@PreviewFeature
inline fun <T> ChatEvent.ifNewChatPhoto(block: (NewChatPhoto) -> T) = newChatPhotoOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.newChatPhotoOrNull(): NewChatPhoto? = this as? NewChatPhoto

@PreviewFeature
inline fun ChatEvent.newChatPhotoOrThrow(): NewChatPhoto = this as NewChatPhoto

@PreviewFeature
inline fun <T> ChatEvent.ifNewChatTitle(block: (NewChatTitle) -> T) = newChatTitleOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.newChatTitleOrNull(): NewChatTitle? = this as? NewChatTitle

@PreviewFeature
inline fun ChatEvent.newChatTitleOrThrow(): NewChatTitle = this as NewChatTitle

@PreviewFeature
inline fun <T> ChatEvent.ifPinnedMessage(block: (PinnedMessage) -> T) = pinnedMessageOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.pinnedMessageOrNull(): PinnedMessage? = this as? PinnedMessage

@PreviewFeature
inline fun ChatEvent.pinnedMessageOrThrow(): PinnedMessage = this as PinnedMessage

@PreviewFeature
inline fun <T> ChatEvent.ifSuccessfulPaymentEvent(block: (SuccessfulPaymentEvent) -> T) =
    successfulPaymentEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.successfulPaymentEventOrNull(): SuccessfulPaymentEvent? = this as? SuccessfulPaymentEvent

@PreviewFeature
inline fun ChatEvent.successfulPaymentEventOrThrow(): SuccessfulPaymentEvent = this as SuccessfulPaymentEvent

@PreviewFeature
inline fun <T> ChatEvent.ifProximityAlertTriggered(block: (ProximityAlertTriggered) -> T) =
    proximityAlertTriggeredOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.proximityAlertTriggeredOrNull(): ProximityAlertTriggered? = this as? ProximityAlertTriggered

@PreviewFeature
inline fun ChatEvent.proximityAlertTriggeredOrThrow(): ProximityAlertTriggered = this as ProximityAlertTriggered

@PreviewFeature
inline fun <T> ChatEvent.ifSupergroupChatCreated(block: (SupergroupChatCreated) -> T) =
    supergroupChatCreatedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.supergroupChatCreatedOrNull(): SupergroupChatCreated? = this as? SupergroupChatCreated

@PreviewFeature
inline fun ChatEvent.supergroupChatCreatedOrThrow(): SupergroupChatCreated = this as SupergroupChatCreated

@PreviewFeature
inline fun <T> ChatEvent.ifMigratedToSupergroup(block: (MigratedToSupergroup) -> T) =
    migratedToSupergroupOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.migratedToSupergroupOrNull(): MigratedToSupergroup? = this as? MigratedToSupergroup

@PreviewFeature
inline fun ChatEvent.migratedToSupergroupOrThrow(): MigratedToSupergroup = this as MigratedToSupergroup

@PreviewFeature
inline fun <T> ChatEvent.ifChannelEvent(block: (ChannelEvent) -> T) = channelEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.channelEventOrNull(): ChannelEvent? = this as? ChannelEvent

@PreviewFeature
inline fun ChatEvent.channelEventOrThrow(): ChannelEvent = this as ChannelEvent

@PreviewFeature
inline fun <T> ChatEvent.ifPublicChatEvent(block: (PublicChatEvent) -> T) = publicChatEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.publicChatEventOrNull(): PublicChatEvent? = this as? PublicChatEvent

@PreviewFeature
inline fun ChatEvent.publicChatEventOrThrow(): PublicChatEvent = this as PublicChatEvent

@PreviewFeature
inline fun <T> ChatEvent.ifCommonEvent(block: (CommonEvent) -> T) = commonEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.commonEventOrNull(): CommonEvent? = this as? CommonEvent

@PreviewFeature
inline fun ChatEvent.commonEventOrThrow(): CommonEvent = this as CommonEvent

@PreviewFeature
inline fun <T> ChatEvent.ifGroupEvent(block: (GroupEvent) -> T) = groupEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.groupEventOrNull(): GroupEvent? = this as? GroupEvent

@PreviewFeature
inline fun ChatEvent.groupEventOrThrow(): GroupEvent = this as GroupEvent

@PreviewFeature
inline fun <T> ChatEvent.ifSupergroupEvent(block: (SupergroupEvent) -> T) = supergroupEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.supergroupEventOrNull(): SupergroupEvent? = this as? SupergroupEvent

@PreviewFeature
inline fun ChatEvent.supergroupEventOrThrow(): SupergroupEvent = this as SupergroupEvent

@PreviewFeature
inline fun <T> ChatEvent.ifVideoChatEvent(block: (VideoChatEvent) -> T) = videoChatEventOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.videoChatEventOrNull(): VideoChatEvent? = this as? VideoChatEvent

@PreviewFeature
inline fun ChatEvent.videoChatEventOrThrow(): VideoChatEvent = this as VideoChatEvent

@PreviewFeature
inline fun <T> ChatEvent.ifVideoChatEnded(block: (VideoChatEnded) -> T) = videoChatEndedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.videoChatEndedOrNull(): VideoChatEnded? = this as? VideoChatEnded

@PreviewFeature
inline fun ChatEvent.videoChatEndedOrThrow(): VideoChatEnded = this as VideoChatEnded

@PreviewFeature
inline fun <T> ChatEvent.ifVideoChatParticipantsInvited(block: (VideoChatParticipantsInvited) -> T) =
    videoChatParticipantsInvitedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.videoChatParticipantsInvitedOrNull(): VideoChatParticipantsInvited? =
    this as? VideoChatParticipantsInvited

@PreviewFeature
inline fun ChatEvent.videoChatParticipantsInvitedOrThrow(): VideoChatParticipantsInvited =
    this as VideoChatParticipantsInvited

@PreviewFeature
inline fun <T> ChatEvent.ifVideoChatStarted(block: (VideoChatStarted) -> T) = videoChatStartedOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.videoChatStartedOrNull(): VideoChatStarted? = this as? VideoChatStarted

@PreviewFeature
inline fun ChatEvent.videoChatStartedOrThrow(): VideoChatStarted = this as VideoChatStarted

@PreviewFeature
inline fun <T> ChatEvent.ifVideoChatScheduled(block: (VideoChatScheduled) -> T) = videoChatScheduledOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.videoChatScheduledOrNull(): VideoChatScheduled? = this as? VideoChatScheduled

@PreviewFeature
inline fun ChatEvent.videoChatScheduledOrThrow(): VideoChatScheduled = this as VideoChatScheduled

@PreviewFeature
inline fun <T> ChatEvent.ifUserLoggedIn(block: (UserLoggedIn) -> T) = userLoggedInOrNull()?.let(block)

@PreviewFeature
inline fun ChatEvent.userLoggedInOrNull(): UserLoggedIn? = this as? UserLoggedIn

@PreviewFeature
inline fun ChatEvent.userLoggedInOrThrow(): UserLoggedIn = this as UserLoggedIn

@PreviewFeature
inline fun <T> CommonSendInvoiceData.ifSendInvoice(block: (SendInvoice) -> T) = sendInvoiceOrNull()?.let(block)

@PreviewFeature
inline fun CommonSendInvoiceData.sendInvoiceOrNull(): SendInvoice? = this as? SendInvoice

@PreviewFeature
inline fun CommonSendInvoiceData.sendInvoiceOrThrow(): SendInvoice = this as SendInvoice

@PreviewFeature
inline fun <T> CommonSendInvoiceData.ifCreateInvoiceLink(block: (CreateInvoiceLink) -> T) =
    createInvoiceLinkOrNull()?.let(block)

@PreviewFeature
inline fun CommonSendInvoiceData.createInvoiceLinkOrNull(): CreateInvoiceLink? = this as? CreateInvoiceLink

@PreviewFeature
inline fun CommonSendInvoiceData.createInvoiceLinkOrThrow(): CreateInvoiceLink = this as CreateInvoiceLink

@PreviewFeature
inline fun <T> CommonSendInvoiceData.ifInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T) =
    inputInvoiceMessageContentOrNull()?.let(block)

@PreviewFeature
inline fun CommonSendInvoiceData.inputInvoiceMessageContentOrNull(): InputInvoiceMessageContent? =
    this as? InputInvoiceMessageContent

@PreviewFeature
inline fun CommonSendInvoiceData.inputInvoiceMessageContentOrThrow(): InputInvoiceMessageContent =
    this as InputInvoiceMessageContent

@PreviewFeature
inline fun <T> Any.ifFromUser(block: (FromUser) -> T) = fromUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.fromUserOrNull(): FromUser? = this as? FromUser

@PreviewFeature
inline fun Any.fromUserOrThrow(): FromUser = this as FromUser

@PreviewFeature
inline fun <T> Any.ifWithUser(block: (WithUser) -> T) = withUserOrNull()?.let(block)

@PreviewFeature
inline fun Any.withUserOrNull(): WithUser? = this as? WithUser

@PreviewFeature
inline fun Any.withUserOrThrow(): WithUser = this as WithUser

@PreviewFeature
inline fun <T> Any.ifWithOptionalLanguageCode(block: (WithOptionalLanguageCode) -> T) =
    withOptionalLanguageCodeOrNull()?.let(block)

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrNull(): WithOptionalLanguageCode? = this as? WithOptionalLanguageCode

@PreviewFeature
inline fun Any.withOptionalLanguageCodeOrThrow(): WithOptionalLanguageCode = this as WithOptionalLanguageCode

@PreviewFeature
inline fun <T> Location.ifStaticLocation(block: (StaticLocation) -> T) = staticLocationOrNull()?.let(block)

@PreviewFeature
inline fun Location.staticLocationOrNull(): StaticLocation? = this as? StaticLocation

@PreviewFeature
inline fun Location.staticLocationOrThrow(): StaticLocation = this as StaticLocation

@PreviewFeature
inline fun <T> Location.ifLiveLocation(block: (LiveLocation) -> T) = liveLocationOrNull()?.let(block)

@PreviewFeature
inline fun Location.liveLocationOrNull(): LiveLocation? = this as? LiveLocation

@PreviewFeature
inline fun Location.liveLocationOrThrow(): LiveLocation = this as LiveLocation

@PreviewFeature
inline fun <T> ChatInviteLink.ifPrimaryInviteLink(block: (PrimaryInviteLink) -> T) =
    primaryInviteLinkOrNull()?.let(block)

@PreviewFeature
inline fun ChatInviteLink.primaryInviteLinkOrNull(): PrimaryInviteLink? = this as? PrimaryInviteLink

@PreviewFeature
inline fun ChatInviteLink.primaryInviteLinkOrThrow(): PrimaryInviteLink = this as PrimaryInviteLink

@PreviewFeature
inline fun <T> ChatInviteLink.ifSecondaryChatInviteLink(block: (SecondaryChatInviteLink) -> T) =
    secondaryChatInviteLinkOrNull()?.let(block)

@PreviewFeature
inline fun ChatInviteLink.secondaryChatInviteLinkOrNull(): SecondaryChatInviteLink? = this as? SecondaryChatInviteLink

@PreviewFeature
inline fun ChatInviteLink.secondaryChatInviteLinkOrThrow(): SecondaryChatInviteLink = this as SecondaryChatInviteLink

@PreviewFeature
inline fun <T> ChatInviteLink.ifChatInviteLinkWithJoinRequest(block: (ChatInviteLinkWithJoinRequest) -> T) =
    chatInviteLinkWithJoinRequestOrNull()?.let(block)

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkWithJoinRequestOrNull(): ChatInviteLinkWithJoinRequest? =
    this as? ChatInviteLinkWithJoinRequest

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkWithJoinRequestOrThrow(): ChatInviteLinkWithJoinRequest =
    this as ChatInviteLinkWithJoinRequest

@PreviewFeature
inline fun <T> ChatInviteLink.ifChatInviteLinkWithLimitedMembers(block: (ChatInviteLinkWithLimitedMembers) -> T) =
    chatInviteLinkWithLimitedMembersOrNull()?.let(block)

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkWithLimitedMembersOrNull(): ChatInviteLinkWithLimitedMembers? =
    this as? ChatInviteLinkWithLimitedMembers

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkWithLimitedMembersOrThrow(): ChatInviteLinkWithLimitedMembers =
    this as ChatInviteLinkWithLimitedMembers

@PreviewFeature
inline fun <T> ChatInviteLink.ifChatInviteLinkUnlimited(block: (ChatInviteLinkUnlimited) -> T) =
    chatInviteLinkUnlimitedOrNull()?.let(block)

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkUnlimitedOrNull(): ChatInviteLinkUnlimited? = this as? ChatInviteLinkUnlimited

@PreviewFeature
inline fun ChatInviteLink.chatInviteLinkUnlimitedOrThrow(): ChatInviteLinkUnlimited = this as ChatInviteLinkUnlimited

@PreviewFeature
inline fun <T> ForwardInfo.ifAnonymousForwardInfo(block: (ForwardInfo.ByAnonymous) -> T) =
    anonymousForwardInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.anonymousForwardInfoOrNull(): ForwardInfo.ByAnonymous? = this as? ForwardInfo.ByAnonymous

@PreviewFeature
inline fun ForwardInfo.anonymousForwardInfoOrThrow(): ForwardInfo.ByAnonymous = this as ForwardInfo.ByAnonymous

@PreviewFeature
inline fun <T> ForwardInfo.ifUserForwardInfo(block: (ForwardInfo.ByUser) -> T) = userForwardInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.userForwardInfoOrNull(): ForwardInfo.ByUser? = this as? ForwardInfo.ByUser

@PreviewFeature
inline fun ForwardInfo.userForwardInfoOrThrow(): ForwardInfo.ByUser = this as ForwardInfo.ByUser

@PreviewFeature
inline fun <T> ForwardInfo.ifForwardFromPublicChatInfo(block: (ForwardInfo.PublicChat) -> T) =
    forwardFromPublicChatInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.forwardFromPublicChatInfoOrNull(): ForwardInfo.PublicChat? =
    this as? ForwardInfo.PublicChat

@PreviewFeature
inline fun ForwardInfo.forwardFromPublicChatInfoOrThrow(): ForwardInfo.PublicChat = this as ForwardInfo.PublicChat

@PreviewFeature
inline fun <T> ForwardInfo.ifForwardFromChannelInfo(block: (ForwardInfo.PublicChat.FromChannel) -> T) =
    forwardFromChannelInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.forwardFromChannelInfoOrNull(): ForwardInfo.PublicChat.FromChannel? =
    this as? ForwardInfo.PublicChat.FromChannel

@PreviewFeature
inline fun ForwardInfo.forwardFromChannelInfoOrThrow(): ForwardInfo.PublicChat.FromChannel =
    this as ForwardInfo.PublicChat.FromChannel

@PreviewFeature
inline fun <T> ForwardInfo.ifForwardSentByChannelInfo(block: (ForwardInfo.PublicChat.SentByChannel) -> T) =
    forwardSentByChannelInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.forwardSentByChannelInfoOrNull(): ForwardInfo.PublicChat.SentByChannel? =
    this as? ForwardInfo.PublicChat.SentByChannel

@PreviewFeature
inline fun ForwardInfo.forwardSentByChannelInfoOrThrow(): ForwardInfo.PublicChat.SentByChannel =
    this as ForwardInfo.PublicChat.SentByChannel

@PreviewFeature
inline fun <T> ForwardInfo.ifForwardFromSupergroupInfo(block: (ForwardInfo.PublicChat.FromSupergroup) -> T) =
    forwardFromSupergroupInfoOrNull()?.let(block)

@PreviewFeature
inline fun ForwardInfo.forwardFromSupergroupInfoOrNull(): ForwardInfo.PublicChat.FromSupergroup? =
    this as? ForwardInfo.PublicChat.FromSupergroup

@PreviewFeature
inline fun ForwardInfo.forwardFromSupergroupInfoOrThrow(): ForwardInfo.PublicChat.FromSupergroup =
    this as ForwardInfo.PublicChat.FromSupergroup

@PreviewFeature
inline fun <T> MessageContent.ifTextedInput(block: (TextedInput) -> T) = textedInputOrNull()?.let(block)

@PreviewFeature
inline fun MessageContent.textedInputOrNull(): TextedInput? = this as? TextedInput

@PreviewFeature
inline fun MessageContent.textedInputOrThrow(): TextedInput = this as TextedInput

@PreviewFeature
inline fun <T> ScheduledCloseInfo.ifExactScheduledCloseInfo(block: (ExactScheduledCloseInfo) -> T) =
    exactScheduledCloseInfoOrNull()?.let(block)

@PreviewFeature
inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrNull(): ExactScheduledCloseInfo? =
    this as? ExactScheduledCloseInfo

@PreviewFeature
inline fun ScheduledCloseInfo.exactScheduledCloseInfoOrThrow(): ExactScheduledCloseInfo =
    this as ExactScheduledCloseInfo

@PreviewFeature
inline fun <T> ScheduledCloseInfo.ifApproximateScheduledCloseInfo(block: (ApproximateScheduledCloseInfo) -> T) =
    approximateScheduledCloseInfoOrNull()?.let(block)

@PreviewFeature
inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrNull(): ApproximateScheduledCloseInfo? =
    this as? ApproximateScheduledCloseInfo

@PreviewFeature
inline fun ScheduledCloseInfo.approximateScheduledCloseInfoOrThrow(): ApproximateScheduledCloseInfo =
    this as ApproximateScheduledCloseInfo

@PreviewFeature
inline fun <T> ChosenInlineResult.ifLocationChosenInlineResult(block: (LocationChosenInlineResult) -> T) =
    locationChosenInlineResultOrNull()?.let(block)

@PreviewFeature
inline fun ChosenInlineResult.locationChosenInlineResultOrNull(): LocationChosenInlineResult? =
    this as? LocationChosenInlineResult

@PreviewFeature
inline fun ChosenInlineResult.locationChosenInlineResultOrThrow(): LocationChosenInlineResult =
    this as LocationChosenInlineResult

@PreviewFeature
inline fun <T> ChosenInlineResult.ifBaseChosenInlineResult(block: (BaseChosenInlineResult) -> T) =
    baseChosenInlineResultOrNull()?.let(block)

@PreviewFeature
inline fun ChosenInlineResult.baseChosenInlineResultOrNull(): BaseChosenInlineResult? = this as? BaseChosenInlineResult

@PreviewFeature
inline fun ChosenInlineResult.baseChosenInlineResultOrThrow(): BaseChosenInlineResult = this as BaseChosenInlineResult
