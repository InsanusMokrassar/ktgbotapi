//[docs](../../index.md)/[dev.inmo.tgbotapi.bot.Ktor](index.md)



# Package dev.inmo.tgbotapi.bot.Ktor  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorCallFactory///PointingToDeclaration/"></a>[KtorCallFactory](-ktor-call-factory/index.md)| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorCallFactory///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [KtorCallFactory](-ktor-call-factory/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor///PointingToDeclaration/"></a>[KtorRequestsExecutor](-ktor-requests-executor/index.md)| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor///PointingToDeclaration/"></a>[common]  <br>Content  <br>class [KtorRequestsExecutor](-ktor-requests-executor/index.md)(**telegramAPIUrlsKeeper**: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), **client**: , **callsFactories**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[KtorCallFactory](-ktor-call-factory/index.md)>, **excludeDefaultFactories**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **requestsLimiter**: [RequestLimiter](../dev.inmo.tgbotapi.bot.settings.limiters/-request-limiter/index.md), **jsonFormatter**: ) : [BaseRequestsExecutor](../dev.inmo.tgbotapi.bot/-base-requests-executor/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutorBuilder///PointingToDeclaration/"></a>[KtorRequestsExecutorBuilder](-ktor-requests-executor-builder/index.md)| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutorBuilder///PointingToDeclaration/"></a>[common]  <br>Content  <br>class [KtorRequestsExecutorBuilder](-ktor-requests-executor-builder/index.md)(**telegramAPIUrlsKeeper**: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md))  <br><br><br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.Ktor//telegramBot/#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#kotlin.Function1[dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder,kotlin.Unit]/PointingToDeclaration/"></a>[telegramBot](telegram-bot.md)| <a name="dev.inmo.tgbotapi.bot.Ktor//telegramBot/#dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper#kotlin.Function1[dev.inmo.tgbotapi.bot.Ktor.KtorRequestsExecutorBuilder,kotlin.Unit]/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [telegramBot](telegram-bot.md)(telegramAPIUrlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), crossinline builder: [KtorRequestsExecutorBuilder](-ktor-requests-executor-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)  <br><br><br>[common]  <br>Content  <br>inline fun [telegramBot](telegram-bot.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), apiUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = telegramBotAPIDefaultUrl, crossinline builder: [KtorRequestsExecutorBuilder](-ktor-requests-executor-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)  <br>More info  <br>Shortcut for [telegramBot](telegram-bot.md)  <br><br><br>|

