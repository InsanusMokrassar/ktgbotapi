@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.requests.send.payments.SendInvoice
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.ChatMember.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.*
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
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.abstracts.*
import dev.inmo.tgbotapi.types.chat.abstracts.extended.*
import dev.inmo.tgbotapi.types.dice.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.types.files.sticker.*
import dev.inmo.tgbotapi.types.files.sticker.Sticker
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMember
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.content.media.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.message.payments.SuccessfulPaymentEvent
import dev.inmo.tgbotapi.types.passport.*
import dev.inmo.tgbotapi.types.passport.decrypted.*
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import dev.inmo.tgbotapi.types.passport.encrypted.*
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import dev.inmo.tgbotapi.types.polls.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
inline fun <T> Chat.whenBot(block: (Bot) -> T) = asBot() ?.let(block)

@PreviewFeature
inline fun Chat.asBot(): Bot? = this as? Bot

@PreviewFeature
inline fun Chat.requireBot(): Bot = this as Bot

@PreviewFeature
inline fun <T> Chat.whenCommonBot(block: (CommonBot) -> T) = asCommonBot() ?.let(block)

@PreviewFeature
inline fun Chat.asCommonBot(): CommonBot? = this as? CommonBot

@PreviewFeature
inline fun Chat.requireCommonBot(): CommonBot = this as CommonBot

@PreviewFeature
inline fun <T> Chat.whenCommonUser(block: (CommonUser) -> T) = asCommonUser() ?.let(block)

@PreviewFeature
inline fun Chat.asCommonUser(): CommonUser? = this as? CommonUser

@PreviewFeature
inline fun Chat.requireCommonUser(): CommonUser = this as CommonUser

@PreviewFeature
inline fun <T> Chat.whenExtendedBot(block: (ExtendedBot) -> T) = asExtendedBot() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedBot(): ExtendedBot? = this as? ExtendedBot

@PreviewFeature
inline fun Chat.requireExtendedBot(): ExtendedBot = this as ExtendedBot

@PreviewFeature
inline fun <T> Chat.whenUser(block: (User) -> T) = asUser() ?.let(block)

@PreviewFeature
inline fun Chat.asUser(): User? = this as? User

@PreviewFeature
inline fun Chat.requireUser(): User = this as User

@PreviewFeature
inline fun <T> Chat.whenChannelChat(block: (ChannelChat) -> T) = asChannelChat() ?.let(block)

@PreviewFeature
inline fun Chat.asChannelChat(): ChannelChat? = this as? ChannelChat

@PreviewFeature
inline fun Chat.requireChannelChat(): ChannelChat = this as ChannelChat

@PreviewFeature
inline fun <T> Chat.whenGroupChat(block: (GroupChat) -> T) = asGroupChat() ?.let(block)

@PreviewFeature
inline fun Chat.asGroupChat(): GroupChat? = this as? GroupChat

@PreviewFeature
inline fun Chat.requireGroupChat(): GroupChat = this as GroupChat

@PreviewFeature
inline fun <T> Chat.whenPrivateChat(block: (PrivateChat) -> T) = asPrivateChat() ?.let(block)

@PreviewFeature
inline fun Chat.asPrivateChat(): PrivateChat? = this as? PrivateChat

@PreviewFeature
inline fun Chat.requirePrivateChat(): PrivateChat = this as PrivateChat

@PreviewFeature
inline fun <T> Chat.whenPublicChat(block: (PublicChat) -> T) = asPublicChat() ?.let(block)

@PreviewFeature
inline fun Chat.asPublicChat(): PublicChat? = this as? PublicChat

@PreviewFeature
inline fun Chat.requirePublicChat(): PublicChat = this as PublicChat

@PreviewFeature
inline fun <T> Chat.whenSuperPublicChat(block: (SuperPublicChat) -> T) = asSuperPublicChat() ?.let(block)

@PreviewFeature
inline fun Chat.asSuperPublicChat(): SuperPublicChat? = this as? SuperPublicChat

@PreviewFeature
inline fun Chat.requireSuperPublicChat(): SuperPublicChat = this as SuperPublicChat

@PreviewFeature
inline fun <T> Chat.whenSupergroupChat(block: (SupergroupChat) -> T) = asSupergroupChat() ?.let(block)

@PreviewFeature
inline fun Chat.asSupergroupChat(): SupergroupChat? = this as? SupergroupChat

@PreviewFeature
inline fun Chat.requireSupergroupChat(): SupergroupChat = this as SupergroupChat

@PreviewFeature
inline fun <T> Chat.whenUnknownChatType(block: (UnknownChatType) -> T) = asUnknownChatType() ?.let(block)

@PreviewFeature
inline fun Chat.asUnknownChatType(): UnknownChatType? = this as? UnknownChatType

@PreviewFeature
inline fun Chat.requireUnknownChatType(): UnknownChatType = this as UnknownChatType

@PreviewFeature
inline fun <T> Chat.whenUsernameChat(block: (UsernameChat) -> T) = asUsernameChat() ?.let(block)

@PreviewFeature
inline fun Chat.asUsernameChat(): UsernameChat? = this as? UsernameChat

@PreviewFeature
inline fun Chat.requireUsernameChat(): UsernameChat = this as UsernameChat

@PreviewFeature
inline fun <T> Chat.whenExtendedChannelChat(block: (ExtendedChannelChat) -> T) = asExtendedChannelChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedChannelChat(): ExtendedChannelChat? = this as? ExtendedChannelChat

@PreviewFeature
inline fun Chat.requireExtendedChannelChat(): ExtendedChannelChat = this as ExtendedChannelChat

@PreviewFeature
inline fun <T> Chat.whenExtendedChat(block: (ExtendedChat) -> T) = asExtendedChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedChat(): ExtendedChat? = this as? ExtendedChat

@PreviewFeature
inline fun Chat.requireExtendedChat(): ExtendedChat = this as ExtendedChat

@PreviewFeature
inline fun <T> Chat.whenExtendedGroupChat(block: (ExtendedGroupChat) -> T) = asExtendedGroupChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedGroupChat(): ExtendedGroupChat? = this as? ExtendedGroupChat

@PreviewFeature
inline fun Chat.requireExtendedGroupChat(): ExtendedGroupChat = this as ExtendedGroupChat

@PreviewFeature
inline fun <T> Chat.whenExtendedPrivateChat(block: (ExtendedPrivateChat) -> T) = asExtendedPrivateChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedPrivateChat(): ExtendedPrivateChat? = this as? ExtendedPrivateChat

@PreviewFeature
inline fun Chat.requireExtendedPrivateChat(): ExtendedPrivateChat = this as ExtendedPrivateChat

@PreviewFeature
inline fun <T> Chat.whenExtendedPublicChat(block: (ExtendedPublicChat) -> T) = asExtendedPublicChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedPublicChat(): ExtendedPublicChat? = this as? ExtendedPublicChat

@PreviewFeature
inline fun Chat.requireExtendedPublicChat(): ExtendedPublicChat = this as ExtendedPublicChat

@PreviewFeature
inline fun <T> Chat.whenExtendedSupergroupChat(block: (ExtendedSupergroupChat) -> T) = asExtendedSupergroupChat() ?.let(block)

@PreviewFeature
inline fun Chat.asExtendedSupergroupChat(): ExtendedSupergroupChat? = this as? ExtendedSupergroupChat

@PreviewFeature
inline fun Chat.requireExtendedSupergroupChat(): ExtendedSupergroupChat = this as ExtendedSupergroupChat

@PreviewFeature
inline fun <T> CallbackQuery.whenDataCallbackQuery(block: (DataCallbackQuery) -> T) = asDataCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asDataCallbackQuery(): DataCallbackQuery? = this as? DataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireDataCallbackQuery(): DataCallbackQuery = this as DataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenGameShortNameCallbackQuery(block: (GameShortNameCallbackQuery) -> T) = asGameShortNameCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asGameShortNameCallbackQuery(): GameShortNameCallbackQuery? =
    this as? GameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireGameShortNameCallbackQuery(): GameShortNameCallbackQuery =
    this as GameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenInlineMessageIdCallbackQuery(block: (InlineMessageIdCallbackQuery) -> T) = asInlineMessageIdCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdCallbackQuery(): InlineMessageIdCallbackQuery? =
    this as? InlineMessageIdCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdCallbackQuery(): InlineMessageIdCallbackQuery =
    this as InlineMessageIdCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenInlineMessageIdDataCallbackQuery(block: (InlineMessageIdDataCallbackQuery) -> T) = asInlineMessageIdDataCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdDataCallbackQuery(): InlineMessageIdDataCallbackQuery? =
    this as? InlineMessageIdDataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdDataCallbackQuery(): InlineMessageIdDataCallbackQuery =
    this as InlineMessageIdDataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenInlineMessageIdGameShortNameCallbackQuery(block: (InlineMessageIdGameShortNameCallbackQuery) -> T) = asInlineMessageIdGameShortNameCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdGameShortNameCallbackQuery(): InlineMessageIdGameShortNameCallbackQuery? =
    this as? InlineMessageIdGameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdGameShortNameCallbackQuery(): InlineMessageIdGameShortNameCallbackQuery =
    this as InlineMessageIdGameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenMessageCallbackQuery(block: (MessageCallbackQuery) -> T) = asMessageCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asMessageCallbackQuery(): MessageCallbackQuery? = this as? MessageCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireMessageCallbackQuery(): MessageCallbackQuery = this as MessageCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenMessageDataCallbackQuery(block: (MessageDataCallbackQuery) -> T) = asMessageDataCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asMessageDataCallbackQuery(): MessageDataCallbackQuery? = this as? MessageDataCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireMessageDataCallbackQuery(): MessageDataCallbackQuery = this as MessageDataCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenMessageGameShortNameCallbackQuery(block: (MessageGameShortNameCallbackQuery) -> T) = asMessageGameShortNameCallbackQuery() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asMessageGameShortNameCallbackQuery(): MessageGameShortNameCallbackQuery? =
    this as? MessageGameShortNameCallbackQuery

@PreviewFeature
inline fun CallbackQuery.requireMessageGameShortNameCallbackQuery(): MessageGameShortNameCallbackQuery =
    this as MessageGameShortNameCallbackQuery

@PreviewFeature
inline fun <T> CallbackQuery.whenUnknownCallbackQueryType(block: (UnknownCallbackQueryType) -> T) = asUnknownCallbackQueryType() ?.let(block)

@PreviewFeature
inline fun CallbackQuery.asUnknownCallbackQueryType(): UnknownCallbackQueryType? = this as? UnknownCallbackQueryType

@PreviewFeature
inline fun CallbackQuery.requireUnknownCallbackQueryType(): UnknownCallbackQueryType = this as UnknownCallbackQueryType

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorDataField(block: (PassportElementErrorDataField) -> T) = asPassportElementErrorDataField() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorDataField(): PassportElementErrorDataField? =
    this as? PassportElementErrorDataField

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorDataField(): PassportElementErrorDataField =
    this as PassportElementErrorDataField

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorFile(block: (PassportElementErrorFile) -> T) = asPassportElementErrorFile() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFile(): PassportElementErrorFile? =
    this as? PassportElementErrorFile

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFile(): PassportElementErrorFile =
    this as PassportElementErrorFile

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorFiles(block: (PassportElementErrorFiles) -> T) = asPassportElementErrorFiles() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFiles(): PassportElementErrorFiles? =
    this as? PassportElementErrorFiles

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFiles(): PassportElementErrorFiles =
    this as PassportElementErrorFiles

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorFrontSide(block: (PassportElementErrorFrontSide) -> T) = asPassportElementErrorFrontSide() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFrontSide(): PassportElementErrorFrontSide? =
    this as? PassportElementErrorFrontSide

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFrontSide(): PassportElementErrorFrontSide =
    this as PassportElementErrorFrontSide

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorReverseSide(block: (PassportElementErrorReverseSide) -> T) = asPassportElementErrorReverseSide() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorReverseSide(): PassportElementErrorReverseSide? =
    this as? PassportElementErrorReverseSide

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorReverseSide(): PassportElementErrorReverseSide =
    this as PassportElementErrorReverseSide

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorSelfie(block: (PassportElementErrorSelfie) -> T) = asPassportElementErrorSelfie() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorSelfie(): PassportElementErrorSelfie? =
    this as? PassportElementErrorSelfie

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorSelfie(): PassportElementErrorSelfie =
    this as PassportElementErrorSelfie

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorTranslationFile(block: (PassportElementErrorTranslationFile) -> T) = asPassportElementErrorTranslationFile() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorTranslationFile(): PassportElementErrorTranslationFile? =
    this as? PassportElementErrorTranslationFile

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorTranslationFile(): PassportElementErrorTranslationFile =
    this as PassportElementErrorTranslationFile

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorTranslationFiles(block: (PassportElementErrorTranslationFiles) -> T) = asPassportElementErrorTranslationFiles() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorTranslationFiles(): PassportElementErrorTranslationFiles? =
    this as? PassportElementErrorTranslationFiles

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorTranslationFiles(): PassportElementErrorTranslationFiles =
    this as PassportElementErrorTranslationFiles

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementErrorUnspecified(block: (PassportElementErrorUnspecified) -> T) = asPassportElementErrorUnspecified() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementErrorUnspecified(): PassportElementErrorUnspecified? =
    this as? PassportElementErrorUnspecified

@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorUnspecified(): PassportElementErrorUnspecified =
    this as PassportElementErrorUnspecified

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementFileError(block: (PassportElementFileError) -> T) = asPassportElementFileError() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementFileError(): PassportElementFileError? =
    this as? PassportElementFileError

@PreviewFeature
inline fun PassportElementError.requirePassportElementFileError(): PassportElementFileError =
    this as PassportElementFileError

@PreviewFeature
inline fun <T> PassportElementError.whenPassportElementFilesError(block: (PassportElementFilesError) -> T) = asPassportElementFilesError() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportElementFilesError(): PassportElementFilesError? =
    this as? PassportElementFilesError

@PreviewFeature
inline fun PassportElementError.requirePassportElementFilesError(): PassportElementFilesError =
    this as PassportElementFilesError

@PreviewFeature
inline fun <T> PassportElementError.whenPassportMultipleElementsError(block: (PassportMultipleElementsError) -> T) = asPassportMultipleElementsError() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportMultipleElementsError(): PassportMultipleElementsError? =
    this as? PassportMultipleElementsError

@PreviewFeature
inline fun PassportElementError.requirePassportMultipleElementsError(): PassportMultipleElementsError =
    this as PassportMultipleElementsError

@PreviewFeature
inline fun <T> PassportElementError.whenPassportSingleElementError(block: (PassportSingleElementError) -> T) = asPassportSingleElementError() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asPassportSingleElementError(): PassportSingleElementError? =
    this as? PassportSingleElementError

@PreviewFeature
inline fun PassportElementError.requirePassportSingleElementError(): PassportSingleElementError =
    this as PassportSingleElementError

@PreviewFeature
inline fun <T> PassportElementError.whenUnknownPassportElementError(block: (UnknownPassportElementError) -> T) = asUnknownPassportElementError() ?.let(block)

@PreviewFeature
inline fun PassportElementError.asUnknownPassportElementError(): UnknownPassportElementError? =
    this as? UnknownPassportElementError

@PreviewFeature
inline fun PassportElementError.requireUnknownPassportElementError(): UnknownPassportElementError =
    this as UnknownPassportElementError

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenBankStatement(block: (BankStatement) -> T) = asBankStatement() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asBankStatement(): BankStatement? = this as? BankStatement

@PreviewFeature
inline fun EncryptedPassportElement.requireBankStatement(): BankStatement = this as BankStatement

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenCommonPassport(block: (CommonPassport) -> T) = asCommonPassport() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asCommonPassport(): CommonPassport? = this as? CommonPassport

@PreviewFeature
inline fun EncryptedPassportElement.requireCommonPassport(): CommonPassport = this as CommonPassport

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenDriverLicense(block: (DriverLicense) -> T) = asDriverLicense() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asDriverLicense(): DriverLicense? = this as? DriverLicense

@PreviewFeature
inline fun EncryptedPassportElement.requireDriverLicense(): DriverLicense = this as DriverLicense

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEmail(block: (Email) -> T) = asEmail() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEmail(): Email? = this as? Email

@PreviewFeature
inline fun EncryptedPassportElement.requireEmail(): Email = this as Email

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedAddress(block: (EncryptedAddress) -> T) = asEncryptedAddress() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedAddress(): EncryptedAddress? = this as? EncryptedAddress

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedAddress(): EncryptedAddress = this as EncryptedAddress

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPersonalDetails(block: (EncryptedPersonalDetails) -> T) = asEncryptedPersonalDetails() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPersonalDetails(): EncryptedPersonalDetails? =
    this as? EncryptedPersonalDetails

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPersonalDetails(): EncryptedPersonalDetails =
    this as EncryptedPersonalDetails

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenIdentityCard(block: (IdentityCard) -> T) = asIdentityCard() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asIdentityCard(): IdentityCard? = this as? IdentityCard

@PreviewFeature
inline fun EncryptedPassportElement.requireIdentityCard(): IdentityCard = this as IdentityCard

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenInternalPassport(block: (InternalPassport) -> T) = asInternalPassport() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asInternalPassport(): InternalPassport? = this as? InternalPassport

@PreviewFeature
inline fun EncryptedPassportElement.requireInternalPassport(): InternalPassport = this as InternalPassport

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenPassport(block: (Passport) -> T) = asPassport() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asPassport(): Passport? = this as? Passport

@PreviewFeature
inline fun EncryptedPassportElement.requirePassport(): Passport = this as Passport

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenPassportRegistration(block: (PassportRegistration) -> T) = asPassportRegistration() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asPassportRegistration(): PassportRegistration? = this as? PassportRegistration

@PreviewFeature
inline fun EncryptedPassportElement.requirePassportRegistration(): PassportRegistration = this as PassportRegistration

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenPhoneNumber(block: (PhoneNumber) -> T) = asPhoneNumber() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asPhoneNumber(): PhoneNumber? = this as? PhoneNumber

@PreviewFeature
inline fun EncryptedPassportElement.requirePhoneNumber(): PhoneNumber = this as PhoneNumber

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenRentalAgreement(block: (RentalAgreement) -> T) = asRentalAgreement() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asRentalAgreement(): RentalAgreement? = this as? RentalAgreement

@PreviewFeature
inline fun EncryptedPassportElement.requireRentalAgreement(): RentalAgreement = this as RentalAgreement

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenTemporaryRegistration(block: (TemporaryRegistration) -> T) = asTemporaryRegistration() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asTemporaryRegistration(): TemporaryRegistration? = this as? TemporaryRegistration

@PreviewFeature
inline fun EncryptedPassportElement.requireTemporaryRegistration(): TemporaryRegistration =
    this as TemporaryRegistration

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithTranslatableFilesCollection(block: (EncryptedPassportElementWithTranslatableFilesCollection) -> T) = asEncryptedPassportElementWithTranslatableFilesCollection() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithTranslatableFilesCollection(): EncryptedPassportElementWithTranslatableFilesCollection? =
    this as? EncryptedPassportElementWithTranslatableFilesCollection

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithTranslatableFilesCollection(): EncryptedPassportElementWithTranslatableFilesCollection =
    this as EncryptedPassportElementWithTranslatableFilesCollection

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithTranslatableIDDocument(block: (EncryptedPassportElementWithTranslatableIDDocument) -> T) = asEncryptedPassportElementWithTranslatableIDDocument() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithTranslatableIDDocument(): EncryptedPassportElementWithTranslatableIDDocument? =
    this as? EncryptedPassportElementWithTranslatableIDDocument

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithTranslatableIDDocument(): EncryptedPassportElementWithTranslatableIDDocument =
    this as EncryptedPassportElementWithTranslatableIDDocument

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenUtilityBill(block: (UtilityBill) -> T) = asUtilityBill() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asUtilityBill(): UtilityBill? = this as? UtilityBill

@PreviewFeature
inline fun EncryptedPassportElement.requireUtilityBill(): UtilityBill = this as UtilityBill

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithFilesCollection(block: (EncryptedPassportElementWithFilesCollection) -> T) = asEncryptedPassportElementWithFilesCollection() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithFilesCollection(): EncryptedPassportElementWithFilesCollection? =
    this as? EncryptedPassportElementWithFilesCollection

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithFilesCollection(): EncryptedPassportElementWithFilesCollection =
    this as EncryptedPassportElementWithFilesCollection

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementTranslatable(block: (EncryptedPassportElementTranslatable) -> T) = asEncryptedPassportElementTranslatable() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementTranslatable(): EncryptedPassportElementTranslatable? =
    this as? EncryptedPassportElementTranslatable

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementTranslatable(): EncryptedPassportElementTranslatable =
    this as EncryptedPassportElementTranslatable

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenUnknownEncryptedPassportElement(block: (UnknownEncryptedPassportElement) -> T) = asUnknownEncryptedPassportElement() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asUnknownEncryptedPassportElement(): UnknownEncryptedPassportElement? =
    this as? UnknownEncryptedPassportElement

@PreviewFeature
inline fun EncryptedPassportElement.requireUnknownEncryptedPassportElement(): UnknownEncryptedPassportElement =
    this as UnknownEncryptedPassportElement

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithData(block: (EncryptedPassportElementWithData) -> T) = asEncryptedPassportElementWithData() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithData(): EncryptedPassportElementWithData? =
    this as? EncryptedPassportElementWithData

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithData(): EncryptedPassportElementWithData =
    this as EncryptedPassportElementWithData

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithEmail(block: (EncryptedPassportElementWithEmail) -> T) = asEncryptedPassportElementWithEmail() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithEmail(): EncryptedPassportElementWithEmail? =
    this as? EncryptedPassportElementWithEmail

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithEmail(): EncryptedPassportElementWithEmail =
    this as EncryptedPassportElementWithEmail

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithFrontSide(block: (EncryptedPassportElementWithFrontSide) -> T) = asEncryptedPassportElementWithFrontSide() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithFrontSide(): EncryptedPassportElementWithFrontSide? =
    this as? EncryptedPassportElementWithFrontSide

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithFrontSide(): EncryptedPassportElementWithFrontSide =
    this as EncryptedPassportElementWithFrontSide

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithPhoneNumber(block: (EncryptedPassportElementWithPhoneNumber) -> T) = asEncryptedPassportElementWithPhoneNumber() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithPhoneNumber(): EncryptedPassportElementWithPhoneNumber? =
    this as? EncryptedPassportElementWithPhoneNumber

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithPhoneNumber(): EncryptedPassportElementWithPhoneNumber =
    this as EncryptedPassportElementWithPhoneNumber

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithReverseSide(block: (EncryptedPassportElementWithReverseSide) -> T) = asEncryptedPassportElementWithReverseSide() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithReverseSide(): EncryptedPassportElementWithReverseSide? =
    this as? EncryptedPassportElementWithReverseSide

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithReverseSide(): EncryptedPassportElementWithReverseSide =
    this as EncryptedPassportElementWithReverseSide

@PreviewFeature
inline fun <T> EncryptedPassportElement.whenEncryptedPassportElementWithSelfie(block: (EncryptedPassportElementWithSelfie) -> T) = asEncryptedPassportElementWithSelfie() ?.let(block)

@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithSelfie(): EncryptedPassportElementWithSelfie? =
    this as? EncryptedPassportElementWithSelfie

@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithSelfie(): EncryptedPassportElementWithSelfie =
    this as EncryptedPassportElementWithSelfie

@PreviewFeature
inline fun <T> SecureValue.whenAddressSecureValue(block: (AddressSecureValue) -> T) = asAddressSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asAddressSecureValue(): AddressSecureValue? = this as? AddressSecureValue

@PreviewFeature
inline fun SecureValue.requireAddressSecureValue(): AddressSecureValue = this as AddressSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenBankStatementSecureValue(block: (BankStatementSecureValue) -> T) = asBankStatementSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asBankStatementSecureValue(): BankStatementSecureValue? = this as? BankStatementSecureValue

@PreviewFeature
inline fun SecureValue.requireBankStatementSecureValue(): BankStatementSecureValue = this as BankStatementSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenCommonPassportSecureValue(block: (CommonPassportSecureValue) -> T) = asCommonPassportSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asCommonPassportSecureValue(): CommonPassportSecureValue? = this as? CommonPassportSecureValue

@PreviewFeature
inline fun SecureValue.requireCommonPassportSecureValue(): CommonPassportSecureValue = this as CommonPassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenDriverLicenseSecureValue(block: (DriverLicenseSecureValue) -> T) = asDriverLicenseSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asDriverLicenseSecureValue(): DriverLicenseSecureValue? = this as? DriverLicenseSecureValue

@PreviewFeature
inline fun SecureValue.requireDriverLicenseSecureValue(): DriverLicenseSecureValue = this as DriverLicenseSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenIdentityCardSecureValue(block: (IdentityCardSecureValue) -> T) = asIdentityCardSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asIdentityCardSecureValue(): IdentityCardSecureValue? = this as? IdentityCardSecureValue

@PreviewFeature
inline fun SecureValue.requireIdentityCardSecureValue(): IdentityCardSecureValue = this as IdentityCardSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenIdentityWithReverseSideSecureValue(block: (IdentityWithReverseSideSecureValue) -> T) = asIdentityWithReverseSideSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asIdentityWithReverseSideSecureValue(): IdentityWithReverseSideSecureValue? =
    this as? IdentityWithReverseSideSecureValue

@PreviewFeature
inline fun SecureValue.requireIdentityWithReverseSideSecureValue(): IdentityWithReverseSideSecureValue =
    this as IdentityWithReverseSideSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenInternalPassportSecureValue(block: (InternalPassportSecureValue) -> T) = asInternalPassportSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asInternalPassportSecureValue(): InternalPassportSecureValue? =
    this as? InternalPassportSecureValue

@PreviewFeature
inline fun SecureValue.requireInternalPassportSecureValue(): InternalPassportSecureValue =
    this as InternalPassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenOtherDocumentsSecureValue(block: (OtherDocumentsSecureValue) -> T) = asOtherDocumentsSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asOtherDocumentsSecureValue(): OtherDocumentsSecureValue? = this as? OtherDocumentsSecureValue

@PreviewFeature
inline fun SecureValue.requireOtherDocumentsSecureValue(): OtherDocumentsSecureValue = this as OtherDocumentsSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenPassportRegistrationSecureValue(block: (PassportRegistrationSecureValue) -> T) = asPassportRegistrationSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asPassportRegistrationSecureValue(): PassportRegistrationSecureValue? =
    this as? PassportRegistrationSecureValue

@PreviewFeature
inline fun SecureValue.requirePassportRegistrationSecureValue(): PassportRegistrationSecureValue =
    this as PassportRegistrationSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenPassportSecureValue(block: (PassportSecureValue) -> T) = asPassportSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asPassportSecureValue(): PassportSecureValue? = this as? PassportSecureValue

@PreviewFeature
inline fun SecureValue.requirePassportSecureValue(): PassportSecureValue = this as PassportSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenPersonalDetailsSecureValue(block: (PersonalDetailsSecureValue) -> T) = asPersonalDetailsSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asPersonalDetailsSecureValue(): PersonalDetailsSecureValue? = this as? PersonalDetailsSecureValue

@PreviewFeature
inline fun SecureValue.requirePersonalDetailsSecureValue(): PersonalDetailsSecureValue =
    this as PersonalDetailsSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenRentalAgreementSecureValue(block: (RentalAgreementSecureValue) -> T) = asRentalAgreementSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asRentalAgreementSecureValue(): RentalAgreementSecureValue? = this as? RentalAgreementSecureValue

@PreviewFeature
inline fun SecureValue.requireRentalAgreementSecureValue(): RentalAgreementSecureValue =
    this as RentalAgreementSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenTemporalRegistrationSecureValue(block: (TemporalRegistrationSecureValue) -> T) = asTemporalRegistrationSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asTemporalRegistrationSecureValue(): TemporalRegistrationSecureValue? =
    this as? TemporalRegistrationSecureValue

@PreviewFeature
inline fun SecureValue.requireTemporalRegistrationSecureValue(): TemporalRegistrationSecureValue =
    this as TemporalRegistrationSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenUtilityBillSecureValue(block: (UtilityBillSecureValue) -> T) = asUtilityBillSecureValue() ?.let(block)

@PreviewFeature
inline fun SecureValue.asUtilityBillSecureValue(): UtilityBillSecureValue? = this as? UtilityBillSecureValue

@PreviewFeature
inline fun SecureValue.requireUtilityBillSecureValue(): UtilityBillSecureValue = this as UtilityBillSecureValue

@PreviewFeature
inline fun <T> SecureValue.whenSecureValueIdentity(block: (SecureValueIdentity) -> T) = asSecureValueIdentity() ?.let(block)

@PreviewFeature
inline fun SecureValue.asSecureValueIdentity(): SecureValueIdentity? = this as? SecureValueIdentity

@PreviewFeature
inline fun SecureValue.requireSecureValueIdentity(): SecureValueIdentity = this as SecureValueIdentity

@PreviewFeature
inline fun <T> SecureValue.whenSecureValueWithData(block: (SecureValueWithData) -> T) = asSecureValueWithData() ?.let(block)

@PreviewFeature
inline fun SecureValue.asSecureValueWithData(): SecureValueWithData? = this as? SecureValueWithData

@PreviewFeature
inline fun SecureValue.requireSecureValueWithData(): SecureValueWithData = this as SecureValueWithData

@PreviewFeature
inline fun <T> SecureValue.whenSecureValueWithFiles(block: (SecureValueWithFiles) -> T) = asSecureValueWithFiles() ?.let(block)

@PreviewFeature
inline fun SecureValue.asSecureValueWithFiles(): SecureValueWithFiles? = this as? SecureValueWithFiles

@PreviewFeature
inline fun SecureValue.requireSecureValueWithFiles(): SecureValueWithFiles = this as SecureValueWithFiles

@PreviewFeature
inline fun <T> SecureValue.whenSecureValueWithReverseSide(block: (SecureValueWithReverseSide) -> T) = asSecureValueWithReverseSide() ?.let(block)

@PreviewFeature
inline fun SecureValue.asSecureValueWithReverseSide(): SecureValueWithReverseSide? = this as? SecureValueWithReverseSide

@PreviewFeature
inline fun SecureValue.requireSecureValueWithReverseSide(): SecureValueWithReverseSide =
    this as SecureValueWithReverseSide

@PreviewFeature
inline fun <T> SecureValue.whenSecureValueWithTranslations(block: (SecureValueWithTranslations) -> T) = asSecureValueWithTranslations() ?.let(block)

@PreviewFeature
inline fun SecureValue.asSecureValueWithTranslations(): SecureValueWithTranslations? =
    this as? SecureValueWithTranslations

@PreviewFeature
inline fun SecureValue.requireSecureValueWithTranslations(): SecureValueWithTranslations =
    this as SecureValueWithTranslations

@PreviewFeature
inline fun <T> Message.whenAnonymousGroupContentMessageImpl(block: (AnonymousGroupContentMessageImpl<MessageContent>) -> T) = asAnonymousGroupContentMessageImpl() ?.let(block)

@PreviewFeature
inline fun Message.asAnonymousGroupContentMessageImpl(): AnonymousGroupContentMessageImpl<MessageContent>? =
    this as? AnonymousGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.requireAnonymousGroupContentMessageImpl(): AnonymousGroupContentMessageImpl<MessageContent> =
    this as AnonymousGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun <T> Message.whenChannelContentMessageImpl(block: (UnconnectedFromChannelGroupContentMessageImpl<MessageContent>) -> T) = asChannelContentMessageImpl() ?.let(block)

@PreviewFeature
inline fun Message.asChannelContentMessageImpl(): UnconnectedFromChannelGroupContentMessageImpl<MessageContent>? =
    this as? UnconnectedFromChannelGroupContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.requireChannelContentMessageImpl(): UnconnectedFromChannelGroupContentMessageImpl<MessageContent> =
    this as UnconnectedFromChannelGroupContentMessageImpl<MessageContent>

@PreviewFeature
@Deprecated("Renamed", ReplaceWith("whenConnectedFromChannelGroupContentMessage", "dev.inmo.tgbotapi.extensions.utils.whenConnectedFromChannelGroupContentMessage"))
inline fun <T> Message.whenFromChannelGroupContentMessageImpl(block: (ConnectedFromChannelGroupContentMessage<MessageContent>) -> T) = whenConnectedFromChannelGroupContentMessage(block)

@PreviewFeature
@Deprecated("Renamed", ReplaceWith("asConnectedFromChannelGroupContentMessage", "dev.inmo.tgbotapi.extensions.utils.asConnectedFromChannelGroupContentMessage"))
inline fun Message.asFromChannelGroupContentMessageImpl(): ConnectedFromChannelGroupContentMessage<MessageContent>? = asConnectedFromChannelGroupContentMessage()

@PreviewFeature
@Deprecated("Renamed", ReplaceWith("requireConnectedFromChannelGroupContentMessage", "dev.inmo.tgbotapi.extensions.utils.requireConnectedFromChannelGroupContentMessage"))
inline fun Message.requireFromChannelGroupContentMessageImpl(): ConnectedFromChannelGroupContentMessage<MessageContent> = requireConnectedFromChannelGroupContentMessage()

@PreviewFeature
inline fun <T> Message.whenPassportMessage(block: (PassportMessage) -> T) = asPassportMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPassportMessage(): PassportMessage? = this as? PassportMessage

@PreviewFeature
inline fun Message.requirePassportMessage(): PassportMessage = this as PassportMessage

@PreviewFeature
inline fun <T> Message.whenPrivateContentMessageImpl(block: (PrivateContentMessageImpl<MessageContent>) -> T) = asPrivateContentMessageImpl() ?.let(block)

@PreviewFeature
inline fun Message.asPrivateContentMessageImpl(): PrivateContentMessageImpl<MessageContent>? =
    this as? PrivateContentMessageImpl<MessageContent>

@PreviewFeature
inline fun Message.requirePrivateContentMessageImpl(): PrivateContentMessageImpl<MessageContent> =
    this as PrivateContentMessageImpl<MessageContent>

@PreviewFeature
inline fun <T> Message.whenChannelEventMessage(block: (ChannelEventMessage<ChannelEvent>) -> T) = asChannelEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asChannelEventMessage(): ChannelEventMessage<ChannelEvent>? =
    this as? ChannelEventMessage<ChannelEvent>

@PreviewFeature
inline fun Message.requireChannelEventMessage(): ChannelEventMessage<ChannelEvent> =
    this as ChannelEventMessage<ChannelEvent>

@PreviewFeature
inline fun <T> Message.whenChannelMediaGroupMessage(block: (ChannelMediaGroupMessage<MediaGroupContent>) -> T) = asChannelMediaGroupMessage() ?.let(block)

@PreviewFeature
inline fun Message.asChannelMediaGroupMessage(): ChannelMediaGroupMessage<MediaGroupContent>? =
    this as? ChannelMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.requireChannelMediaGroupMessage(): ChannelMediaGroupMessage<MediaGroupContent> =
    this as ChannelMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.whenCommonGroupEventMessage(block: (CommonGroupEventMessage<GroupEvent>) -> T) = asCommonGroupEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asCommonGroupEventMessage(): CommonGroupEventMessage<GroupEvent>? =
    this as? CommonGroupEventMessage<GroupEvent>

@PreviewFeature
inline fun Message.requireCommonGroupEventMessage(): CommonGroupEventMessage<GroupEvent> =
    this as CommonGroupEventMessage<GroupEvent>

@PreviewFeature
inline fun <T> Message.whenCommonMediaGroupMessage(block: (CommonMediaGroupMessage<MediaGroupContent>) -> T) = asCommonMediaGroupMessage() ?.let(block)

@PreviewFeature
inline fun Message.asCommonMediaGroupMessage(): CommonMediaGroupMessage<MediaGroupContent>? =
    this as? CommonMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.requireCommonMediaGroupMessage(): CommonMediaGroupMessage<MediaGroupContent> =
    this as CommonMediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.whenCommonSupergroupEventMessage(block: (CommonSupergroupEventMessage<SupergroupEvent>) -> T) = asCommonSupergroupEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asCommonSupergroupEventMessage(): CommonSupergroupEventMessage<SupergroupEvent>? =
    this as? CommonSupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun Message.requireCommonSupergroupEventMessage(): CommonSupergroupEventMessage<SupergroupEvent> =
    this as CommonSupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun <T> Message.whenAnonymousGroupContentMessage(block: (AnonymousGroupContentMessage<MessageContent>) -> T) = asAnonymousGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asAnonymousGroupContentMessage(): AnonymousGroupContentMessage<MessageContent>? =
    this as? AnonymousGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireAnonymousGroupContentMessage(): AnonymousGroupContentMessage<MessageContent> =
    this as AnonymousGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenChannelContentMessage(block: (ChannelContentMessage<MessageContent>) -> T) = asChannelContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asChannelContentMessage(): ChannelContentMessage<MessageContent>? =
    this as? ChannelContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireChannelContentMessage(): ChannelContentMessage<MessageContent> =
    this as ChannelContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenConnectedFromChannelGroupContentMessage(block: (ConnectedFromChannelGroupContentMessage<MessageContent>) -> T) = asConnectedFromChannelGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asConnectedFromChannelGroupContentMessage(): ConnectedFromChannelGroupContentMessage<MessageContent>? =
    this as? ConnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireConnectedFromChannelGroupContentMessage(): ConnectedFromChannelGroupContentMessage<MessageContent> =
    this as ConnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenUnconnectedFromChannelGroupContentMessage(block: (UnconnectedFromChannelGroupContentMessage<MessageContent>) -> T) = asUnconnectedFromChannelGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asUnconnectedFromChannelGroupContentMessage(): UnconnectedFromChannelGroupContentMessage<MessageContent>? =
    this as? UnconnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireUnconnectedFromChannelGroupContentMessage(): UnconnectedFromChannelGroupContentMessage<MessageContent> =
    this as UnconnectedFromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenChatEventMessage(block: (ChatEventMessage<ChatEvent>) -> T) = asChatEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asChatEventMessage(): ChatEventMessage<ChatEvent>? = this as? ChatEventMessage<ChatEvent>

@PreviewFeature
inline fun Message.requireChatEventMessage(): ChatEventMessage<ChatEvent> = this as ChatEventMessage<ChatEvent>

@PreviewFeature
inline fun <T> Message.whenCommonGroupContentMessage(block: (CommonGroupContentMessage<MessageContent>) -> T) = asCommonGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asCommonGroupContentMessage(): CommonGroupContentMessage<MessageContent>? =
    this as? CommonGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireCommonGroupContentMessage(): CommonGroupContentMessage<MessageContent> =
    this as CommonGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenCommonMessage(block: (CommonMessage<MessageContent>) -> T) = asCommonMessage() ?.let(block)

@PreviewFeature
inline fun Message.asCommonMessage(): CommonMessage<MessageContent>? = this as? CommonMessage<MessageContent>

@PreviewFeature
inline fun Message.requireCommonMessage(): CommonMessage<MessageContent> = this as CommonMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenContentMessage(block: (ContentMessage<MessageContent>) -> T) = asContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asContentMessage(): ContentMessage<MessageContent>? = this as? ContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireContentMessage(): ContentMessage<MessageContent> = this as ContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenFromChannelGroupContentMessage(block: (FromChannelGroupContentMessage<MessageContent>) -> T) = asFromChannelGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asFromChannelGroupContentMessage(): FromChannelGroupContentMessage<MessageContent>? =
    this as? FromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireFromChannelGroupContentMessage(): FromChannelGroupContentMessage<MessageContent> =
    this as FromChannelGroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenGroupEventMessage(block: (GroupEventMessage<GroupEvent>) -> T) = asGroupEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asGroupEventMessage(): GroupEventMessage<GroupEvent>? = this as? GroupEventMessage<GroupEvent>

@PreviewFeature
inline fun Message.requireGroupEventMessage(): GroupEventMessage<GroupEvent> = this as GroupEventMessage<GroupEvent>

@PreviewFeature
inline fun <T> Message.whenPrivateEventMessage(block: (PrivateEventMessage<PrivateEvent>) -> T) = asPrivateEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPrivateEventMessage(): PrivateEventMessage<PrivateEvent>? = this as? PrivateEventMessage<PrivateEvent>

@PreviewFeature
inline fun Message.requirePrivateEventMessage(): PrivateEventMessage<PrivateEvent> = this as PrivateEventMessage<PrivateEvent>

@PreviewFeature
inline fun <T> Message.whenGroupContentMessage(block: (GroupContentMessage<MessageContent>) -> T) = asGroupContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asGroupContentMessage(): GroupContentMessage<MessageContent>? =
    this as? GroupContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requireGroupContentMessage(): GroupContentMessage<MessageContent> =
    this as GroupContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenMediaGroupMessage(block: (MediaGroupMessage<MediaGroupContent>) -> T) = asMediaGroupMessage() ?.let(block)

@PreviewFeature
inline fun Message.asMediaGroupMessage(): MediaGroupMessage<MediaGroupContent>? =
    this as? MediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun Message.requireMediaGroupMessage(): MediaGroupMessage<MediaGroupContent> =
    this as MediaGroupMessage<MediaGroupContent>

@PreviewFeature
inline fun <T> Message.whenPossiblyEditedMessage(block: (PossiblyEditedMessage) -> T) = asPossiblyEditedMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPossiblyEditedMessage(): PossiblyEditedMessage? = this as? PossiblyEditedMessage

@PreviewFeature
inline fun Message.requirePossiblyEditedMessage(): PossiblyEditedMessage = this as PossiblyEditedMessage

@PreviewFeature
inline fun <T> Message.whenPossiblyReplyMessage(block: (PossiblyReplyMessage) -> T) = asPossiblyReplyMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPossiblyReplyMessage(): PossiblyReplyMessage? = this as? PossiblyReplyMessage

@PreviewFeature
inline fun Message.requirePossiblyReplyMessage(): PossiblyReplyMessage = this as PossiblyReplyMessage

@PreviewFeature
inline fun <T> Message.whenPossiblyForwardedMessage(block: (PossiblyForwardedMessage) -> T) = asPossiblyForwardedMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPossiblyForwardedMessage(): PossiblyForwardedMessage? = this as? PossiblyForwardedMessage

@PreviewFeature
inline fun Message.requirePossiblyForwardedMessage(): PossiblyForwardedMessage = this as PossiblyForwardedMessage

@PreviewFeature
inline fun <T> Message.whenPossiblyPaymentMessage(block: (PossiblyPaymentMessage) -> T) = asPossiblyPaymentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPossiblyPaymentMessage(): PossiblyPaymentMessage? = this as? PossiblyPaymentMessage

@PreviewFeature
inline fun Message.requirePossiblyPaymentMessage(): PossiblyPaymentMessage = this as PossiblyPaymentMessage

@PreviewFeature
inline fun <T> Message.whenPrivateContentMessage(block: (PrivateContentMessage<MessageContent>) -> T) = asPrivateContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPrivateContentMessage(): PrivateContentMessage<MessageContent>? =
    this as? PrivateContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requirePrivateContentMessage(): PrivateContentMessage<MessageContent> =
    this as PrivateContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenPublicContentMessage(block: (PublicContentMessage<MessageContent>) -> T) = asPublicContentMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPublicContentMessage(): PublicContentMessage<MessageContent>? =
    this as? PublicContentMessage<MessageContent>

@PreviewFeature
inline fun Message.requirePublicContentMessage(): PublicContentMessage<MessageContent> =
    this as PublicContentMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenSignedMessage(block: (SignedMessage) -> T) = asSignedMessage() ?.let(block)

@PreviewFeature
inline fun Message.asSignedMessage(): SignedMessage? = this as? SignedMessage

@PreviewFeature
inline fun Message.requireSignedMessage(): SignedMessage = this as SignedMessage

@PreviewFeature
inline fun <T> Message.whenSupergroupEventMessage(block: (SupergroupEventMessage<SupergroupEvent>) -> T) = asSupergroupEventMessage() ?.let(block)

@PreviewFeature
inline fun Message.asSupergroupEventMessage(): SupergroupEventMessage<SupergroupEvent>? =
    this as? SupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun Message.requireSupergroupEventMessage(): SupergroupEventMessage<SupergroupEvent> =
    this as SupergroupEventMessage<SupergroupEvent>

@PreviewFeature
inline fun <T> Message.whenUnknownMessageType(block: (UnknownMessageType) -> T) = asUnknownMessageType() ?.let(block)

@PreviewFeature
inline fun Message.asUnknownMessageType(): UnknownMessageType? = this as? UnknownMessageType

@PreviewFeature
inline fun Message.requireUnknownMessageType(): UnknownMessageType = this as UnknownMessageType

@PreviewFeature
inline fun <T> Message.whenPossiblySentViaBotCommonMessage(block: (PossiblySentViaBotCommonMessage<MessageContent>) -> T) = asPossiblySentViaBotCommonMessage() ?.let(block)

@PreviewFeature
inline fun Message.asPossiblySentViaBotCommonMessage(): PossiblySentViaBotCommonMessage<MessageContent>? =
    this as? PossiblySentViaBotCommonMessage<MessageContent>

@PreviewFeature
inline fun Message.requirePossiblySentViaBotCommonMessage(): PossiblySentViaBotCommonMessage<MessageContent> =
    this as PossiblySentViaBotCommonMessage<MessageContent>

@PreviewFeature
inline fun <T> Message.whenFromUserMessage(block: (FromUserMessage) -> T) = asFromUserMessage() ?.let(block)

@PreviewFeature
inline fun Message.asFromUserMessage(): FromUserMessage? = this as? FromUserMessage

@PreviewFeature
inline fun Message.requireFromUserMessage(): FromUserMessage = this as FromUserMessage

@PreviewFeature
inline fun <T> BotAction.whenFindLocationAction(block: (FindLocationAction) -> T) = asFindLocationAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asFindLocationAction(): FindLocationAction? = this as? FindLocationAction

@PreviewFeature
inline fun BotAction.requireFindLocationAction(): FindLocationAction = this as FindLocationAction

@PreviewFeature
inline fun <T> BotAction.whenRecordVoiceAction(block: (RecordVoiceAction) -> T) = asRecordVoiceAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asRecordVoiceAction(): RecordVoiceAction? = this as? RecordVoiceAction

@PreviewFeature
inline fun BotAction.requireRecordVoiceAction(): RecordVoiceAction = this as RecordVoiceAction

@PreviewFeature
inline fun <T> BotAction.whenRecordVideoAction(block: (RecordVideoAction) -> T) = asRecordVideoAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asRecordVideoAction(): RecordVideoAction? = this as? RecordVideoAction

@PreviewFeature
inline fun BotAction.requireRecordVideoAction(): RecordVideoAction = this as RecordVideoAction

@PreviewFeature
inline fun <T> BotAction.whenRecordVideoNoteAction(block: (RecordVideoNoteAction) -> T) = asRecordVideoNoteAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asRecordVideoNoteAction(): RecordVideoNoteAction? = this as? RecordVideoNoteAction

@PreviewFeature
inline fun BotAction.requireRecordVideoNoteAction(): RecordVideoNoteAction = this as RecordVideoNoteAction

@PreviewFeature
inline fun <T> BotAction.whenTypingAction(block: (TypingAction) -> T) = asTypingAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asTypingAction(): TypingAction? = this as? TypingAction

@PreviewFeature
inline fun BotAction.requireTypingAction(): TypingAction = this as TypingAction

@PreviewFeature
inline fun <T> BotAction.whenChooseStickerAction(block: (ChooseStickerAction) -> T) = asChooseStickerAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asChooseStickerAction(): ChooseStickerAction? = this as? ChooseStickerAction

@PreviewFeature
inline fun BotAction.requireChooseStickerAction(): ChooseStickerAction = this as ChooseStickerAction

@PreviewFeature
inline fun <T> BotAction.whenUploadVoiceAction(block: (UploadVoiceAction) -> T) = asUploadVoiceAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asUploadVoiceAction(): UploadVoiceAction? = this as? UploadVoiceAction

@PreviewFeature
inline fun BotAction.requireUploadVoiceAction(): UploadVoiceAction = this as UploadVoiceAction

@PreviewFeature
inline fun <T> BotAction.whenUploadDocumentAction(block: (UploadDocumentAction) -> T) = asUploadDocumentAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asUploadDocumentAction(): UploadDocumentAction? = this as? UploadDocumentAction

@PreviewFeature
inline fun BotAction.requireUploadDocumentAction(): UploadDocumentAction = this as UploadDocumentAction

@PreviewFeature
inline fun <T> BotAction.whenUploadPhotoAction(block: (UploadPhotoAction) -> T) = asUploadPhotoAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asUploadPhotoAction(): UploadPhotoAction? = this as? UploadPhotoAction

@PreviewFeature
inline fun BotAction.requireUploadPhotoAction(): UploadPhotoAction = this as UploadPhotoAction

@PreviewFeature
inline fun <T> BotAction.whenUploadVideoAction(block: (UploadVideoAction) -> T) = asUploadVideoAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asUploadVideoAction(): UploadVideoAction? = this as? UploadVideoAction

@PreviewFeature
inline fun BotAction.requireUploadVideoAction(): UploadVideoAction = this as UploadVideoAction

@PreviewFeature
inline fun <T> BotAction.whenUploadVideoNoteAction(block: (UploadVideoNoteAction) -> T) = asUploadVideoNoteAction() ?.let(block)

@PreviewFeature
inline fun BotAction.asUploadVideoNoteAction(): UploadVideoNoteAction? = this as? UploadVideoNoteAction

@PreviewFeature
inline fun BotAction.requireUploadVideoNoteAction(): UploadVideoNoteAction = this as UploadVideoNoteAction

@PreviewFeature
inline fun <T> InlineQuery.whenBaseInlineQuery(block: (BaseInlineQuery) -> T) = asBaseInlineQuery() ?.let(block)

@PreviewFeature
inline fun InlineQuery.asBaseInlineQuery(): BaseInlineQuery? =
    this as? BaseInlineQuery

@PreviewFeature
inline fun InlineQuery.requireBaseInlineQuery(): BaseInlineQuery =
    this as BaseInlineQuery

@PreviewFeature
inline fun <T> InlineQuery.whenLocationInlineQuery(block: (LocationInlineQuery) -> T) = asLocationInlineQuery() ?.let(block)

@PreviewFeature
inline fun InlineQuery.asLocationInlineQuery(): LocationInlineQuery? =
    this as? LocationInlineQuery

@PreviewFeature
inline fun InlineQuery.requireLocationInlineQuery(): LocationInlineQuery =
    this as LocationInlineQuery

@PreviewFeature
inline fun <T> InputMessageContent.whenInputContactMessageContent(block: (InputContactMessageContent) -> T) = asInputContactMessageContent() ?.let(block)

@PreviewFeature
inline fun InputMessageContent.asInputContactMessageContent(): InputContactMessageContent? =
    this as? InputContactMessageContent

@PreviewFeature
inline fun InputMessageContent.requireInputContactMessageContent(): InputContactMessageContent =
    this as InputContactMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.whenInputLocationMessageContent(block: (InputLocationMessageContent) -> T) = asInputLocationMessageContent() ?.let(block)

@PreviewFeature
inline fun InputMessageContent.asInputLocationMessageContent(): InputLocationMessageContent? =
    this as? InputLocationMessageContent

@PreviewFeature
inline fun InputMessageContent.requireInputLocationMessageContent(): InputLocationMessageContent =
    this as InputLocationMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.whenInputTextMessageContent(block: (InputTextMessageContent) -> T) = asInputTextMessageContent() ?.let(block)

@PreviewFeature
inline fun InputMessageContent.asInputTextMessageContent(): InputTextMessageContent? = this as? InputTextMessageContent

@PreviewFeature
inline fun InputMessageContent.requireInputTextMessageContent(): InputTextMessageContent =
    this as InputTextMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.whenInputVenueMessageContent(block: (InputVenueMessageContent) -> T) = asInputVenueMessageContent() ?.let(block)

@PreviewFeature
inline fun InputMessageContent.asInputVenueMessageContent(): InputVenueMessageContent? =
    this as? InputVenueMessageContent

@PreviewFeature
inline fun InputMessageContent.requireInputVenueMessageContent(): InputVenueMessageContent =
    this as InputVenueMessageContent

@PreviewFeature
inline fun <T> InputMessageContent.whenInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T) = asInputInvoiceMessageContent() ?.let(block)

@PreviewFeature
inline fun InputMessageContent.asInputInvoiceMessageContent(): InputInvoiceMessageContent? =
    this as? InputInvoiceMessageContent

@PreviewFeature
inline fun InputMessageContent.requireInputInvoiceMessageContent(): InputInvoiceMessageContent =
    this as InputInvoiceMessageContent

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultArticle(block: (InlineQueryResultArticle) -> T) = asInlineQueryResultArticle() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultArticle(): InlineQueryResultArticle? = this as? InlineQueryResultArticle

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultArticle(): InlineQueryResultArticle =
    this as InlineQueryResultArticle

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultContact(block: (InlineQueryResultContact) -> T) = asInlineQueryResultContact() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultContact(): InlineQueryResultContact? = this as? InlineQueryResultContact

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultContact(): InlineQueryResultContact =
    this as InlineQueryResultContact

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultGame(block: (InlineQueryResultGame) -> T) = asInlineQueryResultGame() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGame(): InlineQueryResultGame? = this as? InlineQueryResultGame

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGame(): InlineQueryResultGame = this as InlineQueryResultGame

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultLocation(block: (InlineQueryResultLocation) -> T) = asInlineQueryResultLocation() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultLocation(): InlineQueryResultLocation? =
    this as? InlineQueryResultLocation

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultLocation(): InlineQueryResultLocation =
    this as InlineQueryResultLocation

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultStickerCached(block: (InlineQueryResultStickerCached) -> T) = asInlineQueryResultStickerCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultStickerCached(): InlineQueryResultStickerCached? =
    this as? InlineQueryResultStickerCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultStickerCached(): InlineQueryResultStickerCached =
    this as InlineQueryResultStickerCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVenue(block: (InlineQueryResultVenue) -> T) = asInlineQueryResultVenue() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVenue(): InlineQueryResultVenue? = this as? InlineQueryResultVenue

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVenue(): InlineQueryResultVenue = this as InlineQueryResultVenue

@PreviewFeature
inline fun <T> InlineQueryResult.whenDescribedInlineQueryResult(block: (DescribedInlineQueryResult) -> T) = asDescribedInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asDescribedInlineQueryResult(): DescribedInlineQueryResult? =
    this as? DescribedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireDescribedInlineQueryResult(): DescribedInlineQueryResult =
    this as DescribedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenFileInlineQueryResult(block: (FileInlineQueryResult) -> T) = asFileInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asFileInlineQueryResult(): FileInlineQueryResult? = this as? FileInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireFileInlineQueryResult(): FileInlineQueryResult = this as FileInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenOptionallyTitledInlineQueryResult(block: (OptionallyTitledInlineQueryResult) -> T) = asOptionallyTitledInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asOptionallyTitledInlineQueryResult(): OptionallyTitledInlineQueryResult? =
    this as? OptionallyTitledInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireOptionallyTitledInlineQueryResult(): OptionallyTitledInlineQueryResult =
    this as OptionallyTitledInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenSizedInlineQueryResult(block: (SizedInlineQueryResult) -> T) = asSizedInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asSizedInlineQueryResult(): SizedInlineQueryResult? = this as? SizedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireSizedInlineQueryResult(): SizedInlineQueryResult = this as SizedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenThumbSizedInlineQueryResult(block: (ThumbSizedInlineQueryResult) -> T) = asThumbSizedInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asThumbSizedInlineQueryResult(): ThumbSizedInlineQueryResult? =
    this as? ThumbSizedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireThumbSizedInlineQueryResult(): ThumbSizedInlineQueryResult =
    this as ThumbSizedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenThumbedInlineQueryResult(block: (ThumbedInlineQueryResult) -> T) = asThumbedInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asThumbedInlineQueryResult(): ThumbedInlineQueryResult? = this as? ThumbedInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireThumbedInlineQueryResult(): ThumbedInlineQueryResult =
    this as ThumbedInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenThumbedWithMimeTypeInlineQueryResult(block: (ThumbedWithMimeTypeInlineQueryResult) -> T) = asThumbedWithMimeTypeInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asThumbedWithMimeTypeInlineQueryResult(): ThumbedWithMimeTypeInlineQueryResult? =
    this as? ThumbedWithMimeTypeInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireThumbedWithMimeTypeInlineQueryResult(): ThumbedWithMimeTypeInlineQueryResult =
    this as ThumbedWithMimeTypeInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenTitledInlineQueryResult(block: (TitledInlineQueryResult) -> T) = asTitledInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asTitledInlineQueryResult(): TitledInlineQueryResult? = this as? TitledInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireTitledInlineQueryResult(): TitledInlineQueryResult = this as TitledInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenUrlInlineQueryResult(block: (UrlInlineQueryResult) -> T) = asUrlInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asUrlInlineQueryResult(): UrlInlineQueryResult? = this as? UrlInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireUrlInlineQueryResult(): UrlInlineQueryResult = this as UrlInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenWithInputMessageContentInlineQueryResult(block: (WithInputMessageContentInlineQueryResult) -> T) = asWithInputMessageContentInlineQueryResult() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asWithInputMessageContentInlineQueryResult(): WithInputMessageContentInlineQueryResult? =
    this as? WithInputMessageContentInlineQueryResult

@PreviewFeature
inline fun InlineQueryResult.requireWithInputMessageContentInlineQueryResult(): WithInputMessageContentInlineQueryResult =
    this as WithInputMessageContentInlineQueryResult

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultAudio(block: (InlineQueryResultAudio) -> T) = asInlineQueryResultAudio() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudio(): InlineQueryResultAudio? = this as? InlineQueryResultAudio

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudio(): InlineQueryResultAudio = this as InlineQueryResultAudio

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultAudioCached(block: (InlineQueryResultAudioCached) -> T) = asInlineQueryResultAudioCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudioCached(): InlineQueryResultAudioCached? =
    this as? InlineQueryResultAudioCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudioCached(): InlineQueryResultAudioCached =
    this as InlineQueryResultAudioCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultAudioCommon(block: (InlineQueryResultAudioCommon) -> T) = asInlineQueryResultAudioCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudioCommon(): InlineQueryResultAudioCommon? =
    this as? InlineQueryResultAudioCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudioCommon(): InlineQueryResultAudioCommon =
    this as InlineQueryResultAudioCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultDocument(block: (InlineQueryResultDocument) -> T) = asInlineQueryResultDocument() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocument(): InlineQueryResultDocument? =
    this as? InlineQueryResultDocument

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocument(): InlineQueryResultDocument =
    this as InlineQueryResultDocument

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultDocumentCached(block: (InlineQueryResultDocumentCached) -> T) = asInlineQueryResultDocumentCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocumentCached(): InlineQueryResultDocumentCached? =
    this as? InlineQueryResultDocumentCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocumentCached(): InlineQueryResultDocumentCached =
    this as InlineQueryResultDocumentCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultDocumentCommon(block: (InlineQueryResultDocumentCommon) -> T) = asInlineQueryResultDocumentCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocumentCommon(): InlineQueryResultDocumentCommon? =
    this as? InlineQueryResultDocumentCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocumentCommon(): InlineQueryResultDocumentCommon =
    this as InlineQueryResultDocumentCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultGif(block: (InlineQueryResultGif) -> T) = asInlineQueryResultGif() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGif(): InlineQueryResultGif? = this as? InlineQueryResultGif

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGif(): InlineQueryResultGif = this as InlineQueryResultGif

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultGifCached(block: (InlineQueryResultGifCached) -> T) = asInlineQueryResultGifCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGifCached(): InlineQueryResultGifCached? =
    this as? InlineQueryResultGifCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGifCached(): InlineQueryResultGifCached =
    this as InlineQueryResultGifCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultGifCommon(block: (InlineQueryResultGifCommon) -> T) = asInlineQueryResultGifCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGifCommon(): InlineQueryResultGifCommon? =
    this as? InlineQueryResultGifCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGifCommon(): InlineQueryResultGifCommon =
    this as InlineQueryResultGifCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultMpeg4Gif(block: (InlineQueryResultMpeg4Gif) -> T) = asInlineQueryResultMpeg4Gif() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4Gif(): InlineQueryResultMpeg4Gif? =
    this as? InlineQueryResultMpeg4Gif

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4Gif(): InlineQueryResultMpeg4Gif =
    this as InlineQueryResultMpeg4Gif

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultMpeg4GifCached(block: (InlineQueryResultMpeg4GifCached) -> T) = asInlineQueryResultMpeg4GifCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCached(): InlineQueryResultMpeg4GifCached? =
    this as? InlineQueryResultMpeg4GifCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4GifCached(): InlineQueryResultMpeg4GifCached =
    this as InlineQueryResultMpeg4GifCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultMpeg4GifCommon(block: (InlineQueryResultMpeg4GifCommon) -> T) = asInlineQueryResultMpeg4GifCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCommon(): InlineQueryResultMpeg4GifCommon? =
    this as? InlineQueryResultMpeg4GifCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4GifCommon(): InlineQueryResultMpeg4GifCommon =
    this as InlineQueryResultMpeg4GifCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultPhoto(block: (InlineQueryResultPhoto) -> T) = asInlineQueryResultPhoto() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhoto(): InlineQueryResultPhoto? = this as? InlineQueryResultPhoto

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhoto(): InlineQueryResultPhoto = this as InlineQueryResultPhoto

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultPhotoCached(block: (InlineQueryResultPhotoCached) -> T) = asInlineQueryResultPhotoCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhotoCached(): InlineQueryResultPhotoCached? =
    this as? InlineQueryResultPhotoCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhotoCached(): InlineQueryResultPhotoCached =
    this as InlineQueryResultPhotoCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultPhotoCommon(block: (InlineQueryResultPhotoCommon) -> T) = asInlineQueryResultPhotoCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhotoCommon(): InlineQueryResultPhotoCommon? =
    this as? InlineQueryResultPhotoCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhotoCommon(): InlineQueryResultPhotoCommon =
    this as InlineQueryResultPhotoCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVideo(block: (InlineQueryResultVideo) -> T) = asInlineQueryResultVideo() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideo(): InlineQueryResultVideo? = this as? InlineQueryResultVideo

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideo(): InlineQueryResultVideo = this as InlineQueryResultVideo

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVideoCached(block: (InlineQueryResultVideoCached) -> T) = asInlineQueryResultVideoCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideoCached(): InlineQueryResultVideoCached? =
    this as? InlineQueryResultVideoCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideoCached(): InlineQueryResultVideoCached =
    this as InlineQueryResultVideoCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVideoCommon(block: (InlineQueryResultVideoCommon) -> T) = asInlineQueryResultVideoCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideoCommon(): InlineQueryResultVideoCommon? =
    this as? InlineQueryResultVideoCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideoCommon(): InlineQueryResultVideoCommon =
    this as InlineQueryResultVideoCommon

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVoice(block: (InlineQueryResultVoice) -> T) = asInlineQueryResultVoice() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoice(): InlineQueryResultVoice? = this as? InlineQueryResultVoice

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoice(): InlineQueryResultVoice = this as InlineQueryResultVoice

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVoiceCached(block: (InlineQueryResultVoiceCached) -> T) = asInlineQueryResultVoiceCached() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoiceCached(): InlineQueryResultVoiceCached? =
    this as? InlineQueryResultVoiceCached

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoiceCached(): InlineQueryResultVoiceCached =
    this as InlineQueryResultVoiceCached

@PreviewFeature
inline fun <T> InlineQueryResult.whenInlineQueryResultVoiceCommon(block: (InlineQueryResultVoiceCommon) -> T) = asInlineQueryResultVoiceCommon() ?.let(block)

@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoiceCommon(): InlineQueryResultVoiceCommon? =
    this as? InlineQueryResultVoiceCommon

@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoiceCommon(): InlineQueryResultVoiceCommon =
    this as InlineQueryResultVoiceCommon

@PreviewFeature
inline fun <T> ChatMember.whenCreatorChatMember(block: (CreatorChatMember) -> T) = asCreatorChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asCreatorChatMember(): CreatorChatMember? = this as? CreatorChatMember

@PreviewFeature
inline fun ChatMember.requireCreatorChatMember(): CreatorChatMember = this as CreatorChatMember

@PreviewFeature
inline fun <T> ChatMember.whenKickedChatMember(block: (KickedChatMember) -> T) = asKickedChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asKickedChatMember(): KickedChatMember? = this as? KickedChatMember

@PreviewFeature
inline fun ChatMember.requireKickedChatMember(): KickedChatMember = this as KickedChatMember

@PreviewFeature
inline fun <T> ChatMember.whenLeftChatMember(block: (LeftChatMember) -> T) = asLeftChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asLeftChatMember(): LeftChatMember? = this as? LeftChatMember

@PreviewFeature
inline fun ChatMember.requireLeftChatMember(): LeftChatMember = this as LeftChatMember

@PreviewFeature
inline fun <T> ChatMember.whenMemberChatMember(block: (MemberChatMember) -> T) = asMemberChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asMemberChatMember(): MemberChatMember? = this as? MemberChatMember

@PreviewFeature
inline fun ChatMember.requireMemberChatMember(): MemberChatMember = this as MemberChatMember

@PreviewFeature
inline fun <T> ChatMember.whenRestrictedChatMember(block: (RestrictedChatMember) -> T) = asRestrictedChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asRestrictedChatMember(): RestrictedChatMember? = this as? RestrictedChatMember

@PreviewFeature
inline fun ChatMember.requireRestrictedChatMember(): RestrictedChatMember = this as RestrictedChatMember

@PreviewFeature
inline fun <T> ChatMember.whenAdministratorChatMember(block: (AdministratorChatMember) -> T) = asAdministratorChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asAdministratorChatMember(): AdministratorChatMember? = this as? AdministratorChatMember

@PreviewFeature
inline fun ChatMember.requireAdministratorChatMember(): AdministratorChatMember = this as AdministratorChatMember

@PreviewFeature
inline fun <T> ChatMember.whenBannedChatMember(block: (BannedChatMember) -> T) = asBannedChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asBannedChatMember(): BannedChatMember? = this as? BannedChatMember

@PreviewFeature
inline fun ChatMember.requireBannedChatMember(): BannedChatMember = this as BannedChatMember

@PreviewFeature
inline fun <T> ChatMember.whenSpecialRightsChatMember(block: (SpecialRightsChatMember) -> T) = asSpecialRightsChatMember() ?.let(block)

@PreviewFeature
inline fun ChatMember.asSpecialRightsChatMember(): SpecialRightsChatMember? = this as? SpecialRightsChatMember

@PreviewFeature
inline fun ChatMember.requireSpecialRightsChatMember(): SpecialRightsChatMember = this as SpecialRightsChatMember

@PreviewFeature
inline fun <T> InputMedia.whenAudioMediaGroupMemberInputMedia(block: (AudioMediaGroupMemberInputMedia) -> T) = asAudioMediaGroupMemberInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asAudioMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia? =
    this as? AudioMediaGroupMemberInputMedia

@PreviewFeature
inline fun InputMedia.requireAudioMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia =
    this as AudioMediaGroupMemberInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenDocumentMediaGroupMemberInputMedia(block: (DocumentMediaGroupMemberInputMedia) -> T) = asDocumentMediaGroupMemberInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asDocumentMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia? =
    this as? DocumentMediaGroupMemberInputMedia

@PreviewFeature
inline fun InputMedia.requireDocumentMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia =
    this as DocumentMediaGroupMemberInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenDuratedInputMedia(block: (DuratedInputMedia) -> T) = asDuratedInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asDuratedInputMedia(): DuratedInputMedia? = this as? DuratedInputMedia

@PreviewFeature
inline fun InputMedia.requireDuratedInputMedia(): DuratedInputMedia = this as DuratedInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenInputMediaAnimation(block: (InputMediaAnimation) -> T) = asInputMediaAnimation() ?.let(block)

@PreviewFeature
inline fun InputMedia.asInputMediaAnimation(): InputMediaAnimation? = this as? InputMediaAnimation

@PreviewFeature
inline fun InputMedia.requireInputMediaAnimation(): InputMediaAnimation = this as InputMediaAnimation

@PreviewFeature
inline fun <T> InputMedia.whenInputMediaAudio(block: (InputMediaAudio) -> T) = asInputMediaAudio() ?.let(block)

@PreviewFeature
inline fun InputMedia.asInputMediaAudio(): InputMediaAudio? = this as? InputMediaAudio

@PreviewFeature
inline fun InputMedia.requireInputMediaAudio(): InputMediaAudio = this as InputMediaAudio

@PreviewFeature
inline fun <T> InputMedia.whenInputMediaDocument(block: (InputMediaDocument) -> T) = asInputMediaDocument() ?.let(block)

@PreviewFeature
inline fun InputMedia.asInputMediaDocument(): InputMediaDocument? = this as? InputMediaDocument

@PreviewFeature
inline fun InputMedia.requireInputMediaDocument(): InputMediaDocument = this as InputMediaDocument

@PreviewFeature
inline fun <T> InputMedia.whenInputMediaPhoto(block: (InputMediaPhoto) -> T) = asInputMediaPhoto() ?.let(block)

@PreviewFeature
inline fun InputMedia.asInputMediaPhoto(): InputMediaPhoto? = this as? InputMediaPhoto

@PreviewFeature
inline fun InputMedia.requireInputMediaPhoto(): InputMediaPhoto = this as InputMediaPhoto

@PreviewFeature
inline fun <T> InputMedia.whenInputMediaVideo(block: (InputMediaVideo) -> T) = asInputMediaVideo() ?.let(block)

@PreviewFeature
inline fun InputMedia.asInputMediaVideo(): InputMediaVideo? = this as? InputMediaVideo

@PreviewFeature
inline fun InputMedia.requireInputMediaVideo(): InputMediaVideo = this as InputMediaVideo

@PreviewFeature
inline fun <T> InputMedia.whenMediaGroupMemberInputMedia(block: (MediaGroupMemberInputMedia) -> T) = asMediaGroupMemberInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia? = this as? MediaGroupMemberInputMedia

@PreviewFeature
inline fun InputMedia.requireMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia =
    this as MediaGroupMemberInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenSizedInputMedia(block: (SizedInputMedia) -> T) = asSizedInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asSizedInputMedia(): SizedInputMedia? = this as? SizedInputMedia

@PreviewFeature
inline fun InputMedia.requireSizedInputMedia(): SizedInputMedia = this as SizedInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenThumbedInputMedia(block: (ThumbedInputMedia) -> T) = asThumbedInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asThumbedInputMedia(): ThumbedInputMedia? = this as? ThumbedInputMedia

@PreviewFeature
inline fun InputMedia.requireThumbedInputMedia(): ThumbedInputMedia = this as ThumbedInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenTitledInputMedia(block: (TitledInputMedia) -> T) = asTitledInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asTitledInputMedia(): TitledInputMedia? = this as? TitledInputMedia

@PreviewFeature
inline fun InputMedia.requireTitledInputMedia(): TitledInputMedia = this as TitledInputMedia

@PreviewFeature
inline fun <T> InputMedia.whenVisualMediaGroupMemberInputMedia(block: (VisualMediaGroupMemberInputMedia) -> T) = asVisualMediaGroupMemberInputMedia() ?.let(block)

@PreviewFeature
inline fun InputMedia.asVisualMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia? =
    this as? VisualMediaGroupMemberInputMedia

@PreviewFeature
inline fun InputMedia.requireVisualMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia =
    this as VisualMediaGroupMemberInputMedia

@PreviewFeature
inline fun <T> Update.whenCallbackQueryUpdate(block: (CallbackQueryUpdate) -> T) = asCallbackQueryUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asCallbackQueryUpdate(): CallbackQueryUpdate? = this as? CallbackQueryUpdate

@PreviewFeature
inline fun Update.requireCallbackQueryUpdate(): CallbackQueryUpdate = this as CallbackQueryUpdate

@PreviewFeature
inline fun <T> Update.whenChannelPostUpdate(block: (ChannelPostUpdate) -> T) = asChannelPostUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asChannelPostUpdate(): ChannelPostUpdate? = this as? ChannelPostUpdate

@PreviewFeature
inline fun Update.requireChannelPostUpdate(): ChannelPostUpdate = this as ChannelPostUpdate

@PreviewFeature
inline fun <T> Update.whenChosenInlineResultUpdate(block: (ChosenInlineResultUpdate) -> T) = asChosenInlineResultUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asChosenInlineResultUpdate(): ChosenInlineResultUpdate? = this as? ChosenInlineResultUpdate

@PreviewFeature
inline fun Update.requireChosenInlineResultUpdate(): ChosenInlineResultUpdate = this as ChosenInlineResultUpdate

@PreviewFeature
inline fun <T> Update.whenEditChannelPostUpdate(block: (EditChannelPostUpdate) -> T) = asEditChannelPostUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asEditChannelPostUpdate(): EditChannelPostUpdate? = this as? EditChannelPostUpdate

@PreviewFeature
inline fun Update.requireEditChannelPostUpdate(): EditChannelPostUpdate = this as EditChannelPostUpdate

@PreviewFeature
inline fun <T> Update.whenEditMessageUpdate(block: (EditMessageUpdate) -> T) = asEditMessageUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asEditMessageUpdate(): EditMessageUpdate? = this as? EditMessageUpdate

@PreviewFeature
inline fun Update.requireEditMessageUpdate(): EditMessageUpdate = this as EditMessageUpdate

@PreviewFeature
inline fun <T> Update.whenInlineQueryUpdate(block: (InlineQueryUpdate) -> T) = asInlineQueryUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asInlineQueryUpdate(): InlineQueryUpdate? = this as? InlineQueryUpdate

@PreviewFeature
inline fun Update.requireInlineQueryUpdate(): InlineQueryUpdate = this as InlineQueryUpdate

@PreviewFeature
inline fun <T> Update.whenChannelPostMediaGroupUpdate(block: (ChannelPostMediaGroupUpdate) -> T) = asChannelPostMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asChannelPostMediaGroupUpdate(): ChannelPostMediaGroupUpdate? = this as? ChannelPostMediaGroupUpdate

@PreviewFeature
inline fun Update.requireChannelPostMediaGroupUpdate(): ChannelPostMediaGroupUpdate =
    this as ChannelPostMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenEditChannelPostMediaGroupUpdate(block: (EditChannelPostMediaGroupUpdate) -> T) = asEditChannelPostMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asEditChannelPostMediaGroupUpdate(): EditChannelPostMediaGroupUpdate? =
    this as? EditChannelPostMediaGroupUpdate

@PreviewFeature
inline fun Update.requireEditChannelPostMediaGroupUpdate(): EditChannelPostMediaGroupUpdate =
    this as EditChannelPostMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenEditMediaGroupUpdate(block: (EditMediaGroupUpdate) -> T) = asEditMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asEditMediaGroupUpdate(): EditMediaGroupUpdate? = this as? EditMediaGroupUpdate

@PreviewFeature
inline fun Update.requireEditMediaGroupUpdate(): EditMediaGroupUpdate = this as EditMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenEditMessageMediaGroupUpdate(block: (EditMessageMediaGroupUpdate) -> T) = asEditMessageMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asEditMessageMediaGroupUpdate(): EditMessageMediaGroupUpdate? = this as? EditMessageMediaGroupUpdate

@PreviewFeature
inline fun Update.requireEditMessageMediaGroupUpdate(): EditMessageMediaGroupUpdate =
    this as EditMessageMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenMediaGroupUpdate(block: (MediaGroupUpdate) -> T) = asMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asMediaGroupUpdate(): MediaGroupUpdate? = this as? MediaGroupUpdate

@PreviewFeature
inline fun Update.requireMediaGroupUpdate(): MediaGroupUpdate = this as MediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenMessageMediaGroupUpdate(block: (MessageMediaGroupUpdate) -> T) = asMessageMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asMessageMediaGroupUpdate(): MessageMediaGroupUpdate? = this as? MessageMediaGroupUpdate

@PreviewFeature
inline fun Update.requireMessageMediaGroupUpdate(): MessageMediaGroupUpdate = this as MessageMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenSentMediaGroupUpdate(block: (SentMediaGroupUpdate) -> T) = asSentMediaGroupUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asSentMediaGroupUpdate(): SentMediaGroupUpdate? = this as? SentMediaGroupUpdate

@PreviewFeature
inline fun Update.requireSentMediaGroupUpdate(): SentMediaGroupUpdate = this as SentMediaGroupUpdate

@PreviewFeature
inline fun <T> Update.whenMessageUpdate(block: (MessageUpdate) -> T) = asMessageUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asMessageUpdate(): MessageUpdate? = this as? MessageUpdate

@PreviewFeature
inline fun Update.requireMessageUpdate(): MessageUpdate = this as MessageUpdate

@PreviewFeature
inline fun <T> Update.whenPollAnswerUpdate(block: (PollAnswerUpdate) -> T) = asPollAnswerUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asPollAnswerUpdate(): PollAnswerUpdate? = this as? PollAnswerUpdate

@PreviewFeature
inline fun Update.requirePollAnswerUpdate(): PollAnswerUpdate = this as PollAnswerUpdate

@PreviewFeature
inline fun <T> Update.whenPollUpdate(block: (PollUpdate) -> T) = asPollUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asPollUpdate(): PollUpdate? = this as? PollUpdate

@PreviewFeature
inline fun Update.requirePollUpdate(): PollUpdate = this as PollUpdate

@PreviewFeature
inline fun <T> Update.whenPreCheckoutQueryUpdate(block: (PreCheckoutQueryUpdate) -> T) = asPreCheckoutQueryUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asPreCheckoutQueryUpdate(): PreCheckoutQueryUpdate? = this as? PreCheckoutQueryUpdate

@PreviewFeature
inline fun Update.requirePreCheckoutQueryUpdate(): PreCheckoutQueryUpdate = this as PreCheckoutQueryUpdate

@PreviewFeature
inline fun <T> Update.whenShippingQueryUpdate(block: (ShippingQueryUpdate) -> T) = asShippingQueryUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asShippingQueryUpdate(): ShippingQueryUpdate? = this as? ShippingQueryUpdate

@PreviewFeature
inline fun Update.requireShippingQueryUpdate(): ShippingQueryUpdate = this as ShippingQueryUpdate

@PreviewFeature
inline fun <T> Update.whenBaseEditMessageUpdate(block: (BaseEditMessageUpdate) -> T) = asBaseEditMessageUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asBaseEditMessageUpdate(): BaseEditMessageUpdate? = this as? BaseEditMessageUpdate

@PreviewFeature
inline fun Update.requireBaseEditMessageUpdate(): BaseEditMessageUpdate = this as BaseEditMessageUpdate

@PreviewFeature
inline fun <T> Update.whenBaseMessageUpdate(block: (BaseMessageUpdate) -> T) = asBaseMessageUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asBaseMessageUpdate(): BaseMessageUpdate? = this as? BaseMessageUpdate

@PreviewFeature
inline fun Update.requireBaseMessageUpdate(): BaseMessageUpdate = this as BaseMessageUpdate

@PreviewFeature
inline fun <T> Update.whenBaseSentMessageUpdate(block: (BaseSentMessageUpdate) -> T) = asBaseSentMessageUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asBaseSentMessageUpdate(): BaseSentMessageUpdate? = this as? BaseSentMessageUpdate

@PreviewFeature
inline fun Update.requireBaseSentMessageUpdate(): BaseSentMessageUpdate = this as BaseSentMessageUpdate

@PreviewFeature
inline fun <T> Update.whenUnknownUpdate(block: (UnknownUpdate) -> T) = asUnknownUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asUnknownUpdate(): UnknownUpdate? = this as? UnknownUpdate

@PreviewFeature
inline fun Update.requireUnknownUpdate(): UnknownUpdate = this as UnknownUpdate

@PreviewFeature
inline fun <T> Update.whenCommonChatMemberUpdatedUpdate(block: (CommonChatMemberUpdatedUpdate) -> T) = asCommonChatMemberUpdatedUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asCommonChatMemberUpdatedUpdate(): CommonChatMemberUpdatedUpdate? =
    this as? CommonChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.requireCommonChatMemberUpdatedUpdate(): CommonChatMemberUpdatedUpdate =
    this as CommonChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.whenMyChatMemberUpdatedUpdate(block: (MyChatMemberUpdatedUpdate) -> T) = asMyChatMemberUpdatedUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asMyChatMemberUpdatedUpdate(): MyChatMemberUpdatedUpdate? = this as? MyChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.requireMyChatMemberUpdatedUpdate(): MyChatMemberUpdatedUpdate = this as MyChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.whenChatMemberUpdatedUpdate(block: (ChatMemberUpdatedUpdate) -> T) = asChatMemberUpdatedUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asChatMemberUpdatedUpdate(): ChatMemberUpdatedUpdate? = this as? ChatMemberUpdatedUpdate

@PreviewFeature
inline fun Update.requireChatMemberUpdatedUpdate(): ChatMemberUpdatedUpdate = this as ChatMemberUpdatedUpdate

@PreviewFeature
inline fun <T> Update.whenChatJoinRequestUpdate(block: (ChatJoinRequestUpdate) -> T) = asChatJoinRequestUpdate() ?.let(block)

@PreviewFeature
inline fun Update.asChatJoinRequestUpdate(): ChatJoinRequestUpdate? = this as? ChatJoinRequestUpdate

@PreviewFeature
inline fun Update.requireChatJoinRequestUpdate(): ChatJoinRequestUpdate = this as ChatJoinRequestUpdate

@PreviewFeature
inline fun <T> TelegramMediaFile.whenAnimationFile(block: (AnimationFile) -> T) = asAnimationFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asAnimationFile(): AnimationFile? = this as? AnimationFile

@PreviewFeature
inline fun TelegramMediaFile.requireAnimationFile(): AnimationFile = this as AnimationFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenAudioFile(block: (AudioFile) -> T) = asAudioFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asAudioFile(): AudioFile? = this as? AudioFile

@PreviewFeature
inline fun TelegramMediaFile.requireAudioFile(): AudioFile = this as AudioFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenDocumentFile(block: (DocumentFile) -> T) = asDocumentFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asDocumentFile(): DocumentFile? = this as? DocumentFile

@PreviewFeature
inline fun TelegramMediaFile.requireDocumentFile(): DocumentFile = this as DocumentFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenFile(block: (File) -> T) = asFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asFile(): File? = this as? File

@PreviewFeature
inline fun TelegramMediaFile.requireFile(): File = this as File

@PreviewFeature
inline fun <T> TelegramMediaFile.whenPathedFile(block: (PathedFile) -> T) = asPathedFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asPathedFile(): PathedFile? = this as? PathedFile

@PreviewFeature
inline fun TelegramMediaFile.requirePathedFile(): PathedFile = this as PathedFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenPhotoSize(block: (PhotoSize) -> T) = asPhotoSize() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asPhotoSize(): PhotoSize? = this as? PhotoSize

@PreviewFeature
inline fun TelegramMediaFile.requirePhotoSize(): PhotoSize = this as PhotoSize

@PreviewFeature
inline fun <T> TelegramMediaFile.whenSticker(block: (Sticker) -> T) = asSticker() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asSticker(): Sticker? = this as? Sticker

@PreviewFeature
inline fun TelegramMediaFile.requireSticker(): Sticker = this as Sticker

@PreviewFeature
inline fun <T> TelegramMediaFile.whenSimpleSticker(block: (SimpleSticker) -> T) = asSimpleSticker() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asSimpleSticker(): SimpleSticker? = this as? SimpleSticker

@PreviewFeature
inline fun TelegramMediaFile.requireSimpleSticker(): SimpleSticker = this as SimpleSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.whenAnimatedSticker(block: (AnimatedSticker) -> T) = asAnimatedSticker() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asAnimatedSticker(): AnimatedSticker? = this as? AnimatedSticker

@PreviewFeature
inline fun TelegramMediaFile.requireAnimatedSticker(): AnimatedSticker = this as AnimatedSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.whenVideoSticker(block: (VideoSticker) -> T) = asVideoSticker() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asVideoSticker(): VideoSticker? = this as? VideoSticker

@PreviewFeature
inline fun TelegramMediaFile.requireVideoSticker(): VideoSticker = this as VideoSticker

@PreviewFeature
inline fun <T> TelegramMediaFile.whenVideoFile(block: (VideoFile) -> T) = asVideoFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asVideoFile(): VideoFile? = this as? VideoFile

@PreviewFeature
inline fun TelegramMediaFile.requireVideoFile(): VideoFile = this as VideoFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenVideoNoteFile(block: (VideoNoteFile) -> T) = asVideoNoteFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asVideoNoteFile(): VideoNoteFile? = this as? VideoNoteFile

@PreviewFeature
inline fun TelegramMediaFile.requireVideoNoteFile(): VideoNoteFile = this as VideoNoteFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenVoiceFile(block: (VoiceFile) -> T) = asVoiceFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asVoiceFile(): VoiceFile? = this as? VoiceFile

@PreviewFeature
inline fun TelegramMediaFile.requireVoiceFile(): VoiceFile = this as VoiceFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenMimedMediaFile(block: (MimedMediaFile) -> T) = asMimedMediaFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asMimedMediaFile(): MimedMediaFile? = this as? MimedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.requireMimedMediaFile(): MimedMediaFile = this as MimedMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenPlayableMediaFile(block: (PlayableMediaFile) -> T) = asPlayableMediaFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asPlayableMediaFile(): PlayableMediaFile? = this as? PlayableMediaFile

@PreviewFeature
inline fun TelegramMediaFile.requirePlayableMediaFile(): PlayableMediaFile = this as PlayableMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenSizedMediaFile(block: (SizedMediaFile) -> T) = asSizedMediaFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asSizedMediaFile(): SizedMediaFile? = this as? SizedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.requireSizedMediaFile(): SizedMediaFile = this as SizedMediaFile

@PreviewFeature
inline fun <T> TelegramMediaFile.whenThumbedMediaFile(block: (ThumbedMediaFile) -> T) = asThumbedMediaFile() ?.let(block)

@PreviewFeature
inline fun TelegramMediaFile.asThumbedMediaFile(): ThumbedMediaFile? = this as? ThumbedMediaFile

@PreviewFeature
inline fun TelegramMediaFile.requireThumbedMediaFile(): ThumbedMediaFile = this as ThumbedMediaFile

@PreviewFeature
inline fun <T> KeyboardMarkup.whenForceReply(block: (ReplyForce) -> T) = asForceReply() ?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.asForceReply(): ReplyForce? = this as? ReplyForce

@PreviewFeature
inline fun KeyboardMarkup.requireForceReply(): ReplyForce = this as ReplyForce

@PreviewFeature
inline fun <T> KeyboardMarkup.whenInlineKeyboardMarkup(block: (InlineKeyboardMarkup) -> T) = asInlineKeyboardMarkup() ?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.asInlineKeyboardMarkup(): InlineKeyboardMarkup? = this as? InlineKeyboardMarkup

@PreviewFeature
inline fun KeyboardMarkup.requireInlineKeyboardMarkup(): InlineKeyboardMarkup = this as InlineKeyboardMarkup

@PreviewFeature
inline fun <T> KeyboardMarkup.whenReplyKeyboardMarkup(block: (ReplyKeyboardMarkup) -> T) = asReplyKeyboardMarkup() ?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.asReplyKeyboardMarkup(): ReplyKeyboardMarkup? = this as? ReplyKeyboardMarkup

@PreviewFeature
inline fun KeyboardMarkup.requireReplyKeyboardMarkup(): ReplyKeyboardMarkup = this as ReplyKeyboardMarkup

@PreviewFeature
inline fun <T> KeyboardMarkup.whenReplyKeyboardRemove(block: (ReplyKeyboardRemove) -> T) = asReplyKeyboardRemove() ?.let(block)

@PreviewFeature
inline fun KeyboardMarkup.asReplyKeyboardRemove(): ReplyKeyboardRemove? = this as? ReplyKeyboardRemove

@PreviewFeature
inline fun KeyboardMarkup.requireReplyKeyboardRemove(): ReplyKeyboardRemove = this as ReplyKeyboardRemove

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenCallbackDataInlineKeyboardButton(block: (CallbackDataInlineKeyboardButton) -> T) = asCallbackDataInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asCallbackDataInlineKeyboardButton(): CallbackDataInlineKeyboardButton? =
    this as? CallbackDataInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireCallbackDataInlineKeyboardButton(): CallbackDataInlineKeyboardButton =
    this as CallbackDataInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenCallbackGameInlineKeyboardButton(block: (CallbackGameInlineKeyboardButton) -> T) = asCallbackGameInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asCallbackGameInlineKeyboardButton(): CallbackGameInlineKeyboardButton? =
    this as? CallbackGameInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireCallbackGameInlineKeyboardButton(): CallbackGameInlineKeyboardButton =
    this as CallbackGameInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenLoginURLInlineKeyboardButton(block: (LoginURLInlineKeyboardButton) -> T) = asLoginURLInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asLoginURLInlineKeyboardButton(): LoginURLInlineKeyboardButton? =
    this as? LoginURLInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireLoginURLInlineKeyboardButton(): LoginURLInlineKeyboardButton =
    this as LoginURLInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenPayInlineKeyboardButton(block: (PayInlineKeyboardButton) -> T) = asPayInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asPayInlineKeyboardButton(): PayInlineKeyboardButton? = this as? PayInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requirePayInlineKeyboardButton(): PayInlineKeyboardButton =
    this as PayInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenSwitchInlineQueryCurrentChatInlineKeyboardButton(block: (SwitchInlineQueryCurrentChatInlineKeyboardButton) -> T) = asSwitchInlineQueryCurrentChatInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asSwitchInlineQueryCurrentChatInlineKeyboardButton(): SwitchInlineQueryCurrentChatInlineKeyboardButton? =
    this as? SwitchInlineQueryCurrentChatInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireSwitchInlineQueryCurrentChatInlineKeyboardButton(): SwitchInlineQueryCurrentChatInlineKeyboardButton =
    this as SwitchInlineQueryCurrentChatInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenSwitchInlineQueryInlineKeyboardButton(block: (SwitchInlineQueryInlineKeyboardButton) -> T) = asSwitchInlineQueryInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asSwitchInlineQueryInlineKeyboardButton(): SwitchInlineQueryInlineKeyboardButton? =
    this as? SwitchInlineQueryInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireSwitchInlineQueryInlineKeyboardButton(): SwitchInlineQueryInlineKeyboardButton =
    this as SwitchInlineQueryInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenURLInlineKeyboardButton(block: (URLInlineKeyboardButton) -> T) = asURLInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asURLInlineKeyboardButton(): URLInlineKeyboardButton? = this as? URLInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireURLInlineKeyboardButton(): URLInlineKeyboardButton =
    this as URLInlineKeyboardButton

@PreviewFeature
inline fun <T> InlineKeyboardButton.whenUnknownInlineKeyboardButton(block: (UnknownInlineKeyboardButton) -> T) = asUnknownInlineKeyboardButton() ?.let(block)

@PreviewFeature
inline fun InlineKeyboardButton.asUnknownInlineKeyboardButton(): UnknownInlineKeyboardButton? =
    this as? UnknownInlineKeyboardButton

@PreviewFeature
inline fun InlineKeyboardButton.requireUnknownInlineKeyboardButton(): UnknownInlineKeyboardButton =
    this as UnknownInlineKeyboardButton

@PreviewFeature
inline fun <T> Poll.whenMultipleAnswersPoll(block: (MultipleAnswersPoll) -> T) = asMultipleAnswersPoll() ?.let(block)

@PreviewFeature
inline fun Poll.asMultipleAnswersPoll(): MultipleAnswersPoll? = this as? MultipleAnswersPoll

@PreviewFeature
inline fun Poll.requireMultipleAnswersPoll(): MultipleAnswersPoll = this as MultipleAnswersPoll

@PreviewFeature
inline fun <T> Poll.whenQuizPoll(block: (QuizPoll) -> T) = asQuizPoll() ?.let(block)

@PreviewFeature
inline fun Poll.asQuizPoll(): QuizPoll? = this as? QuizPoll

@PreviewFeature
inline fun Poll.requireQuizPoll(): QuizPoll = this as QuizPoll

@PreviewFeature
inline fun <T> Poll.whenRegularPoll(block: (RegularPoll) -> T) = asRegularPoll() ?.let(block)

@PreviewFeature
inline fun Poll.asRegularPoll(): RegularPoll? = this as? RegularPoll

@PreviewFeature
inline fun Poll.requireRegularPoll(): RegularPoll = this as RegularPoll

@PreviewFeature
inline fun <T> Poll.whenUnknownPollType(block: (UnknownPollType) -> T) = asUnknownPollType() ?.let(block)

@PreviewFeature
inline fun Poll.asUnknownPollType(): UnknownPollType? = this as? UnknownPollType

@PreviewFeature
inline fun Poll.requireUnknownPollType(): UnknownPollType = this as UnknownPollType

@PreviewFeature
inline fun <T> ResendableContent.whenContactContent(block: (ContactContent) -> T) = asContactContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asContactContent(): ContactContent? = this as? ContactContent

@PreviewFeature
inline fun ResendableContent.requireContactContent(): ContactContent = this as ContactContent

@PreviewFeature
inline fun <T> ResendableContent.whenDiceContent(block: (DiceContent) -> T) = asDiceContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asDiceContent(): DiceContent? = this as? DiceContent

@PreviewFeature
inline fun ResendableContent.requireDiceContent(): DiceContent = this as DiceContent

@PreviewFeature
inline fun <T> ResendableContent.whenGameContent(block: (GameContent) -> T) = asGameContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asGameContent(): GameContent? = this as? GameContent

@PreviewFeature
inline fun ResendableContent.requireGameContent(): GameContent = this as GameContent

@PreviewFeature
inline fun <T> ResendableContent.whenLocationContent(block: (LocationContent) -> T) = asLocationContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asLocationContent(): LocationContent? = this as? LocationContent

@PreviewFeature
inline fun ResendableContent.requireLocationContent(): LocationContent = this as LocationContent

@PreviewFeature
inline fun <T> ResendableContent.whenLiveLocationContent(block: (LiveLocationContent) -> T) = asLiveLocationContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asLiveLocationContent(): LiveLocationContent? = this as? LiveLocationContent

@PreviewFeature
inline fun ResendableContent.requireLiveLocationContent(): LiveLocationContent = this as LiveLocationContent

@PreviewFeature
inline fun <T> ResendableContent.whenStaticLocationContent(block: (StaticLocationContent) -> T) = asStaticLocationContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asStaticLocationContent(): StaticLocationContent? = this as? StaticLocationContent

@PreviewFeature
inline fun ResendableContent.requireStaticLocationContent(): StaticLocationContent = this as StaticLocationContent

@PreviewFeature
inline fun <T> ResendableContent.whenPollContent(block: (PollContent) -> T) = asPollContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asPollContent(): PollContent? = this as? PollContent

@PreviewFeature
inline fun ResendableContent.requirePollContent(): PollContent = this as PollContent

@PreviewFeature
inline fun <T> ResendableContent.whenTextContent(block: (TextContent) -> T) = asTextContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asTextContent(): TextContent? = this as? TextContent

@PreviewFeature
inline fun ResendableContent.requireTextContent(): TextContent = this as TextContent

@PreviewFeature
inline fun <T> ResendableContent.whenVenueContent(block: (VenueContent) -> T) = asVenueContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asVenueContent(): VenueContent? = this as? VenueContent

@PreviewFeature
inline fun ResendableContent.requireVenueContent(): VenueContent = this as VenueContent

@PreviewFeature
inline fun <T> ResendableContent.whenAudioMediaGroupContent(block: (AudioMediaGroupContent) -> T) = asAudioMediaGroupContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asAudioMediaGroupContent(): AudioMediaGroupContent? = this as? AudioMediaGroupContent

@PreviewFeature
inline fun ResendableContent.requireAudioMediaGroupContent(): AudioMediaGroupContent = this as AudioMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.whenDocumentMediaGroupContent(block: (DocumentMediaGroupContent) -> T) = asDocumentMediaGroupContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asDocumentMediaGroupContent(): DocumentMediaGroupContent? =
    this as? DocumentMediaGroupContent

@PreviewFeature
inline fun ResendableContent.requireDocumentMediaGroupContent(): DocumentMediaGroupContent =
    this as DocumentMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.whenMediaCollectionContent(block: (MediaCollectionContent<TelegramMediaFile>) -> T) = asMediaCollectionContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asMediaCollectionContent(): MediaCollectionContent<TelegramMediaFile>? =
    this as? MediaCollectionContent<TelegramMediaFile>

@PreviewFeature
inline fun ResendableContent.requireMediaCollectionContent(): MediaCollectionContent<TelegramMediaFile> =
    this as MediaCollectionContent<TelegramMediaFile>

@PreviewFeature
inline fun <T> ResendableContent.whenTextedMediaContent(block: (TextedMediaContent) -> T) = asTextedMediaContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asTextedMediaContent(): TextedMediaContent? =
    this as? TextedMediaContent

@PreviewFeature
inline fun ResendableContent.requireTextedMediaContent(): TextedMediaContent =
    this as TextedMediaContent

@PreviewFeature
inline fun <T> ResendableContent.whenMediaContent(block: (MediaContent) -> T) = asMediaContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asMediaContent(): MediaContent? = this as? MediaContent

@PreviewFeature
inline fun ResendableContent.requireMediaContent(): MediaContent = this as MediaContent

@PreviewFeature
inline fun <T> ResendableContent.whenMediaGroupContent(block: (MediaGroupContent) -> T) = asMediaGroupContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asMediaGroupContent(): MediaGroupContent? = this as? MediaGroupContent

@PreviewFeature
inline fun ResendableContent.requireMediaGroupContent(): MediaGroupContent = this as MediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.whenMessageContent(block: (MessageContent) -> T) = asMessageContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asMessageContent(): MessageContent? = this as? MessageContent

@PreviewFeature
inline fun ResendableContent.requireMessageContent(): MessageContent = this as MessageContent

@PreviewFeature
inline fun <T> ResendableContent.whenVisualMediaGroupContent(block: (VisualMediaGroupContent) -> T) = asVisualMediaGroupContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asVisualMediaGroupContent(): VisualMediaGroupContent? = this as? VisualMediaGroupContent

@PreviewFeature
inline fun ResendableContent.requireVisualMediaGroupContent(): VisualMediaGroupContent = this as VisualMediaGroupContent

@PreviewFeature
inline fun <T> ResendableContent.whenAnimationContent(block: (AnimationContent) -> T) = asAnimationContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asAnimationContent(): AnimationContent? = this as? AnimationContent

@PreviewFeature
inline fun ResendableContent.requireAnimationContent(): AnimationContent = this as AnimationContent

@PreviewFeature
inline fun <T> ResendableContent.whenAudioContent(block: (AudioContent) -> T) = asAudioContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asAudioContent(): AudioContent? = this as? AudioContent

@PreviewFeature
inline fun ResendableContent.requireAudioContent(): AudioContent = this as AudioContent

@PreviewFeature
inline fun <T> ResendableContent.whenDocumentContent(block: (DocumentContent) -> T) = asDocumentContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asDocumentContent(): DocumentContent? = this as? DocumentContent

@PreviewFeature
inline fun ResendableContent.requireDocumentContent(): DocumentContent = this as DocumentContent

@PreviewFeature
inline fun <T> ResendableContent.whenPhotoContent(block: (PhotoContent) -> T) = asPhotoContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asPhotoContent(): PhotoContent? = this as? PhotoContent

@PreviewFeature
inline fun ResendableContent.requirePhotoContent(): PhotoContent = this as PhotoContent

@PreviewFeature
inline fun <T> ResendableContent.whenStickerContent(block: (StickerContent) -> T) = asStickerContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asStickerContent(): StickerContent? = this as? StickerContent

@PreviewFeature
inline fun ResendableContent.requireStickerContent(): StickerContent = this as StickerContent

@PreviewFeature
inline fun <T> ResendableContent.whenVideoContent(block: (VideoContent) -> T) = asVideoContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asVideoContent(): VideoContent? = this as? VideoContent

@PreviewFeature
inline fun ResendableContent.requireVideoContent(): VideoContent = this as VideoContent

@PreviewFeature
inline fun <T> ResendableContent.whenVideoNoteContent(block: (VideoNoteContent) -> T) = asVideoNoteContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asVideoNoteContent(): VideoNoteContent? = this as? VideoNoteContent

@PreviewFeature
inline fun ResendableContent.requireVideoNoteContent(): VideoNoteContent = this as VideoNoteContent

@PreviewFeature
inline fun <T> ResendableContent.whenVoiceContent(block: (VoiceContent) -> T) = asVoiceContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asVoiceContent(): VoiceContent? = this as? VoiceContent

@PreviewFeature
inline fun ResendableContent.requireVoiceContent(): VoiceContent = this as VoiceContent

@PreviewFeature
inline fun <T> ResendableContent.whenInvoiceContent(block: (InvoiceContent) -> T) = asInvoiceContent() ?.let(block)

@PreviewFeature
inline fun ResendableContent.asInvoiceContent(): InvoiceContent? = this as? InvoiceContent

@PreviewFeature
inline fun ResendableContent.requireInvoiceContent(): InvoiceContent = this as InvoiceContent

@PreviewFeature
inline fun <T> TextSource.whenMultilevelTextSource(block: (MultilevelTextSource) -> T) = asMultilevelTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asMultilevelTextSource(): MultilevelTextSource? = this as? MultilevelTextSource

@PreviewFeature
inline fun TextSource.requireMultilevelTextSource(): MultilevelTextSource = this as MultilevelTextSource

@PreviewFeature
inline fun <T> TextSource.whenBoldTextSource(block: (BoldTextSource) -> T) = asBoldTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asBoldTextSource(): BoldTextSource? = this as? BoldTextSource

@PreviewFeature
inline fun TextSource.requireBoldTextSource(): BoldTextSource = this as BoldTextSource

@PreviewFeature
inline fun <T> TextSource.whenBotCommandTextSource(block: (BotCommandTextSource) -> T) = asBotCommandTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asBotCommandTextSource(): BotCommandTextSource? = this as? BotCommandTextSource

@PreviewFeature
inline fun TextSource.requireBotCommandTextSource(): BotCommandTextSource = this as BotCommandTextSource

@PreviewFeature
inline fun <T> TextSource.whenCashTagTextSource(block: (CashTagTextSource) -> T) = asCashTagTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asCashTagTextSource(): CashTagTextSource? = this as? CashTagTextSource

@PreviewFeature
inline fun TextSource.requireCashTagTextSource(): CashTagTextSource = this as CashTagTextSource

@PreviewFeature
inline fun <T> TextSource.whenCodeTextSource(block: (CodeTextSource) -> T) = asCodeTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asCodeTextSource(): CodeTextSource? = this as? CodeTextSource

@PreviewFeature
inline fun TextSource.requireCodeTextSource(): CodeTextSource = this as CodeTextSource

@PreviewFeature
inline fun <T> TextSource.whenEMailTextSource(block: (EMailTextSource) -> T) = asEMailTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asEMailTextSource(): EMailTextSource? = this as? EMailTextSource

@PreviewFeature
inline fun TextSource.requireEMailTextSource(): EMailTextSource = this as EMailTextSource

@PreviewFeature
inline fun <T> TextSource.whenHashTagTextSource(block: (HashTagTextSource) -> T) = asHashTagTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asHashTagTextSource(): HashTagTextSource? = this as? HashTagTextSource

@PreviewFeature
inline fun TextSource.requireHashTagTextSource(): HashTagTextSource = this as HashTagTextSource

@PreviewFeature
inline fun <T> TextSource.whenItalicTextSource(block: (ItalicTextSource) -> T) = asItalicTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asItalicTextSource(): ItalicTextSource? = this as? ItalicTextSource

@PreviewFeature
inline fun TextSource.requireItalicTextSource(): ItalicTextSource = this as ItalicTextSource

@PreviewFeature
inline fun <T> TextSource.whenMentionTextSource(block: (MentionTextSource) -> T) = asMentionTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asMentionTextSource(): MentionTextSource? = this as? MentionTextSource

@PreviewFeature
inline fun TextSource.requireMentionTextSource(): MentionTextSource = this as MentionTextSource

@PreviewFeature
inline fun <T> TextSource.whenPhoneNumberTextSource(block: (PhoneNumberTextSource) -> T) = asPhoneNumberTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asPhoneNumberTextSource(): PhoneNumberTextSource? = this as? PhoneNumberTextSource

@PreviewFeature
inline fun TextSource.requirePhoneNumberTextSource(): PhoneNumberTextSource = this as PhoneNumberTextSource

@PreviewFeature
inline fun <T> TextSource.whenPreTextSource(block: (PreTextSource) -> T) = asPreTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asPreTextSource(): PreTextSource? = this as? PreTextSource

@PreviewFeature
inline fun TextSource.requirePreTextSource(): PreTextSource = this as PreTextSource

@PreviewFeature
inline fun <T> TextSource.whenRegularTextSource(block: (RegularTextSource) -> T) = asRegularTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asRegularTextSource(): RegularTextSource? = this as? RegularTextSource

@PreviewFeature
inline fun TextSource.requireRegularTextSource(): RegularTextSource = this as RegularTextSource

@PreviewFeature
inline fun <T> TextSource.whenStrikethroughTextSource(block: (StrikethroughTextSource) -> T) = asStrikethroughTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asStrikethroughTextSource(): StrikethroughTextSource? = this as? StrikethroughTextSource

@PreviewFeature
inline fun TextSource.requireStrikethroughTextSource(): StrikethroughTextSource = this as StrikethroughTextSource

@PreviewFeature
inline fun <T> TextSource.whenTextLinkTextSource(block: (TextLinkTextSource) -> T) = asTextLinkTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asTextLinkTextSource(): TextLinkTextSource? = this as? TextLinkTextSource

@PreviewFeature
inline fun TextSource.requireTextLinkTextSource(): TextLinkTextSource = this as TextLinkTextSource

@PreviewFeature
inline fun <T> TextSource.whenTextMentionTextSource(block: (TextMentionTextSource) -> T) = asTextMentionTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asTextMentionTextSource(): TextMentionTextSource? = this as? TextMentionTextSource

@PreviewFeature
inline fun TextSource.requireTextMentionTextSource(): TextMentionTextSource = this as TextMentionTextSource

@PreviewFeature
inline fun <T> TextSource.whenURLTextSource(block: (URLTextSource) -> T) = asURLTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asURLTextSource(): URLTextSource? = this as? URLTextSource

@PreviewFeature
inline fun TextSource.requireURLTextSource(): URLTextSource = this as URLTextSource

@PreviewFeature
inline fun <T> TextSource.whenUnderlineTextSource(block: (UnderlineTextSource) -> T) = asUnderlineTextSource() ?.let(block)

@PreviewFeature
inline fun TextSource.asUnderlineTextSource(): UnderlineTextSource? = this as? UnderlineTextSource

@PreviewFeature
inline fun TextSource.requireUnderlineTextSource(): UnderlineTextSource = this as UnderlineTextSource

@PreviewFeature
inline fun <T> DiceAnimationType.whenBasketballDiceAnimationType(block: (BasketballDiceAnimationType) -> T) = asBasketballDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asBasketballDiceAnimationType(): BasketballDiceAnimationType? =
    this as? BasketballDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireBasketballDiceAnimationType(): BasketballDiceAnimationType =
    this as BasketballDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenBowlingDiceAnimationType(block: (BowlingDiceAnimationType) -> T) = asBowlingDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asBowlingDiceAnimationType(): BowlingDiceAnimationType? = this as? BowlingDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireBowlingDiceAnimationType(): BowlingDiceAnimationType =
    this as BowlingDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenCubeDiceAnimationType(block: (CubeDiceAnimationType) -> T) = asCubeDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asCubeDiceAnimationType(): CubeDiceAnimationType? = this as? CubeDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireCubeDiceAnimationType(): CubeDiceAnimationType = this as CubeDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenCustomDiceAnimationType(block: (CustomDiceAnimationType) -> T) = asCustomDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asCustomDiceAnimationType(): CustomDiceAnimationType? = this as? CustomDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireCustomDiceAnimationType(): CustomDiceAnimationType = this as CustomDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenDartsDiceAnimationType(block: (DartsDiceAnimationType) -> T) = asDartsDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asDartsDiceAnimationType(): DartsDiceAnimationType? = this as? DartsDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireDartsDiceAnimationType(): DartsDiceAnimationType = this as DartsDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenFootballDiceAnimationType(block: (FootballDiceAnimationType) -> T) = asFootballDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asFootballDiceAnimationType(): FootballDiceAnimationType? =
    this as? FootballDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireFootballDiceAnimationType(): FootballDiceAnimationType =
    this as FootballDiceAnimationType

@PreviewFeature
inline fun <T> DiceAnimationType.whenSlotMachineDiceAnimationType(block: (SlotMachineDiceAnimationType) -> T) = asSlotMachineDiceAnimationType() ?.let(block)

@PreviewFeature
inline fun DiceAnimationType.asSlotMachineDiceAnimationType(): SlotMachineDiceAnimationType? =
    this as? SlotMachineDiceAnimationType

@PreviewFeature
inline fun DiceAnimationType.requireSlotMachineDiceAnimationType(): SlotMachineDiceAnimationType =
    this as SlotMachineDiceAnimationType

@PreviewFeature
inline fun <T> ChatEvent.whenChannelChatCreated(block: (ChannelChatCreated) -> T) = asChannelChatCreated() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asChannelChatCreated(): ChannelChatCreated? = this as? ChannelChatCreated

@PreviewFeature
inline fun ChatEvent.requireChannelChatCreated(): ChannelChatCreated = this as ChannelChatCreated

@PreviewFeature
inline fun <T> ChatEvent.whenDeleteChatPhoto(block: (DeleteChatPhoto) -> T) = asDeleteChatPhoto() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asDeleteChatPhoto(): DeleteChatPhoto? = this as? DeleteChatPhoto

@PreviewFeature
inline fun ChatEvent.requireDeleteChatPhoto(): DeleteChatPhoto = this as DeleteChatPhoto

@PreviewFeature
inline fun <T> ChatEvent.whenGroupChatCreated(block: (GroupChatCreated) -> T) = asGroupChatCreated() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asGroupChatCreated(): GroupChatCreated? = this as? GroupChatCreated

@PreviewFeature
inline fun ChatEvent.requireGroupChatCreated(): GroupChatCreated = this as GroupChatCreated

@PreviewFeature
inline fun <T> ChatEvent.whenLeftChatMember(block: (LeftChatMember) -> T) = asLeftChatMember() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asLeftChatMember(): LeftChatMember? = this as? LeftChatMember

@PreviewFeature
inline fun ChatEvent.requireLeftChatMember(): LeftChatMember = this as LeftChatMember

@PreviewFeature
inline fun <T> ChatEvent.whenMessageAutoDeleteTimerChanged(block: (MessageAutoDeleteTimerChanged) -> T) = asMessageAutoDeleteTimerChanged() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asMessageAutoDeleteTimerChanged(): MessageAutoDeleteTimerChanged? =
    this as? MessageAutoDeleteTimerChanged

@PreviewFeature
inline fun ChatEvent.requireMessageAutoDeleteTimerChanged(): MessageAutoDeleteTimerChanged =
    this as MessageAutoDeleteTimerChanged

@PreviewFeature
inline fun <T> ChatEvent.whenNewChatMembers(block: (NewChatMembers) -> T) = asNewChatMembers() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asNewChatMembers(): NewChatMembers? = this as? NewChatMembers

@PreviewFeature
inline fun ChatEvent.requireNewChatMembers(): NewChatMembers = this as NewChatMembers

@PreviewFeature
inline fun <T> ChatEvent.whenNewChatPhoto(block: (NewChatPhoto) -> T) = asNewChatPhoto() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asNewChatPhoto(): NewChatPhoto? = this as? NewChatPhoto

@PreviewFeature
inline fun ChatEvent.requireNewChatPhoto(): NewChatPhoto = this as NewChatPhoto

@PreviewFeature
inline fun <T> ChatEvent.whenNewChatTitle(block: (NewChatTitle) -> T) = asNewChatTitle() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asNewChatTitle(): NewChatTitle? = this as? NewChatTitle

@PreviewFeature
inline fun ChatEvent.requireNewChatTitle(): NewChatTitle = this as NewChatTitle

@PreviewFeature
inline fun <T> ChatEvent.whenPinnedMessage(block: (PinnedMessage) -> T) = asPinnedMessage() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asPinnedMessage(): PinnedMessage? = this as? PinnedMessage

@PreviewFeature
inline fun ChatEvent.requirePinnedMessage(): PinnedMessage = this as PinnedMessage

@PreviewFeature
inline fun <T> ChatEvent.whenSuccessfulPaymentEvent(block: (SuccessfulPaymentEvent) -> T) = asSuccessfulPaymentEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asSuccessfulPaymentEvent(): SuccessfulPaymentEvent? = this as? SuccessfulPaymentEvent

@PreviewFeature
inline fun ChatEvent.requireSuccessfulPaymentEvent(): SuccessfulPaymentEvent = this as SuccessfulPaymentEvent

@PreviewFeature
inline fun <T> ChatEvent.whenProximityAlertTriggered(block: (ProximityAlertTriggered) -> T) = asProximityAlertTriggered() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asProximityAlertTriggered(): ProximityAlertTriggered? = this as? ProximityAlertTriggered

@PreviewFeature
inline fun ChatEvent.requireProximityAlertTriggered(): ProximityAlertTriggered = this as ProximityAlertTriggered

@PreviewFeature
inline fun <T> ChatEvent.whenSupergroupChatCreated(block: (SupergroupChatCreated) -> T) = asSupergroupChatCreated() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asSupergroupChatCreated(): SupergroupChatCreated? = this as? SupergroupChatCreated

@PreviewFeature
inline fun ChatEvent.requireSupergroupChatCreated(): SupergroupChatCreated = this as SupergroupChatCreated

@PreviewFeature
inline fun <T> ChatEvent.whenMigratedToSupergroup(block: (MigratedToSupergroup) -> T) = asMigratedToSupergroup() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asMigratedToSupergroup(): MigratedToSupergroup? = this as? MigratedToSupergroup

@PreviewFeature
inline fun ChatEvent.requireMigratedToSupergroup(): MigratedToSupergroup = this as MigratedToSupergroup

@PreviewFeature
inline fun <T> ChatEvent.whenChannelEvent(block: (ChannelEvent) -> T) = asChannelEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asChannelEvent(): ChannelEvent? = this as? ChannelEvent

@PreviewFeature
inline fun ChatEvent.requireChannelEvent(): ChannelEvent = this as ChannelEvent

@PreviewFeature
inline fun <T> ChatEvent.whenPublicChatEvent(block: (PublicChatEvent) -> T) = asPublicChatEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asPublicChatEvent(): PublicChatEvent? = this as? PublicChatEvent

@PreviewFeature
inline fun ChatEvent.requirePublicChatEvent(): PublicChatEvent = this as PublicChatEvent

@PreviewFeature
inline fun <T> ChatEvent.whenCommonEvent(block: (CommonEvent) -> T) = asCommonEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asCommonEvent(): CommonEvent? = this as? CommonEvent

@PreviewFeature
inline fun ChatEvent.requireCommonEvent(): CommonEvent = this as CommonEvent

@PreviewFeature
inline fun <T> ChatEvent.whenGroupEvent(block: (GroupEvent) -> T) = asGroupEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asGroupEvent(): GroupEvent? = this as? GroupEvent

@PreviewFeature
inline fun ChatEvent.requireGroupEvent(): GroupEvent = this as GroupEvent

@PreviewFeature
inline fun <T> ChatEvent.whenSupergroupEvent(block: (SupergroupEvent) -> T) = asSupergroupEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asSupergroupEvent(): SupergroupEvent? = this as? SupergroupEvent

@PreviewFeature
inline fun ChatEvent.requireSupergroupEvent(): SupergroupEvent = this as SupergroupEvent

@PreviewFeature
inline fun <T> ChatEvent.whenVoiceChatEvent(block: (VoiceChatEvent) -> T) = asVoiceChatEvent() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asVoiceChatEvent(): VoiceChatEvent? = this as? VoiceChatEvent

@PreviewFeature
inline fun ChatEvent.requireVoiceChatEvent(): VoiceChatEvent = this as VoiceChatEvent

@PreviewFeature
inline fun <T> ChatEvent.whenVoiceChatEnded(block: (VoiceChatEnded) -> T) = asVoiceChatEnded() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asVoiceChatEnded(): VoiceChatEnded? = this as? VoiceChatEnded

@PreviewFeature
inline fun ChatEvent.requireVoiceChatEnded(): VoiceChatEnded = this as VoiceChatEnded

@PreviewFeature
inline fun <T> ChatEvent.whenVoiceChatParticipantsInvited(block: (VoiceChatParticipantsInvited) -> T) = asVoiceChatParticipantsInvited() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asVoiceChatParticipantsInvited(): VoiceChatParticipantsInvited? =
    this as? VoiceChatParticipantsInvited

@PreviewFeature
inline fun ChatEvent.requireVoiceChatParticipantsInvited(): VoiceChatParticipantsInvited =
    this as VoiceChatParticipantsInvited

@PreviewFeature
inline fun <T> ChatEvent.whenVoiceChatStarted(block: (VoiceChatStarted) -> T) = asVoiceChatStarted() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asVoiceChatStarted(): VoiceChatStarted? = this as? VoiceChatStarted

@PreviewFeature
inline fun ChatEvent.requireVoiceChatStarted(): VoiceChatStarted = this as VoiceChatStarted

@PreviewFeature
inline fun <T> ChatEvent.whenVoiceChatScheduled(block: (VoiceChatScheduled) -> T) = asVoiceChatScheduled() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asVoiceChatScheduled(): VoiceChatScheduled? = this as? VoiceChatScheduled

@PreviewFeature
inline fun ChatEvent.requireVoiceChatScheduled(): VoiceChatScheduled = this as VoiceChatScheduled

@PreviewFeature
inline fun <T> ChatEvent.whenUserLoggedIn(block: (UserLoggedIn) -> T) = asUserLoggedIn() ?.let(block)

@PreviewFeature
inline fun ChatEvent.asUserLoggedIn(): UserLoggedIn? = this as? UserLoggedIn

@PreviewFeature
inline fun ChatEvent.requireUserLoggedIn(): UserLoggedIn = this as UserLoggedIn

@PreviewFeature
inline fun <T> CommonSendInvoiceData.whenSendInvoice(block: (SendInvoice) -> T) = asSendInvoice() ?.let(block)

@PreviewFeature
inline fun CommonSendInvoiceData.asSendInvoice(): SendInvoice? = this as? SendInvoice

@PreviewFeature
inline fun CommonSendInvoiceData.requireVoiceChatParticipantsInvited(): SendInvoice = this as SendInvoice

@PreviewFeature
inline fun <T> CommonSendInvoiceData.whenInputInvoiceMessageContent(block: (InputInvoiceMessageContent) -> T) = asInputInvoiceMessageContent() ?.let(block)

@PreviewFeature
inline fun CommonSendInvoiceData.asInputInvoiceMessageContent(): InputInvoiceMessageContent? =
    this as? InputInvoiceMessageContent

@PreviewFeature
inline fun CommonSendInvoiceData.requireInputInvoiceMessageContent(): InputInvoiceMessageContent =
    this as InputInvoiceMessageContent

@PreviewFeature
inline fun <T> Any.whenFromUser(block: (FromUser) -> T) = asFromUser() ?.let(block)

@PreviewFeature
inline fun Any.asFromUser(): FromUser? = this as? FromUser

@PreviewFeature
inline fun Any.requireFromUser(): FromUser = this as FromUser

@PreviewFeature
inline fun <T> Any.whenWithUser(block: (WithUser) -> T) = asWithUser() ?.let(block)

@PreviewFeature
inline fun Any.asWithUser(): WithUser? = this as? WithUser

@PreviewFeature
inline fun Any.requireWithUser(): WithUser = this as WithUser

@PreviewFeature
inline fun <T> Any.whenWithOptionalLanguageCode(block: (WithOptionalLanguageCode) -> T) = asWithOptionalLanguageCode() ?.let(block)

@PreviewFeature
inline fun Any.asWithOptionalLanguageCode(): WithOptionalLanguageCode? = this as? WithOptionalLanguageCode

@PreviewFeature
inline fun Any.requireWithOptionalLanguageCode(): WithOptionalLanguageCode = this as WithOptionalLanguageCode

@PreviewFeature
inline fun <T> Location.whenStaticLocation(block: (StaticLocation) -> T) = asStaticLocation() ?.let(block)

@PreviewFeature
inline fun Location.asStaticLocation(): StaticLocation? = this as? StaticLocation

@PreviewFeature
inline fun Location.requireStaticLocation(): StaticLocation = this as StaticLocation

@PreviewFeature
inline fun <T> Location.whenLiveLocation(block: (LiveLocation) -> T) = asLiveLocation() ?.let(block)

@PreviewFeature
inline fun Location.asLiveLocation(): LiveLocation? = this as? LiveLocation

@PreviewFeature
inline fun Location.requireLiveLocation(): LiveLocation = this as LiveLocation

@PreviewFeature
inline fun <T> ChatInviteLink.whenPrimaryInviteLink(block: (PrimaryInviteLink) -> T) = asPrimaryInviteLink() ?.let(block)

@PreviewFeature
inline fun ChatInviteLink.asPrimaryInviteLink(): PrimaryInviteLink? = this as? PrimaryInviteLink

@PreviewFeature
inline fun ChatInviteLink.requirePrimaryInviteLink(): PrimaryInviteLink = this as PrimaryInviteLink

@PreviewFeature
inline fun <T> ChatInviteLink.whenSecondaryChatInviteLink(block: (SecondaryChatInviteLink) -> T) = asSecondaryChatInviteLink() ?.let(block)

@PreviewFeature
inline fun ChatInviteLink.asSecondaryChatInviteLink(): SecondaryChatInviteLink? = this as? SecondaryChatInviteLink

@PreviewFeature
inline fun ChatInviteLink.requireSecondaryChatInviteLink(): SecondaryChatInviteLink = this as SecondaryChatInviteLink

@PreviewFeature
inline fun <T> ChatInviteLink.whenChatInviteLinkWithJoinRequest(block: (ChatInviteLinkWithJoinRequest) -> T) = asChatInviteLinkWithJoinRequest() ?.let(block)

@PreviewFeature
inline fun ChatInviteLink.asChatInviteLinkWithJoinRequest(): ChatInviteLinkWithJoinRequest? = this as? ChatInviteLinkWithJoinRequest

@PreviewFeature
inline fun ChatInviteLink.requireChatInviteLinkWithJoinRequest(): ChatInviteLinkWithJoinRequest = this as ChatInviteLinkWithJoinRequest

@PreviewFeature
inline fun <T> ChatInviteLink.whenChatInviteLinkWithLimitedMembers(block: (ChatInviteLinkWithLimitedMembers) -> T) = asChatInviteLinkWithLimitedMembers() ?.let(block)

@PreviewFeature
inline fun ChatInviteLink.asChatInviteLinkWithLimitedMembers(): ChatInviteLinkWithLimitedMembers? = this as? ChatInviteLinkWithLimitedMembers

@PreviewFeature
inline fun ChatInviteLink.requireChatInviteLinkWithLimitedMembers(): ChatInviteLinkWithLimitedMembers = this as ChatInviteLinkWithLimitedMembers

@PreviewFeature
inline fun <T> ChatInviteLink.whenChatInviteLinkUnlimited(block: (ChatInviteLinkUnlimited) -> T) = asChatInviteLinkUnlimited() ?.let(block)

@PreviewFeature
inline fun ChatInviteLink.asChatInviteLinkUnlimited(): ChatInviteLinkUnlimited? = this as? ChatInviteLinkUnlimited

@PreviewFeature
inline fun ChatInviteLink.requireChatInviteLinkUnlimited(): ChatInviteLinkUnlimited = this as ChatInviteLinkUnlimited

@PreviewFeature
inline fun <T> ForwardInfo.whenAnonymousForwardInfo(block: (AnonymousForwardInfo) -> T) = asAnonymousForwardInfo() ?.let(block)

@PreviewFeature
inline fun ForwardInfo.asAnonymousForwardInfo(): AnonymousForwardInfo? = this as? AnonymousForwardInfo

@PreviewFeature
inline fun ForwardInfo.requireAnonymousForwardInfo(): AnonymousForwardInfo = this as AnonymousForwardInfo

@PreviewFeature
inline fun <T> ForwardInfo.whenUserForwardInfo(block: (UserForwardInfo) -> T) = asUserForwardInfo() ?.let(block)

@PreviewFeature
inline fun ForwardInfo.asUserForwardInfo(): UserForwardInfo? = this as? UserForwardInfo

@PreviewFeature
inline fun ForwardInfo.requireUserForwardInfo(): UserForwardInfo = this as UserForwardInfo

@PreviewFeature
inline fun <T> ForwardInfo.whenForwardFromPublicChatInfo(block: (ForwardFromPublicChatInfo) -> T) = asForwardFromPublicChatInfo() ?.let(block)

@PreviewFeature
inline fun ForwardInfo.asForwardFromPublicChatInfo(): ForwardFromPublicChatInfo? = this as? ForwardFromPublicChatInfo

@PreviewFeature
inline fun ForwardInfo.requireForwardFromPublicChatInfo(): ForwardFromPublicChatInfo = this as ForwardFromPublicChatInfo

@PreviewFeature
inline fun <T> ForwardInfo.whenForwardFromChannelInfo(block: (ForwardFromChannelInfo) -> T) = asForwardFromChannelInfo() ?.let(block)

@PreviewFeature
inline fun ForwardInfo.asForwardFromChannelInfo(): ForwardFromChannelInfo? = this as? ForwardFromChannelInfo

@PreviewFeature
inline fun ForwardInfo.requireForwardFromChannelInfo(): ForwardFromChannelInfo = this as ForwardFromChannelInfo

@PreviewFeature
inline fun <T> ForwardInfo.whenForwardFromSupergroupInfo(block: (ForwardFromSupergroupInfo) -> T) = asForwardFromSupergroupInfo() ?.let(block)

@PreviewFeature
inline fun ForwardInfo.asForwardFromSupergroupInfo(): ForwardFromSupergroupInfo? = this as? ForwardFromSupergroupInfo

@PreviewFeature
inline fun ForwardInfo.requireForwardFromSupergroupInfo(): ForwardFromSupergroupInfo = this as ForwardFromSupergroupInfo

@PreviewFeature
inline fun <T> MessageContent.whenTextedInput(block: (TextedInput) -> T) = asTextedInput() ?.let(block)

@PreviewFeature
inline fun MessageContent.asTextedInput(): TextedInput? = this as? TextedInput

@PreviewFeature
inline fun MessageContent.requireTextedInput(): TextedInput = this as TextedInput

@PreviewFeature
inline fun <T> ScheduledCloseInfo.whenExactScheduledCloseInfo(block: (ExactScheduledCloseInfo) -> T) = asExactScheduledCloseInfo() ?.let(block)

@PreviewFeature
inline fun ScheduledCloseInfo.asExactScheduledCloseInfo(): ExactScheduledCloseInfo? = this as? ExactScheduledCloseInfo

@PreviewFeature
inline fun ScheduledCloseInfo.requireExactScheduledCloseInfo(): ExactScheduledCloseInfo = this as ExactScheduledCloseInfo

@PreviewFeature
inline fun <T> ScheduledCloseInfo.whenApproximateScheduledCloseInfo(block: (ApproximateScheduledCloseInfo) -> T) = asApproximateScheduledCloseInfo() ?.let(block)

@PreviewFeature
inline fun ScheduledCloseInfo.asApproximateScheduledCloseInfo(): ApproximateScheduledCloseInfo? = this as? ApproximateScheduledCloseInfo

@PreviewFeature
inline fun ScheduledCloseInfo.requireApproximateScheduledCloseInfo(): ApproximateScheduledCloseInfo = this as ApproximateScheduledCloseInfo

@PreviewFeature
inline fun <T> ChosenInlineResult.whenLocationChosenInlineResult(block: (LocationChosenInlineResult) -> T) = asLocationChosenInlineResult() ?.let(block)

@PreviewFeature
inline fun ChosenInlineResult.asLocationChosenInlineResult(): LocationChosenInlineResult? = this as? LocationChosenInlineResult

@PreviewFeature
inline fun ChosenInlineResult.requireLocationChosenInlineResult(): LocationChosenInlineResult = this as LocationChosenInlineResult

@PreviewFeature
inline fun <T> ChosenInlineResult.whenBaseChosenInlineResult(block: (BaseChosenInlineResult) -> T) = asBaseChosenInlineResult() ?.let(block)

@PreviewFeature
inline fun ChosenInlineResult.asBaseChosenInlineResult(): BaseChosenInlineResult? = this as? BaseChosenInlineResult

@PreviewFeature
inline fun ChosenInlineResult.requireBaseChosenInlineResult(): BaseChosenInlineResult = this as BaseChosenInlineResult
