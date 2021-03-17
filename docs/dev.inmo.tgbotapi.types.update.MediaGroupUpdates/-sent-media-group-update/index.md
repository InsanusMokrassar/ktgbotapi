//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.update.MediaGroupUpdates](../index.md)/[SentMediaGroupUpdate](index.md)



# SentMediaGroupUpdate  
 [common] interface [SentMediaGroupUpdate](index.md) : [MediaGroupUpdate](../-media-group-update/index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/data/#/PointingToDeclaration/"></a>[data](data.md)| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/data/#/PointingToDeclaration/"></a> [common] abstract override val [data](data.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MediaGroupMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-media-group-message/index.md)<[MediaGroupContent](../../dev.inmo.tgbotapi.types.message.content.abstracts/-media-group-content/index.md)>>   <br>|
| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/origins/#/PointingToDeclaration/"></a>[origins](origins.md)| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/origins/#/PointingToDeclaration/"></a> [common] abstract val [origins](origins.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[BaseMessageUpdate](../../dev.inmo.tgbotapi.types.update.abstracts/-base-message-update/index.md)>   <br>|
| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/updateId/#/PointingToDeclaration/"></a>[updateId](index.md#%5Bdev.inmo.tgbotapi.types.update.MediaGroupUpdates%2FSentMediaGroupUpdate%2FupdateId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/SentMediaGroupUpdate/updateId/#/PointingToDeclaration/"></a> [common] abstract val [updateId](index.md#%5Bdev.inmo.tgbotapi.types.update.MediaGroupUpdates%2FSentMediaGroupUpdate%2FupdateId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [UpdateIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/ChannelPostMediaGroupUpdate///PointingToDeclaration/"></a>[ChannelPostMediaGroupUpdate](../-channel-post-media-group-update/index.md)|
| <a name="dev.inmo.tgbotapi.types.update.MediaGroupUpdates/MessageMediaGroupUpdate///PointingToDeclaration/"></a>[MessageMediaGroupUpdate](../-message-media-group-update/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//chat/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[chat](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/chat.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//chat/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [SentMediaGroupUpdate](index.md).[chat](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/chat.md): [Chat](../../dev.inmo.tgbotapi.types.chat.abstracts/-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//createResend/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#kotlin.Boolean#kotlin.Long?/PointingToDeclaration/"></a>[createResend](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/create-resend.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//createResend/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#kotlin.Boolean#kotlin.Long?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [SentMediaGroupUpdate](index.md).[createResend](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/create-resend.md)(disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyTo: [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null): [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[MediaGroupMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-media-group-message/index.md)<[MediaGroupContent](../../dev.inmo.tgbotapi.types.message.content.abstracts/-media-group-content/index.md)>>>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//forwardInfo/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[forwardInfo](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/forward-info.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//forwardInfo/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [SentMediaGroupUpdate](index.md).[forwardInfo](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/forward-info.md): [ForwardInfo](../../dev.inmo.tgbotapi.types.message/-forward-info/index.md)?  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//mediaGroupId/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[mediaGroupId](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/media-group-id.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//mediaGroupId/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [SentMediaGroupUpdate](index.md).[mediaGroupId](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/media-group-id.md): [MediaGroupIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMediaGroupIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//replyTo/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[replyTo](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/reply-to.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//replyTo/dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate#/PointingToDeclaration/"></a>[common]  <br>Content  <br>val [SentMediaGroupUpdate](index.md).[replyTo](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/reply-to.md): [Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)?  <br><br><br>|

