package dev.inmo.tgbotapi.ksp.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAllSuperTypes
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.ClassCastsExcluded
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import java.io.File

class TelegramBotAPISymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val targetPackage: String = "",
    private val outputFile: String = "Output",
    private val outputFolder: String? = null
) : SymbolProcessor {
    private val classCastsIncludedClassName = ClassCastsIncluded::class.asClassName()
    @OptIn(KspExperimental::class, RiskFeature::class)
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val classes = resolver.getSymbolsWithAnnotation(classCastsIncludedClassName.canonicalName).filterIsInstance<KSClassDeclaration>()
        val classesRegexes: Map<KSClassDeclaration, Pair<Regex?, Regex?>> = classes.mapNotNull {
            it to (it.getAnnotationsByType(ClassCastsIncluded::class).firstNotNullOfOrNull {
                it.typesRegex.takeIf { it.isNotEmpty() } ?.let(::Regex) to it.excludeRegex.takeIf { it.isNotEmpty() } ?.let(::Regex)
            } ?: return@mapNotNull null)
        }.toMap()
        val classesSubtypes = mutableMapOf<KSClassDeclaration, MutableSet<KSClassDeclaration>>()

        resolver.getAllFiles().forEach {
            it.declarations.forEach { potentialSubtype ->
                if (
                    potentialSubtype is KSClassDeclaration
                    && potentialSubtype.isAnnotationPresent(ClassCastsExcluded::class).not()
                ) {
                    val allSupertypes = potentialSubtype.getAllSuperTypes().map { it.declaration }

                    for (currentClass in classes) {
                        val regexes = classesRegexes[currentClass]
                        val simpleName = potentialSubtype.simpleName.getShortName()
                        when {
                            currentClass !in allSupertypes
                            || regexes ?.first ?.matches(simpleName) == false
                            || regexes ?.second ?.matches(simpleName) == true -> continue
                            else -> {
                                classesSubtypes.getOrPut(currentClass) { mutableSetOf() }.add(potentialSubtype)
                            }
                        }
                    }
                }
            }
        }
        fun fillWithSealeds(source: KSClassDeclaration, current: KSClassDeclaration = source) {
            val regexes = classesRegexes[source]
            current.getSealedSubclasses().forEach {
                val simpleName = it.simpleName.getShortName()
                if (
                    regexes ?.first ?.matches(simpleName) == false
                    || regexes ?.second ?.matches(simpleName) == true
                    || it.isAnnotationPresent(ClassCastsExcluded::class)
                ) {
                    return@forEach
                }
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
