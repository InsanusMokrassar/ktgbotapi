package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.encapsulator.Encapsulator
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

val encryptedElementsClassesByTypes = mapOf(
    "personal_details" to Encapsulator(EncryptedPersonalDetails::class, EncryptedPersonalDetails.serializer()),
    "passport" to Encapsulator(CommonPassport::class, CommonPassport.serializer()),
    "driver_license" to Encapsulator(DriverLicense::class, DriverLicense.serializer()),
    "identity_card" to Encapsulator(IdentityCard::class, IdentityCard.serializer()),
    "internal_passport" to Encapsulator(InternalPassport::class, InternalPassport.serializer()),
    "address" to Encapsulator(EncryptedAddress::class, EncryptedAddress.serializer()),
    "utility_bill" to Encapsulator(UtilityBill::class, UtilityBill.serializer()),
    "bank_statement" to Encapsulator(BankStatement::class, BankStatement.serializer()),
    "rental_agreement" to Encapsulator(RentalAgreement::class, RentalAgreement.serializer()),
    "passport_registration" to Encapsulator(PassportRegistration::class, PassportRegistration.serializer()),
    "temporary_registration" to Encapsulator(TemporaryRegistration::class, TemporaryRegistration.serializer()),
    "phone_number" to Encapsulator(PhoneNumber::class, PhoneNumber.serializer()),
    "email" to Encapsulator(Email::class, Email.serializer())
)

@Serializer(EncryptedPassportElement::class)
object EncryptedElementSerializer : KSerializer<EncryptedPassportElement> {
    private val jsonSerializer = JsonObject.serializer()
    override val descriptor: SerialDescriptor = jsonSerializer.descriptor

    override fun deserialize(decoder: Decoder): EncryptedPassportElement {
        val json = jsonSerializer.deserialize(decoder)
        return json[typeField] ?.jsonPrimitive ?.content ?.let { type ->
            encryptedElementsClassesByTypes[type] ?.serializer ?.let { deserializer ->
                nonstrictJsonFormat.decodeFromJsonElement(deserializer, json)
            }
        } ?: UnknownEncryptedPassportElement(json, json[hashField] ?.jsonPrimitive ?.content ?: "")
    }

    override fun serialize(encoder: Encoder, value: EncryptedPassportElement) {
        val json = value.let {
            encryptedElementsClassesByTypes.forEach { (key, encapsulator) ->
                val json = encapsulator.encapsulate(value) { data ->
                    nonstrictJsonFormat.encodeToJsonElement(this as KSerializer<EncryptedPassportElement>, data).jsonObject
                } ?: return@forEach
                return@let JsonObject(json + (typeField to JsonPrimitive(key)))
            }
            (value as? UnknownEncryptedPassportElement) ?.rawJson ?: return
        }
        jsonSerializer.serialize(encoder, json)
    }
}
