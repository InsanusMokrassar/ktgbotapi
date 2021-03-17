//[docs](../../../index.md)/[dev.inmo.tgbotapi.bot.settings.limiters](../index.md)/[PowLimiter](index.md)



# PowLimiter  
 [common] data class [PowLimiter](index.md)(**minAwaitTime**: [MilliSeconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **maxAwaitTime**: [MilliSeconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMilliSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **powValue**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **powK**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **scope**: ) : [RequestLimiter](../-request-limiter/index.md)   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.bot.settings.limiters/PowLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[limit](limit.md)| <a name="dev.inmo.tgbotapi.bot.settings.limiters/PowLimiter/limit/#kotlin.coroutines.SuspendFunction0[TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[common]  <br>Content  <br>open suspend override fun <[T](limit.md)> [limit](limit.md)(block: suspend () -> [T](limit.md)): [T](limit.md)  <br>More info  <br>Use limit for working of block (like delay between or after, for example)  <br><br><br>|

