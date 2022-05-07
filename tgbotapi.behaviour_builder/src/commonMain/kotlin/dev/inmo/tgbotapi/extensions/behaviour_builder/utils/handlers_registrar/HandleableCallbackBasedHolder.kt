package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

class HandleableCallbackBasedHolder<T> : HandleableTriggersHolder<suspend (T) -> Boolean>() {
    suspend fun isHandled(data: T) = handleable.any { it(data) }
}
