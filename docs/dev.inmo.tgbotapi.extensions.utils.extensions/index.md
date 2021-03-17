//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.extensions](index.md)



# Package dev.inmo.tgbotapi.extensions.utils.extensions  


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//download/dev.inmo.tgbotapi.types.files.PathedFile#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#io.ktor.client.HttpClient/PointingToDeclaration/"></a>[download](download.md)| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//download/dev.inmo.tgbotapi.types.files.PathedFile#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#io.ktor.client.HttpClient/PointingToDeclaration/"></a>[common]  <br>Content  <br>suspend fun [PathedFile](../dev.inmo.tgbotapi.types.files/-pathed-file/index.md).[download](download.md)(telegramAPIUrlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), client:  = HttpClient()): [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//loadFile/io.ktor.client.HttpClient#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#dev.inmo.tgbotapi.types.files.PathedFile/PointingToDeclaration/"></a>[loadFile](load-file.md)| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//loadFile/io.ktor.client.HttpClient#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#dev.inmo.tgbotapi.types.files.PathedFile/PointingToDeclaration/"></a>[common]  <br>Content  <br>suspend fun .[loadFile](load-file.md)(telegramAPIUrlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), pathedFile: [PathedFile](../dev.inmo.tgbotapi.types.files/-pathed-file/index.md)): [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)  <br>suspend fun .[loadFile](load-file.md)(telegramAPIUrlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//sourceChat/dev.inmo.tgbotapi.types.update.abstracts.Update#/PointingToDeclaration/"></a>[sourceChat](source-chat.md)| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//sourceChat/dev.inmo.tgbotapi.types.update.abstracts.Update#/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md).[sourceChat](source-chat.md)(): [Chat](../dev.inmo.tgbotapi.types.chat.abstracts/-chat/index.md)?  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//allSentMediaGroupsFlow/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#/PointingToDeclaration/"></a>[allSentMediaGroupsFlow](all-sent-media-groups-flow.md)| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//allSentMediaGroupsFlow/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#/PointingToDeclaration/"></a> [common] val [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md).[allSentMediaGroupsFlow](all-sent-media-groups-flow.md): <[SentMediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-sent-media-group-update/index.md)>   <br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//allSentMessagesFlow/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#/PointingToDeclaration/"></a>[allSentMessagesFlow](all-sent-messages-flow.md)| <a name="dev.inmo.tgbotapi.extensions.utils.extensions//allSentMessagesFlow/dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#/PointingToDeclaration/"></a> [common] val [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md).[allSentMessagesFlow](all-sent-messages-flow.md): <[BaseSentMessageUpdate](../dev.inmo.tgbotapi.types.update.abstracts/-base-sent-message-update/index.md)>   <br>|

