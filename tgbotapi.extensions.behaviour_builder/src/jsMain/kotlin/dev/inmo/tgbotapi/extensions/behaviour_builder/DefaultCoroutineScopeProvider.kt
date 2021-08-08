package dev.inmo.tgbotapi.extensions.behaviour_builder

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual var defaultCoroutineScopeProvider = {
    CoroutineScope(Dispatchers.Default)
}
