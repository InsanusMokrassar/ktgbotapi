//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.shortcuts](index.md)/[filterCommandsInsideTextMessages](filter-commands-inside-text-messages.md)



# filterCommandsInsideTextMessages  
[common]  
Content  
fun <[T](filter-commands-inside-text-messages.md) : [ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[TextContent](../dev.inmo.tgbotapi.types.message.content/-text-content/index.md)>> <[T](filter-commands-inside-text-messages.md)>.[filterCommandsInsideTextMessages](filter-commands-inside-text-messages.md)(commandRegex: [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)): <[T](filter-commands-inside-text-messages.md)>  
More info  


Convert incoming [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage.content](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/content.md) of messages with fullEntitiesList and check that incoming message contains [BotCommandTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-bot-command-text-source/index.md). Besides, it is checking that [BotCommandTextSource.command](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/matches.html) with incoming [commandRegex](filter-commands-inside-text-messages.md)



#### Return  


The same message in case if it contains somewhere in text [BotCommandTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-bot-command-text-source/index.md) with [Regex.matches](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-bot-command-text-source/command.md)



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>fullEntitiesList| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[asContentMessagesFlow](../dev.inmo.tgbotapi.extensions.utils.updates/as-content-messages-flow.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[onlyTextContentMessages](../dev.inmo.tgbotapi.extensions.utils/only-text-content-messages.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[textMessages](text-messages.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsInsideTextMessages/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
  
  



