//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates.retrieving](index.md)/[includeWebhookHandlingInRoute](include-webhook-handling-in-route.md)



# includeWebhookHandlingInRoute  
[jvm]  
Content  
fun .[includeWebhookHandlingInRoute](include-webhook-handling-in-route.md)(scope: , exceptionsHandler: <[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>? = null, mediaGroupsDebounceTimeMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000L, block: <[Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>)  
More info  


Allows to include webhook in custom route everywhere in your server



## See also  
  
jvm  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md)| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.updateshandlers.UpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-updates-filter/as-update-receiver.md)| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>|
  


## Parameters  
  
jvm  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>scope| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>Will be used for mapping of media groups<br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>exceptionsHandler| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>Pass this parameter to set custom exception handler for getting updates<br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>block| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//includeWebhookHandlingInRoute/io.ktor.routing.Route#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]?#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>Some receiver block like [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md)<br><br>|
  
  



