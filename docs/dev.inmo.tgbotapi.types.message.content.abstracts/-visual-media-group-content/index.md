//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content.abstracts](../index.md)/[VisualMediaGroupContent](index.md)



# VisualMediaGroupContent  
 [common] interface [VisualMediaGroupContent](index.md) : [MediaGroupContent](../-media-group-content/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[asInputMedia](../-media-content/as-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [asInputMedia](../-media-content/as-input-media.md)(): [InputMedia](../../dev.inmo.tgbotapi.types.InputMedia/-input-media/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](../-resendable-content/create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [createResend](../-resendable-content/create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<out [Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract override fun [toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)(): [VisualMediaGroupMemberInputMedia](../../dev.inmo.tgbotapi.types.InputMedia/-visual-media-group-member-input-media/index.md)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/caption/#/PointingToDeclaration/"></a>[caption](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2Fcaption%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/caption/#/PointingToDeclaration/"></a> [common] abstract val [caption](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2Fcaption%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/captionEntities/#/PointingToDeclaration/"></a>[captionEntities](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2FcaptionEntities%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/captionEntities/#/PointingToDeclaration/"></a> [common] abstract val [captionEntities](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2FcaptionEntities%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>Full list of entities.   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/media/#/PointingToDeclaration/"></a>[media](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2Fmedia%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent/media/#/PointingToDeclaration/"></a> [common] abstract val [media](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FVisualMediaGroupContent%2Fmedia%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [TelegramMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-telegram-media-file/index.md)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.message.content.media/PhotoContent///PointingToDeclaration/"></a>[PhotoContent](../../dev.inmo.tgbotapi.types.message.content.media/-photo-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.media/VideoContent///PointingToDeclaration/"></a>[VideoContent](../../dev.inmo.tgbotapi.types.message.content.media/-video-content/index.md)|

