#!/usr/bin/env kotlin
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

fun generateEvent(eventName: String, callbacks: String) {
    val uppercaseEventName = eventName.take(1).uppercase() + eventName.drop(1)
    val subpackage = eventName.map { if (it.isUpperCase()) "_${it.lowercase()}" else it }.joinToString("")
    val command = "${rfAbsolutePath}/.templates/generator.kts -s -a \"subpackage=$subpackage\" -a \"event_name=$eventName\" -a \"event_name_uppercase=$uppercaseEventName\" -a \"callback_args=$callbacks\" -a \"callback_typealias_name=${uppercaseEventName}EventHandler\" -o \"$cfAbsolutePath\" -ex \"kt\" \"${rfAbsolutePath}/.templates/{{\$subpackage}}\""

    val process = Runtime.getRuntime().exec(command)
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
}

val eventsList: JsonArray = Json.parseToJsonElement(File("EventsList.json").readText()).jsonArray

eventsList.forEach {
    generateEvent(
        it.jsonObject["event_name"]!!.jsonPrimitive.content,
        it.jsonObject["callback"] ?.jsonPrimitive ?.content ?: ""
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
val eventTypeFileContent = "package dev.inmo.tgbotapi.webapps\n\nsealed class EventType(val typeName: String) {\n${eventTypePartsString}\n}\n"
println(eventTypeFileContent)

val eventTypeOutputFile = File(currentFolder, "../EventType.kt")
eventTypeOutputFile.writeText(
    eventTypeFileContent
)

val webAppPartsString = webAppParts.joinToString("\n")
webAppParts.clear()
val eventTypeFileContent = webAppPartsString
println(eventTypeFileContent)

val webAppOutputFile = File(currentFolder, "ToPutInWebApp!!!!!.kt")
webAppOutputFile.writeText(
    webAppPartsString
)

val extensionsPartsString = extensionsParts.joinToString("\n")
extensionsParts.clear()
val extensionsPartsFileContent = "package dev.inmo.tgbotapi.webapps.events\n\n${extensionsPartsString}\n"
println(extensionsPartsFileContent)

val extensionsPartsOutputFile = File(currentFolder, "Extensions.kt")
extensionsPartsOutputFile.writeText(
    extensionsPartsFileContent
)


currentFolder.listFiles() ?.toList() ?.forEach { generatedFolder: File ->
    if (it.isDirectory) {
        it.deleteRecursively()
    }
}
