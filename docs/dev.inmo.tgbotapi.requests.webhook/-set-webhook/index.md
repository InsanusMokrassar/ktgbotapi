//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.webhook](../index.md)/[SetWebhook](index.md)



# SetWebhook  
 [common] data class [SetWebhook](index.md) : [SetWebhookRequest](../-set-webhook-request/index.md), [DataRequest](../../dev.inmo.tgbotapi.requests.send.media.base/-data-request/index.md)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)> 

Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an update for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.



If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the [url](url.md), e.g. https://www.example.com/<token>. Since nobody else knows your bot's token, you can be pretty sure it's us.

   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/allowedUpdates/#/PointingToDeclaration/"></a>[allowedUpdates](allowed-updates.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/allowedUpdates/#/PointingToDeclaration/"></a> [common] val [allowedUpdates](allowed-updates.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/certificateFile/#/PointingToDeclaration/"></a>[certificateFile](certificate-file.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/certificateFile/#/PointingToDeclaration/"></a> [common] val [certificateFile](certificate-file.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/dropPendingUpdates/#/PointingToDeclaration/"></a>[dropPendingUpdates](drop-pending-updates.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/dropPendingUpdates/#/PointingToDeclaration/"></a> [common] val [dropPendingUpdates](drop-pending-updates.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/ipAddress/#/PointingToDeclaration/"></a>[ipAddress](ip-address.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/ipAddress/#/PointingToDeclaration/"></a> [common] val [ipAddress](ip-address.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/maxAllowedConnections/#/PointingToDeclaration/"></a>[maxAllowedConnections](max-allowed-connections.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/maxAllowedConnections/#/PointingToDeclaration/"></a> [common] val [maxAllowedConnections](max-allowed-connections.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)>   <br>|
| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/url/#/PointingToDeclaration/"></a>[url](url.md)| <a name="dev.inmo.tgbotapi.requests.webhook/SetWebhook/url/#/PointingToDeclaration/"></a> [common] val [url](url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|

