#!/usr/bin/env kotlin
/**
 * Generates files and folders as they have been put in the folder. Envs uses common syntax, but
 * values may contains {{${'$'}sampleVariable}} parts, where {{${'$'}sampleVariable}} will be replaced with variable value.
 * Example:
 *
 * .env:
 * sampleVariable=${'$'}prompt # require request from command line
 *         sampleVariable2=just some value
 * sampleVariable3=${'$'}{sampleVariable}.${'$'}{sampleVariable2}
 *
 * Result variables:
 * sampleVariable=your input in console # lets imagine you typed it
 * sampleVariable2=just some value
 * sampleVariable3=your input in console.just some value
 *
 * To use these variables in template, you will need to write {{${'$'}sampleVariable}}.
 * You may use it in text of files as well as in files/folders names.
 *
 * Usage: kotlin generator.kts [args] folders...
 * Args:
 * -e, --env: Path to file with args for generation; Use "${'$'}prompt" as values to read variable value from console
 * -o, --outputFolder: Folder where templates should be used. Folder of calling by default
 *         folders: Folders-templates
 * -s, --skip: Skip variables setup
 * -a, --args: Pass several for several args. Use with syntax `--args a=b` or `-a a=b` to set variable with key `a` to value `b`
 * -v, --verbose: Show more verbose output
 */
import java.io.File

val console = System.console()

var envFile: File? = null
var outputFolder: File = File("./") // current folder by default
val templatesFolders = mutableListOf<File>()
var extensions: List<String>? = null
var skipPrompts: Boolean = false
val commandLineArgs = mutableMapOf<String, String>()
val globalEnvs = System.getenv().toMutableMap()
var verboseMode: Boolean = false

if (args.any { it == "-v" || it == "--verbose" }) {
    println(args.joinToString("\n"))
}

fun String.replaceWithVariables(envs: Map<String, String>): String {
    var currentString = this
    var changed = false

    do {
        changed = false
        envs.forEach { (k, v) ->
            val previousString = currentString
            currentString = currentString.replace("{{$${k}}}", v)
            changed = changed || currentString != previousString
        }
    } while (changed)

    return currentString
}

fun requestVariable(variableName: String, defaultValue: String?): String {
    console.printf("Enter value for variable $variableName${defaultValue ?.let { " [$it]" } ?: ""}: ")
    return console.readLine().ifEmpty { defaultValue } ?: ""
}

fun readEnvs(content: String, presets: Map<String, String>?): Map<String, String> {
    val initialEnvs = mutableMapOf<String, String>()
    val contentAsMap = mutableMapOf<String, String>()
    content.split("\n").forEach {
        val withoutComment = it.replace(Regex("\\#.*"), "")

        runCatching {
            val (key, value) = withoutComment.split("=")

            contentAsMap[key] = value
        }
    }

    if (skipPrompts) {
        initialEnvs.putAll(
            contentAsMap + (presets ?: emptyMap()) + globalEnvs + commandLineArgs
        )
    } else {
        contentAsMap.forEach { (key, value) ->
            val existsValue = presets ?.get(key) ?: commandLineArgs[key] ?: globalEnvs[key]
            initialEnvs[key] = when {
                value == "\$prompt" -> requestVariable(key, existsValue)
                else -> requestVariable(key, value.replaceWithVariables(initialEnvs))
            }
        }
    }
    var i = 0
    val readEnvs = initialEnvs.toMutableMap()
    while (i < readEnvs.size) {
        val key = readEnvs.keys.elementAt(i)
        val currentValue = readEnvs.getValue(key)
        val withReplaced = currentValue.replaceWithVariables(readEnvs)
        var changed = false
        if (withReplaced != currentValue) {
            i = 0
            readEnvs[key] = withReplaced
        } else {
            i++
        }
    }
    return (presets ?: emptyMap()) + readEnvs
}

val realArgs = args.map { sourceArg ->
    if (sourceArg.startsWith("\"") && sourceArg.endsWith("\"")) {
        sourceArg.removePrefix("\"").removeSuffix("\"")
    } else {
        sourceArg
    }
}

fun readParameters() {
    var i = 0
    while (i < realArgs.size) {
        val arg = realArgs[i]
        when (arg) {
            "--env",
            "-e" -> {
                i++
                envFile = File(realArgs[i])
            }
            "--skip",
            "-s" -> {
                skipPrompts = true
            }
            "--extensions",
            "-ex" -> {
                i++
                extensions = realArgs[i].split(",")
            }
            "--outputFolder",
            "-o" -> {
                i++
                outputFolder = File(realArgs[i])
            }
            "--verbose",
            "-v" -> {
                verboseMode = true
            }
            "--args",
            "-a" -> {
                i++
                val subarg = realArgs[i]
                val key = subarg.takeWhile { it != '=' }
                val value = subarg.dropWhile { it != '=' }.removePrefix("=")
                if (verboseMode) {
                    println("Argument $key=$value")
                }
                commandLineArgs[key] = value
            }
            "--help",
            "-h" -> {
                println("""
                    Generates files and folders as the have been put in the folder. Envs uses common syntax, but
                    values may contains {{${'$'}sampleVariable}} parts, where {{${'$'}sampleVariable}} will be replaced with variable value.
                    Example:
                    
                    .env:
                    sampleVariable=${'$'}prompt # require request from command line
                    sampleVariable2=just some value
                    sampleVariable3=${'$'}{sampleVariable}.${'$'}{sampleVariable2}
                    
                    Result variables:
                    sampleVariable=your input in console # lets imagine you typed it
                    sampleVariable2=just some value
                    sampleVariable3=your input in console.just some value
                    
                    To use these variables in template, you will need to write {{${'$'}sampleVariable}}.
                    You may use it in text of files as well as in files/folders names.
                    
                    Usage: kotlin generator.kts [args] folders...
                    Args:
                        -e, --env: Path to file with args for generation; Use "${'$'}prompt" as values to read variable value from console
                        -o, --outputFolder: Folder where templates should be used. Folder of calling by default
                        folders: Folders-templates
                        -s, --skip: Skip variables setup
                        -a, --args: Pass several for several args. Use with syntax `--args a=b` or `-a a=b` to set variable with key `a` to value `b`
                """.trimIndent())
                Runtime.getRuntime().exit(0)
            }
            else -> {
                println(arg)
                val potentialFile = File(arg)
                println("Potential file/folder as template: ${potentialFile.absolutePath}")
                runCatching {
                    if (potentialFile.exists()) {
                        println("Adding file/folder as template: ${potentialFile.absolutePath}")
                        templatesFolders.add(potentialFile)
                    }
                }.onFailure { e ->
                    println("Unable to use folder $arg as template folder")
                    e.printStackTrace()
                }
            }
        }
        i++
    }
}

readParameters()

val envs: MutableMap<String, String> = (envFile ?.let { readEnvs(it.readText(), null) } ?: (globalEnvs + commandLineArgs)).toMutableMap()

println(
    """
    Result environments:
        ${envs.toList().joinToString("\n        ") { (k, v) -> "$k=$v" }}
    Result extensions:
        ${extensions ?.joinToString()}
    Input folders:
        ${templatesFolders.joinToString("\n        ") { it.absolutePath }}
    Output folder:
        ${outputFolder.absolutePath}
    """.trimIndent()
)

fun File.handleTemplate(targetFolder: File, envs: Map<String, String>) {
    println("Handling $absolutePath")
    val localEnvs = File(absolutePath, ".env").takeIf { it.exists() } ?.let {
        println("Reading .env in ${absolutePath}")
        readEnvs(it.readText(), envs)
    } ?: envs
    println(
        """
    Local environments:
        ${localEnvs.toList().joinToString("\n        ") { (k, v) -> "$k=$v" }}
    """.trimIndent()
    )
    val newName = name.replaceWithVariables(localEnvs)
    println("New name $newName")
    when {
        !exists() -> return
        isFile -> {
            val content = useLines {
                it.map { it.replaceWithVariables(localEnvs) }.toList()
            }.joinToString("\n")
            val targetFile = File(targetFolder, newName)
            targetFile.writeText(content)
            println("Target file: ${targetFile.absolutePath}")
        }
        else -> {
            val folder = File(targetFolder, newName)
            println("Target folder: ${folder.absolutePath}")
            folder.mkdirs()
            listFiles() ?.forEach { fileOrFolder ->
                fileOrFolder.handleTemplate(folder, localEnvs)
            }
        }
    }
}

templatesFolders.forEach { folderOrFile ->
    folderOrFile.handleTemplate(outputFolder, envs)
}
