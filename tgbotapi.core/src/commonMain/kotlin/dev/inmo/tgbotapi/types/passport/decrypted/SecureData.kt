package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SecureData(
    @SerialName(personalDetailsField)
    val personalDetails: PersonalDetailsSecureValue? = null,
    @SerialName(passportField)
    val passport: CommonPassportSecureValue? = null,
    @SerialName(internalPassportField)
    val internalPassport: InternalPassportSecureValue? = null,
    @SerialName(driverLicenseField)
    val driverLicense: DriverLicenseSecureValue? = null,
    @SerialName(identityCardField)
    val identityCard: IdentityCardSecureValue? = null,
    @SerialName(utilityBillField)
    val utilityBill: UtilityBillSecureValue? = null,
    @SerialName(bankStatementField)
    val bankStatement: BankStatementSecureValue? = null,
    @SerialName(rentalAgreementField)
    val rentalAgreement: RentalAgreementSecureValue? = null,
    @SerialName(passportRegistrationField)
    val passportRegistration: PassportRegistrationSecureValue? = null,
    @SerialName(temporaryRegistrationField)
    val temporaryRegistration: TemporalRegistrationSecureValue? = null,
) {
    val allCredentials by lazy {
        (personalDetails ?.credentials ?: emptyList()) +
            (passport ?.credentials ?: emptyList()) +
            (internalPassport ?.credentials ?: emptyList()) +
            (driverLicense ?.credentials ?: emptyList()) +
            (identityCard ?.credentials ?: emptyList()) +
            (utilityBill ?.credentials ?: emptyList()) +
            (bankStatement ?.credentials ?: emptyList()) +
            (rentalAgreement ?.credentials ?: emptyList()) +
            (passportRegistration ?.credentials ?: emptyList()) +
            (temporaryRegistration ?.credentials ?: emptyList())
    }
}
