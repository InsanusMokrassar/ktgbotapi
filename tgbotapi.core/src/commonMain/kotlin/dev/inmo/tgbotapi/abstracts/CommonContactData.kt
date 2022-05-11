package dev.inmo.tgbotapi.abstracts

interface CommonContactData {
    val phoneNumber: String
    val firstName: String
    val lastName: String?
    val vcard: String? // TODO:: Replace by some vCard abstraction
}
