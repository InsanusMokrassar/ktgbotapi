package dev.inmo.tgbotapi.types.passport.errors

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.type
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Encoder

@Serializable(PassportElementErrorSerializer::class)
sealed class PassportElementError {
    abstract val source: String
    abstract val type: String
    abstract val message: String
}

@Serializer(PassportElementError::class)
internal object PassportElementErrorSerializer : KSerializer<PassportElementError> {
    override fun serialize(encoder: Encoder, value: PassportElementError) {
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
        }
    }
}

@Serializable
sealed class PassportSingleElementError : PassportElementError() {
    abstract val elementHash: String
}

@Serializable
sealed class PassportMultipleElementsError : PassportElementError() {
    abstract val elementsHashes: List<String>
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
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportSingleElementError() {
    @SerialName(sourceField)
    @Required
    override val source: String = dataField
}
fun WithData.createDataError(field: String, message: String) = PassportElementErrorDataField(
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
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = frontSideField
}
fun WithFrontSide.createFrontSideError(message: String, unencryptedFileHash: String) = PassportElementErrorFrontSide(
    type,
    unencryptedFileHash,
    message
)

@Serializable
data class PassportElementErrorReverseSide(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = reverseSideField
}
fun WithReverseSide.createReverseSideError(message: String, unencryptedFileHash: String) = PassportElementErrorReverseSide(
    type,
    unencryptedFileHash,
    message
)
@Serializable
data class PassportElementErrorSelfie(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = selfieField
}
fun WithSelfie.createSelfieError(message: String, unencryptedFileHash: String) = PassportElementErrorSelfie(
    type,
    unencryptedFileHash,
    message
)


@Serializable
data class PassportElementErrorFile(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = fileField
}
fun FilesCollection.createFileError(message: String, unencryptedFileHash: String) = PassportElementErrorFile(
    type,
    unencryptedFileHash,
    message
)

@Serializable
data class PassportElementErrorFiles(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashesField)
    override val elementsHashes: List<@Serializable(Base64StringSerializer::class) String>,
    @SerialName(messageField)
    override val message: String
) : PassportElementFilesError() {
    @SerialName(sourceField)
    @Required
    override val source: String = filesField
}
fun FilesCollection.createFilesError(message: String, unencryptedFileHashes: List<String>) = PassportElementErrorFiles(
    type,
    unencryptedFileHashes,
    message
)


@Serializable
data class PassportElementErrorTranslationFile(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = translationFileField
}
fun Translatable.createFileError(message: String, unencryptedFileHash: String) = PassportElementErrorTranslationFile(
    type,
    unencryptedFileHash,
    message
)
@Serializable
data class PassportElementErrorTranslationFiles(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashesField)
    override val elementsHashes: List<@Serializable(Base64StringSerializer::class) String>,
    @SerialName(messageField)
    override val message: String
) : PassportElementFilesError() {
    @SerialName(sourceField)
    @Required
    override val source: String = translationFilesField
}
fun Translatable.createFilesError(message: String, unencryptedFileHashes: List<String>) = PassportElementErrorTranslationFiles(
    type,
    unencryptedFileHashes,
    message
)

@Serializable
data class PassportElementErrorUnspecified(
    @SerialName(typeField)
    override val type: String,
    @SerialName(fileHashField)
    @Serializable(Base64StringSerializer::class)
    override val elementHash: String,
    @SerialName(messageField)
    override val message: String
) : PassportElementFileError() {
    @SerialName(sourceField)
    @Required
    override val source: String = unspecifiedField
}
fun EncryptedPassportElement.createUnspecifiedError(message: String, elementHash: String) = PassportElementErrorUnspecified(
    type,
    elementHash,
    message
)
