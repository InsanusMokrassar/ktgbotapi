//[docs](../../index.md)/[dev.inmo.tgbotapi.types.MessageEntity.textsources](index.md)/[mention](mention.md)



# mention  
[common]  
Content  
inline fun [mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>): [MentionTextSource](-mention-text-source/index.md)  
inline fun [mention](mention.md)(vararg parts: [TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)): [MentionTextSource](-mention-text-source/index.md)  
inline fun [mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>, user: [User](../dev.inmo.tgbotapi.types/-user/index.md)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [User](../dev.inmo.tgbotapi.types/-user/index.md).[mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>, userId: [UserId](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [UserId](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>, id: [Identifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [Identifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[mention](mention.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(user: [User](../dev.inmo.tgbotapi.types/-user/index.md), vararg parts: [TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), user: [User](../dev.inmo.tgbotapi.types/-user/index.md)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [User](../dev.inmo.tgbotapi.types/-user/index.md).[mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), userId: [UserId](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [UserId](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUserId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), id: [Identifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [TextMentionTextSource](-text-mention-text-source/index.md)  
inline fun [Identifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[mention](mention.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TextMentionTextSource](-text-mention-text-source/index.md)  


[common]  
Content  
inline fun [mention](mention.md)(whoToMention: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [MentionTextSource](-mention-text-source/index.md)  
More info  


Without leading "@"

  



