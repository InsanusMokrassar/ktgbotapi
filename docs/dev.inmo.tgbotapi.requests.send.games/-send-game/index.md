//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.send.games](../index.md)/[SendGame](index.md)



# SendGame  
 [common] data class [SendGame](index.md)(**chatId**: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), **gameShortName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **disableNotification**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **replyToMessageId**: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **allowSendingWithoutReply**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, **replyMarkup**: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)?) : [SendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[GameContent](../../dev.inmo.tgbotapi.types.message.content/-game-content/index.md)>> , [ReplyMarkup](../../dev.inmo.tgbotapi.CommonAbstracts.types/-reply-markup/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/allowSendingWithoutReply/#/PointingToDeclaration/"></a>[allowSendingWithoutReply](allow-sending-without-reply.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/allowSendingWithoutReply/#/PointingToDeclaration/"></a> [common] open override val [allowSendingWithoutReply](allow-sending-without-reply.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/chatId/#/PointingToDeclaration/"></a>[chatId](chat-id.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/chatId/#/PointingToDeclaration/"></a> [common] open override val [chatId](chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/disableNotification/#/PointingToDeclaration/"></a>[disableNotification](disable-notification.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/disableNotification/#/PointingToDeclaration/"></a> [common] open override val [disableNotification](disable-notification.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/gameShortName/#/PointingToDeclaration/"></a>[gameShortName](game-short-name.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/gameShortName/#/PointingToDeclaration/"></a> [common] val [gameShortName](game-short-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/replyMarkup/#/PointingToDeclaration/"></a>[replyMarkup](reply-markup.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/replyMarkup/#/PointingToDeclaration/"></a> [common] open override val [replyMarkup](reply-markup.md): [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/replyToMessageId/#/PointingToDeclaration/"></a>[replyToMessageId](reply-to-message-id.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/replyToMessageId/#/PointingToDeclaration/"></a> [common] open override val [replyToMessageId](reply-to-message-id.md): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.send.games/SendGame/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[GameContent](../../dev.inmo.tgbotapi.types.message.content/-game-content/index.md)>>   <br>|

