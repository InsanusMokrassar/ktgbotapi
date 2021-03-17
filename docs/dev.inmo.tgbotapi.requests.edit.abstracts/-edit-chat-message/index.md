//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.edit.abstracts](../index.md)/[EditChatMessage](index.md)



# EditChatMessage  
 [common] interface [EditChatMessage](index.md)<[MT](index.md) : [MessageContent](../../dev.inmo.tgbotapi.types.message.content.abstracts/-message-content/index.md)> : [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[MT](index.md)>> , [MessageAction](../../dev.inmo.tgbotapi.CommonAbstracts.types/-message-action/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.abstracts/Request/method/#/PointingToDeclaration/"></a>[method](../../dev.inmo.tgbotapi.requests.abstracts/-request/method.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/Request/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [method](../../dev.inmo.tgbotapi.requests.abstracts/-request/method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/chatId/#/PointingToDeclaration/"></a>[chatId](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FchatId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/chatId/#/PointingToDeclaration/"></a> [common] abstract val [chatId](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FchatId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/messageId/#/PointingToDeclaration/"></a>[messageId](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FmessageId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/messageId/#/PointingToDeclaration/"></a> [common] abstract val [messageId](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FmessageId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FrequestSerializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/requestSerializer/#/PointingToDeclaration/"></a> [common] abstract val [requestSerializer](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FrequestSerializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FresultDeserializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.edit.abstracts/EditChatMessage/resultDeserializer/#/PointingToDeclaration/"></a> [common] abstract val [resultDeserializer](index.md#%5Bdev.inmo.tgbotapi.requests.edit.abstracts%2FEditChatMessage%2FresultDeserializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): <[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[MT](index.md)>>   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.requests.edit.LiveLocation/EditChatMessageLiveLocation///PointingToDeclaration/"></a>[EditChatMessageLiveLocation](../../dev.inmo.tgbotapi.requests.edit.LiveLocation/-edit-chat-message-live-location/index.md)|
| <a name="dev.inmo.tgbotapi.requests.edit.LiveLocation/StopChatMessageLiveLocation///PointingToDeclaration/"></a>[StopChatMessageLiveLocation](../../dev.inmo.tgbotapi.requests.edit.LiveLocation/-stop-chat-message-live-location/index.md)|
| <a name="dev.inmo.tgbotapi.requests.edit.ReplyMarkup/EditChatMessageReplyMarkup///PointingToDeclaration/"></a>[EditChatMessageReplyMarkup](../../dev.inmo.tgbotapi.requests.edit.ReplyMarkup/-edit-chat-message-reply-markup/index.md)|
| <a name="dev.inmo.tgbotapi.requests.edit.caption/EditChatMessageCaption///PointingToDeclaration/"></a>[EditChatMessageCaption](../../dev.inmo.tgbotapi.requests.edit.caption/-edit-chat-message-caption/index.md)|
| <a name="dev.inmo.tgbotapi.requests.edit.media/EditChatMessageMedia///PointingToDeclaration/"></a>[EditChatMessageMedia](../../dev.inmo.tgbotapi.requests.edit.media/-edit-chat-message-media/index.md)|
| <a name="dev.inmo.tgbotapi.requests.edit.text/EditChatMessageText///PointingToDeclaration/"></a>[EditChatMessageText](../../dev.inmo.tgbotapi.requests.edit.text/-edit-chat-message-text/index.md)|

