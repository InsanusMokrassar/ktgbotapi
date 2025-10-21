package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextData
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.DefaultCustomBehaviourContextAndTypeReceiver.Companion.BOT_INFO_RECEIVER
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.botInfo
import dev.inmo.tgbotapi.extensions.utils.botCommandTextSourceOrNull
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.types.chat.ExtendedBot
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Returns bot information (result of [GetMe]) associated with this [BehaviourContext], if available.
 *
 * The value is lazily computed and cached by [DefaultCustomBehaviourContextAndTypeReceiver] when it is used
 * to wrap behaviour handlers. If this context was not prepared by that wrapper, the function returns null.
 *
 * Thread-safety:
 * - The underlying retrieval is protected by a mutex to ensure the info is fetched at most once per context.
 *
 * @return [ExtendedBot] with bot details, or null if no bot info provider is registered in this context.
 */
suspend fun BehaviourContext.botInfo(): ExtendedBot? {
    return (data[BOT_INFO_RECEIVER] as? DefaultCustomBehaviourContextAndTypeReceiver.IReceiver) ?.run {
        invoke()
    }
}

suspend fun BehaviourContext.containsCommand(commandRegex: Regex, textSources: TextSourcesList) = textSources.any {
    val command = it.botCommandTextSourceOrNull() ?.takeIf {
        commandRegex.matches(it.command)
    } ?: return@any false
    if (command.username == null) {
        return@any true
    }
    val botInfo = botInfo()
    if (botInfo == null || command.username == botInfo.username) {
        return@any true
    }
    false
}

context(textSources: TextSourcesList, bc: BehaviourContext)
suspend fun containsCommand(commandRegex: Regex) = bc.containsCommand(commandRegex, textSources)

context(textSources: TextSourcesList, bc: BehaviourContext)
suspend fun containsCommand(command: String) = containsCommand(Regex(command))

suspend fun BehaviourContext.containsCommand(command: String, textSources: TextSourcesList) = containsCommand(Regex(command), textSources)

@Warning("It is internal API and can be changed without notes")
fun <BC : BehaviourContext, R, U : Update> CustomBehaviourContextAndTypeReceiver<BC, R, U>.withDefaultReceiver(
    data: BehaviourContextData
) = DefaultCustomBehaviourContextAndTypeReceiver(this).also {
    it(data)
}
@Warning("It is internal API and can be changed without notes")
fun <BC : BehaviourContext, R, U : Update> CustomBehaviourContextAndTypeReceiver<BC, R, U>.optionallyWithDefaultReceiver(
    include: Boolean,
    data: BehaviourContextData
) = if (include) {
    withDefaultReceiver(data)
} else {
    this
}

/**
 * Behaviour wrapper that injects a lazily-evaluated, cached provider of bot information into the [BehaviourContext].
 *
 * When this wrapper is used, any code executed inside it may call [BehaviourContext.botInfo] to obtain
 * the current bot's [ExtendedBot] information. The info is fetched via [GetMe] only once and then cached
 * for subsequent calls, with concurrent access synchronized by a mutex.
 *
 * @param BC Type of [BehaviourContext] used in the wrapped logic.
 * @param R Result type produced by the wrapped receiver.
 * @param U Type of [Update] handled by the wrapped receiver.
 * @param wrapperReceiver The original receiver to be invoked after the bot info provider is registered in the context.
 */
class DefaultCustomBehaviourContextAndTypeReceiver<BC : BehaviourContext, R, U : Update>(
    private val wrapperReceiver: CustomBehaviourContextAndTypeReceiver<BC, R, U>
) : suspend (BC, U) -> R {
    private var botInfo: ExtendedBot? = null
    private val mutex = Mutex()

    /**
     * Lightweight provider of bot information bound to a [BehaviourContext].
     *
     * Implementations must return the current [ExtendedBot] instance, computing it if necessary.
     */
    fun interface IReceiver {
        /**
         * Get or compute the bot information for the current [BehaviourContext].
         *
         * Implementations may cache the value and are free to apply synchronization as needed.
         */
        suspend fun BehaviourContext.invoke(): ExtendedBot
    }

    /**
     * Internal provider that performs a single, thread-safe retrieval of bot info via [GetMe] and caches it.
     */
    private val internalReceiver: IReceiver = IReceiver {
        botInfo ?: mutex.withLock {
            botInfo ?: execute(GetMe).also {
                botInfo = it
            }
        }
    }

    operator fun invoke(data: BehaviourContextData) {
        data[BOT_INFO_RECEIVER] = internalReceiver
    }

    /**
     * Registers the internal bot info provider in [BehaviourContext.data] and then delegates to [wrapperReceiver].
     *
     * The provider is stored under [BOT_INFO_RECEIVER] key, enabling calls to [BehaviourContext.botInfo] within
     * the wrapped behaviour.
     */
    override suspend fun invoke(p1: BC, p2: U): R {
        invoke(p1.data)

        return wrapperReceiver(p1, p2)
    }

    companion object {
        /**
         * Key used to store the bot info provider inside [BehaviourContext.data].
         */
        const val BOT_INFO_RECEIVER = "ktgbotapi_bot_info_receiver"
    }
}