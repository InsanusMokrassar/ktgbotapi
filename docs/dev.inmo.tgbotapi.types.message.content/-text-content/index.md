//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content](../index.md)/[TextContent](index.md)



# TextContent  
 [common] data class [TextContent](index.md)(**text**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **textEntities**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>) : [MessageContent](../../dev.inmo.tgbotapi.types.message.content.abstracts/-message-content/index.md), [TextedInput](../../dev.inmo.tgbotapi.CommonAbstracts/-texted-input/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [createResend](create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)?): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[TextContent](index.md)>>  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/text/#/PointingToDeclaration/"></a>[text](text.md)| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/text/#/PointingToDeclaration/"></a> [common] open override val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/textEntities/#/PointingToDeclaration/"></a>[textEntities](text-entities.md)| <a name="dev.inmo.tgbotapi.types.message.content/TextContent/textEntities/#/PointingToDeclaration/"></a> [common] open override val [textEntities](text-entities.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>Here must be full list of entities.   <br>|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toHtmlTexts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[toHtmlTexts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-html-texts.md)| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toHtmlTexts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [TextContent](index.md).[toHtmlTexts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-html-texts.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toMarkdownTexts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[toMarkdownTexts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-markdown-texts.md)| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toMarkdownTexts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [TextContent](index.md).[toMarkdownTexts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-markdown-texts.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toMarkdownV2Texts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[toMarkdownV2Texts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-markdown-v2-texts.md)| <a name="dev.inmo.tgbotapi.extensions.utils.formatting//toMarkdownV2Texts/dev.inmo.tgbotapi.types.message.content.TextContent#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [TextContent](index.md).[toMarkdownV2Texts](../../dev.inmo.tgbotapi.extensions.utils.formatting/to-markdown-v2-texts.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>  <br><br><br>|

