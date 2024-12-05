#!/usr/bin/env kotlin
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

import kotlinx.serialization.json.*
import java.io.File
import java.lang.Runtime

val rootFolder = File("../../../../../../../../")
val rfAbsolutePath = rootFolder.absolutePath

fun generateEvent(eventName: String, callbacks: String) {
    val uppercaseEventName = eventName.take(1).uppercase() + eventName.drop(1)
    val command = "${rfAbsolutePath}/.templates/generator.kts -s -a \"event_name=$eventName\" -a \"event_name_uppercase=$uppercaseEventName\" -a \"callback_args=$callbacks\" --env \"${rfAbsolutePath}/.templates/events/.env\" -o \"./\" -ex \"kt\" \"${rfAbsolutePath}/.templates/events/\""

    Runtime.getRuntime().exec(command).waitFor()
}

val eventsList: JsonArray = Json.parseToJsonElement(File("EventsList.json").readText()).jsonArray

eventsList.forEach {
    generateEvent(
        it.jsonObject["event_name"]!!.jsonPrimitive.content,
        it.jsonObject["callback"] ?.jsonPrimitive ?.content ?: ""
    )
}

println(eventsList.toString())
