//[docs](../../index.md)/[dev.inmo.tgbotapi.requests.send.media](index.md)/[SendDocument](-send-document.md)



# SendDocument  
[common]  
Content  
fun [SendDocument](-send-document.md)(chatId: [ChatIdentifier](../dev.inmo.tgbotapi.types/-chat-identifier/index.md), document: [InputFile](../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md), thumb: [InputFile](../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md)? = null, caption: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, parseMode: [ParseMode](../dev.inmo.tgbotapi.types.ParseMode/-parse-mode/index.md)? = null, disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null, disableContentTypeDetection: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Request](../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>>  
More info  


Use this method to send general files. On success, the sent [ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md) with [DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md) is returned. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.content.media.DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>|
  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>disableContentTypeDetection| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Disables automatic server-side content type detection for [document](../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md)<br><br>|
  
  


[common]  
Content  
fun [SendDocument](-send-document.md)(chatId: [ChatIdentifier](../dev.inmo.tgbotapi.types/-chat-identifier/index.md), document: [InputFile](../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md), thumb: [InputFile](../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md)? = null, entities: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>, disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null, disableContentTypeDetection: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null): [Request](../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)>>  
More info  


Use this method to send general files. On success, the sent [ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md) with [DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md) is returned. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.types.message.content.media.DocumentContent](../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>|
  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a>disableContentTypeDetection| <a name="dev.inmo.tgbotapi.requests.send.media//SendDocument/#dev.inmo.tgbotapi.types.ChatIdentifier#dev.inmo.tgbotapi.requests.abstracts.InputFile#dev.inmo.tgbotapi.requests.abstracts.InputFile?#kotlin.collections.List[dev.inmo.tgbotapi.CommonAbstracts.TextSource]#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?#kotlin.Boolean?/PointingToDeclaration/"></a><br><br>Disables automatic server-side content type detection for [document](../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md)<br><br>|
  
  



