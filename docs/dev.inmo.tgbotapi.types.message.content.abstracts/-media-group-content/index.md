//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content.abstracts](../index.md)/[MediaGroupContent](index.md)



# MediaGroupContent  
 [common] interface [MediaGroupContent](index.md) : [MediaContent](../-media-content/index.md), [CaptionedInput](../../dev.inmo.tgbotapi.CommonAbstracts/-captioned-input/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[asInputMedia](../-media-content/as-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [asInputMedia](../-media-content/as-input-media.md)(): [InputMedia](../../dev.inmo.tgbotapi.types.InputMedia/-input-media/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](../-resendable-content/create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [createResend](../-resendable-content/create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<out [Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/toMediaGroupMemberInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [toMediaGroupMemberInputMedia](to-media-group-member-input-media.md)(): [MediaGroupMemberInputMedia](../../dev.inmo.tgbotapi.types.InputMedia/-media-group-member-input-media/index.md)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/caption/#/PointingToDeclaration/"></a>[caption](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2Fcaption%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/caption/#/PointingToDeclaration/"></a> [common] abstract val [caption](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2Fcaption%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/captionEntities/#/PointingToDeclaration/"></a>[captionEntities](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2FcaptionEntities%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/captionEntities/#/PointingToDeclaration/"></a> [common] abstract val [captionEntities](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2FcaptionEntities%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextPart](../../dev.inmo.tgbotapi.CommonAbstracts/-text-part/index.md)>Full list of entities.   <br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/media/#/PointingToDeclaration/"></a>[media](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2Fmedia%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent/media/#/PointingToDeclaration/"></a> [common] abstract val [media](index.md#%5Bdev.inmo.tgbotapi.types.message.content.abstracts%2FMediaGroupContent%2Fmedia%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [TelegramMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-telegram-media-file/index.md)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/VisualMediaGroupContent///PointingToDeclaration/"></a>[VisualMediaGroupContent](../-visual-media-group-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/AudioMediaGroupContent///PointingToDeclaration/"></a>[AudioMediaGroupContent](../-audio-media-group-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/DocumentMediaGroupContent///PointingToDeclaration/"></a>[DocumentMediaGroupContent](../-document-media-group-content/index.md)|

