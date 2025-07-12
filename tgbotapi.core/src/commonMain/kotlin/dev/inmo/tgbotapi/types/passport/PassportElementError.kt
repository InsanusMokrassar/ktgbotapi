@file:Suppress("unused", "EXPERIMENTAL_API_USAGE", "DuplicatedCode")
@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.passport

import dev.inmo.micro_utils.crypto.MD5
import dev.inmo.micro_utils.crypto.md5
import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import dev.inmo.tgbotapi.types.passport.encrypted.type
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

val ByteArray.passportFileHash: MD5
    get() = md5()

@ClassCastsIncluded
@Serializable(PassportElementErrorSerializer::class)
sealed class PassportElementError {
    abstract val source: String
    abstract val type: String
    abstract val message: String
}

data class UnknownPassportElementError(
    val raw: JsonObject
) : PassportElementError() {
    override val source: String = raw[sourceField] ?.jsonPrimitive ?.contentOrNull ?: ""
    override val type: String = raw[typeField] ?.jsonPrimitive ?.contentOrNull ?: ""
    override val message: String = raw[messageField] ?.jsonPrimitive ?.contentOrNull ?: ""
}

object PassportElementErrorSerializer : KSerializer<PassportElementError> {
    private val jsonObjectSerializer = JsonObject.serializer()
    override val descriptor: SerialDescriptor
        get() = jsonObjectSerializer.descriptor
    override fun deserialize(decoder: Decoder): PassportElementError {
        val json = jsonObjectSerializer.deserialize(decoder)
        return when (json[sourceField] ?.jsonPrimitive ?.content) {
            "dataField" -> nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorDataField.serializer(), json)
            "frontSideField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorFrontSide.serializer(), json)
            "reverseSideField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorReverseSide.serializer(), json)
            "selfieField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorSelfie.serializer(), json)
            "fileField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementFileError.serializer(), json)
            "filesField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementFilesError.serializer(), json)
            "translationFileField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorTranslationFile.serializer(), json)
            "translationFilesField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorTranslationFiles.serializer(), json)
            "unspecifiedField" ->  nonstrictJsonFormat.decodeFromJsonElement(PassportElementErrorUnspecified.serializer(), json)
            else -> UnknownPassportElementError(json)
        }
    }
    override fun serialize(encoder: Encoder, value: PassportElementError) {
        @Suppress("UnusedVariable")
        val neverMindAboutThisVariable = when (value) {
            is PassportElementErrorFrontSide -> PassportElementErrorFrontSide.serializer().serialize(encoder, value)
            is PassportElementErrorReverseSide -> PassportElementErrorReverseSide.serializer().serialize(encoder, value)
            is PassportElementErrorSelfie -> PassportElementErrorSelfie.serializer().serialize(encoder, value)
            is PassportElementErrorFile -> PassportElementErrorFile.serializer().serialize(encoder, value)
            is PassportElementErrorTranslationFile -> PassportElementErrorTranslationFile.serializer().serialize(encoder, value)
            is PassportElementErrorUnspecified -> PassportElementErrorUnspecified.serializer().serialize(encoder, value)
            is PassportElementErrorDataField -> PassportElementErrorDataField.serializer().serialize(encoder, value)
            is PassportElementErrorFiles -> PassportElementErrorFiles.serializer().serialize(encoder, value)
            is PassportElementErrorTranslationFiles -> PassportElementErrorTranslationFiles.serializer().serialize(encoder, value)
            is UnknownPassportElementError -> jsonObjectSerializer.serialize(encoder, value.raw)
        }
    }
}

@Serializable
sealed class PassportSingleElementError : PassportElementError() {
    abstract val elementHash: PassportElementHash
}

@Serializable
sealed class PassportMultipleElementsError : PassportElementError() {
    abstract val elementsHashes: List<PassportElementHash>
}

@Serializable
sealed class PassportElementFileError : PassportSingleElementError()

@Serializable
sealed class PassportElementFilesError : PassportMultipleElementsError()

@Serializable
data class PassportElementErrorDataField(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fieldNameField)
    val fieldName: String,
    @SerialName(dataHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportSingleElementError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = dataField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorDataField

        if (type != other.type) return false
        if (fieldName != other.fieldName) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + fieldName.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementWithData.createDataError(field: String, message: String) = PassportElementErrorDataField(
    type,
    field,
    hash,
    message
)

@Serializable
data class PassportElementErrorFrontSide(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = frontSideField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorFrontSide

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementWithFrontSide.createFrontSideError(message: String, unencryptedFileHash: PassportElementHash) = PassportElementErrorFrontSide(
    type,
    unencryptedFileHash,
    message
)

@Serializable
data class PassportElementErrorReverseSide(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = reverseSideField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorReverseSide

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementWithReverseSide.createReverseSideError(message: String, unencryptedFileHash: PassportElementHash) = PassportElementErrorReverseSide(
    type,
    unencryptedFileHash,
    message
)
@Serializable
data class PassportElementErrorSelfie(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = selfieField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorSelfie

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementWithSelfie.createSelfieError(message: String, unencryptedFileHash: PassportElementHash) = PassportElementErrorSelfie(
    type,
    unencryptedFileHash,
    message
)


@Serializable
data class PassportElementErrorFile(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = fileField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorFile

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementWithFilesCollection.createFileError(message: String, unencryptedFileHash: PassportElementHash) = PassportElementErrorFile(
    type,
    unencryptedFileHash,
    message
)

@Serializable
data class PassportElementErrorFiles(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashesField)
    override val elementsHashes: List<@Serializable(Base64BytesToFromStringSerializer::class) PassportElementHash>,
    @SerialName(messageField)
    override val message: String
) : PassportElementFilesError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = filesField
}
fun EncryptedPassportElementWithFilesCollection.createFilesError(message: String, unencryptedFileHashes: List<PassportElementHash>) = PassportElementErrorFiles(
    type,
    unencryptedFileHashes,
    message
)


@Serializable
data class PassportElementErrorTranslationFile(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = translationFileField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorTranslationFile

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElementTranslatable.createFileError(message: String, unencryptedFileHash: PassportElementHash) = PassportElementErrorTranslationFile(
    type,
    unencryptedFileHash,
    message
)
@Serializable
data class PassportElementErrorTranslationFiles(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashesField)
    override val elementsHashes: List<@Serializable(Base64BytesToFromStringSerializer::class) PassportElementHash>,
    @SerialName(messageField)
    override val message: String
) : PassportElementFilesError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = translationFilesField
}
fun EncryptedPassportElementTranslatable.createFilesError(message: String, unencryptedFileHashes: List<PassportElementHash>) = PassportElementErrorTranslationFiles(
    type,
    unencryptedFileHashes,
    message
)

@Serializable
data class PassportElementErrorUnspecified(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val elementHash: PassportElementHash,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    @EncodeDefault
    override val source: String = unspecifiedField
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportElementErrorUnspecified

        if (type != other.type) return false
        if (!elementHash.contentEquals(other.elementHash)) return false
        if (message != other.message) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + elementHash.contentHashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }
}
fun EncryptedPassportElement.createUnspecifiedError(message: String, elementHash: PassportElementHash) = PassportElementErrorUnspecified(
    type,
    elementHash,
    message
)
