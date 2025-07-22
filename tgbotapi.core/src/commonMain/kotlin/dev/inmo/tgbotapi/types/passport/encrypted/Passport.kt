@file:Suppress("DuplicatedCode")

package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PassportFile
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(EncryptedElementSerializer::class)
sealed class Passport : EncryptedPassportElementWithData, EncryptedPassportElementWithFrontSide, EncryptedPassportElementWithSelfie, EncryptedPassportElementTranslatable

@Serializable
data class CommonPassport(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile? = null,
    @SerialName(selfieField)
    override val selfie: PassportFile? = null,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : Passport() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CommonPassport

        if (!data.contentEquals(other.data)) return false
        if (frontSide != other.frontSide) return false
        if (selfie != other.selfie) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + (frontSide?.hashCode() ?: 0)
        result = 31 * result + (selfie?.hashCode() ?: 0)
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}

@Serializable
data class InternalPassport(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile? = null,
    @SerialName(selfieField)
    override val selfie: PassportFile? = null,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : Passport() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as InternalPassport

        if (!data.contentEquals(other.data)) return false
        if (frontSide != other.frontSide) return false
        if (selfie != other.selfie) return false
        if (translations != other.translations) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + (frontSide?.hashCode() ?: 0)
        result = 31 * result + (selfie?.hashCode() ?: 0)
        result = 31 * result + translations.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}
