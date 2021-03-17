//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content.abstracts](../index.md)/[MessageContent](index.md)



# MessageContent  
 [common] interface [MessageContent](index.md) : [ResendableContent](../-resendable-content/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](../-resendable-content/create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [createResend](../-resendable-content/create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<out [Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)>  <br><br><br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.message.content/ContactContent///PointingToDeclaration/"></a>[ContactContent](../../dev.inmo.tgbotapi.types.message.content/-contact-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/DiceContent///PointingToDeclaration/"></a>[DiceContent](../../dev.inmo.tgbotapi.types.message.content/-dice-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/GameContent///PointingToDeclaration/"></a>[GameContent](../../dev.inmo.tgbotapi.types.message.content/-game-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/LocationContent///PointingToDeclaration/"></a>[LocationContent](../../dev.inmo.tgbotapi.types.message.content/-location-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/PollContent///PointingToDeclaration/"></a>[PollContent](../../dev.inmo.tgbotapi.types.message.content/-poll-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/TextContent///PointingToDeclaration/"></a>[TextContent](../../dev.inmo.tgbotapi.types.message.content/-text-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content/VenueContent///PointingToDeclaration/"></a>[VenueContent](../../dev.inmo.tgbotapi.types.message.content/-venue-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaCollectionContent///PointingToDeclaration/"></a>[MediaCollectionContent](../-media-collection-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent///PointingToDeclaration/"></a>[MediaContent](../-media-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.payments/InvoiceContent///PointingToDeclaration/"></a>[InvoiceContent](../../dev.inmo.tgbotapi.types.message.payments/-invoice-content/index.md)|

