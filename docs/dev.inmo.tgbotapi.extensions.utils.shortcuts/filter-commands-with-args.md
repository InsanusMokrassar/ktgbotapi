//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.shortcuts](index.md)/[filterCommandsWithArgs](filter-commands-with-args.md)



# filterCommandsWithArgs  
[common]  
Content  
fun <[T](filter-commands-with-args.md) : [ContentMessage](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[TextContent](../dev.inmo.tgbotapi.types.message.content/-text-content/index.md)>> <[T](filter-commands-with-args.md)>.[filterCommandsWithArgs](filter-commands-with-args.md)(commandRegex: [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/index.html)): <[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)<[T](filter-commands-with-args.md), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>>>  
More info  


Convert incoming [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage.content](../dev.inmo.tgbotapi.types.message.abstracts/-content-message/content.md) of messages with fullEntitiesList and check that incoming message contains first [TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md) as [BotCommandTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-bot-command-text-source/index.md). Besides, it is checking that [BotCommandTextSource.command](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/matches.html) with incoming [commandRegex](filter-commands-with-args.md) and for other [TextSource](../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md) objects used next rules: all incoming text sources will be passed as is, [RegularTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-regular-text-source/index.md) will be split by " " for several [RegularTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-regular-text-source/index.md) which will contains not empty args without spaces.



#### Return  


Paired original message and converted list with first entity [BotCommandTextSource](../dev.inmo.tgbotapi.types.MessageEntity.textsources/-bot-command-text-source/index.md) and than all others according to rules in description



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>fullEntitiesList| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[asContentMessagesFlow](../dev.inmo.tgbotapi.extensions.utils.updates/as-content-messages-flow.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[onlyTextContentMessages](../dev.inmo.tgbotapi.extensions.utils/only-text-content-messages.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>[textMessages](text-messages.md)| <a name="dev.inmo.tgbotapi.extensions.utils.shortcuts//filterCommandsWithArgs/kotlinx.coroutines.flow.Flow[TypeParam(bounds=[dev.inmo.tgbotapi.types.message.abstracts.ContentMessage[dev.inmo.tgbotapi.types.message.content.TextContent]])]#kotlin.text.Regex/PointingToDeclaration/"></a>|
  
  



