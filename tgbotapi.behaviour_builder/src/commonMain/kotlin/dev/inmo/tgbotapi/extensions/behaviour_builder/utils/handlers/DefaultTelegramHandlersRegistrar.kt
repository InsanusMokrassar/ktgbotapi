package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers

import dev.inmo.micro_utils.handlers.common.HandlersRegistrar
import dev.inmo.tgbotapi.types.update.abstracts.Update

class DefaultTelegramHandlersRegistrar : TelegramUpdateHandler, TelegramHandlersRegistrar {
    private val mainLayer = mutableListOf<TelegramUpdateHandler>()
    private val defaultsLayer = mutableListOf<TelegramUpdateHandler>()
    private var defaultHandler: TelegramUpdateHandler? = null

    private val handlersRegistrar = HandlersRegistrar(
        listOf(
            mainLayer,
            defaultsLayer
        ),
        object : TelegramUpdateHandler {
            override suspend fun check(data: Update): Boolean = defaultHandler ?.check(data) ?: false
            override suspend fun handle(data: Update) { defaultHandler ?.handle(data) }
        }
    )

    override suspend fun check(data: Update): Boolean {
        return handlersRegistrar.check(data)
    }

    override suspend fun handle(data: Update) {
        handlersRegistrar.handle(data)
    }

    override suspend fun includeHandler(handler: TelegramUpdateHandler) {
        if (handler in mainLayer) {
            return
        }
        mainLayer.add(handler)
    }

    override suspend fun excludeHandler(handler: TelegramUpdateHandler) {
        mainLayer.remove(handler)
    }

    override suspend fun includeDefaultHandler(handler: TelegramUpdateHandler) {
        if (handler in defaultsLayer) {
            return
        }
        defaultsLayer.add(handler)
    }

    override suspend fun excludeDefaultHandler(handler: TelegramUpdateHandler) {
        defaultsLayer.remove(handler)
    }
}
