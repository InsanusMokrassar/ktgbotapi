//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot](../index.md)/[BaseRequestsExecutor](index.md)



# BaseRequestsExecutor  
 [common] abstract class [BaseRequestsExecutor](index.md)(**telegramAPIUrlsKeeper**: [TelegramAPIUrlsKeeper](../../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md)) : [RequestsExecutor](../-requests-executor/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="io.ktor.utils.io.core/Closeable/close/#/PointingToDeclaration/"></a>[close](../../dev.inmo.tgbotapi.extensions.behaviour_builder/-behaviour-context/index.md#%5Bio.ktor.utils.io.core%2FCloseable%2Fclose%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F625018081)| <a name="io.ktor.utils.io.core/Closeable/close/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract fun [close](../../dev.inmo.tgbotapi.extensions.behaviour_builder/-behaviour-context/index.md#%5Bio.ktor.utils.io.core%2FCloseable%2Fclose%2F%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F625018081)()  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot/RequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>[execute](../-requests-executor/execute.md)| <a name="dev.inmo.tgbotapi.bot/RequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun <[T](../-requests-executor/execute.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [execute](../-requests-executor/execute.md)(request: [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[T](../-requests-executor/execute.md)>): [T](../-requests-executor/execute.md)  <br>More info  <br>Unsafe execution of incoming [request](../-requests-executor/execute.md).  <br><br><br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.bot.Ktor/KtorRequestsExecutor///PointingToDeclaration/"></a>[KtorRequestsExecutor](../../dev.inmo.tgbotapi.bot.Ktor/-ktor-requests-executor/index.md)|

