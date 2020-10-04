package dev.inmo.tgbotapi.CommonAbstracts

interface CommonVenueData : Titled {
    override val title: String
    val address: String
    val foursquareId: String?
    val foursquareType: String? // TODO:: Rewrite with enum or interface
}
