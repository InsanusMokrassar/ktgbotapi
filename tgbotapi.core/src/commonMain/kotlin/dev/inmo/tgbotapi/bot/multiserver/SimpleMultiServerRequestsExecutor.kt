package dev.inmo.tgbotapi.bot.multiserver

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.bot.ktor.KtorRequestsExecutorBuilder
import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import kotlinx.coroutines.*
import kotlin.js.JsName
import kotlin.jvm.JvmName

/**
 * This type of [RequestsExecutor] (aka [TelegramBot]) has been created to aggregate several bots under the hood and make
 * requests, for example, with changing of api url
 *
 * @param bots Bots which will be used to [execute] [Request]s
 * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
 * when request execution just started and this call is first in context of one [execute]. By default, will select next
 * [TelegramBot] when called (or first when current index is last or -1)
 * @param onClose This method will be called inside of [close] method. By default, will close all [bots]
 */
class SimpleMultiServerRequestsExecutor(
    private val bots: List<TelegramBot>,
    private val botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
        getOrElse(i + 1) { first() }
    },
    private val onClose: () -> Unit = {
        bots.forEach(TelegramBot::close)
    },
) : RequestsExecutor {
    override suspend fun <T : Any> execute(request: Request<T>): T {
        var currentBot = bots.botSelector(-1, null)
        while (currentCoroutineContext().isActive) {
            val i = bots.indexOf(currentBot)
            runCatching {
                currentBot.execute(request)
            }.onSuccess {
                return it
            }.onFailure {
                currentBot = bots.botSelector(i, it)
            }
        }
        error("Coroutine has been terminated")
    }

    override fun close() {
        onClose()
    }

    companion object {
        /**
         * @param keepers Will be used to create result [bots]
         * @param builder Will be called with each item from [keepers] in process of bots creation
         * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
         * when request execution just started and this call is first in context of one [execute]. By default, will select next
         * [TelegramBot] when called (or first when current index is last or -1)
         * @param onClose This method will be called inside of [close] method. By default, will close all [bots]
         */
        @JvmName("createByUrlsKeepers")
        @JsName("createByUrlsKeepers")
        inline operator fun invoke(
            keepers: Iterable<TelegramAPIUrlsKeeper>,
            crossinline builder: KtorRequestsExecutorBuilder.(TelegramAPIUrlsKeeper) -> Unit = {},
            noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
                getOrElse(i + 1) { first() }
            },
            noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
        ): SimpleMultiServerRequestsExecutor {
            val bots = keepers.map { telegramBot(it) { builder(it) } }
            return if (onClose == null) {
                SimpleMultiServerRequestsExecutor(bots, botSelector)
            } else {
                SimpleMultiServerRequestsExecutor(bots, botSelector) {
                    onClose(bots)
                }
            }
        }

        /**
         * @param tokens Will be used to create result [bots]
         * @param builder Will be called with each item from [tokens] in process of bots creation
         * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
         * when request execution just started and this call is first in context of one [execute]. By default, will select next
         * [TelegramBot] when called (or first when current index is last or -1)
         * @param onClose This method will be called inside of [close] method. By default, will close all [bots]
         */
        @JvmName("createByTokens")
        @JsName("createByTokens")
        inline operator fun invoke(
            tokens: Iterable<String>,
            crossinline builder: KtorRequestsExecutorBuilder.(String) -> Unit = {},
            noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
                getOrElse(i + 1) { first() }
            },
            noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
        ): SimpleMultiServerRequestsExecutor {
            val bots = tokens.map { telegramBot(it) { builder(it) } }
            return if (onClose == null) {
                SimpleMultiServerRequestsExecutor(bots, botSelector)
            } else {
                SimpleMultiServerRequestsExecutor(bots, botSelector) {
                    onClose(bots)
                }
            }
        }

        /**
         * @param tokens [Pair]s with first parameter as a token and second parameter api url. Will be used to create
         * result [bots]
         * @param builder Will be called with each item from [tokens] in process of bots creation
         * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
         * when request execution just started and this call is first in context of one [execute]. By default, will select next
         * [TelegramBot] when called (or first when current index is last or -1)
         * @param onClose This method will be called inside of [close] method. By default, will close all [bots]
         */
        @JvmName("createByTokensAndApiUrls")
        @JsName("createByTokensAndApiUrls")
        inline operator fun invoke(
            tokens: Iterable<Pair<String, String>>,
            crossinline builder: KtorRequestsExecutorBuilder.(Pair<String, String>) -> Unit = {},
            noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
                getOrElse(i + 1) { first() }
            },
            noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
        ): SimpleMultiServerRequestsExecutor {
            val bots = tokens.map { telegramBot(it.first, it.second) { builder(it) } }
            return if (onClose == null) {
                SimpleMultiServerRequestsExecutor(bots, botSelector)
            } else {
                SimpleMultiServerRequestsExecutor(bots, botSelector) {
                    onClose(bots)
                }
            }
        }
    }
}

/**
 * Creates [SimpleMultiServerRequestsExecutor]
 *
 * @param bots Bots which will be used to [SimpleMultiServerRequestsExecutor.execute] [Request]s
 * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
 * when request execution just started and this call is first in context of one
 * [SimpleMultiServerRequestsExecutor.execute]. By default, will select next [TelegramBot] when called (or first when
 * current index is last or -1)
 * @param onClose This method will be called inside of [SimpleMultiServerRequestsExecutor.close] method. By default, will close all [bots]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun telegramBot(
    bots: List<TelegramBot>,
    noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
        getOrElse(i + 1) { first() }
    },
    noinline onClose: () -> Unit = {
        bots.forEach(TelegramBot::close)
    },
): TelegramBot = SimpleMultiServerRequestsExecutor(bots, botSelector, onClose)

/**
 * @param keepers Will be used to create result [SimpleMultiServerRequestsExecutor.bots]
 * @param builder Will be called with each item from [keepers] in process of bots creation
 * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
 * when request execution just started and this call is first in context of one
 * [SimpleMultiServerRequestsExecutor.execute]. By default, will select next [TelegramBot] when called (or first when
 * current index is last or -1)
 * @param onClose This method will be called inside of [SimpleMultiServerRequestsExecutor.close] method. By default,
 * will close all [SimpleMultiServerRequestsExecutor.bots]
 */
@JvmName("telegramBotByApiUrlsKeepers")
@JsName("telegramBotByApiUrlsKeepers")
inline fun telegramBot(
    keepers: Iterable<TelegramAPIUrlsKeeper>,
    crossinline builder: KtorRequestsExecutorBuilder.(TelegramAPIUrlsKeeper) -> Unit = {},
    noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
        getOrElse(i + 1) { first() }
    },
    noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
): TelegramBot = SimpleMultiServerRequestsExecutor(keepers, builder, botSelector, onClose)

/**
 * @param tokens Will be used to create result [SimpleMultiServerRequestsExecutor.bots]
 * @param builder Will be called with each item from [tokens] in process of bots creation
 * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
 * when request execution just started and this call is first in context of one
 * [SimpleMultiServerRequestsExecutor.execute]. By default, will select next [TelegramBot] when called (or first when
 * current index is last or -1)
 * @param onClose This method will be called inside of [SimpleMultiServerRequestsExecutor.close] method. By default,
 * will close all [SimpleMultiServerRequestsExecutor.bots]
 */
@JvmName("telegramBotByTokens")
@JsName("telegramBotByTokens")
inline fun telegramBot(
    tokens: Iterable<String>,
    crossinline builder: KtorRequestsExecutorBuilder.(String) -> Unit = {},
    noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
        getOrElse(i + 1) { first() }
    },
    noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
): TelegramBot = SimpleMultiServerRequestsExecutor(tokens, builder, botSelector, onClose)

/**
 * @param tokens [Pair]s with first parameter as a token and second parameter api url. Will be used to create
 * result [SimpleMultiServerRequestsExecutor.bots]
 * @param builder Will be called with each item from [tokens] in process of bots creation
 * @param botSelector It is strategy by which the bot is selected for the execution. This lambda will receive **-1**
 * when request execution just started and this call is first in context of one
 * [SimpleMultiServerRequestsExecutor.execute]. By default, will select next
 * [TelegramBot] when called (or first when current index is last or -1)
 * @param onClose This method will be called inside of [SimpleMultiServerRequestsExecutor.close] method. By default,
 * will close all [SimpleMultiServerRequestsExecutor.bots]
 */
@JvmName("telegramBotByTokensAndApiUrls")
@JsName("telegramBotByTokensAndApiUrls")
inline fun telegramBot(
    tokens: Iterable<Pair<String, String>>,
    crossinline builder: KtorRequestsExecutorBuilder.(Pair<String, String>) -> Unit = {},
    noinline botSelector: suspend List<TelegramBot>.(currentBotIndex: Int, t: Throwable?) -> TelegramBot = { i, _ ->
        getOrElse(i + 1) { first() }
    },
    noinline onClose: ((List<TelegramBot>) -> Unit)? = null,
): TelegramBot = SimpleMultiServerRequestsExecutor(tokens, builder, botSelector, onClose)
