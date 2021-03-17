//[docs](../../index.md)/[dev.inmo.tgbotapi.bot.settings.limiters](index.md)



# Package dev.inmo.tgbotapi.bot.settings.limiters  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/CommonLimiter///PointingToDeclaration/"></a>[CommonLimiter](-common-limiter/index.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/CommonLimiter///PointingToDeclaration/"></a>[common]  <br>Content  <br>class [CommonLimiter](-common-limiter/index.md)(**lockCount**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **regenTime**: [MilliSeconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **scope**: ) : [RequestLimiter](-request-limiter/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter///PointingToDeclaration/"></a>[ExceptionsOnlyLimiter](-exceptions-only-limiter/index.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/ExceptionsOnlyLimiter///PointingToDeclaration/"></a>[common]  <br>Content  <br>class [ExceptionsOnlyLimiter](-exceptions-only-limiter/index.md)(**defaultTooManyRequestsDelay**: [MilliSeconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)) : [RequestLimiter](-request-limiter/index.md)  <br>More info  <br>This limiter will limit requests only after getting a [RetryAfterError](../dev.inmo.tgbotapi.types/-retry-after-error/index.md) or  with  status code.  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/PowLimiter///PointingToDeclaration/"></a>[PowLimiter](-pow-limiter/index.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/PowLimiter///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [PowLimiter](-pow-limiter/index.md)(**minAwaitTime**: [MilliSeconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **maxAwaitTime**: [MilliSeconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **powValue**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **powK**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **scope**: ) : [RequestLimiter](-request-limiter/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/RequestLimiter///PointingToDeclaration/"></a>[RequestLimiter](-request-limiter/index.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/RequestLimiter///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [RequestLimiter](-request-limiter/index.md)  <br><br><br>|

