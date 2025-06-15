package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.kslog.common.KSLog
import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.coroutines.runCatchingSafely
import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.extensions.utils.nonstrictJsonFormat
import dev.inmo.tgbotapi.extensions.utils.updates.flowsUpdatesFilter
import dev.inmo.tgbotapi.requests.webhook.SetWebhookRequest
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.updateshandlers.UpdateReceiver
import dev.inmo.tgbotapi.updateshandlers.UpdatesFilter
import dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors


/**
 * Allows to include webhook in custom route everywhere in your server
 *
 * @param [scope] Will be used for mapping of media groups
 * @param [exceptionsHandler] Pass this parameter to set custom exception handler for getting updates
 * @param [block] Some receiver block like [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter]
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 *
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun Route.includeWebhookHandlingInRoute(
    scope: CoroutineScope,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    mediaGroupsDebounceTimeMillis: Long = 1000L,
    logger: KSLog = DefaultKTgBotAPIKSLog,
    block: UpdateReceiver<Update>,
) {
    val transformer = scope.updateHandlerWithMediaGroupsAdaptation(block, mediaGroupsDebounceTimeMillis, logger = logger)
    post {
        try {
            runCatchingSafely {
                val update = nonstrictJsonFormat.decodeFromString(
                    UpdateDeserializationStrategy,
                    call.receiveText()
                )
                transformer(update)
            }.onSuccess {
                call.respond(HttpStatusCode.OK)
            }.onFailure {
                call.respond(HttpStatusCode.InternalServerError)
            }.getOrThrow()
        } catch (e: Throwable) {
            exceptionsHandler?.invoke(e)
        }
    }
}

/**
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
fun Route.includeWebhookHandlingInRouteWithFlows(
    scope: CoroutineScope,
    exceptionsHandler: ExceptionHandler<Unit>? = null,
    mediaGroupsDebounceTimeMillis: Long = 1000L,
    logger: KSLog = DefaultKTgBotAPIKSLog,
    block: FlowsUpdatesFilter.() -> Unit,
) = includeWebhookHandlingInRoute(
    scope,
    exceptionsHandler,
    mediaGroupsDebounceTimeMillis,
    logger,
    flowsUpdatesFilter(block = block).asUpdateReceiver
)

/**
 * Setting up ktor server
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot. If null - will be set up in root of host
 * @param scope Scope which will be used for
 * @param privateKeyConfig If configured - server will be created with [sslConnector]. [connector] will be used otherwise
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 *
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> startListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<TEngine, TConfiguration>,
    exceptionsHandler: ExceptionHandler<Unit>,
    listenHost: String = "0.0.0.0",
    listenRoute: String? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    mediaGroupsDebounceTimeMillis: Long = 1000L,
    additionalApplicationEnvironmentConfigurator: ApplicationEnvironmentBuilder.() -> Unit = {},
    additionalEngineConfigurator: TConfiguration.() -> Unit = {},
    logger: KSLog = DefaultKTgBotAPIKSLog,
    block: UpdateReceiver<Update>,
): EmbeddedServer<TEngine, TConfiguration> =
    embeddedServer(
        factory = engineFactory,
        environment = applicationEnvironment {
            additionalApplicationEnvironmentConfigurator()
        },
        configure = {
            privateKeyConfig?.let {
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

            additionalEngineConfigurator()
        },
        module = {
            routing {
                listenRoute?.also {
                    createRouteFromPath(it).includeWebhookHandlingInRoute(
                        scope = scope,
                        exceptionsHandler = exceptionsHandler,
                        mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
                        logger = logger,
                        block = block
                    )
                } ?: includeWebhookHandlingInRoute(
                    scope = scope,
                    exceptionsHandler = exceptionsHandler,
                    mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
                    logger = logger,
                    block = block
                )
            }
        }
    ).also {
        it.start(false)
    }

/**
 * Setting up ktor server, set webhook info via [SetWebhookRequest] request.
 *
 * @param listenPort port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param scope Scope which will be used for
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 *
 * @see dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
@Suppress("unused")
suspend fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> RequestsExecutor.setWebhookInfoAndStartListenWebhooks(
    listenPort: Int,
    engineFactory: ApplicationEngineFactory<TEngine, TConfiguration>,
    setWebhookRequest: SetWebhookRequest,
    exceptionsHandler: ExceptionHandler<Unit> = {},
    listenHost: String = "0.0.0.0",
    listenRoute: String = "/",
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    mediaGroupsDebounceTimeMillis: Long = 1000L,
    additionalApplicationEnvironmentConfigurator: ApplicationEnvironmentBuilder.() -> Unit = {},
    additionalEngineConfigurator: TConfiguration.() -> Unit = {},
    logger: KSLog = Log,
    block: UpdateReceiver<Update>,
): EmbeddedServer<TEngine, TConfiguration> = try {
    execute(setWebhookRequest)
    startListenWebhooks(
        listenPort = listenPort,
        engineFactory = engineFactory,
        exceptionsHandler = exceptionsHandler,
        listenHost = listenHost,
        listenRoute = listenRoute,
        privateKeyConfig = privateKeyConfig,
        scope = scope,
        mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
        additionalApplicationEnvironmentConfigurator = additionalApplicationEnvironmentConfigurator,
        additionalEngineConfigurator = additionalEngineConfigurator,
        logger = logger,
        block = block
    )
} catch (e: Exception) {
    throw e
}
