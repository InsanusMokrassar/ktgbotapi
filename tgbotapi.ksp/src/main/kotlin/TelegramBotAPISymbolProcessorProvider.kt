package dev.inmo.tgbotapi.ksp.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class TelegramBotAPISymbolProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return TelegramBotAPISymbolProcessor(
            environment.codeGenerator,
            environment.options["cctargetPackage"] ?: "",
            environment.options["ccoutputFileName"] ?: "Output",
            environment.options["ccoutputFolder"],
        )
    }
}
