package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler

@Deprecated("Has been added in microutils", ReplaceWith("StateHandlingErrorHandler", "dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler"))
typealias StateHandlingErrorHandler<T> = StateHandlingErrorHandler<T>
@Deprecated("Has been added in microutils", ReplaceWith("DefaultStateHandlingErrorHandler", "dev.inmo.micro_utils.fsm.common.utils.DefaultStateHandlingErrorHandler"))
val DefaultStateHandlingErrorHandler = dev.inmo.micro_utils.fsm.common.utils.DefaultStateHandlingErrorHandler
@Deprecated("Has been added in microutils", ReplaceWith("defaultStateHandlingErrorHandler", "dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler"))
inline fun <T> defaultStateHandlingErrorHandler() = dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler<T>()
