//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.message.content.abstracts](../index.md)/[MediaContent](index.md)



# MediaContent  
 [common] interface [MediaContent](index.md) : [MessageContent](../-message-content/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[asInputMedia](as-input-media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/asInputMedia/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [asInputMedia](as-input-media.md)(): [InputMedia](../../dev.inmo.tgbotapi.types.InputMedia/-input-media/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[createResend](../-resendable-content/create-resend.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/ResendableContent/createResend/#dev.inmo.tgbotapi.types.ChatIdentifier#kotlin.Boolean#kotlin.Long?#kotlin.Boolean?#dev.inmo.tgbotapi.types.buttons.KeyboardMarkup?/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [createResend](../-resendable-content/create-resend.md)(chatId: [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<out [Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)>  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/media/#/PointingToDeclaration/"></a>[media](media.md)| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaContent/media/#/PointingToDeclaration/"></a> [common] abstract val [media](media.md): [TelegramMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-telegram-media-file/index.md)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaCollectionContent///PointingToDeclaration/"></a>[MediaCollectionContent](../-media-collection-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.abstracts/MediaGroupContent///PointingToDeclaration/"></a>[MediaGroupContent](../-media-group-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.media/AnimationContent///PointingToDeclaration/"></a>[AnimationContent](../../dev.inmo.tgbotapi.types.message.content.media/-animation-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.media/StickerContent///PointingToDeclaration/"></a>[StickerContent](../../dev.inmo.tgbotapi.types.message.content.media/-sticker-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.media/VideoNoteContent///PointingToDeclaration/"></a>[VideoNoteContent](../../dev.inmo.tgbotapi.types.message.content.media/-video-note-content/index.md)|
| <a name="dev.inmo.tgbotapi.types.message.content.media/VoiceContent///PointingToDeclaration/"></a>[VoiceContent](../../dev.inmo.tgbotapi.types.message.content.media/-voice-content/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.message.content.media//asDocumentContent/dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent#/PointingToDeclaration/"></a>[asDocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/as-document-content.md)| <a name="dev.inmo.tgbotapi.types.message.content.media//asDocumentContent/dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent#/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [MediaContent](index.md).[asDocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/as-document-content.md)(): [DocumentContent](../../dev.inmo.tgbotapi.types.message.content.media/-document-content/index.md)  <br><br><br>|

