//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot.settings.limiters](../index.md)/[ExceptionsOnlyLimiter](index.md)



# ExceptionsOnlyLimiter  
 [common] class [ExceptionsOnlyLimiter](index.md)(**defaultTooManyRequestsDelay**: [MilliSeconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)) : [RequestLimiter](../-request-limiter/index.md)

This limiter will limit requests only after getting a [RetryAfterError](../../dev.inmo.tgbotapi.types/-retry-after-error/index.md) or  with  status code. Important thing is that in case if some of block has been blocked, all the others will wait until it will be possible to be called

   


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter///PointingToDeclaration/"></a>defaultTooManyRequestsDelay| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter///PointingToDeclaration/"></a><br><br>This parameter will be used in case of getting  with  as a parameter for delay like it would be [TooMuchRequestsException](../../dev.inmo.tgbotapi.bot.exceptions/-too-much-requests-exception/index.md). The reason of it is that in  there is no information about required delay between requests<br><br>|
  


## Constructors  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter/ExceptionsOnlyLimiter/#kotlin.Long/PointingToDeclaration/"></a>[ExceptionsOnlyLimiter](-exceptions-only-limiter.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter/ExceptionsOnlyLimiter/#kotlin.Long/PointingToDeclaration/"></a> [common] fun [ExceptionsOnlyLimiter](-exceptions-only-limiter.md)(defaultTooManyRequestsDelay: [MilliSeconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081) = 1000L)This parameter will be used in case of getting  with  as a parameter for delay like it would be [TooMuchRequestsException](../../dev.inmo.tgbotapi.bot.exceptions/-too-much-requests-exception/index.md).   <br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[limit](limit.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>open suspend override fun <[T](limit.md)> [limit](limit.md)(block: suspend () -> [T](limit.md)): [T](limit.md)  <br>More info  <br>Use limit for working of block (like delay between or after, for example)  <br><br><br>|

