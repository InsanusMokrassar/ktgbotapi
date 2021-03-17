//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.shortcuts](index.md)/[executeUnsafe](execute-unsafe.md)



# executeUnsafe  
[common]  
Content  
suspend fun <[T](execute-unsafe.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md).[executeUnsafe](execute-unsafe.md)(request: [Request](../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<[T](execute-unsafe.md)>, retries: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, retriesDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000L, onAllFailed: suspend (exceptions: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null): [T](execute-unsafe.md)?  



