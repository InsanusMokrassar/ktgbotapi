package dev.inmo.tgbotapi.requests.answers

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.widgets.WidgetQueryId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

/** Answers a widget query. Telegram accepts an answer for 15 seconds after delivery. */
@Serializable
data class AnswerWidgetQuery(
    @SerialName("widget_query_id") val widgetQueryId: WidgetQueryId,
    @SerialName(dataField) val data: String? = null
) : SimpleRequest<Unit> {
    override fun method(): String = "answerWidgetQuery"
    override val resultDeserializer: DeserializationStrategy<Unit> get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
}
