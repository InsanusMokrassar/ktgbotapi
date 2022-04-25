package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers

interface TelegramHandlersRegistrar {
    suspend fun includeHandler(handler: TelegramUpdateHandler)
    suspend fun excludeHandler(handler: TelegramUpdateHandler)

    suspend fun includeDefaultHandler(handler: TelegramUpdateHandler)
    suspend fun excludeDefaultHandler(handler: TelegramUpdateHandler)
}
