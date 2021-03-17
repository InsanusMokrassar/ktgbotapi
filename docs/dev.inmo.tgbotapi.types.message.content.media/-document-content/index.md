//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content.media](../index.md)/[DocumentContent](index.md)



# DocumentContent  
 [common] data class [DocumentContent](index.md)(**media**: [DocumentFile](../../dev.inmo.tgbotapi.types.files/-document-file/index.md), **caption**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, **captionEntities**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>) : [DocumentMediaGroupContent](../../dev.inmo.tgbotapi.types.message.content.abstracts/-document-media-group-content/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/asInputMedia/#/PointingToDeclaration/"></a>[asInputMedia](as-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/asInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [asInputMedia](as-input-media.md)(): [InputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/-input-media-document/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [createResend](create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)?): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[DocumentContent](index.md)>>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)(): [InputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/-input-media-document/index.md)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/caption/#/PointingToDeclaration/"></a>[caption](caption.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/caption/#/PointingToDeclaration/"></a> [common] open override val [caption](caption.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/captionEntities/#/PointingToDeclaration/"></a>[captionEntities](caption-entities.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/captionEntities/#/PointingToDeclaration/"></a> [common] open override val [captionEntities](caption-entities.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>Full list of entities.   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/media/#/PointingToDeclaration/"></a>[media](media.md)| <a name="dev.inmo.tgbotapi.types.message.content.media/DocumentContent/media/#/PointingToDeclaration/"></a> [common] open override val [media](media.md): [DocumentFile](../../dev.inmo.tgbotapi.types.files/-document-file/index.md)   <br>|

