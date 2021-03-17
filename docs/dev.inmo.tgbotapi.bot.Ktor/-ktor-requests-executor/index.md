//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot.Ktor](../index.md)/[KtorRequestsExecutor](index.md)



# KtorRequestsExecutor  
 [common] class [KtorRequestsExecutor](index.md)(**telegramAPIUrlsKeeper**: [TelegramAPIUrlsKeeper](../../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), **client**: , **callsFactories**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[KtorCallFactory](../-ktor-call-factory/index.md)>, **excludeDefaultFactories**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **requestsLimiter**: [RequestLimiter](../../dev.inmo.tgbotapi.bot.settings.limiters/-request-limiter/index.md), **jsonFormatter**: ) : [BaseRequestsExecutor](../../dev.inmo.tgbotapi.bot/-base-requests-executor/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor/close/#/PointingToDeclaration/"></a>[close](close.md)| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor/close/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [close](close.md)()  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>[execute](execute.md)| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>open suspend override fun <[T](execute.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [execute](execute.md)(request: [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[T](execute.md)>): [T](execute.md)  <br>More info  <br>Unsafe execution of incoming [request](execute.md).  <br><br><br>|

