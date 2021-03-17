//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.chat.modify](../index.md)/[UnpinAllChatMessages](index.md)



# UnpinAllChatMessages  
 [common] data class [UnpinAllChatMessages](index.md)(**chatId**: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)) : [ChatRequest](../../dev.inmo.tgbotapi.CommonAbstracts.types/-chat-request/index.md), [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)> 

Use this method to clear the list of pinned messages in a chat. If the chat is not a private chat, the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' admin right in a supergroup or 'can_edit_messages' admin right in a channel.

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.requests.chat.modify.PinChatMessage](../-pin-chat-message/index.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages///PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.requests.chat.modify.UnpinChatMessage](../-unpin-chat-message/index.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages///PointingToDeclaration/"></a>|
  


## Constructors  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/UnpinAllChatMessages/#dev.inmo.tgbotapi.types.ChatIdentifier/PointingToDeclaration/"></a>[UnpinAllChatMessages](-unpin-all-chat-messages.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/UnpinAllChatMessages/#dev.inmo.tgbotapi.types.ChatIdentifier/PointingToDeclaration/"></a> [common] fun [UnpinAllChatMessages](-unpin-all-chat-messages.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md))   <br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/chatId/#/PointingToDeclaration/"></a>[chatId](chat-id.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/chatId/#/PointingToDeclaration/"></a> [common] open override val [chatId](chat-id.md): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.chat.modify/UnpinAllChatMessages/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)>   <br>|

