//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot](../index.md)/[RequestsExecutor](index.md)/[execute](execute.md)



# execute  
[common]  
Content  
abstract suspend fun <[T](execute.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [execute](execute.md)(request: [Request](../../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[T](execute.md)>): [T](execute.md)  
More info  


Unsafe execution of incoming [request](execute.md). Can throw almost any exception. So, it is better to use something like [dev.inmo.tgbotapi.extensions.utils.shortcuts.executeAsync](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/execute-async.md) or [dev.inmo.tgbotapi.extensions.utils.shortcuts.executeUnsafe](../../dev.inmo.tgbotapi.extensions.utils.shortcuts/execute-unsafe.md)



#### Throws  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.bot/RequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>[kotlin.Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)| <a name="dev.inmo.tgbotapi.bot/RequestsExecutor/execute/#dev.inmo.tgbotapi.requests.abstracts.Request[TypeParam(bounds=[kotlin.Any])]/PointingToDeclaration/"></a>|
  



