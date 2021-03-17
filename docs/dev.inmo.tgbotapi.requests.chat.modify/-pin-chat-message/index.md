//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.chat.modify](../index.md)/[PinChatMessage](index.md)



# PinChatMessage  
 [common] data class [PinChatMessage](index.md)(**chatId**: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), **messageId**: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **disableNotification**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ChatRequest](../../dev.inmo.tgbotapi.CommonAbstracts.types/-chat-request/index.md), [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)> , [MessageAction](../../dev.inmo.tgbotapi.CommonAbstracts.types/-message-action/index.md), [DisableNotification](../../dev.inmo.tgbotapi.CommonAbstracts.types/-disable-notification/index.md)

Use this method to add a message to the list of pinned messages in a chat. If the chat is not a private chat, the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or 'can_edit_messages' admin right in a channel.

   


## Constructors  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/PinChatMessage/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Long#kotlin.Boolean/PointingToDeclaration/"></a>[PinChatMessage](-pin-chat-message.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/PinChatMessage/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Long#kotlin.Boolean/PointingToDeclaration/"></a> [common] fun [PinChatMessage](-pin-chat-message.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), messageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)   <br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/chatId/#/PointingToDeclaration/"></a>[chatId](chat-id.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/chatId/#/PointingToDeclaration/"></a> [common] open override val [chatId](chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/disableNotification/#/PointingToDeclaration/"></a>[disableNotification](disable-notification.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/disableNotification/#/PointingToDeclaration/"></a> [common] open override val [disableNotification](disable-notification.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/messageId/#/PointingToDeclaration/"></a>[messageId](message-id.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/messageId/#/PointingToDeclaration/"></a> [common] open override val [messageId](message-id.md): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/PinChatMessage/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)>   <br>|

