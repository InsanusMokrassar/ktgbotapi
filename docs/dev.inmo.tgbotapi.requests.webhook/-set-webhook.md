//[docs](../../index.md)/[dev.inmo.tgbotapi.requests.webhook](index.md)/[SetWebhook](-set-webhook.md)



# SetWebhook  
[common]  
Content  
fun [SetWebhook](-set-webhook.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), certificate: [MultipartFile](../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md), ipAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, maxAllowedConnections: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, allowedUpdates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, dropPendingUpdates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [MultipartSetWebhookRequest](-multipart-set-webhook-request/index.md)  
fun [SetWebhook](-set-webhook.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), certificate: [FileId](../dev.inmo.tgbotapi.requests.abstracts/-file-id/index.md), ipAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, maxAllowedConnections: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, allowedUpdates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, dropPendingUpdates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [SetWebhook](-set-webhook/index.md)  


[common]  
Content  
fun [SetWebhook](-set-webhook.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), certificate: [InputFile](../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md), ipAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, maxAllowedConnections: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, allowedUpdates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, dropPendingUpdates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [SetWebhookRequest](-set-webhook-request/index.md)  
fun [SetWebhook](-set-webhook.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ipAddress: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, maxAllowedConnections: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, allowedUpdates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null, dropPendingUpdates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [SetWebhook](-set-webhook/index.md)  
More info  


Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an update for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.



If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the [url](-set-webhook.md), e.g. https://www.example.com/<token>. Since nobody else knows your bot's token, you can be pretty sure it's us.

  



