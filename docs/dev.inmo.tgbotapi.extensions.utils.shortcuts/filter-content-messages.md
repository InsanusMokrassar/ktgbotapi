//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.shortcuts](index.md)/[filterContentMessages](filter-content-messages.md)



# filterContentMessages  
[common]  
Content  
inline fun <[T](filter-content-messages.md) : [MessageContent](../dev.inmo.tgbotapi.types.message.content.abstracts/-message-content/index.md)> <[BaseSentMessageUpdate](../dev.inmo.tgbotapi.types.update.abstracts/-base-sent-message-update/index.md)>.[filterContentMessages](filter-content-messages.md)(): <[ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[T](filter-content-messages.md)>>  


[common]  
Content  
inline fun <[T](filter-content-messages.md) : [MessageContent](../dev.inmo.tgbotapi.types.message.content.abstracts/-message-content/index.md)> [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md).[filterContentMessages](filter-content-messages.md)(scopeToIncludeChannels: ? = null): <[ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[T](filter-content-messages.md)>>  
More info  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterContentMessages/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlinx.coroutines.CoroutineScope?/PointingToDeclaration/"></a>scopeToIncludeChannels| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterContentMessages/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlinx.coroutines.CoroutineScope?/PointingToDeclaration/"></a><br><br>This parameter is required when you want to include [textMessages](text-messages.md) for channels too. In this case will be created new channel which will aggregate messages from [FlowsUpdatesFilter.messageFlow](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/message-flow.md) and [FlowsUpdatesFilter.channelPostFlow](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/channel-post-flow.md). In case it is null will be used s mapping<br><br>|
  
  



