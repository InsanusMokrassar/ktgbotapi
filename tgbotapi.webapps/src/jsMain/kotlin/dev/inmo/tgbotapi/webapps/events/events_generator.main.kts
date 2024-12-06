#!/usr/bin/env kotlin
// This script will read Event
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

import kotlinx.serialization.json.*
import java.io.File
import java.io.InputStream
import java.lang.Runtime
import java.lang.System

val rootFolder = File("../../../../../../../../")
val rfAbsolutePath = rootFolder.absolutePath
val currentFolder = File("./")
val cfAbsolutePath = currentFolder.absolutePath

val realArgs = args.map { sourceArg ->
    if (sourceArg.startsWith("\"") && sourceArg.endsWith("\"")) {
        sourceArg.removePrefix("\"").removeSuffix("\"")
    } else {
        sourceArg
    }
}

var verboseMode: Boolean = false

fun readParameters() {
    var i = 0
    while (i < realArgs.size) {
        val arg = realArgs[i]

        when (arg) {
            "-v", "--verbose" -> {
                verboseMode = true
            }
        }

        i++
    }
}

readParameters()

fun generateEvent(eventName: String, callbackArgs: String) {
    println("Start generating $eventName (callbacks: $callbackArgs)")
    val uppercaseEventName = eventName.take(1).uppercase() + eventName.drop(1)
    val subpackage = eventName.map { if (it.isUpperCase()) "_${it.lowercase()}" else it }.joinToString("")
    var callbackNumber: Int = -1
    val splittedCallbacks = callbackArgs.split(Regex(" ?, ?")).filter { !it.isBlank() }
    val callback_args_names = splittedCallbacks.joinToString(", ") {
        callbackNumber++
        "p$callbackNumber"
    }
    callbackNumber = -1
    val callback_args_definitions = splittedCallbacks.joinToString(", ") {
        callbackNumber++
        "p$callbackNumber: $it"
    }
    val verboseFlag = if (verboseMode) "-v" else ""

    val commandParts = arrayOf(
        "${rfAbsolutePath}/.templates/generator.kts",
        verboseFlag,
        "-s",
        "-a", "subpackage=$subpackage",
        "-a", "event_name=$eventName",
        "-a", "callback_args_names=$callback_args_names",
        "-a", "event_name_uppercase=$uppercaseEventName",
        "-a", "callback_args_definitions=$callback_args_definitions",
        "-a", "callback_args=$callbackArgs",
        "-a", "callback_typealias_name=${uppercaseEventName}EventHandler",
        "-o", cfAbsolutePath,
        "-ex", "kt",
        "${rfAbsolutePath}/.templates/{{\$subpackage}}"
    )
    val command = commandParts.joinToString(" ") { "\"$it\"" }
    if (verboseMode) {
        println(command)
    }

    val process = Runtime.getRuntime().exec(commandParts)
    val inputStream: InputStream = process.getInputStream()
    val errorStream: InputStream = process.getErrorStream()

    val exitCode by lazy { process.waitFor() }

    if (verboseMode) {
        inputStream.use {
            it.copyTo(System.out)
        }
        println(exitCode)
    }

    if (exitCode != 0) {
        errorStream.use {
            it.copyTo(System.out)
        }
        Runtime.getRuntime().exit(exitCode)
    }
    println("Complete generating $eventName")
}

val eventsList: JsonArray = Json.parseToJsonElement(File("EventsList.json").readText()).jsonArray

eventsList.forEach {
    generateEvent(
        it.jsonObject["event_name"]!!.jsonPrimitive.content,
        it.jsonObject["callback_args"] ?.jsonPrimitive ?.content ?: ""
    )
}

val eventTypeParts = mutableListOf<String>()
val webAppParts = mutableListOf<String>()
val extensionsParts = mutableListOf<String>()

currentFolder.listFiles() ?.forEach { generatedFolder: File ->
    if (generatedFolder.isDirectory) {
        generatedFolder.listFiles() ?.forEach { generatedFile: File ->
            when {
                generatedFile.name.startsWith("EventType") -> {
                    eventTypeParts
                }
                generatedFile.name.startsWith("WebApp") -> {
                    webAppParts
                }
                else -> {
                    extensionsParts
                }
            }.add(generatedFile.readText())
        }
    }
}

val eventTypePartsString = eventTypeParts.joinToString("\n") { "    $it" }
eventTypeParts.clear()
val eventTypeFileContent = "package dev.inmo.tgbotapi.webapps\n\nimport dev.inmo.tgbotapi.webapps.EventType\nimport dev.inmo.tgbotapi.webapps.WebApp\n\nsealed class EventType(val typeName: String) {\n${eventTypePartsString}\n}\n"
if (verboseMode) {
    println(eventTypeFileContent)
}

val eventTypeOutputFile = File(currentFolder, "../EventType.kt")
eventTypeOutputFile.writeText(
    eventTypeFileContent
)

val webAppPartsString = webAppParts.joinToString("\n")
webAppParts.clear()
val webAppPartsFileContent = webAppPartsString
if (verboseMode) {
    println(webAppPartsFileContent)
}

val webAppOutputFile = File(currentFolder, "ToPutInWebApp!!!!!.kt")
webAppOutputFile.writeText(
    webAppPartsFileContent
)

val extensionsPartsString = extensionsParts.joinToString("\n")
extensionsParts.clear()
val extensionsPartsFileContent = "package dev.inmo.tgbotapi.webapps.events\n\n${extensionsPartsString}\n"
if (verboseMode) {
    println(extensionsPartsFileContent)
}

val extensionsPartsOutputFile = File(currentFolder, "Extensions.kt")
extensionsPartsOutputFile.writeText(
    extensionsPartsFileContent
)


currentFolder.listFiles() ?.toList() ?.forEach { generatedFolder: File ->
    if (generatedFolder.isDirectory) {
        generatedFolder.deleteRecursively()
    }
}
