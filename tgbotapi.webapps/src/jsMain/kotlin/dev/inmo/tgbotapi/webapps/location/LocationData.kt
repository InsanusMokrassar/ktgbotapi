package dev.inmo.tgbotapi.webapps.location

external interface LocationData {
    val latitude: Double
    val longitude: Double
    val altitude: Double?
    val course: Double?
    val speed: Double?

    @JsName("horizontal_accuracy")
    val horizontalAccuracy: Double?

    @JsName("vertical_accuracy")
    val verticalAccuracy: Double?

    @JsName("course_accuracy")
    val courseAccuracy: Double?

    @JsName("speed_accuracy")
    val speedAccuracy: Double?
}
