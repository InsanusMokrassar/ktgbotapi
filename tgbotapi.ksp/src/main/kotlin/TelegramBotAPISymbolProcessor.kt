package dev.inmo.tgbotapi.ksp.processor

import com.google.devtools.ksp.getAllSuperTypes
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import java.io.File

class TelegramBotAPISymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val targetPackage: String = "",
    private val outputFile: String = "Output",
    private val outputFolder: String? = null
) : SymbolProcessor {
    private val classCastsIncludedClassName = ClassName("dev.inmo.tgbotapi.utils.internal", "ClassCastsIncluded")
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val classes = resolver.getSymbolsWithAnnotation(classCastsIncludedClassName.canonicalName).filterIsInstance<KSClassDeclaration>()
        val classesRegexes: Map<KSClassDeclaration, Pair<Regex, Regex?>> = classes.mapNotNull {
            it to (it.annotations.firstNotNullOfOrNull {
                runCatching {
                    if (it.annotationType.resolve().toClassName() == classCastsIncludedClassName) {
                        val regex = it.arguments.first().value as? String ?: return@runCatching null
                        val negativeRegex = (it.arguments.first().value as? String) ?.takeIf { it.isNotEmpty() }
                        Regex(regex) to (negativeRegex ?.let(::Regex))
                    } else {
                        null
                    }
                }.getOrNull()
            } ?: return@mapNotNull null)
        }.toMap()
        val classesSubtypes = mutableMapOf<KSClassDeclaration, MutableSet<KSClassDeclaration>>()

        resolver.getAllFiles().forEach {
            it.declarations.forEach { potentialSubtype ->
                if (potentialSubtype is KSClassDeclaration) {
                    val allSupertypes = potentialSubtype.getAllSuperTypes().map { it.declaration }

                    for (currentClass in classes) {
                        val regexes = classesRegexes[currentClass]
                        when {
                            currentClass in allSupertypes
                                && regexes ?.first ?.matches(potentialSubtype.simpleName.toString()) != false
                                && regexes ?.second ?.matches(potentialSubtype.simpleName.toString()) != true-> {
                                classesSubtypes.getOrPut(currentClass) { mutableSetOf() }.add(potentialSubtype)
                            }
                        }
                    }
                }
            }
        }
        fun fillWithSealeds(source: KSClassDeclaration, current: KSClassDeclaration = source) {
            current.getSealedSubclasses().forEach {
                classesSubtypes.getOrPut(source) { mutableSetOf() }.add(it)
                fillWithSealeds(source, it)
            }
        }
        classes.forEach { fillWithSealeds(it) }

        val fileSpec = FileSpec.builder(
            targetPackage,
            outputFile
        ).apply {
            addAnnotation(
                AnnotationSpec.builder(Suppress::class).apply {
                    addMember("\"unused\"")
                    addMember("\"RemoveRedundantQualifierName\"")
                    addMember("\"RedundantVisibilityModifier\"")
                    addMember("\"NOTHING_TO_INLINE\"")
                    addMember("\"UNCHECKED_CAST\"")
                    addMember("\"OPT_IN_USAGE\"")
                    useSiteTarget(AnnotationSpec.UseSiteTarget.FILE)
                }.build()
            )
            classes.forEach {
                fill(
                    it,
                    classesSubtypes.toMap()
                )
            }
        }.build()
        runCatching {
            outputFolder ?.also {
                File(it).apply {
                    delete()
                    runCatching { mkdirs() }
                    fileSpec.writeTo(this)
                }
            } ?: fileSpec.writeTo(codeGenerator, false)
        }

        return emptyList()
    }
}
