//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.send.media](../index.md)/[SendDocumentData](index.md)



# SendDocumentData  
 [common] data class [SendDocumentData](index.md) : [DataRequest](../../dev.inmo.tgbotapi.requests.send.media.base/-data-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>> , [SendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>> , [ReplyingMarkupSendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-replying-markup-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>> , [TextableSendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-textable-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>> , [ThumbedSendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-thumbed-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>> 

Use this method to send general files. On success, the sent [ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md) with [DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md) is returned. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.content.media.DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a>|
  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a>disableContentTypeDetection| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData///PointingToDeclaration/"></a><br><br>Disables automatic server-side content type detection for [document](../../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md)<br><br>|
  


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/allowSendingWithoutReply/#/PointingToDeclaration/"></a>[allowSendingWithoutReply](allow-sending-without-reply.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/allowSendingWithoutReply/#/PointingToDeclaration/"></a> [common] open override val [allowSendingWithoutReply](allow-sending-without-reply.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/chatId/#/PointingToDeclaration/"></a>[chatId](chat-id.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/chatId/#/PointingToDeclaration/"></a> [common] open override val [chatId](chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/disableContentTypeDetection/#/PointingToDeclaration/"></a>[disableContentTypeDetection](disable-content-type-detection.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/disableContentTypeDetection/#/PointingToDeclaration/"></a> [common] val [disableContentTypeDetection](disable-content-type-detection.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = nullDisables automatic server-side content type detection for [document](../../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/disableNotification/#/PointingToDeclaration/"></a>[disableNotification](disable-notification.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/disableNotification/#/PointingToDeclaration/"></a> [common] open override val [disableNotification](disable-notification.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/document/#/PointingToDeclaration/"></a>[document](document.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/document/#/PointingToDeclaration/"></a> [common] val [document](document.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/entities/#/PointingToDeclaration/"></a>[entities](entities.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/entities/#/PointingToDeclaration/"></a> [common] open override val [entities](entities.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/parseMode/#/PointingToDeclaration/"></a>[parseMode](parse-mode.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/parseMode/#/PointingToDeclaration/"></a> [common] open override val [parseMode](parse-mode.md): [ParseMode](../../dev.inmo.tgbotapi.types.ParseMode/-parse-mode/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/replyMarkup/#/PointingToDeclaration/"></a>[replyMarkup](reply-markup.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/replyMarkup/#/PointingToDeclaration/"></a> [common] open override val [replyMarkup](reply-markup.md): [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/replyToMessageId/#/PointingToDeclaration/"></a>[replyToMessageId](reply-to-message-id.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/replyToMessageId/#/PointingToDeclaration/"></a> [common] open override val [replyToMessageId](reply-to-message-id.md): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/text/#/PointingToDeclaration/"></a>[text](text.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/text/#/PointingToDeclaration/"></a> [common] open override val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/thumb/#/PointingToDeclaration/"></a>[thumb](thumb.md)| <a name="dev.inmo.tgbotapi.requests.send.media/SendDocumentData/thumb/#/PointingToDeclaration/"></a> [common] open override val [thumb](thumb.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|

