package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PassportFile
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(EncryptedElementSerializer::class)
sealed class EncryptedPassportElementWithTranslatableFilesCollection : EncryptedPassportElementTranslatable, EncryptedPassportElementWithFilesCollection

@Serializable
data class UtilityBill(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableFilesCollection() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as UtilityBill

        if (files != other.files) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.hashCode()
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

@Serializable
data class BankStatement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableFilesCollection() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as BankStatement

        if (files != other.files) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.hashCode()
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

@Serializable
data class RentalAgreement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableFilesCollection() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as RentalAgreement

        if (files != other.files) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.hashCode()
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

@Serializable
data class PassportRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableFilesCollection() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PassportRegistration

        if (files != other.files) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.hashCode()
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

@Serializable
data class TemporaryRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableFilesCollection() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TemporaryRegistration

        if (files != other.files) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.hashCode()
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

