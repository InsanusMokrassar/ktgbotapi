//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.api](index.md)/[telegramBot](telegram-bot.md)



# telegramBot  
[common]  
Content  
fun [telegramBot](telegram-bot.md)(urlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), client: ): [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)  
More info  


Allows to create bot using bot [urlsKeeper](telegram-bot.md) and already prepared [client](telegram-bot.md)

  


[common]  
Content  
inline fun <[T](telegram-bot.md) : > [telegramBot](telegram-bot.md)(urlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), clientFactory: <[T](telegram-bot.md)>, noinline clientConfig: <[T](telegram-bot.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  
More info  


Allows to create bot using bot [urlsKeeper](telegram-bot.md) and specify  by passing [clientFactory](telegram-bot.md) param and optionally configure it with [clientConfig](telegram-bot.md)

  


[common]  
Content  
inline fun [telegramBot](telegram-bot.md)(urlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), clientEngine: , noinline clientConfig: <*>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  
More info  


Allows to create bot using bot [urlsKeeper](telegram-bot.md) and specify  by passing [clientEngine](telegram-bot.md) param and optionally configure  using [clientConfig](telegram-bot.md)

  


[common]  
Content  
inline fun [telegramBot](telegram-bot.md)(urlsKeeper: [TelegramAPIUrlsKeeper](../dev.inmo.tgbotapi.utils/-telegram-a-p-i-urls-keeper/index.md), noinline clientConfig: <*>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  
More info  


Allows to create bot using bot [urlsKeeper](telegram-bot.md) and specify  by configuring  using [clientConfig](telegram-bot.md)

  


[common]  
Content  
inline fun [telegramBot](telegram-bot.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), apiUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = telegramBotAPIDefaultUrl, client: ): [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)  
More info  


Allows to create bot using bot [token](telegram-bot.md), [apiUrl](telegram-bot.md) (for custom api servers) and already prepared [client](telegram-bot.md)

  


[common]  
Content  
inline fun <[T](telegram-bot.md) : > [telegramBot](telegram-bot.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), clientFactory: <[T](telegram-bot.md)>, apiUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = telegramBotAPIDefaultUrl, noinline clientConfig: <[T](telegram-bot.md)>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  


[common]  
Content  
inline fun [telegramBot](telegram-bot.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), clientEngine: , apiUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = telegramBotAPIDefaultUrl, noinline clientConfig: <*>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  
More info  


Allows to create bot using bot [token](telegram-bot.md) and specify  by passing [clientEngine](telegram-bot.md) param and optionally configure  using [clientConfig](telegram-bot.md)

  


[common]  
Content  
inline fun [telegramBot](telegram-bot.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), apiUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = telegramBotAPIDefaultUrl, noinline clientConfig: <*>.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [RequestsExecutor](../dev.inmo.tgbotapi.bot/-requests-executor/index.md)  
More info  


Allows to create bot using bot [token](telegram-bot.md) and [apiUrl](telegram-bot.md) and specify  by configuring  using [clientConfig](telegram-bot.md)

  



