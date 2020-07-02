package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates.retrieving

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.nonstrictJsonFormat
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.MultipartRequestImpl
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UpdateDeserializationStrategy
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdateReceiver
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.webhook.WebhookPrivateKeyConfig
import com.github.insanusmokrassar.TelegramBotAPI.utils.ExceptionHandler
import com.github.insanusmokrassar.TelegramBotAPI.utils.handleSafely
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors


/**
 * Allows to include webhook in custom route everywhere in your server
 *
 * @param [scope] Will be used for mapping of media groups
 * @param [exceptionsHandler] Pass this parameter to set custom exception handler for getting updates
 * @param [block] Some receiver block like [com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter]
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun Route.includeWebhookHandlingInRoute(
    scope: CoroutineScope,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    block: UpdateReceiver<Update>
) {
    val transformer = scope.updateHandlerWithMediaGroupsAdaptation(block)
    post {
        handleSafely(
            exceptionsHandler ?: {}
        ) {
            val asJson =
                nonstrictJsonFormat.parseJson(call.receiveText())
            val update = nonstrictJsonFormat.fromJson(
                UpdateDeserializationStrategy,
                asJson
            )
            transformer(update)
        }
        call.respond("Ok")
    }
}

/**
 * Setting up ktor server, set webhook info via [SetWebhook] request.
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot. If null - will be set up in root of host
 * @param scope Scope which will be used for
 * @param privateKeyConfig If configured - server will be created with [sslConnector]. [connector] will be used otherwise
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun startListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    exceptionsHandler: ExceptionHandler<Unit>,
    listenHost: String = "0.0.0.0",
    listenRoute: String? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    block: UpdateReceiver<Update>
): ApplicationEngine {
    val env = applicationEngineEnvironment {

        module {
            routing {
                listenRoute ?.also {
                    createRouteFromPath(it).includeWebhookHandlingInRoute(scope, exceptionsHandler, block)
                } ?: includeWebhookHandlingInRoute(scope, exceptionsHandler, block)
            }
        }
        privateKeyConfig ?.let {
            sslConnector(
                privateKeyConfig.keyStore,
                privateKeyConfig.aliasName,
                privateKeyConfig::keyStorePassword,
                privateKeyConfig::aliasPassword
            ) {
                host = listenHost
                port = listenPort
            }
        } ?: connector {
            host = listenHost
            port = listenPort
        }

    }

    return embeddedServer(engineFactory, env).also {
        it.start(false)
    }
}

private suspend fun RequestsExecutor.internalSetWebhookInfoAndStartListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    setWebhookRequest: Request<Boolean>,
    exceptionsHandler: ExceptionHandler<Unit> = {},
    listenHost: String = "0.0.0.0",
    listenRoute: String? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    block: UpdateReceiver<Update>
): ApplicationEngine {
    return try {
        execute(setWebhookRequest)
        startListenWebhooks(listenPort, engineFactory, exceptionsHandler, listenHost, listenRoute, privateKeyConfig, scope, block)
    } catch (e: Exception) {
        throw e
    }
}

/**
 * Setting up ktor server, set webhook info via [SetWebhook] request.
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param scope Scope which will be used for
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
@Suppress("unused")
suspend fun RequestsExecutor.setWebhookInfoAndStartListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    setWebhookRequest: SetWebhook,
    exceptionsHandler: ExceptionHandler<Unit> = {},
    listenHost: String = "0.0.0.0",
    listenRoute: String = "/",
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    block: UpdateReceiver<Update>
): ApplicationEngine = internalSetWebhookInfoAndStartListenWebhooks(
    listenPort,
    engineFactory,
    setWebhookRequest as Request<Boolean>,
    exceptionsHandler,
    listenHost,
    listenRoute,
    privateKeyConfig,
    scope,
    block
)

/**
 * Setting up ktor server, set webhook info via [SetWebhook] request.
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param scope Scope which will be used for
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
@Suppress("unused")
suspend fun RequestsExecutor.setWebhookInfoAndStartListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    setWebhookRequest: MultipartRequestImpl<SetWebhook, Map<String, MultipartFile>, Boolean>,
    exceptionsHandler: ExceptionHandler<Unit> = {},
    listenHost: String = "0.0.0.0",
    listenRoute: String? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    block: UpdateReceiver<Update>
): ApplicationEngine = internalSetWebhookInfoAndStartListenWebhooks(
    listenPort,
    engineFactory,
    setWebhookRequest as Request<Boolean>,
    exceptionsHandler,
    listenHost,
    listenRoute,
    privateKeyConfig,
    scope,
    block
)
