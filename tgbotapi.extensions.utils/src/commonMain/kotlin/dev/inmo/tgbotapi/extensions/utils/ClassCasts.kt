@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.utils
import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.ChatMember.*
import dev.inmo.tgbotapi.types.ChatMember.abstracts.*
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
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.buttons.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.*
import dev.inmo.tgbotapi.types.chat.abstracts.*
import dev.inmo.tgbotapi.types.chat.abstracts.extended.*
import dev.inmo.tgbotapi.types.dice.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.files.abstracts.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.LeftChatMember
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.types.message.ChatEvents.voice.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
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
inline fun Chat.asBot(): Bot? = this as? Bot
@PreviewFeature
inline fun Chat.requireBot(): Bot = this as Bot
@PreviewFeature
inline fun Chat.asCommonBot(): CommonBot? = this as? CommonBot
@PreviewFeature
inline fun Chat.requireCommonBot(): CommonBot = this as CommonBot
@PreviewFeature
inline fun Chat.asCommonUser(): CommonUser? = this as? CommonUser
@PreviewFeature
inline fun Chat.requireCommonUser(): CommonUser = this as CommonUser
@PreviewFeature
inline fun Chat.asExtendedBot(): ExtendedBot? = this as? ExtendedBot
@PreviewFeature
inline fun Chat.requireExtendedBot(): ExtendedBot = this as ExtendedBot
@PreviewFeature
inline fun Chat.asUser(): User? = this as? User
@PreviewFeature
inline fun Chat.requireUser(): User = this as User
@PreviewFeature
inline fun Chat.asChannelChat(): ChannelChat? = this as? ChannelChat
@PreviewFeature
inline fun Chat.requireChannelChat(): ChannelChat = this as ChannelChat
@PreviewFeature
inline fun Chat.asGroupChat(): GroupChat? = this as? GroupChat
@PreviewFeature
inline fun Chat.requireGroupChat(): GroupChat = this as GroupChat
@PreviewFeature
inline fun Chat.asPrivateChat(): PrivateChat? = this as? PrivateChat
@PreviewFeature
inline fun Chat.requirePrivateChat(): PrivateChat = this as PrivateChat
@PreviewFeature
inline fun Chat.asPublicChat(): PublicChat? = this as? PublicChat
@PreviewFeature
inline fun Chat.requirePublicChat(): PublicChat = this as PublicChat
@PreviewFeature
inline fun Chat.asSuperPublicChat(): SuperPublicChat? = this as? SuperPublicChat
@PreviewFeature
inline fun Chat.requireSuperPublicChat(): SuperPublicChat = this as SuperPublicChat
@PreviewFeature
inline fun Chat.asSupergroupChat(): SupergroupChat? = this as? SupergroupChat
@PreviewFeature
inline fun Chat.requireSupergroupChat(): SupergroupChat = this as SupergroupChat
@PreviewFeature
inline fun Chat.asUnknownChatType(): UnknownChatType? = this as? UnknownChatType
@PreviewFeature
inline fun Chat.requireUnknownChatType(): UnknownChatType = this as UnknownChatType
@PreviewFeature
inline fun Chat.asUsernameChat(): UsernameChat? = this as? UsernameChat
@PreviewFeature
inline fun Chat.requireUsernameChat(): UsernameChat = this as UsernameChat
@PreviewFeature
inline fun Chat.asExtendedChannelChat(): ExtendedChannelChat? = this as? ExtendedChannelChat
@PreviewFeature
inline fun Chat.requireExtendedChannelChat(): ExtendedChannelChat = this as ExtendedChannelChat
@PreviewFeature
inline fun Chat.asExtendedChat(): ExtendedChat? = this as? ExtendedChat
@PreviewFeature
inline fun Chat.requireExtendedChat(): ExtendedChat = this as ExtendedChat
@PreviewFeature
inline fun Chat.asExtendedGroupChat(): ExtendedGroupChat? = this as? ExtendedGroupChat
@PreviewFeature
inline fun Chat.requireExtendedGroupChat(): ExtendedGroupChat = this as ExtendedGroupChat
@PreviewFeature
inline fun Chat.asExtendedPrivateChat(): ExtendedPrivateChat? = this as? ExtendedPrivateChat
@PreviewFeature
inline fun Chat.requireExtendedPrivateChat(): ExtendedPrivateChat = this as ExtendedPrivateChat
@PreviewFeature
inline fun Chat.asExtendedPublicChat(): ExtendedPublicChat? = this as? ExtendedPublicChat
@PreviewFeature
inline fun Chat.requireExtendedPublicChat(): ExtendedPublicChat = this as ExtendedPublicChat
@PreviewFeature
inline fun Chat.asExtendedSupergroupChat(): ExtendedSupergroupChat? = this as? ExtendedSupergroupChat
@PreviewFeature
inline fun Chat.requireExtendedSupergroupChat(): ExtendedSupergroupChat = this as ExtendedSupergroupChat
@PreviewFeature
inline fun CallbackQuery.asDataCallbackQuery(): DataCallbackQuery? = this as? DataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireDataCallbackQuery(): DataCallbackQuery = this as DataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asGameShortNameCallbackQuery(): GameShortNameCallbackQuery? = this as? GameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireGameShortNameCallbackQuery(): GameShortNameCallbackQuery = this as GameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdCallbackQuery(): InlineMessageIdCallbackQuery? = this as? InlineMessageIdCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdCallbackQuery(): InlineMessageIdCallbackQuery = this as InlineMessageIdCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdDataCallbackQuery(): InlineMessageIdDataCallbackQuery? = this as? InlineMessageIdDataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdDataCallbackQuery(): InlineMessageIdDataCallbackQuery = this as InlineMessageIdDataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asInlineMessageIdGameShortNameCallbackQuery(): InlineMessageIdGameShortNameCallbackQuery? = this as? InlineMessageIdGameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireInlineMessageIdGameShortNameCallbackQuery(): InlineMessageIdGameShortNameCallbackQuery = this as InlineMessageIdGameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asMessageCallbackQuery(): MessageCallbackQuery? = this as? MessageCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireMessageCallbackQuery(): MessageCallbackQuery = this as MessageCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asMessageDataCallbackQuery(): MessageDataCallbackQuery? = this as? MessageDataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireMessageDataCallbackQuery(): MessageDataCallbackQuery = this as MessageDataCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asMessageGameShortNameCallbackQuery(): MessageGameShortNameCallbackQuery? = this as? MessageGameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.requireMessageGameShortNameCallbackQuery(): MessageGameShortNameCallbackQuery = this as MessageGameShortNameCallbackQuery
@PreviewFeature
inline fun CallbackQuery.asUnknownCallbackQueryType(): UnknownCallbackQueryType? = this as? UnknownCallbackQueryType
@PreviewFeature
inline fun CallbackQuery.requireUnknownCallbackQueryType(): UnknownCallbackQueryType = this as UnknownCallbackQueryType
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorDataField(): PassportElementErrorDataField? = this as? PassportElementErrorDataField
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorDataField(): PassportElementErrorDataField = this as PassportElementErrorDataField
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFile(): PassportElementErrorFile? = this as? PassportElementErrorFile
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFile(): PassportElementErrorFile = this as PassportElementErrorFile
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFiles(): PassportElementErrorFiles? = this as? PassportElementErrorFiles
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFiles(): PassportElementErrorFiles = this as PassportElementErrorFiles
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorFrontSide(): PassportElementErrorFrontSide? = this as? PassportElementErrorFrontSide
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorFrontSide(): PassportElementErrorFrontSide = this as PassportElementErrorFrontSide
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorReverseSide(): PassportElementErrorReverseSide? = this as? PassportElementErrorReverseSide
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorReverseSide(): PassportElementErrorReverseSide = this as PassportElementErrorReverseSide
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorSelfie(): PassportElementErrorSelfie? = this as? PassportElementErrorSelfie
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorSelfie(): PassportElementErrorSelfie = this as PassportElementErrorSelfie
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorTranslationFile(): PassportElementErrorTranslationFile? = this as? PassportElementErrorTranslationFile
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorTranslationFile(): PassportElementErrorTranslationFile = this as PassportElementErrorTranslationFile
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorTranslationFiles(): PassportElementErrorTranslationFiles? = this as? PassportElementErrorTranslationFiles
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorTranslationFiles(): PassportElementErrorTranslationFiles = this as PassportElementErrorTranslationFiles
@PreviewFeature
inline fun PassportElementError.asPassportElementErrorUnspecified(): PassportElementErrorUnspecified? = this as? PassportElementErrorUnspecified
@PreviewFeature
inline fun PassportElementError.requirePassportElementErrorUnspecified(): PassportElementErrorUnspecified = this as PassportElementErrorUnspecified
@PreviewFeature
inline fun PassportElementError.asPassportElementFileError(): PassportElementFileError? = this as? PassportElementFileError
@PreviewFeature
inline fun PassportElementError.requirePassportElementFileError(): PassportElementFileError = this as PassportElementFileError
@PreviewFeature
inline fun PassportElementError.asPassportElementFilesError(): PassportElementFilesError? = this as? PassportElementFilesError
@PreviewFeature
inline fun PassportElementError.requirePassportElementFilesError(): PassportElementFilesError = this as PassportElementFilesError
@PreviewFeature
inline fun PassportElementError.asPassportMultipleElementsError(): PassportMultipleElementsError? = this as? PassportMultipleElementsError
@PreviewFeature
inline fun PassportElementError.requirePassportMultipleElementsError(): PassportMultipleElementsError = this as PassportMultipleElementsError
@PreviewFeature
inline fun PassportElementError.asPassportSingleElementError(): PassportSingleElementError? = this as? PassportSingleElementError
@PreviewFeature
inline fun PassportElementError.requirePassportSingleElementError(): PassportSingleElementError = this as PassportSingleElementError
@PreviewFeature
inline fun PassportElementError.asUnknownPassportElementError(): UnknownPassportElementError? = this as? UnknownPassportElementError
@PreviewFeature
inline fun PassportElementError.requireUnknownPassportElementError(): UnknownPassportElementError = this as UnknownPassportElementError
@PreviewFeature
inline fun EncryptedPassportElement.asBankStatement(): BankStatement? = this as? BankStatement
@PreviewFeature
inline fun EncryptedPassportElement.requireBankStatement(): BankStatement = this as BankStatement
@PreviewFeature
inline fun EncryptedPassportElement.asCommonPassport(): CommonPassport? = this as? CommonPassport
@PreviewFeature
inline fun EncryptedPassportElement.requireCommonPassport(): CommonPassport = this as CommonPassport
@PreviewFeature
inline fun EncryptedPassportElement.asDriverLicense(): DriverLicense? = this as? DriverLicense
@PreviewFeature
inline fun EncryptedPassportElement.requireDriverLicense(): DriverLicense = this as DriverLicense
@PreviewFeature
inline fun EncryptedPassportElement.asEmail(): Email? = this as? Email
@PreviewFeature
inline fun EncryptedPassportElement.requireEmail(): Email = this as Email
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedAddress(): EncryptedAddress? = this as? EncryptedAddress
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedAddress(): EncryptedAddress = this as EncryptedAddress
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPersonalDetails(): EncryptedPersonalDetails? = this as? EncryptedPersonalDetails
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPersonalDetails(): EncryptedPersonalDetails = this as EncryptedPersonalDetails
@PreviewFeature
inline fun EncryptedPassportElement.asIdentityCard(): IdentityCard? = this as? IdentityCard
@PreviewFeature
inline fun EncryptedPassportElement.requireIdentityCard(): IdentityCard = this as IdentityCard
@PreviewFeature
inline fun EncryptedPassportElement.asInternalPassport(): InternalPassport? = this as? InternalPassport
@PreviewFeature
inline fun EncryptedPassportElement.requireInternalPassport(): InternalPassport = this as InternalPassport
@PreviewFeature
inline fun EncryptedPassportElement.asPassport(): Passport? = this as? Passport
@PreviewFeature
inline fun EncryptedPassportElement.requirePassport(): Passport = this as Passport
@PreviewFeature
inline fun EncryptedPassportElement.asPassportRegistration(): PassportRegistration? = this as? PassportRegistration
@PreviewFeature
inline fun EncryptedPassportElement.requirePassportRegistration(): PassportRegistration = this as PassportRegistration
@PreviewFeature
inline fun EncryptedPassportElement.asPhoneNumber(): PhoneNumber? = this as? PhoneNumber
@PreviewFeature
inline fun EncryptedPassportElement.requirePhoneNumber(): PhoneNumber = this as PhoneNumber
@PreviewFeature
inline fun EncryptedPassportElement.asRentalAgreement(): RentalAgreement? = this as? RentalAgreement
@PreviewFeature
inline fun EncryptedPassportElement.requireRentalAgreement(): RentalAgreement = this as RentalAgreement
@PreviewFeature
inline fun EncryptedPassportElement.asTemporaryRegistration(): TemporaryRegistration? = this as? TemporaryRegistration
@PreviewFeature
inline fun EncryptedPassportElement.requireTemporaryRegistration(): TemporaryRegistration = this as TemporaryRegistration
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithTranslatableFilesCollection(): EncryptedPassportElementWithTranslatableFilesCollection? = this as? EncryptedPassportElementWithTranslatableFilesCollection
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithTranslatableFilesCollection(): EncryptedPassportElementWithTranslatableFilesCollection = this as EncryptedPassportElementWithTranslatableFilesCollection
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithTranslatableIDDocument(): EncryptedPassportElementWithTranslatableIDDocument? = this as? EncryptedPassportElementWithTranslatableIDDocument
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithTranslatableIDDocument(): EncryptedPassportElementWithTranslatableIDDocument = this as EncryptedPassportElementWithTranslatableIDDocument
@PreviewFeature
inline fun EncryptedPassportElement.asUtilityBill(): UtilityBill? = this as? UtilityBill
@PreviewFeature
inline fun EncryptedPassportElement.requireUtilityBill(): UtilityBill = this as UtilityBill
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithFilesCollection(): EncryptedPassportElementWithFilesCollection? = this as? EncryptedPassportElementWithFilesCollection
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithFilesCollection(): EncryptedPassportElementWithFilesCollection = this as EncryptedPassportElementWithFilesCollection
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementTranslatable(): EncryptedPassportElementTranslatable? = this as? EncryptedPassportElementTranslatable
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementTranslatable(): EncryptedPassportElementTranslatable = this as EncryptedPassportElementTranslatable
@PreviewFeature
inline fun EncryptedPassportElement.asUnknownEncryptedPassportElement(): UnknownEncryptedPassportElement? = this as? UnknownEncryptedPassportElement
@PreviewFeature
inline fun EncryptedPassportElement.requireUnknownEncryptedPassportElement(): UnknownEncryptedPassportElement = this as UnknownEncryptedPassportElement
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithData(): EncryptedPassportElementWithData? = this as? EncryptedPassportElementWithData
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithData(): EncryptedPassportElementWithData = this as EncryptedPassportElementWithData
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithEmail(): EncryptedPassportElementWithEmail? = this as? EncryptedPassportElementWithEmail
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithEmail(): EncryptedPassportElementWithEmail = this as EncryptedPassportElementWithEmail
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithFrontSide(): EncryptedPassportElementWithFrontSide? = this as? EncryptedPassportElementWithFrontSide
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithFrontSide(): EncryptedPassportElementWithFrontSide = this as EncryptedPassportElementWithFrontSide
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithPhoneNumber(): EncryptedPassportElementWithPhoneNumber? = this as? EncryptedPassportElementWithPhoneNumber
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithPhoneNumber(): EncryptedPassportElementWithPhoneNumber = this as EncryptedPassportElementWithPhoneNumber
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithReverseSide(): EncryptedPassportElementWithReverseSide? = this as? EncryptedPassportElementWithReverseSide
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithReverseSide(): EncryptedPassportElementWithReverseSide = this as EncryptedPassportElementWithReverseSide
@PreviewFeature
inline fun EncryptedPassportElement.asEncryptedPassportElementWithSelfie(): EncryptedPassportElementWithSelfie? = this as? EncryptedPassportElementWithSelfie
@PreviewFeature
inline fun EncryptedPassportElement.requireEncryptedPassportElementWithSelfie(): EncryptedPassportElementWithSelfie = this as EncryptedPassportElementWithSelfie
@PreviewFeature
inline fun SecureValue.asAddressSecureValue(): AddressSecureValue? = this as? AddressSecureValue
@PreviewFeature
inline fun SecureValue.requireAddressSecureValue(): AddressSecureValue = this as AddressSecureValue
@PreviewFeature
inline fun SecureValue.asBankStatementSecureValue(): BankStatementSecureValue? = this as? BankStatementSecureValue
@PreviewFeature
inline fun SecureValue.requireBankStatementSecureValue(): BankStatementSecureValue = this as BankStatementSecureValue
@PreviewFeature
inline fun SecureValue.asCommonPassportSecureValue(): CommonPassportSecureValue? = this as? CommonPassportSecureValue
@PreviewFeature
inline fun SecureValue.requireCommonPassportSecureValue(): CommonPassportSecureValue = this as CommonPassportSecureValue
@PreviewFeature
inline fun SecureValue.asDriverLicenseSecureValue(): DriverLicenseSecureValue? = this as? DriverLicenseSecureValue
@PreviewFeature
inline fun SecureValue.requireDriverLicenseSecureValue(): DriverLicenseSecureValue = this as DriverLicenseSecureValue
@PreviewFeature
inline fun SecureValue.asIdentityCardSecureValue(): IdentityCardSecureValue? = this as? IdentityCardSecureValue
@PreviewFeature
inline fun SecureValue.requireIdentityCardSecureValue(): IdentityCardSecureValue = this as IdentityCardSecureValue
@PreviewFeature
inline fun SecureValue.asIdentityWithReverseSideSecureValue(): IdentityWithReverseSideSecureValue? = this as? IdentityWithReverseSideSecureValue
@PreviewFeature
inline fun SecureValue.requireIdentityWithReverseSideSecureValue(): IdentityWithReverseSideSecureValue = this as IdentityWithReverseSideSecureValue
@PreviewFeature
inline fun SecureValue.asInternalPassportSecureValue(): InternalPassportSecureValue? = this as? InternalPassportSecureValue
@PreviewFeature
inline fun SecureValue.requireInternalPassportSecureValue(): InternalPassportSecureValue = this as InternalPassportSecureValue
@PreviewFeature
inline fun SecureValue.asOtherDocumentsSecureValue(): OtherDocumentsSecureValue? = this as? OtherDocumentsSecureValue
@PreviewFeature
inline fun SecureValue.requireOtherDocumentsSecureValue(): OtherDocumentsSecureValue = this as OtherDocumentsSecureValue
@PreviewFeature
inline fun SecureValue.asPassportRegistrationSecureValue(): PassportRegistrationSecureValue? = this as? PassportRegistrationSecureValue
@PreviewFeature
inline fun SecureValue.requirePassportRegistrationSecureValue(): PassportRegistrationSecureValue = this as PassportRegistrationSecureValue
@PreviewFeature
inline fun SecureValue.asPassportSecureValue(): PassportSecureValue? = this as? PassportSecureValue
@PreviewFeature
inline fun SecureValue.requirePassportSecureValue(): PassportSecureValue = this as PassportSecureValue
@PreviewFeature
inline fun SecureValue.asPersonalDetailsSecureValue(): PersonalDetailsSecureValue? = this as? PersonalDetailsSecureValue
@PreviewFeature
inline fun SecureValue.requirePersonalDetailsSecureValue(): PersonalDetailsSecureValue = this as PersonalDetailsSecureValue
@PreviewFeature
inline fun SecureValue.asRentalAgreementSecureValue(): RentalAgreementSecureValue? = this as? RentalAgreementSecureValue
@PreviewFeature
inline fun SecureValue.requireRentalAgreementSecureValue(): RentalAgreementSecureValue = this as RentalAgreementSecureValue
@PreviewFeature
inline fun SecureValue.asTemporalRegistrationSecureValue(): TemporalRegistrationSecureValue? = this as? TemporalRegistrationSecureValue
@PreviewFeature
inline fun SecureValue.requireTemporalRegistrationSecureValue(): TemporalRegistrationSecureValue = this as TemporalRegistrationSecureValue
@PreviewFeature
inline fun SecureValue.asUtilityBillSecureValue(): UtilityBillSecureValue? = this as? UtilityBillSecureValue
@PreviewFeature
inline fun SecureValue.requireUtilityBillSecureValue(): UtilityBillSecureValue = this as UtilityBillSecureValue
@PreviewFeature
inline fun SecureValue.asSecureValueIdentity(): SecureValueIdentity? = this as? SecureValueIdentity
@PreviewFeature
inline fun SecureValue.requireSecureValueIdentity(): SecureValueIdentity = this as SecureValueIdentity
@PreviewFeature
inline fun SecureValue.asSecureValueWithData(): SecureValueWithData? = this as? SecureValueWithData
@PreviewFeature
inline fun SecureValue.requireSecureValueWithData(): SecureValueWithData = this as SecureValueWithData
@PreviewFeature
inline fun SecureValue.asSecureValueWithFiles(): SecureValueWithFiles? = this as? SecureValueWithFiles
@PreviewFeature
inline fun SecureValue.requireSecureValueWithFiles(): SecureValueWithFiles = this as SecureValueWithFiles
@PreviewFeature
inline fun SecureValue.asSecureValueWithReverseSide(): SecureValueWithReverseSide? = this as? SecureValueWithReverseSide
@PreviewFeature
inline fun SecureValue.requireSecureValueWithReverseSide(): SecureValueWithReverseSide = this as SecureValueWithReverseSide
@PreviewFeature
inline fun SecureValue.asSecureValueWithTranslations(): SecureValueWithTranslations? = this as? SecureValueWithTranslations
@PreviewFeature
inline fun SecureValue.requireSecureValueWithTranslations(): SecureValueWithTranslations = this as SecureValueWithTranslations
@PreviewFeature
inline fun Message.asAnonymousGroupContentMessageImpl(): AnonymousGroupContentMessageImpl<MessageContent>? = this as? AnonymousGroupContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.requireAnonymousGroupContentMessageImpl(): AnonymousGroupContentMessageImpl<MessageContent> = this as AnonymousGroupContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.asChannelContentMessageImpl(): ChannelContentMessageImpl<MessageContent>? = this as? ChannelContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.requireChannelContentMessageImpl(): ChannelContentMessageImpl<MessageContent> = this as ChannelContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.asFromChannelGroupContentMessageImpl(): FromChannelGroupContentMessageImpl<MessageContent>? = this as? FromChannelGroupContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.requireFromChannelGroupContentMessageImpl(): FromChannelGroupContentMessageImpl<MessageContent> = this as FromChannelGroupContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.asPassportMessage(): PassportMessage? = this as? PassportMessage
@PreviewFeature
inline fun Message.requirePassportMessage(): PassportMessage = this as PassportMessage
@PreviewFeature
inline fun Message.asPrivateContentMessageImpl(): PrivateContentMessageImpl<MessageContent>? = this as? PrivateContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.requirePrivateContentMessageImpl(): PrivateContentMessageImpl<MessageContent> = this as PrivateContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.asChannelEventMessage(): ChannelEventMessage<ChannelEvent>? = this as? ChannelEventMessage<ChannelEvent>
@PreviewFeature
inline fun Message.requireChannelEventMessage(): ChannelEventMessage<ChannelEvent> = this as ChannelEventMessage<ChannelEvent>
@PreviewFeature
inline fun Message.asChannelMediaGroupMessage(): ChannelMediaGroupMessage<MediaGroupContent>? = this as? ChannelMediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.requireChannelMediaGroupMessage(): ChannelMediaGroupMessage<MediaGroupContent> = this as ChannelMediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.asCommonGroupEventMessage(): CommonGroupEventMessage<GroupEvent>? = this as? CommonGroupEventMessage<GroupEvent>
@PreviewFeature
inline fun Message.requireCommonGroupEventMessage(): CommonGroupEventMessage<GroupEvent> = this as CommonGroupEventMessage<GroupEvent>
@PreviewFeature
inline fun Message.asCommonMediaGroupMessage(): CommonMediaGroupMessage<MediaGroupContent>? = this as? CommonMediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.requireCommonMediaGroupMessage(): CommonMediaGroupMessage<MediaGroupContent> = this as CommonMediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.asCommonSupergroupEventMessage(): CommonSupergroupEventMessage<SupergroupEvent>? = this as? CommonSupergroupEventMessage<SupergroupEvent>
@PreviewFeature
inline fun Message.requireCommonSupergroupEventMessage(): CommonSupergroupEventMessage<SupergroupEvent> = this as CommonSupergroupEventMessage<SupergroupEvent>
@PreviewFeature
inline fun Message.asAnonymousGroupContentMessage(): AnonymousGroupContentMessage<MessageContent>? = this as? AnonymousGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requireAnonymousGroupContentMessage(): AnonymousGroupContentMessage<MessageContent> = this as AnonymousGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asChannelContentMessage(): ChannelContentMessageImpl<MessageContent>? = this as? ChannelContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.requireChannelContentMessage(): ChannelContentMessageImpl<MessageContent> = this as ChannelContentMessageImpl<MessageContent>
@PreviewFeature
inline fun Message.asChatEventMessage(): ChatEventMessage<ChatEvent>? = this as? ChatEventMessage<ChatEvent>
@PreviewFeature
inline fun Message.requireChatEventMessage(): ChatEventMessage<ChatEvent> = this as ChatEventMessage<ChatEvent>
@PreviewFeature
inline fun Message.asCommonGroupContentMessage(): CommonGroupContentMessage<MessageContent>? = this as? CommonGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requireCommonGroupContentMessage(): CommonGroupContentMessage<MessageContent> = this as CommonGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asCommonMessage(): CommonMessage<MessageContent>? = this as? CommonMessage<MessageContent>
@PreviewFeature
inline fun Message.requireCommonMessage(): CommonMessage<MessageContent> = this as CommonMessage<MessageContent>
@PreviewFeature
inline fun Message.asContentMessage(): ContentMessage<MessageContent>? = this as? ContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requireContentMessage(): ContentMessage<MessageContent> = this as ContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asFromChannelGroupContentMessage(): FromChannelGroupContentMessage<MessageContent>? = this as? FromChannelGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requireFromChannelGroupContentMessage(): FromChannelGroupContentMessage<MessageContent> = this as FromChannelGroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asGroupEventMessage(): GroupEventMessage<GroupEvent>? = this as? GroupEventMessage<GroupEvent>
@PreviewFeature
inline fun Message.requireGroupEventMessage(): GroupEventMessage<GroupEvent> = this as GroupEventMessage<GroupEvent>
@PreviewFeature
inline fun Message.asGroupContentMessage(): GroupContentMessage<MessageContent>? = this as? GroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requireGroupContentMessage(): GroupContentMessage<MessageContent> = this as GroupContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asMediaGroupMessage(): MediaGroupMessage<MediaGroupContent>? = this as? MediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.requireMediaGroupMessage(): MediaGroupMessage<MediaGroupContent> = this as MediaGroupMessage<MediaGroupContent>
@PreviewFeature
inline fun Message.asPossiblyEditedMessage(): PossiblyEditedMessage? = this as? PossiblyEditedMessage
@PreviewFeature
inline fun Message.requirePossiblyEditedMessage(): PossiblyEditedMessage = this as PossiblyEditedMessage
@PreviewFeature
inline fun Message.asPossiblyForwardedMessage(): PossiblyForwardedMessage? = this as? PossiblyForwardedMessage
@PreviewFeature
inline fun Message.requirePossiblyForwardedMessage(): PossiblyForwardedMessage = this as PossiblyForwardedMessage
@PreviewFeature
inline fun Message.asPossiblyPaymentMessage(): PossiblyPaymentMessage? = this as? PossiblyPaymentMessage
@PreviewFeature
inline fun Message.requirePossiblyPaymentMessage(): PossiblyPaymentMessage = this as PossiblyPaymentMessage
@PreviewFeature
inline fun Message.asPrivateContentMessage(): PrivateContentMessage<MessageContent>? = this as? PrivateContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requirePrivateContentMessage(): PrivateContentMessage<MessageContent> = this as PrivateContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asPublicContentMessage(): PublicContentMessage<MessageContent>? = this as? PublicContentMessage<MessageContent>
@PreviewFeature
inline fun Message.requirePublicContentMessage(): PublicContentMessage<MessageContent> = this as PublicContentMessage<MessageContent>
@PreviewFeature
inline fun Message.asSignedMessage(): SignedMessage? = this as? SignedMessage
@PreviewFeature
inline fun Message.requireSignedMessage(): SignedMessage = this as SignedMessage
@PreviewFeature
inline fun Message.asSupergroupEventMessage(): SupergroupEventMessage<SupergroupEvent>? = this as? SupergroupEventMessage<SupergroupEvent>
@PreviewFeature
inline fun Message.requireSupergroupEventMessage(): SupergroupEventMessage<SupergroupEvent> = this as SupergroupEventMessage<SupergroupEvent>
@PreviewFeature
inline fun Message.asUnknownMessageType(): UnknownMessageType? = this as? UnknownMessageType
@PreviewFeature
inline fun Message.requireUnknownMessageType(): UnknownMessageType = this as UnknownMessageType
@PreviewFeature
inline fun Message.asPossiblySentViaBotCommonMessage(): PossiblySentViaBotCommonMessage<MessageContent>? = this as? PossiblySentViaBotCommonMessage<MessageContent>
@PreviewFeature
inline fun Message.requirePossiblySentViaBotCommonMessage(): PossiblySentViaBotCommonMessage<MessageContent> = this as PossiblySentViaBotCommonMessage<MessageContent>
@PreviewFeature
inline fun BotAction.asFindLocationAction(): FindLocationAction? = this as? FindLocationAction
@PreviewFeature
inline fun BotAction.requireFindLocationAction(): FindLocationAction = this as FindLocationAction
@PreviewFeature
inline fun BotAction.asRecordAudioAction(): RecordAudioAction? = this as? RecordAudioAction
@PreviewFeature
inline fun BotAction.requireRecordAudioAction(): RecordAudioAction = this as RecordAudioAction
@PreviewFeature
inline fun BotAction.asRecordVideoAction(): RecordVideoAction? = this as? RecordVideoAction
@PreviewFeature
inline fun BotAction.requireRecordVideoAction(): RecordVideoAction = this as RecordVideoAction
@PreviewFeature
inline fun BotAction.asRecordVideoNoteAction(): RecordVideoNoteAction? = this as? RecordVideoNoteAction
@PreviewFeature
inline fun BotAction.requireRecordVideoNoteAction(): RecordVideoNoteAction = this as RecordVideoNoteAction
@PreviewFeature
inline fun BotAction.asTypingAction(): TypingAction? = this as? TypingAction
@PreviewFeature
inline fun BotAction.requireTypingAction(): TypingAction = this as TypingAction
@PreviewFeature
inline fun BotAction.asUploadAudioAction(): UploadAudioAction? = this as? UploadAudioAction
@PreviewFeature
inline fun BotAction.requireUploadAudioAction(): UploadAudioAction = this as UploadAudioAction
@PreviewFeature
inline fun BotAction.asUploadDocumentAction(): UploadDocumentAction? = this as? UploadDocumentAction
@PreviewFeature
inline fun BotAction.requireUploadDocumentAction(): UploadDocumentAction = this as UploadDocumentAction
@PreviewFeature
inline fun BotAction.asUploadPhotoAction(): UploadPhotoAction? = this as? UploadPhotoAction
@PreviewFeature
inline fun BotAction.requireUploadPhotoAction(): UploadPhotoAction = this as UploadPhotoAction
@PreviewFeature
inline fun BotAction.asUploadVideoAction(): UploadVideoAction? = this as? UploadVideoAction
@PreviewFeature
inline fun BotAction.requireUploadVideoAction(): UploadVideoAction = this as UploadVideoAction
@PreviewFeature
inline fun BotAction.asUploadVideoNoteAction(): UploadVideoNoteAction? = this as? UploadVideoNoteAction
@PreviewFeature
inline fun BotAction.requireUploadVideoNoteAction(): UploadVideoNoteAction = this as UploadVideoNoteAction
@PreviewFeature
inline fun InlineQuery.asBaseInlineQuery(): BaseInlineQuery? = this as? BaseInlineQuery
@PreviewFeature
inline fun InlineQuery.requireBaseInlineQuery(): BaseInlineQuery = this as BaseInlineQuery
@PreviewFeature
inline fun InlineQuery.asLocationInlineQuery(): LocationInlineQuery? = this as? LocationInlineQuery
@PreviewFeature
inline fun InlineQuery.requireLocationInlineQuery(): LocationInlineQuery = this as LocationInlineQuery
@PreviewFeature
inline fun InputMessageContent.asInputContactMessageContent(): InputContactMessageContent? = this as? InputContactMessageContent
@PreviewFeature
inline fun InputMessageContent.requireInputContactMessageContent(): InputContactMessageContent = this as InputContactMessageContent
@PreviewFeature
inline fun InputMessageContent.asInputLocationMessageContent(): InputLocationMessageContent? = this as? InputLocationMessageContent
@PreviewFeature
inline fun InputMessageContent.requireInputLocationMessageContent(): InputLocationMessageContent = this as InputLocationMessageContent
@PreviewFeature
inline fun InputMessageContent.asInputTextMessageContent(): InputTextMessageContent? = this as? InputTextMessageContent
@PreviewFeature
inline fun InputMessageContent.requireInputTextMessageContent(): InputTextMessageContent = this as InputTextMessageContent
@PreviewFeature
inline fun InputMessageContent.asInputVenueMessageContent(): InputVenueMessageContent? = this as? InputVenueMessageContent
@PreviewFeature
inline fun InputMessageContent.requireInputVenueMessageContent(): InputVenueMessageContent = this as InputVenueMessageContent
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultArticle(): InlineQueryResultArticle? = this as? InlineQueryResultArticle
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultArticle(): InlineQueryResultArticle = this as InlineQueryResultArticle
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultContact(): InlineQueryResultContact? = this as? InlineQueryResultContact
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultContact(): InlineQueryResultContact = this as InlineQueryResultContact
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGame(): InlineQueryResultGame? = this as? InlineQueryResultGame
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGame(): InlineQueryResultGame = this as InlineQueryResultGame
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultLocation(): InlineQueryResultLocation? = this as? InlineQueryResultLocation
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultLocation(): InlineQueryResultLocation = this as InlineQueryResultLocation
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultStickerCached(): InlineQueryResultStickerCached? = this as? InlineQueryResultStickerCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultStickerCached(): InlineQueryResultStickerCached = this as InlineQueryResultStickerCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVenue(): InlineQueryResultVenue? = this as? InlineQueryResultVenue
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVenue(): InlineQueryResultVenue = this as InlineQueryResultVenue
@PreviewFeature
inline fun InlineQueryResult.asDescribedInlineQueryResult(): DescribedInlineQueryResult? = this as? DescribedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireDescribedInlineQueryResult(): DescribedInlineQueryResult = this as DescribedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asFileInlineQueryResult(): FileInlineQueryResult? = this as? FileInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireFileInlineQueryResult(): FileInlineQueryResult = this as FileInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asOptionallyTitledInlineQueryResult(): OptionallyTitledInlineQueryResult? = this as? OptionallyTitledInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireOptionallyTitledInlineQueryResult(): OptionallyTitledInlineQueryResult = this as OptionallyTitledInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asSizedInlineQueryResult(): SizedInlineQueryResult? = this as? SizedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireSizedInlineQueryResult(): SizedInlineQueryResult = this as SizedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asThumbSizedInlineQueryResult(): ThumbSizedInlineQueryResult? = this as? ThumbSizedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireThumbSizedInlineQueryResult(): ThumbSizedInlineQueryResult = this as ThumbSizedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asThumbedInlineQueryResult(): ThumbedInlineQueryResult? = this as? ThumbedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireThumbedInlineQueryResult(): ThumbedInlineQueryResult = this as ThumbedInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asThumbedWithMimeTypeInlineQueryResult(): ThumbedWithMimeTypeInlineQueryResult? = this as? ThumbedWithMimeTypeInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireThumbedWithMimeTypeInlineQueryResult(): ThumbedWithMimeTypeInlineQueryResult = this as ThumbedWithMimeTypeInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asTitledInlineQueryResult(): TitledInlineQueryResult? = this as? TitledInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireTitledInlineQueryResult(): TitledInlineQueryResult = this as TitledInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asUrlInlineQueryResult(): UrlInlineQueryResult? = this as? UrlInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireUrlInlineQueryResult(): UrlInlineQueryResult = this as UrlInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asWithInputMessageContentInlineQueryResult(): WithInputMessageContentInlineQueryResult? = this as? WithInputMessageContentInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.requireWithInputMessageContentInlineQueryResult(): WithInputMessageContentInlineQueryResult = this as WithInputMessageContentInlineQueryResult
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudio(): InlineQueryResultAudio? = this as? InlineQueryResultAudio
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudio(): InlineQueryResultAudio = this as InlineQueryResultAudio
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudioCached(): InlineQueryResultAudioCached? = this as? InlineQueryResultAudioCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudioCached(): InlineQueryResultAudioCached = this as InlineQueryResultAudioCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultAudioCommon(): InlineQueryResultAudioCommon? = this as? InlineQueryResultAudioCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultAudioCommon(): InlineQueryResultAudioCommon = this as InlineQueryResultAudioCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocument(): InlineQueryResultDocument? = this as? InlineQueryResultDocument
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocument(): InlineQueryResultDocument = this as InlineQueryResultDocument
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocumentCached(): InlineQueryResultDocumentCached? = this as? InlineQueryResultDocumentCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocumentCached(): InlineQueryResultDocumentCached = this as InlineQueryResultDocumentCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultDocumentCommon(): InlineQueryResultDocumentCommon? = this as? InlineQueryResultDocumentCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultDocumentCommon(): InlineQueryResultDocumentCommon = this as InlineQueryResultDocumentCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGif(): InlineQueryResultGif? = this as? InlineQueryResultGif
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGif(): InlineQueryResultGif = this as InlineQueryResultGif
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGifCached(): InlineQueryResultGifCached? = this as? InlineQueryResultGifCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGifCached(): InlineQueryResultGifCached = this as InlineQueryResultGifCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultGifCommon(): InlineQueryResultGifCommon? = this as? InlineQueryResultGifCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultGifCommon(): InlineQueryResultGifCommon = this as InlineQueryResultGifCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4Gif(): InlineQueryResultMpeg4Gif? = this as? InlineQueryResultMpeg4Gif
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4Gif(): InlineQueryResultMpeg4Gif = this as InlineQueryResultMpeg4Gif
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCached(): InlineQueryResultMpeg4GifCached? = this as? InlineQueryResultMpeg4GifCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4GifCached(): InlineQueryResultMpeg4GifCached = this as InlineQueryResultMpeg4GifCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultMpeg4GifCommon(): InlineQueryResultMpeg4GifCommon? = this as? InlineQueryResultMpeg4GifCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultMpeg4GifCommon(): InlineQueryResultMpeg4GifCommon = this as InlineQueryResultMpeg4GifCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhoto(): InlineQueryResultPhoto? = this as? InlineQueryResultPhoto
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhoto(): InlineQueryResultPhoto = this as InlineQueryResultPhoto
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhotoCached(): InlineQueryResultPhotoCached? = this as? InlineQueryResultPhotoCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhotoCached(): InlineQueryResultPhotoCached = this as InlineQueryResultPhotoCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultPhotoCommon(): InlineQueryResultPhotoCommon? = this as? InlineQueryResultPhotoCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultPhotoCommon(): InlineQueryResultPhotoCommon = this as InlineQueryResultPhotoCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideo(): InlineQueryResultVideo? = this as? InlineQueryResultVideo
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideo(): InlineQueryResultVideo = this as InlineQueryResultVideo
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideoCached(): InlineQueryResultVideoCached? = this as? InlineQueryResultVideoCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideoCached(): InlineQueryResultVideoCached = this as InlineQueryResultVideoCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVideoCommon(): InlineQueryResultVideoCommon? = this as? InlineQueryResultVideoCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVideoCommon(): InlineQueryResultVideoCommon = this as InlineQueryResultVideoCommon
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoice(): InlineQueryResultVoice? = this as? InlineQueryResultVoice
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoice(): InlineQueryResultVoice = this as InlineQueryResultVoice
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoiceCached(): InlineQueryResultVoiceCached? = this as? InlineQueryResultVoiceCached
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoiceCached(): InlineQueryResultVoiceCached = this as InlineQueryResultVoiceCached
@PreviewFeature
inline fun InlineQueryResult.asInlineQueryResultVoiceCommon(): InlineQueryResultVoiceCommon? = this as? InlineQueryResultVoiceCommon
@PreviewFeature
inline fun InlineQueryResult.requireInlineQueryResultVoiceCommon(): InlineQueryResultVoiceCommon = this as InlineQueryResultVoiceCommon
@PreviewFeature
inline fun ChatMember.asCreatorChatMember(): CreatorChatMember? = this as? CreatorChatMember
@PreviewFeature
inline fun ChatMember.requireCreatorChatMember(): CreatorChatMember = this as CreatorChatMember
@PreviewFeature
inline fun ChatMember.asKickedChatMember(): KickedChatMember? = this as? KickedChatMember
@PreviewFeature
inline fun ChatMember.requireKickedChatMember(): KickedChatMember = this as KickedChatMember
@PreviewFeature
inline fun ChatMember.asLeftChatMember(): LeftChatMember? = this as? LeftChatMember
@PreviewFeature
inline fun ChatMember.requireLeftChatMember(): LeftChatMember = this as LeftChatMember
@PreviewFeature
inline fun ChatMember.asMemberChatMember(): MemberChatMember? = this as? MemberChatMember
@PreviewFeature
inline fun ChatMember.requireMemberChatMember(): MemberChatMember = this as MemberChatMember
@PreviewFeature
inline fun ChatMember.asRestrictedChatMember(): RestrictedChatMember? = this as? RestrictedChatMember
@PreviewFeature
inline fun ChatMember.requireRestrictedChatMember(): RestrictedChatMember = this as RestrictedChatMember
@PreviewFeature
inline fun ChatMember.asAdministratorChatMember(): AdministratorChatMember? = this as? AdministratorChatMember
@PreviewFeature
inline fun ChatMember.requireAdministratorChatMember(): AdministratorChatMember = this as AdministratorChatMember
@PreviewFeature
inline fun ChatMember.asBannedChatMember(): BannedChatMember? = this as? BannedChatMember
@PreviewFeature
inline fun ChatMember.requireBannedChatMember(): BannedChatMember = this as BannedChatMember
@PreviewFeature
inline fun ChatMember.asSpecialRightsChatMember(): SpecialRightsChatMember? = this as? SpecialRightsChatMember
@PreviewFeature
inline fun ChatMember.requireSpecialRightsChatMember(): SpecialRightsChatMember = this as SpecialRightsChatMember
@PreviewFeature
inline fun InputMedia.asAudioMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia? = this as? AudioMediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.requireAudioMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia = this as AudioMediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.asDocumentMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia? = this as? DocumentMediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.requireDocumentMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia = this as DocumentMediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.asDuratedInputMedia(): DuratedInputMedia? = this as? DuratedInputMedia
@PreviewFeature
inline fun InputMedia.requireDuratedInputMedia(): DuratedInputMedia = this as DuratedInputMedia
@PreviewFeature
inline fun InputMedia.asInputMediaAnimation(): InputMediaAnimation? = this as? InputMediaAnimation
@PreviewFeature
inline fun InputMedia.requireInputMediaAnimation(): InputMediaAnimation = this as InputMediaAnimation
@PreviewFeature
inline fun InputMedia.asInputMediaAudio(): InputMediaAudio? = this as? InputMediaAudio
@PreviewFeature
inline fun InputMedia.requireInputMediaAudio(): InputMediaAudio = this as InputMediaAudio
@PreviewFeature
inline fun InputMedia.asInputMediaDocument(): InputMediaDocument? = this as? InputMediaDocument
@PreviewFeature
inline fun InputMedia.requireInputMediaDocument(): InputMediaDocument = this as InputMediaDocument
@PreviewFeature
inline fun InputMedia.asInputMediaPhoto(): InputMediaPhoto? = this as? InputMediaPhoto
@PreviewFeature
inline fun InputMedia.requireInputMediaPhoto(): InputMediaPhoto = this as InputMediaPhoto
@PreviewFeature
inline fun InputMedia.asInputMediaVideo(): InputMediaVideo? = this as? InputMediaVideo
@PreviewFeature
inline fun InputMedia.requireInputMediaVideo(): InputMediaVideo = this as InputMediaVideo
@PreviewFeature
inline fun InputMedia.asMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia? = this as? MediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.requireMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia = this as MediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.asSizedInputMedia(): SizedInputMedia? = this as? SizedInputMedia
@PreviewFeature
inline fun InputMedia.requireSizedInputMedia(): SizedInputMedia = this as SizedInputMedia
@PreviewFeature
inline fun InputMedia.asThumbedInputMedia(): ThumbedInputMedia? = this as? ThumbedInputMedia
@PreviewFeature
inline fun InputMedia.requireThumbedInputMedia(): ThumbedInputMedia = this as ThumbedInputMedia
@PreviewFeature
inline fun InputMedia.asTitledInputMedia(): TitledInputMedia? = this as? TitledInputMedia
@PreviewFeature
inline fun InputMedia.requireTitledInputMedia(): TitledInputMedia = this as TitledInputMedia
@PreviewFeature
inline fun InputMedia.asVisualMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia? = this as? VisualMediaGroupMemberInputMedia
@PreviewFeature
inline fun InputMedia.requireVisualMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia = this as VisualMediaGroupMemberInputMedia
@PreviewFeature
inline fun Update.asCallbackQueryUpdate(): CallbackQueryUpdate? = this as? CallbackQueryUpdate
@PreviewFeature
inline fun Update.requireCallbackQueryUpdate(): CallbackQueryUpdate = this as CallbackQueryUpdate
@PreviewFeature
inline fun Update.asChannelPostUpdate(): ChannelPostUpdate? = this as? ChannelPostUpdate
@PreviewFeature
inline fun Update.requireChannelPostUpdate(): ChannelPostUpdate = this as ChannelPostUpdate
@PreviewFeature
inline fun Update.asChosenInlineResultUpdate(): ChosenInlineResultUpdate? = this as? ChosenInlineResultUpdate
@PreviewFeature
inline fun Update.requireChosenInlineResultUpdate(): ChosenInlineResultUpdate = this as ChosenInlineResultUpdate
@PreviewFeature
inline fun Update.asEditChannelPostUpdate(): EditChannelPostUpdate? = this as? EditChannelPostUpdate
@PreviewFeature
inline fun Update.requireEditChannelPostUpdate(): EditChannelPostUpdate = this as EditChannelPostUpdate
@PreviewFeature
inline fun Update.asEditMessageUpdate(): EditMessageUpdate? = this as? EditMessageUpdate
@PreviewFeature
inline fun Update.requireEditMessageUpdate(): EditMessageUpdate = this as EditMessageUpdate
@PreviewFeature
inline fun Update.asInlineQueryUpdate(): InlineQueryUpdate? = this as? InlineQueryUpdate
@PreviewFeature
inline fun Update.requireInlineQueryUpdate(): InlineQueryUpdate = this as InlineQueryUpdate
@PreviewFeature
inline fun Update.asChannelPostMediaGroupUpdate(): ChannelPostMediaGroupUpdate? = this as? ChannelPostMediaGroupUpdate
@PreviewFeature
inline fun Update.requireChannelPostMediaGroupUpdate(): ChannelPostMediaGroupUpdate = this as ChannelPostMediaGroupUpdate
@PreviewFeature
inline fun Update.asEditChannelPostMediaGroupUpdate(): EditChannelPostMediaGroupUpdate? = this as? EditChannelPostMediaGroupUpdate
@PreviewFeature
inline fun Update.requireEditChannelPostMediaGroupUpdate(): EditChannelPostMediaGroupUpdate = this as EditChannelPostMediaGroupUpdate
@PreviewFeature
inline fun Update.asEditMediaGroupUpdate(): EditMediaGroupUpdate? = this as? EditMediaGroupUpdate
@PreviewFeature
inline fun Update.requireEditMediaGroupUpdate(): EditMediaGroupUpdate = this as EditMediaGroupUpdate
@PreviewFeature
inline fun Update.asEditMessageMediaGroupUpdate(): EditMessageMediaGroupUpdate? = this as? EditMessageMediaGroupUpdate
@PreviewFeature
inline fun Update.requireEditMessageMediaGroupUpdate(): EditMessageMediaGroupUpdate = this as EditMessageMediaGroupUpdate
@PreviewFeature
inline fun Update.asMediaGroupUpdate(): MediaGroupUpdate? = this as? MediaGroupUpdate
@PreviewFeature
inline fun Update.requireMediaGroupUpdate(): MediaGroupUpdate = this as MediaGroupUpdate
@PreviewFeature
inline fun Update.asMessageMediaGroupUpdate(): MessageMediaGroupUpdate? = this as? MessageMediaGroupUpdate
@PreviewFeature
inline fun Update.requireMessageMediaGroupUpdate(): MessageMediaGroupUpdate = this as MessageMediaGroupUpdate
@PreviewFeature
inline fun Update.asSentMediaGroupUpdate(): SentMediaGroupUpdate? = this as? SentMediaGroupUpdate
@PreviewFeature
inline fun Update.requireSentMediaGroupUpdate(): SentMediaGroupUpdate = this as SentMediaGroupUpdate
@PreviewFeature
inline fun Update.asMessageUpdate(): MessageUpdate? = this as? MessageUpdate
@PreviewFeature
inline fun Update.requireMessageUpdate(): MessageUpdate = this as MessageUpdate
@PreviewFeature
inline fun Update.asPollAnswerUpdate(): PollAnswerUpdate? = this as? PollAnswerUpdate
@PreviewFeature
inline fun Update.requirePollAnswerUpdate(): PollAnswerUpdate = this as PollAnswerUpdate
@PreviewFeature
inline fun Update.asPollUpdate(): PollUpdate? = this as? PollUpdate
@PreviewFeature
inline fun Update.requirePollUpdate(): PollUpdate = this as PollUpdate
@PreviewFeature
inline fun Update.asPreCheckoutQueryUpdate(): PreCheckoutQueryUpdate? = this as? PreCheckoutQueryUpdate
@PreviewFeature
inline fun Update.requirePreCheckoutQueryUpdate(): PreCheckoutQueryUpdate = this as PreCheckoutQueryUpdate
@PreviewFeature
inline fun Update.asShippingQueryUpdate(): ShippingQueryUpdate? = this as? ShippingQueryUpdate
@PreviewFeature
inline fun Update.requireShippingQueryUpdate(): ShippingQueryUpdate = this as ShippingQueryUpdate
@PreviewFeature
inline fun Update.asBaseEditMessageUpdate(): BaseEditMessageUpdate? = this as? BaseEditMessageUpdate
@PreviewFeature
inline fun Update.requireBaseEditMessageUpdate(): BaseEditMessageUpdate = this as BaseEditMessageUpdate
@PreviewFeature
inline fun Update.asBaseMessageUpdate(): BaseMessageUpdate? = this as? BaseMessageUpdate
@PreviewFeature
inline fun Update.requireBaseMessageUpdate(): BaseMessageUpdate = this as BaseMessageUpdate
@PreviewFeature
inline fun Update.asBaseSentMessageUpdate(): BaseSentMessageUpdate? = this as? BaseSentMessageUpdate
@PreviewFeature
inline fun Update.requireBaseSentMessageUpdate(): BaseSentMessageUpdate = this as BaseSentMessageUpdate
@PreviewFeature
inline fun Update.asUnknownUpdate(): UnknownUpdate? = this as? UnknownUpdate
@PreviewFeature
inline fun Update.requireUnknownUpdate(): UnknownUpdate = this as UnknownUpdate
@PreviewFeature
inline fun Update.asCommonChatMemberUpdatedUpdate(): CommonChatMemberUpdatedUpdate? = this as? CommonChatMemberUpdatedUpdate
@PreviewFeature
inline fun Update.requireCommonChatMemberUpdatedUpdate(): CommonChatMemberUpdatedUpdate = this as CommonChatMemberUpdatedUpdate
@PreviewFeature
inline fun Update.asMyChatMemberUpdatedUpdate(): MyChatMemberUpdatedUpdate? = this as? MyChatMemberUpdatedUpdate
@PreviewFeature
inline fun Update.requireMyChatMemberUpdatedUpdate(): MyChatMemberUpdatedUpdate = this as MyChatMemberUpdatedUpdate
@PreviewFeature
inline fun Update.asChatMemberUpdatedUpdate(): ChatMemberUpdatedUpdate? = this as? ChatMemberUpdatedUpdate
@PreviewFeature
inline fun Update.requireChatMemberUpdatedUpdate(): ChatMemberUpdatedUpdate = this as ChatMemberUpdatedUpdate
@PreviewFeature
inline fun TelegramMediaFile.asAnimationFile(): AnimationFile? = this as? AnimationFile
@PreviewFeature
inline fun TelegramMediaFile.requireAnimationFile(): AnimationFile = this as AnimationFile
@PreviewFeature
inline fun TelegramMediaFile.asAudioFile(): AudioFile? = this as? AudioFile
@PreviewFeature
inline fun TelegramMediaFile.requireAudioFile(): AudioFile = this as AudioFile
@PreviewFeature
inline fun TelegramMediaFile.asDocumentFile(): DocumentFile? = this as? DocumentFile
@PreviewFeature
inline fun TelegramMediaFile.requireDocumentFile(): DocumentFile = this as DocumentFile
@PreviewFeature
inline fun TelegramMediaFile.asFile(): File? = this as? File
@PreviewFeature
inline fun TelegramMediaFile.requireFile(): File = this as File
@PreviewFeature
inline fun TelegramMediaFile.asPathedFile(): PathedFile? = this as? PathedFile
@PreviewFeature
inline fun TelegramMediaFile.requirePathedFile(): PathedFile = this as PathedFile
@PreviewFeature
inline fun TelegramMediaFile.asPhotoSize(): PhotoSize? = this as? PhotoSize
@PreviewFeature
inline fun TelegramMediaFile.requirePhotoSize(): PhotoSize = this as PhotoSize
@PreviewFeature
inline fun TelegramMediaFile.asSticker(): Sticker? = this as? Sticker
@PreviewFeature
inline fun TelegramMediaFile.requireSticker(): Sticker = this as Sticker
@PreviewFeature
inline fun TelegramMediaFile.asVideoFile(): VideoFile? = this as? VideoFile
@PreviewFeature
inline fun TelegramMediaFile.requireVideoFile(): VideoFile = this as VideoFile
@PreviewFeature
inline fun TelegramMediaFile.asVideoNoteFile(): VideoNoteFile? = this as? VideoNoteFile
@PreviewFeature
inline fun TelegramMediaFile.requireVideoNoteFile(): VideoNoteFile = this as VideoNoteFile
@PreviewFeature
inline fun TelegramMediaFile.asVoiceFile(): VoiceFile? = this as? VoiceFile
@PreviewFeature
inline fun TelegramMediaFile.requireVoiceFile(): VoiceFile = this as VoiceFile
@PreviewFeature
inline fun TelegramMediaFile.asMimedMediaFile(): MimedMediaFile? = this as? MimedMediaFile
@PreviewFeature
inline fun TelegramMediaFile.requireMimedMediaFile(): MimedMediaFile = this as MimedMediaFile
@PreviewFeature
inline fun TelegramMediaFile.asPlayableMediaFile(): PlayableMediaFile? = this as? PlayableMediaFile
@PreviewFeature
inline fun TelegramMediaFile.requirePlayableMediaFile(): PlayableMediaFile = this as PlayableMediaFile
@PreviewFeature
inline fun TelegramMediaFile.asSizedMediaFile(): SizedMediaFile? = this as? SizedMediaFile
@PreviewFeature
inline fun TelegramMediaFile.requireSizedMediaFile(): SizedMediaFile = this as SizedMediaFile
@PreviewFeature
inline fun TelegramMediaFile.asThumbedMediaFile(): ThumbedMediaFile? = this as? ThumbedMediaFile
@PreviewFeature
inline fun TelegramMediaFile.requireThumbedMediaFile(): ThumbedMediaFile = this as ThumbedMediaFile
@PreviewFeature
inline fun KeyboardMarkup.asForceReply(): ForceReply? = this as? ForceReply
@PreviewFeature
inline fun KeyboardMarkup.requireForceReply(): ForceReply = this as ForceReply
@PreviewFeature
inline fun KeyboardMarkup.asInlineKeyboardMarkup(): InlineKeyboardMarkup? = this as? InlineKeyboardMarkup
@PreviewFeature
inline fun KeyboardMarkup.requireInlineKeyboardMarkup(): InlineKeyboardMarkup = this as InlineKeyboardMarkup
@PreviewFeature
inline fun KeyboardMarkup.asReplyKeyboardMarkup(): ReplyKeyboardMarkup? = this as? ReplyKeyboardMarkup
@PreviewFeature
inline fun KeyboardMarkup.requireReplyKeyboardMarkup(): ReplyKeyboardMarkup = this as ReplyKeyboardMarkup
@PreviewFeature
inline fun KeyboardMarkup.asReplyKeyboardRemove(): ReplyKeyboardRemove? = this as? ReplyKeyboardRemove
@PreviewFeature
inline fun KeyboardMarkup.requireReplyKeyboardRemove(): ReplyKeyboardRemove = this as ReplyKeyboardRemove
@PreviewFeature
inline fun InlineKeyboardButton.asCallbackDataInlineKeyboardButton(): CallbackDataInlineKeyboardButton? = this as? CallbackDataInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireCallbackDataInlineKeyboardButton(): CallbackDataInlineKeyboardButton = this as CallbackDataInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asCallbackGameInlineKeyboardButton(): CallbackGameInlineKeyboardButton? = this as? CallbackGameInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireCallbackGameInlineKeyboardButton(): CallbackGameInlineKeyboardButton = this as CallbackGameInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asLoginURLInlineKeyboardButton(): LoginURLInlineKeyboardButton? = this as? LoginURLInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireLoginURLInlineKeyboardButton(): LoginURLInlineKeyboardButton = this as LoginURLInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asPayInlineKeyboardButton(): PayInlineKeyboardButton? = this as? PayInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requirePayInlineKeyboardButton(): PayInlineKeyboardButton = this as PayInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asSwitchInlineQueryCurrentChatInlineKeyboardButton(): SwitchInlineQueryCurrentChatInlineKeyboardButton? = this as? SwitchInlineQueryCurrentChatInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireSwitchInlineQueryCurrentChatInlineKeyboardButton(): SwitchInlineQueryCurrentChatInlineKeyboardButton = this as SwitchInlineQueryCurrentChatInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asSwitchInlineQueryInlineKeyboardButton(): SwitchInlineQueryInlineKeyboardButton? = this as? SwitchInlineQueryInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireSwitchInlineQueryInlineKeyboardButton(): SwitchInlineQueryInlineKeyboardButton = this as SwitchInlineQueryInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asURLInlineKeyboardButton(): URLInlineKeyboardButton? = this as? URLInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireURLInlineKeyboardButton(): URLInlineKeyboardButton = this as URLInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.asUnknownInlineKeyboardButton(): UnknownInlineKeyboardButton? = this as? UnknownInlineKeyboardButton
@PreviewFeature
inline fun InlineKeyboardButton.requireUnknownInlineKeyboardButton(): UnknownInlineKeyboardButton = this as UnknownInlineKeyboardButton
@PreviewFeature
inline fun Poll.asMultipleAnswersPoll(): MultipleAnswersPoll? = this as? MultipleAnswersPoll
@PreviewFeature
inline fun Poll.requireMultipleAnswersPoll(): MultipleAnswersPoll = this as MultipleAnswersPoll
@PreviewFeature
inline fun Poll.asQuizPoll(): QuizPoll? = this as? QuizPoll
@PreviewFeature
inline fun Poll.requireQuizPoll(): QuizPoll = this as QuizPoll
@PreviewFeature
inline fun Poll.asRegularPoll(): RegularPoll? = this as? RegularPoll
@PreviewFeature
inline fun Poll.requireRegularPoll(): RegularPoll = this as RegularPoll
@PreviewFeature
inline fun Poll.asUnknownPollType(): UnknownPollType? = this as? UnknownPollType
@PreviewFeature
inline fun Poll.requireUnknownPollType(): UnknownPollType = this as UnknownPollType
@PreviewFeature
inline fun ResendableContent.asContactContent(): ContactContent? = this as? ContactContent
@PreviewFeature
inline fun ResendableContent.requireContactContent(): ContactContent = this as ContactContent
@PreviewFeature
inline fun ResendableContent.asDiceContent(): DiceContent? = this as? DiceContent
@PreviewFeature
inline fun ResendableContent.requireDiceContent(): DiceContent = this as DiceContent
@PreviewFeature
inline fun ResendableContent.asGameContent(): GameContent? = this as? GameContent
@PreviewFeature
inline fun ResendableContent.requireGameContent(): GameContent = this as GameContent
@PreviewFeature
inline fun ResendableContent.asLocationContent(): LocationContent? = this as? LocationContent
@PreviewFeature
inline fun ResendableContent.requireLocationContent(): LocationContent = this as LocationContent
@PreviewFeature
inline fun ResendableContent.asPollContent(): PollContent? = this as? PollContent
@PreviewFeature
inline fun ResendableContent.requirePollContent(): PollContent = this as PollContent
@PreviewFeature
inline fun ResendableContent.asTextContent(): TextContent? = this as? TextContent
@PreviewFeature
inline fun ResendableContent.requireTextContent(): TextContent = this as TextContent
@PreviewFeature
inline fun ResendableContent.asVenueContent(): VenueContent? = this as? VenueContent
@PreviewFeature
inline fun ResendableContent.requireVenueContent(): VenueContent = this as VenueContent
@PreviewFeature
inline fun ResendableContent.asAudioMediaGroupContent(): AudioMediaGroupContent? = this as? AudioMediaGroupContent
@PreviewFeature
inline fun ResendableContent.requireAudioMediaGroupContent(): AudioMediaGroupContent = this as AudioMediaGroupContent
@PreviewFeature
inline fun ResendableContent.asDocumentMediaGroupContent(): DocumentMediaGroupContent? = this as? DocumentMediaGroupContent
@PreviewFeature
inline fun ResendableContent.requireDocumentMediaGroupContent(): DocumentMediaGroupContent = this as DocumentMediaGroupContent
@PreviewFeature
inline fun ResendableContent.asMediaCollectionContent(): MediaCollectionContent<TelegramMediaFile>? = this as? MediaCollectionContent<TelegramMediaFile>
@PreviewFeature
inline fun ResendableContent.requireMediaCollectionContent(): MediaCollectionContent<TelegramMediaFile> = this as MediaCollectionContent<TelegramMediaFile>
@PreviewFeature
inline fun ResendableContent.asMediaContent(): MediaContent? = this as? MediaContent
@PreviewFeature
inline fun ResendableContent.requireMediaContent(): MediaContent = this as MediaContent
@PreviewFeature
inline fun ResendableContent.asMediaGroupContent(): MediaGroupContent? = this as? MediaGroupContent
@PreviewFeature
inline fun ResendableContent.requireMediaGroupContent(): MediaGroupContent = this as MediaGroupContent
@PreviewFeature
inline fun ResendableContent.asMessageContent(): MessageContent? = this as? MessageContent
@PreviewFeature
inline fun ResendableContent.requireMessageContent(): MessageContent = this as MessageContent
@PreviewFeature
inline fun ResendableContent.asVisualMediaGroupContent(): VisualMediaGroupContent? = this as? VisualMediaGroupContent
@PreviewFeature
inline fun ResendableContent.requireVisualMediaGroupContent(): VisualMediaGroupContent = this as VisualMediaGroupContent
@PreviewFeature
inline fun ResendableContent.asAnimationContent(): AnimationContent? = this as? AnimationContent
@PreviewFeature
inline fun ResendableContent.requireAnimationContent(): AnimationContent = this as AnimationContent
@PreviewFeature
inline fun ResendableContent.asAudioContent(): AudioContent? = this as? AudioContent
@PreviewFeature
inline fun ResendableContent.requireAudioContent(): AudioContent = this as AudioContent
@PreviewFeature
inline fun ResendableContent.asDocumentContent(): DocumentContent? = this as? DocumentContent
@PreviewFeature
inline fun ResendableContent.requireDocumentContent(): DocumentContent = this as DocumentContent
@PreviewFeature
inline fun ResendableContent.asPhotoContent(): PhotoContent? = this as? PhotoContent
@PreviewFeature
inline fun ResendableContent.requirePhotoContent(): PhotoContent = this as PhotoContent
@PreviewFeature
inline fun ResendableContent.asStickerContent(): StickerContent? = this as? StickerContent
@PreviewFeature
inline fun ResendableContent.requireStickerContent(): StickerContent = this as StickerContent
@PreviewFeature
inline fun ResendableContent.asVideoContent(): VideoContent? = this as? VideoContent
@PreviewFeature
inline fun ResendableContent.requireVideoContent(): VideoContent = this as VideoContent
@PreviewFeature
inline fun ResendableContent.asVideoNoteContent(): VideoNoteContent? = this as? VideoNoteContent
@PreviewFeature
inline fun ResendableContent.requireVideoNoteContent(): VideoNoteContent = this as VideoNoteContent
@PreviewFeature
inline fun ResendableContent.asVoiceContent(): VoiceContent? = this as? VoiceContent
@PreviewFeature
inline fun ResendableContent.requireVoiceContent(): VoiceContent = this as VoiceContent
@PreviewFeature
inline fun ResendableContent.asInvoiceContent(): InvoiceContent? = this as? InvoiceContent
@PreviewFeature
inline fun ResendableContent.requireInvoiceContent(): InvoiceContent = this as InvoiceContent
@PreviewFeature
inline fun TextSource.asMultilevelTextSource(): MultilevelTextSource? = this as? MultilevelTextSource
@PreviewFeature
inline fun TextSource.requireMultilevelTextSource(): MultilevelTextSource = this as MultilevelTextSource
@PreviewFeature
inline fun TextSource.asBoldTextSource(): BoldTextSource? = this as? BoldTextSource
@PreviewFeature
inline fun TextSource.requireBoldTextSource(): BoldTextSource = this as BoldTextSource
@PreviewFeature
inline fun TextSource.asBotCommandTextSource(): BotCommandTextSource? = this as? BotCommandTextSource
@PreviewFeature
inline fun TextSource.requireBotCommandTextSource(): BotCommandTextSource = this as BotCommandTextSource
@PreviewFeature
inline fun TextSource.asCashTagTextSource(): CashTagTextSource? = this as? CashTagTextSource
@PreviewFeature
inline fun TextSource.requireCashTagTextSource(): CashTagTextSource = this as CashTagTextSource
@PreviewFeature
inline fun TextSource.asCodeTextSource(): CodeTextSource? = this as? CodeTextSource
@PreviewFeature
inline fun TextSource.requireCodeTextSource(): CodeTextSource = this as CodeTextSource
@PreviewFeature
inline fun TextSource.asEMailTextSource(): EMailTextSource? = this as? EMailTextSource
@PreviewFeature
inline fun TextSource.requireEMailTextSource(): EMailTextSource = this as EMailTextSource
@PreviewFeature
inline fun TextSource.asHashTagTextSource(): HashTagTextSource? = this as? HashTagTextSource
@PreviewFeature
inline fun TextSource.requireHashTagTextSource(): HashTagTextSource = this as HashTagTextSource
@PreviewFeature
inline fun TextSource.asItalicTextSource(): ItalicTextSource? = this as? ItalicTextSource
@PreviewFeature
inline fun TextSource.requireItalicTextSource(): ItalicTextSource = this as ItalicTextSource
@PreviewFeature
inline fun TextSource.asMentionTextSource(): MentionTextSource? = this as? MentionTextSource
@PreviewFeature
inline fun TextSource.requireMentionTextSource(): MentionTextSource = this as MentionTextSource
@PreviewFeature
inline fun TextSource.asPhoneNumberTextSource(): PhoneNumberTextSource? = this as? PhoneNumberTextSource
@PreviewFeature
inline fun TextSource.requirePhoneNumberTextSource(): PhoneNumberTextSource = this as PhoneNumberTextSource
@PreviewFeature
inline fun TextSource.asPreTextSource(): PreTextSource? = this as? PreTextSource
@PreviewFeature
inline fun TextSource.requirePreTextSource(): PreTextSource = this as PreTextSource
@PreviewFeature
inline fun TextSource.asRegularTextSource(): RegularTextSource? = this as? RegularTextSource
@PreviewFeature
inline fun TextSource.requireRegularTextSource(): RegularTextSource = this as RegularTextSource
@PreviewFeature
inline fun TextSource.asStrikethroughTextSource(): StrikethroughTextSource? = this as? StrikethroughTextSource
@PreviewFeature
inline fun TextSource.requireStrikethroughTextSource(): StrikethroughTextSource = this as StrikethroughTextSource
@PreviewFeature
inline fun TextSource.asTextLinkTextSource(): TextLinkTextSource? = this as? TextLinkTextSource
@PreviewFeature
inline fun TextSource.requireTextLinkTextSource(): TextLinkTextSource = this as TextLinkTextSource
@PreviewFeature
inline fun TextSource.asTextMentionTextSource(): TextMentionTextSource? = this as? TextMentionTextSource
@PreviewFeature
inline fun TextSource.requireTextMentionTextSource(): TextMentionTextSource = this as TextMentionTextSource
@PreviewFeature
inline fun TextSource.asURLTextSource(): URLTextSource? = this as? URLTextSource
@PreviewFeature
inline fun TextSource.requireURLTextSource(): URLTextSource = this as URLTextSource
@PreviewFeature
inline fun TextSource.asUnderlineTextSource(): UnderlineTextSource? = this as? UnderlineTextSource
@PreviewFeature
inline fun TextSource.requireUnderlineTextSource(): UnderlineTextSource = this as UnderlineTextSource
@PreviewFeature
inline fun DiceAnimationType.asBasketballDiceAnimationType(): BasketballDiceAnimationType? = this as? BasketballDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireBasketballDiceAnimationType(): BasketballDiceAnimationType = this as BasketballDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asBowlingDiceAnimationType(): BowlingDiceAnimationType? = this as? BowlingDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireBowlingDiceAnimationType(): BowlingDiceAnimationType = this as BowlingDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asCubeDiceAnimationType(): CubeDiceAnimationType? = this as? CubeDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireCubeDiceAnimationType(): CubeDiceAnimationType = this as CubeDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asCustomDiceAnimationType(): CustomDiceAnimationType? = this as? CustomDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireCustomDiceAnimationType(): CustomDiceAnimationType = this as CustomDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asDartsDiceAnimationType(): DartsDiceAnimationType? = this as? DartsDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireDartsDiceAnimationType(): DartsDiceAnimationType = this as DartsDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asFootballDiceAnimationType(): FootballDiceAnimationType? = this as? FootballDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireFootballDiceAnimationType(): FootballDiceAnimationType = this as FootballDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.asSlotMachineDiceAnimationType(): SlotMachineDiceAnimationType? = this as? SlotMachineDiceAnimationType
@PreviewFeature
inline fun DiceAnimationType.requireSlotMachineDiceAnimationType(): SlotMachineDiceAnimationType = this as SlotMachineDiceAnimationType
@PreviewFeature
inline fun ChatEvent.asChannelChatCreated(): ChannelChatCreated? = this as? ChannelChatCreated
@PreviewFeature
inline fun ChatEvent.requireChannelChatCreated(): ChannelChatCreated = this as ChannelChatCreated
@PreviewFeature
inline fun ChatEvent.asDeleteChatPhoto(): DeleteChatPhoto? = this as? DeleteChatPhoto
@PreviewFeature
inline fun ChatEvent.requireDeleteChatPhoto(): DeleteChatPhoto = this as DeleteChatPhoto
@PreviewFeature
inline fun ChatEvent.asGroupChatCreated(): GroupChatCreated? = this as? GroupChatCreated
@PreviewFeature
inline fun ChatEvent.requireGroupChatCreated(): GroupChatCreated = this as GroupChatCreated
@PreviewFeature
inline fun ChatEvent.asLeftChatMember(): LeftChatMember? = this as? LeftChatMember
@PreviewFeature
inline fun ChatEvent.requireLeftChatMember(): LeftChatMember = this as LeftChatMember
@PreviewFeature
inline fun ChatEvent.asMessageAutoDeleteTimerChanged(): MessageAutoDeleteTimerChanged? = this as? MessageAutoDeleteTimerChanged
@PreviewFeature
inline fun ChatEvent.requireMessageAutoDeleteTimerChanged(): MessageAutoDeleteTimerChanged = this as MessageAutoDeleteTimerChanged
@PreviewFeature
inline fun ChatEvent.asNewChatMembers(): NewChatMembers? = this as? NewChatMembers
@PreviewFeature
inline fun ChatEvent.requireNewChatMembers(): NewChatMembers = this as NewChatMembers
@PreviewFeature
inline fun ChatEvent.asNewChatPhoto(): NewChatPhoto? = this as? NewChatPhoto
@PreviewFeature
inline fun ChatEvent.requireNewChatPhoto(): NewChatPhoto = this as NewChatPhoto
@PreviewFeature
inline fun ChatEvent.asNewChatTitle(): NewChatTitle? = this as? NewChatTitle
@PreviewFeature
inline fun ChatEvent.requireNewChatTitle(): NewChatTitle = this as NewChatTitle
@PreviewFeature
inline fun ChatEvent.asPinnedMessage(): PinnedMessage? = this as? PinnedMessage
@PreviewFeature
inline fun ChatEvent.requirePinnedMessage(): PinnedMessage = this as PinnedMessage
@PreviewFeature
inline fun ChatEvent.asProximityAlertTriggered(): ProximityAlertTriggered? = this as? ProximityAlertTriggered
@PreviewFeature
inline fun ChatEvent.requireProximityAlertTriggered(): ProximityAlertTriggered = this as ProximityAlertTriggered
@PreviewFeature
inline fun ChatEvent.asSupergroupChatCreated(): SupergroupChatCreated? = this as? SupergroupChatCreated
@PreviewFeature
inline fun ChatEvent.requireSupergroupChatCreated(): SupergroupChatCreated = this as SupergroupChatCreated
@PreviewFeature
inline fun ChatEvent.asChannelEvent(): ChannelEvent? = this as? ChannelEvent
@PreviewFeature
inline fun ChatEvent.requireChannelEvent(): ChannelEvent = this as ChannelEvent
@PreviewFeature
inline fun ChatEvent.asCommonEvent(): CommonEvent? = this as? CommonEvent
@PreviewFeature
inline fun ChatEvent.requireCommonEvent(): CommonEvent = this as CommonEvent
@PreviewFeature
inline fun ChatEvent.asGroupEvent(): GroupEvent? = this as? GroupEvent
@PreviewFeature
inline fun ChatEvent.requireGroupEvent(): GroupEvent = this as GroupEvent
@PreviewFeature
inline fun ChatEvent.asSupergroupEvent(): SupergroupEvent? = this as? SupergroupEvent
@PreviewFeature
inline fun ChatEvent.requireSupergroupEvent(): SupergroupEvent = this as SupergroupEvent
@PreviewFeature
inline fun ChatEvent.asVoiceChatEvent(): VoiceChatEvent? = this as? VoiceChatEvent
@PreviewFeature
inline fun ChatEvent.requireVoiceChatEvent(): VoiceChatEvent = this as VoiceChatEvent
@PreviewFeature
inline fun ChatEvent.asVoiceChatEnded(): VoiceChatEnded? = this as? VoiceChatEnded
@PreviewFeature
inline fun ChatEvent.requireVoiceChatEnded(): VoiceChatEnded = this as VoiceChatEnded
@PreviewFeature
inline fun ChatEvent.asVoiceChatParticipantsInvited(): VoiceChatParticipantsInvited? = this as? VoiceChatParticipantsInvited
@PreviewFeature
inline fun ChatEvent.requireVoiceChatParticipantsInvited(): VoiceChatParticipantsInvited = this as VoiceChatParticipantsInvited
@PreviewFeature
inline fun ChatEvent.asVoiceChatStarted(): VoiceChatStarted? = this as? VoiceChatStarted
@PreviewFeature
inline fun ChatEvent.requireVoiceChatStarted(): VoiceChatStarted = this as VoiceChatStarted
