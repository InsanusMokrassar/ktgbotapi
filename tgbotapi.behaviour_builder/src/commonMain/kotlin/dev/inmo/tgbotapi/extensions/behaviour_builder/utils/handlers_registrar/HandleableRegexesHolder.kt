package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

class HandleableRegexesHolder : HandleableTriggersHolder<Regex>() {
    fun isHandled(command: String) =
        handleable.any {
            it.matches(command)
        }
}
