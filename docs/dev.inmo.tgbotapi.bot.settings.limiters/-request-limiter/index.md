//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot.settings.limiters](../index.md)/[RequestLimiter](index.md)



# RequestLimiter  
 [common] interface [RequestLimiter](index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/RequestLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[limit](limit.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/RequestLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>abstract suspend fun <[T](limit.md)> [limit](limit.md)(block: suspend () -> [T](limit.md)): [T](limit.md)  <br>More info  <br>Use limit for working of block (like delay between or after, for example)  <br><br><br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/CommonLimiter///PointingToDeclaration/"></a>[CommonLimiter](../-common-limiter/index.md)|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter///PointingToDeclaration/"></a>[ExceptionsOnlyLimiter](../-exceptions-only-limiter/index.md)|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/PowLimiter///PointingToDeclaration/"></a>[PowLimiter](../-pow-limiter/index.md)|

