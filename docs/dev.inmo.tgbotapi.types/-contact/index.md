//[docs](../../../index.md)/[dev.inmo.tgbotapi.types](../index.md)/[Contact](index.md)



# Contact  
 [common] data class [Contact](index.md)(**phoneNumber**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **firstName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **lastName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **userId**: [UserId](../index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **vcard**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [CommonContactData](../../dev.inmo.tgbotapi.CommonAbstracts/-common-contact-data/index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types/Contact/firstName/#/PointingToDeclaration/"></a>[firstName](first-name.md)| <a name="dev.inmo.tgbotapi.types/Contact/firstName/#/PointingToDeclaration/"></a> [common] open override val [firstName](first-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.types/Contact/lastName/#/PointingToDeclaration/"></a>[lastName](last-name.md)| <a name="dev.inmo.tgbotapi.types/Contact/lastName/#/PointingToDeclaration/"></a> [common] open override val [lastName](last-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types/Contact/phoneNumber/#/PointingToDeclaration/"></a>[phoneNumber](phone-number.md)| <a name="dev.inmo.tgbotapi.types/Contact/phoneNumber/#/PointingToDeclaration/"></a> [common] open override val [phoneNumber](phone-number.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.types/Contact/userId/#/PointingToDeclaration/"></a>[userId](user-id.md)| <a name="dev.inmo.tgbotapi.types/Contact/userId/#/PointingToDeclaration/"></a> [common] val [userId](user-id.md): [UserId](../index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types/Contact/vcard/#/PointingToDeclaration/"></a>[vcard](vcard.md)| <a name="dev.inmo.tgbotapi.types/Contact/vcard/#/PointingToDeclaration/"></a> [common] open override val [vcard](vcard.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send//toRequest/dev.inmo.tgbotapi.types.Contact#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[toRequest](../../dev.inmo.tgbotapi.requests.send/to-request.md)| <a name="dev.inmo.tgbotapi.requests.send//toRequest/dev.inmo.tgbotapi.types.Contact#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [Contact](index.md).[toRequest](../../dev.inmo.tgbotapi.requests.send/to-request.md)(chatId: [ChatIdentifier](../-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [SendContact](../../dev.inmo.tgbotapi.requests.send/-send-contact/index.md)  <br><br><br>|

