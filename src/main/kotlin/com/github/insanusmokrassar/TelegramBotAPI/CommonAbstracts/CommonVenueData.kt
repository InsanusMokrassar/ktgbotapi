package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

interface CommonVenueData {
    val title: String
    val address: String
    val foursquareId: String?
    val foursquareType: String? // TODO:: Rewrite with enum or interface
}
