package dev.inmo.tgbotapi.ksp.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.*
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded

private fun FileSpec.Builder.addTopLevelImport(className: ClassName) {
    className.topLevelClassName().let {
        addImport(it.packageName, it.simpleNames)
    }
}

private fun FileSpec.Builder.createTypeDefinition(ksClassDeclaration: KSClassDeclaration): TypeName {
    val className = ksClassDeclaration.toClassName()
    return if (ksClassDeclaration.typeParameters.isNotEmpty()) {
        className.parameterizedBy(
            ksClassDeclaration.typeParameters.map {
                it.bounds.first().resolve().also {
                    val typeClassName = it.toClassName()
                    addTopLevelImport(typeClassName)
                }.toTypeName()
            },
        )
    } else {
        className
    }
}

@OptIn(KspExperimental::class)
private fun KSClassDeclaration.buildPrefix(sourceDeclaration: KSClassDeclaration): String {
    val ownName =
        if (isAnnotationPresent(ClassCastsIncluded.ExcludeSubName::class)) {
            ""
        } else {
            simpleName.asString()
        }
    when (val parentDeclaration = parentDeclaration) {
        is KSClassDeclaration ->
            if (parentDeclaration === sourceDeclaration) {
                return ownName
            } else {
                return "${parentDeclaration.buildPrefix(sourceDeclaration)}$ownName"
            }
    }
    return ownName
}

@OptIn(KspExperimental::class)
fun FileSpec.Builder.fill(
    sourceKSClassDeclaration: KSClassDeclaration,
    subtypesMap: Map<KSClassDeclaration, Set<KSClassDeclaration>>,
    targetClassDeclaration: KSClassDeclaration = sourceKSClassDeclaration,
) {
    if (sourceKSClassDeclaration == targetClassDeclaration) {
        subtypesMap[sourceKSClassDeclaration] ?.forEach {
            fill(sourceKSClassDeclaration, subtypesMap, it)
        }
    } else {
        val sourceClassName = sourceKSClassDeclaration.toClassName()
        val targetClassClassName = targetClassDeclaration.toClassName()
        val targetClassTypeDefinition = createTypeDefinition(targetClassDeclaration)
//        val simpleName = targetClassDeclaration.simpleName.asString()
//        val additionalPrefix = targetClassDeclaration.buildPrefix()
        val resultPrefix = targetClassDeclaration.buildPrefix(sourceKSClassDeclaration)
        val withFirstLowerCase = resultPrefix.replaceFirstChar { it.lowercase() }
        val castedOrNullName = "${withFirstLowerCase}OrNull"

        addTopLevelImport(targetClassClassName)
        addFunction(
            FunSpec.builder(castedOrNullName).apply {
                receiver(sourceClassName)
                addCode(
                    "return this as? %L",
                    targetClassTypeDefinition,
                )
                returns(targetClassTypeDefinition.copy(nullable = true))
                addModifiers(KModifier.INLINE)
            }.build(),
        )
        addFunction(
            FunSpec.builder("${withFirstLowerCase}OrThrow").apply {
                receiver(sourceClassName)
                addCode(
                    "return this as %L",
                    targetClassTypeDefinition,
                )
                returns(targetClassTypeDefinition)
                addModifiers(KModifier.INLINE)
            }.build(),
        )
        addFunction(
            FunSpec.builder("if$resultPrefix").apply {
                val genericType = TypeVariableName("T", null)
                addTypeVariable(genericType)
                receiver(sourceClassName)
                addParameter(
                    "block",
                    LambdaTypeName.get(
                        null,
                        targetClassTypeDefinition,
                        returnType = genericType,
                    ),
                )
                addCode(
                    "return $castedOrNullName() ?.let(block)",
                    targetClassTypeDefinition,
                )
                returns(genericType.copy(nullable = true))
                addModifiers(KModifier.INLINE)
            }.build(),
        )

        subtypesMap[targetClassDeclaration] ?.let {
            if (it.count { it.classKind == ClassKind.CLASS } > 1) {
                it
            } else {
                it.filter { it.classKind != ClassKind.CLASS }
            }
        }?.forEach {
            fill(sourceKSClassDeclaration, subtypesMap, it)
            fill(targetClassDeclaration, subtypesMap, it)
        }
    }
}
