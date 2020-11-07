package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.extensions.utils.nonstrictJsonFormat
import dev.inmo.tgbotapi.extensions.utils.updates.flowsUpdatesFilter
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.media.base.MultipartRequestImpl
import dev.inmo.tgbotapi.requests.webhook.SetWebhook
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import dev.inmo.tgbotapi.updateshandlers.*
import dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig
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
 * @param [block] Some receiver block like [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter]
 *
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
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
        safely(
            exceptionsHandler ?: {}
        ) {
            val asJson =
                nonstrictJsonFormat.parseToJsonElement(call.receiveText())
            val update = nonstrictJsonFormat.decodeFromJsonElement(
                UpdateDeserializationStrategy,
                asJson
            )
            transformer(update)
        }
        call.respond("Ok")
    }
}

fun Route.includeWebhookHandlingInRouteWithFlows(
    scope: CoroutineScope,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    block: FlowsUpdatesFilter.() -> Unit
) = includeWebhookHandlingInRoute(
    scope,
    exceptionsHandler,
    flowsUpdatesFilter(block = block).asUpdateReceiver
)

/**
 * Setting up ktor server, set webhook info via [SetWebhook] request.
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot. If null - will be set up in root of host
 * @param scope Scope which will be used for
 * @param privateKeyConfig If configured - server will be created with [sslConnector]. [connector] will be used otherwise
 *
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
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
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
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
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
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
