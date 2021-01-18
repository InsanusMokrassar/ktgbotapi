package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.UnknownEncryptedPassportElement
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializer(EncryptedPassportElement::class)
object EncryptedElementSerializer : KSerializer<EncryptedPassportElement> {
    private val jsonSerializer = JsonObject.serializer()
    override val descriptor: SerialDescriptor = jsonSerializer.descriptor

    override fun deserialize(decoder: Decoder): EncryptedPassportElement {
        val json = jsonSerializer.deserialize(decoder)
        return when (json[typeField] ?.jsonPrimitive ?.content) {
            "personal_details" -> nonstrictJsonFormat.decodeFromJsonElement(EncryptedPersonalDetails.serializer(), json)
            "passport" -> nonstrictJsonFormat.decodeFromJsonElement(CommonPassport.serializer(), json)
            "driver_license" -> nonstrictJsonFormat.decodeFromJsonElement(DriverLicense.serializer(), json)
            "identity_card" -> nonstrictJsonFormat.decodeFromJsonElement(IdentityCard.serializer(), json)
            "internal_passport" -> nonstrictJsonFormat.decodeFromJsonElement(InternalPassport.serializer(), json)
            "address" -> nonstrictJsonFormat.decodeFromJsonElement(EncryptedAddress.serializer(), json)
            "utility_bill" -> nonstrictJsonFormat.decodeFromJsonElement(UtilityBill.serializer(), json)
            "bank_statement" -> nonstrictJsonFormat.decodeFromJsonElement(BankStatement.serializer(), json)
            "rental_agreement" -> nonstrictJsonFormat.decodeFromJsonElement(RentalAgreement.serializer(), json)
            "passport_registration" -> nonstrictJsonFormat.decodeFromJsonElement(PassportRegistration.serializer(), json)
            "temporary_registration" -> nonstrictJsonFormat.decodeFromJsonElement(TemporaryRegistration.serializer(), json)
            "phone_number" -> nonstrictJsonFormat.decodeFromJsonElement(PhoneNumber.serializer(), json)
            "email" -> nonstrictJsonFormat.decodeFromJsonElement(Email.serializer(), json)
            else -> UnknownEncryptedPassportElement(json, json[hashField] ?.jsonPrimitive ?.content ?: "")
        }
    }

    override fun serialize(encoder: Encoder, value: EncryptedPassportElement) {
        val json = when (value) {
            is EncryptedPersonalDetails -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(EncryptedPersonalDetails.serializer(), value).jsonObject + (typeField to JsonPrimitive("personal_details"))
            )
            is CommonPassport -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(CommonPassport.serializer(), value).jsonObject + (typeField to JsonPrimitive("passport"))
            )
            is DriverLicense -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(DriverLicense.serializer(), value).jsonObject + (typeField to JsonPrimitive("driver_license"))
            )
            is IdentityCard -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(IdentityCard.serializer(), value).jsonObject + (typeField to JsonPrimitive("identity_card"))
            )
            is InternalPassport -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(InternalPassport.serializer(), value).jsonObject + (typeField to JsonPrimitive("internal_passport"))
            )
            is EncryptedAddress -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(EncryptedAddress.serializer(), value).jsonObject + (typeField to JsonPrimitive("address"))
            )
            is UtilityBill -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(UtilityBill.serializer(), value).jsonObject + (typeField to JsonPrimitive("utility_bill"))
            )
            is BankStatement -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(BankStatement.serializer(), value).jsonObject + (typeField to JsonPrimitive("bank_statement"))
            )
            is RentalAgreement -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(RentalAgreement.serializer(), value).jsonObject + (typeField to JsonPrimitive("rental_agreement"))
            )
            is PassportRegistration -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(PassportRegistration.serializer(), value).jsonObject + (typeField to JsonPrimitive("passport_registration"))
            )
            is TemporaryRegistration -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(TemporaryRegistration.serializer(), value).jsonObject + (typeField to JsonPrimitive("temporary_registration"))
            )
            is PhoneNumber -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(PhoneNumber.serializer(), value).jsonObject + (typeField to JsonPrimitive("phone_number"))
            )
            is Email -> JsonObject(
                nonstrictJsonFormat.encodeToJsonElement(Email.serializer(), value).jsonObject + (typeField to JsonPrimitive("email"))
            )
            is UnknownEncryptedPassportElement -> value.rawJson
            else -> return
        }
        jsonSerializer.serialize(encoder, json)
    }

}
