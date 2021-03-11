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
import dev.inmo.tgbotapi.types.dice.DiceAnimationType
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ChatEvent
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.abstracts.ResendableContent
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValue
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
    result[Update::class] = KlassIndex.getSubclasses(Update::class).toSet()
    result[ChatEvent::class] = KlassIndex.getSubclasses(ChatEvent::class).toSet()
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