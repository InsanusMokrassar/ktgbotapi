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
import dev.inmo.tgbotapi.types.message.content.abstracts.ResendableContent
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlin.reflect.KClass

fun printlnInstanceSubclassesFuns(kclass: KClass<*>) {
    KlassIndex.getSubclasses(Message::class).forEach { subclass ->
        println("inline fun ${kclass.simpleName}.as${subclass.simpleName}(): ${subclass.simpleName}? = this as? ${subclass.simpleName}")
    }
}

fun printlnInstancesSubclassesFuns(kclass: KClass<*>, subclasses: Set<KClass<*>>): List<Pair<String, String>> {
    return subclasses.map { subclass ->
        val typeUpperBounds = subclass.typeParameters.map { it.upperBounds.first() }
        val imports = "import ${subclass.qualifiedName}" + if (typeUpperBounds.isEmpty()) "" else typeUpperBounds.joinToString("\nimport ", "\nimport ")
        val subtype = "${subclass.simpleName}${if (typeUpperBounds.isEmpty()) "" else "<${typeUpperBounds.joinToString() { (it.classifier as KClass<*>).simpleName!! }}>"}"
        val code = "@PreviewFeature\ninline fun ${kclass.simpleName}.as${subclass.simpleName}(): $subtype? = this as? $subtype\n" +
            "@PreviewFeature\ninline fun ${kclass.simpleName}.require${subclass.simpleName}(): $subtype = this as $subtype"
        imports to code
    }
}

val result = mutableMapOf<KClass<*>, Set<KClass<*>>>()


fun main() {
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
            dev.inmo.tgbotapi.types.message.PassportMessage::class,
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
    result[dev.inmo.tgbotapi.types.passport.PassportElementError::class] =
        setOf(dev.inmo.tgbotapi.types.passport.PassportElementErrorDataField::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorFile::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorFiles::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorFrontSide::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorReverseSide::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorSelfie::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFile::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorTranslationFiles::class,
            dev.inmo.tgbotapi.types.passport.PassportElementErrorUnspecified::class,
            dev.inmo.tgbotapi.types.passport.PassportElementFileError::class,
            dev.inmo.tgbotapi.types.passport.PassportElementFilesError::class,
            dev.inmo.tgbotapi.types.passport.PassportMultipleElementsError::class,
            dev.inmo.tgbotapi.types.passport.PassportSingleElementError::class,
            dev.inmo.tgbotapi.types.passport.UnknownPassportElementError::class)
    result[dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.EncryptedPassportElement::class] = setOf(dev.inmo.tgbotapi.types.passport.encrypted_data.BankStatement::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.CommonPassport::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.DriverLicense::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.Email::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedAddress::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.EncryptedPersonalDetails::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.IdentityCard::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.InternalPassport::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.Passport::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.PassportRegistration::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.PhoneNumber::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.RentalAgreement::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.TemporaryRegistration::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.TranslatableFilesCollection::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.TranslatableIDDocument::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.UtilityBill::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.FilesCollection::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.Translatable::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.UnknownEncryptedPassportElement::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithData::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithEmail::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithFrontSide::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithPhoneNumber::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithReverseSide::class,
        dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithSelfie::class)
    println("import dev.inmo.tgbotapi.utils.PreviewFeature")
    val importsToFuns = result.keys.flatMap {
        println("import ${it.qualifiedName}")
        printlnInstancesSubclassesFuns(it, result.getValue(it))
    }
    importsToFuns.forEach { println(it.first) }
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