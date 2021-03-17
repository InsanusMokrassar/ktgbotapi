//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.send](../index.md)/[CopyMessage](index.md)



# CopyMessage  
 [common] data class [CopyMessage](index.md) : [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)> , [ReplyingMarkupSendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-replying-markup-send-message-request/index.md)<[MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)> , [MessageAction](../../dev.inmo.tgbotapi.CommonAbstracts.types/-message-action/index.md), [TextedOutput](../../dev.inmo.tgbotapi.CommonAbstracts/-texted-output/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/allowSendingWithoutReply/#/PointingToDeclaration/"></a>[allowSendingWithoutReply](allow-sending-without-reply.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/allowSendingWithoutReply/#/PointingToDeclaration/"></a> [common] open override val [allowSendingWithoutReply](allow-sending-without-reply.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/chatId/#/PointingToDeclaration/"></a>[chatId](chat-id.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/chatId/#/PointingToDeclaration/"></a> [common] open override val [chatId](chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/disableNotification/#/PointingToDeclaration/"></a>[disableNotification](disable-notification.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/disableNotification/#/PointingToDeclaration/"></a> [common] open override val [disableNotification](disable-notification.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/entities/#/PointingToDeclaration/"></a>[entities](entities.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/entities/#/PointingToDeclaration/"></a> [common] open override val [entities](entities.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/fromChatId/#/PointingToDeclaration/"></a>[fromChatId](from-chat-id.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/fromChatId/#/PointingToDeclaration/"></a> [common] val [fromChatId](from-chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/messageId/#/PointingToDeclaration/"></a>[messageId](message-id.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/messageId/#/PointingToDeclaration/"></a> [common] open override val [messageId](message-id.md): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/parseMode/#/PointingToDeclaration/"></a>[parseMode](parse-mode.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/parseMode/#/PointingToDeclaration/"></a> [common] open override val [parseMode](parse-mode.md): [ParseMode](../../dev.inmo.tgbotapi.types.ParseMode/-parse-mode/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/replyMarkup/#/PointingToDeclaration/"></a>[replyMarkup](reply-markup.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/replyMarkup/#/PointingToDeclaration/"></a> [common] open override val [replyMarkup](reply-markup.md): [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/replyToMessageId/#/PointingToDeclaration/"></a>[replyToMessageId](reply-to-message-id.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/replyToMessageId/#/PointingToDeclaration/"></a> [common] open override val [replyToMessageId](reply-to-message-id.md): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/text/#/PointingToDeclaration/"></a>[text](text.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/text/#/PointingToDeclaration/"></a> [common] open override val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/toChatId/#/PointingToDeclaration/"></a>[toChatId](to-chat-id.md)| <a name="dev.inmo.tgbotapi.requests.send/CopyMessage/toChatId/#/PointingToDeclaration/"></a> [common] val [toChatId](to-chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|

