package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

private val commonAnyMarker = MarkerFactory<Any, Any> { it }

fun <T> AnyMarkerFactory() = commonAnyMarker as MarkerFactory<T, T>
