package com.github.insanusmokrassar.TelegramBotAPI.extensions.api

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils.convertWithMediaGroupUpdates
import com.github.insanusmokrassar.TelegramBotAPI.extensions.api.utils.updateHandlerWithMediaGroupsAdaptation
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdateReceiver
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.webhook.WebhookPrivateKeyConfig
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.accumulateByKey
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.executeAsync
import com.github.insanusmokrassar.TelegramBotAPI.utils.handleSafely
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.Executors

/**
 * @param [scope] Will be used for mapping of media groups
 * @param [exceptionsHandler] Pass this parameter to set custom exception handler for getting updates
 * @param [block] Some receiver block like [com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter]
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun Route.includeWebhookInRoute(
    scope: CoroutineScope,
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
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
 * @param port port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param scope Scope which will be used for
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
fun setWebhook(
    port: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    listenHost: String = "0.0.0.0",
    listenRoute: String = "/",
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    block: UpdateReceiver<Update>
): ApplicationEngine {
    lateinit var engine: ApplicationEngine
    val env = applicationEngineEnvironment {

        module {
            routing {
                route(listenRoute) {
                    includeWebhookInRoute(scope, exceptionsHandler, block)
                }
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
                this.port = port
            }
        } ?: connector {
            host = listenHost
            this.port = port
        }

    }
    engine = embeddedServer(engineFactory, env)
    engine.start(false)

    return engine
}

/**
 * Setting up ktor server, set webhook info via [SetWebhook] request.
 *
 * @param url URL of webhook WITHOUT including of [port]
 * @param port port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param certificate [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile] or [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId]
 * which will be used by telegram to send encrypted messages
 * @param scope Scope which will be used for
 *
 * @see com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
 * @see UpdatesFilter
 * @see UpdatesFilter.asUpdateReceiver
 */
suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    listenHost: String = "0.0.0.0",
    listenRoute: String = "/",
    certificate: InputFile? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    allowedUpdates: List<String>? = null,
    maxAllowedConnections: Int? = null,
    exceptionsHandler: (suspend (Exception) -> Unit)? = null,
    block: UpdateReceiver<Update>
): Job {
    val executeDeferred = certificate ?.let {
        executeAsync(
            SetWebhook(
                url,
                certificate,
                maxAllowedConnections,
                allowedUpdates
            )
        )
    } ?: executeAsync(
        SetWebhook(
            url,
            maxAllowedConnections,
            allowedUpdates
        )
    )


    return try {
        executeDeferred.await()
        val engine = setWebhook(port, engineFactory, listenHost, listenRoute, privateKeyConfig, scope, exceptionsHandler, block)
        scope.launch {
            engine.environment.parentCoroutineContext[Job] ?.join()
            engine.stop(1000, 5000)
        }
    } catch (e: Exception) {
        throw e
    }
}

suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    listenHost: String = "0.0.0.0",
    listenRoute: String = "/",
    certificate: InputFile? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    allowedUpdates: List<String>? = null,
    maxAllowedConnections: Int? = null,
    block: UpdateReceiver<Update>
) = setWebhook(
    url,
    port,
    engineFactory,
    listenHost,
    listenRoute,
    certificate,
    privateKeyConfig,
    scope,
    allowedUpdates,
    maxAllowedConnections,
    null,
    block
)

@Suppress("unused")
suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    engineFactory: ApplicationEngineFactory<*, *>,
    certificate: InputFile? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    allowedUpdates: List<String>? = null,
    maxAllowedConnections: Int? = null,
    block: UpdateReceiver<Update>
) = setWebhook(
    url,
    port,
    engineFactory,
    certificate ?.let { "0.0.0.0" } ?: "localhost",
    "/",
    certificate,
    privateKeyConfig,
    scope,
    allowedUpdates,
    maxAllowedConnections,
    block
)

@Suppress("unused")
suspend fun RequestsExecutor.setWebhook(
    url: String,
    port: Int,
    filter: UpdatesFilter,
    engineFactory: ApplicationEngineFactory<*, *>,
    certificate: InputFile? = null,
    privateKeyConfig: WebhookPrivateKeyConfig? = null,
    scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    maxAllowedConnections: Int? = null,
    listenHost: String = certificate ?.let { "0.0.0.0" } ?: "localhost",
    listenRoute: String = "/"
): Job = setWebhook(
    url,
    port,
    engineFactory,
    listenHost,
    listenRoute,
    certificate,
    privateKeyConfig,
    scope,
    filter.allowedUpdates,
    maxAllowedConnections,
    filter.asUpdateReceiver
)


