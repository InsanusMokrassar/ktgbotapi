package dev.inmo.tgbotapi.ksp.processor

import com.google.devtools.ksp.processing.*

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
