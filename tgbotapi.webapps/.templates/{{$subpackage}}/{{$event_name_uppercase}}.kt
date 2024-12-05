// Part for callback typealias

typealias {{$callback_typealias_name}} = WebApp.({{$callback_args}}) -> Unit

// Part for outside of WebApp

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.onEvent(type: EventType.{{$event_name_uppercase}}, eventHandler: {{$callback_typealias_name}}) = { it: {{$callback_typealias_name}} ->
    eventHandler(js("this").unsafeCast<WebApp>(), it)
}.also {
    on{{$event_name_uppercase}}(
        type.typeName,
        callback = it
    )
}

/**
 * @return The callback which should be used in case you want to turn off events handling
 */
fun WebApp.on{{$event_name_uppercase}}(eventHandler: {{$callback_typealias_name}}) = onEvent(EventType.{{$event_name_uppercase}}, eventHandler)
