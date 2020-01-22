package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.InputFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.SetWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.MediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.RawUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.UpdatesFilter
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.webhook.WebhookPrivateKeyConfig
import com.github.insanusmokrassar.TelegramBotAPI.utils.convertWithMediaGroupUpdates
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.serialization.json.Json
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Reverse proxy webhook.
 *
 * @param url URL of webhook WITHOUT including of [port]
 * @param port port which will be listen by bot
 * @param listenRoute address to listen by bot
 * @param certificate [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile] or [com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId]
 * which will be used by telegram to send encrypted messages
 * @param scope Scope which will be used for
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
    val updatesChannel = Channel<Update>(Channel.UNLIMITED)
    val mediaGroupChannel = Channel<Pair<String, BaseMessageUpdate>>(Channel.UNLIMITED)
    val mediaGroupAccumulatedChannel = mediaGroupChannel.accumulateByKey(
        1000L,
        scope = scope
    )
    val env = applicationEngineEnvironment {

        module {
            routing {
                post(listenRoute) {
                    val deserialized = call.receiveText()
                    val update = Json.nonstrict.parse(
                        RawUpdate.serializer(),
                        deserialized
                    )
                    updatesChannel.send(update.asUpdate(deserialized))
                    call.respond("Ok")
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
    val engine = embeddedServer(engineFactory, env)

    try {
        executeDeferred.await()
    } catch (e: Exception) {
        env.stop()
        throw e
    }

    return scope.launch {
        launch {
            for (update in updatesChannel) {
                val data = update.data
                if (data is MediaGroupUpdate) {

                } else {

                }
                when (data) {
                    is MediaGroupMessage -> mediaGroupChannel.send("${data.mediaGroupId}${update::class.simpleName}" to update as BaseMessageUpdate)
                    else -> block(update)
                }
            }
        }
        launch {
            for ((_, mediaGroup) in mediaGroupAccumulatedChannel) {
                mediaGroup.convertWithMediaGroupUpdates().forEach {
                    block(it)
                }
            }
        }
        engine.start(false)
    }.also {
        it.invokeOnCompletion {
            engine.stop(1000L, 0L, TimeUnit.MILLISECONDS)
        }
    }
}

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
