//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates](index.md)/[toSentMediaGroupUpdate](to-sent-media-group-update.md)



# toSentMediaGroupUpdate  
[common]  
Content  
fun [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[BaseSentMessageUpdate](../dev.inmo.tgbotapi.types.update.abstracts/-base-sent-message-update/index.md)>.[toSentMediaGroupUpdate](to-sent-media-group-update.md)(): [SentMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-sent-media-group-update/index.md)?  
More info  


#### Return  


[MessageMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-message-media-group-update/index.md) in case if [first](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html) object of  is [MessageUpdate](../dev.inmo.tgbotapi.types.update/-message-update/index.md). When [first](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html) object is [ChannelPostUpdate](../dev.inmo.tgbotapi.types.update/-channel-post-update/index.md) instance - will return [ChannelPostMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-channel-post-media-group-update/index.md). Otherwise will be returned null

  



