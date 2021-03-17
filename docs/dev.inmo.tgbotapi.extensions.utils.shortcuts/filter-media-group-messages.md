//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.shortcuts](index.md)/[filterMediaGroupMessages](filter-media-group-messages.md)



# filterMediaGroupMessages  
[common]  
Content  
inline fun <[T](filter-media-group-messages.md) : [MediaGroupContent](../dev.inmo.tgbotapi.types.message.content.abstracts/-media-group-content/index.md)> <[SentMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-sent-media-group-update/index.md)>.[filterMediaGroupMessages](filter-media-group-messages.md)(): <[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[CommonMessage](../dev.inmo.tgbotapi.types.message.abstracts/-common-message/index.md)<[T](filter-media-group-messages.md)>>>  


[common]  
Content  
inline fun <[T](filter-media-group-messages.md) : [MediaGroupContent](../dev.inmo.tgbotapi.types.message.content.abstracts/-media-group-content/index.md)> [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md).[filterMediaGroupMessages](filter-media-group-messages.md)(scopeToIncludeChannels: ? = null): <[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[CommonMessage](../dev.inmo.tgbotapi.types.message.abstracts/-common-message/index.md)<[T](filter-media-group-messages.md)>>>  
More info  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterMediaGroupMessages/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlinx.coroutines.CoroutineScope?/PointingToDeclaration/"></a>scopeToIncludeChannels| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterMediaGroupMessages/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlinx.coroutines.CoroutineScope?/PointingToDeclaration/"></a><br><br>This parameter is required when you want to include [SentMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-sent-media-group-update/index.md) for channels too. In this case will be created new channel which will aggregate messages from [FlowsUpdatesFilter.messageFlow](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/message-flow.md) and [FlowsUpdatesFilter.channelPostFlow](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/channel-post-flow.md). In case it is null will be used s mapping<br><br>|
  
  



