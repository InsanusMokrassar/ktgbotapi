//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.formatting](index.md)/[makeLinkToMessage](make-link-to-message.md)



# makeLinkToMessage  
[common]  
Content  
fun [makeLinkToMessage](make-link-to-message.md)(username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), messageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
fun [makeLinkToMessage](make-link-to-message.md)(username: [Username](../dev.inmo.tgbotapi.types/-username/index.md), messageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  
fun [makeLinkToMessage](make-link-to-message.md)(chat: [UsernameChat](../dev.inmo.tgbotapi.types.chat.abstracts/-username-chat/index.md), messageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?  


[common]  
Content  
fun [makeLinkToMessage](make-link-to-message.md)(chat: [Chat](../dev.inmo.tgbotapi.types.chat.abstracts/-chat/index.md), messageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?  
More info  


Link which can be used as by any user to get access to [Message](../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md). Returns null in case when there are no known way to build link (for [PrivateChat](../dev.inmo.tgbotapi.types.chat.abstracts/-private-chat/index.md)s, for example)

  



