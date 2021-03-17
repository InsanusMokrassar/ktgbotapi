//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates.retrieving](index.md)/[startListenWebhooks](start-listen-webhooks.md)



# startListenWebhooks  
[jvm]  
Content  
fun [startListenWebhooks](start-listen-webhooks.md)(listenPort: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), engineFactory: <*, *>, exceptionsHandler: <[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>, listenHost: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = "0.0.0.0", listenRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, privateKeyConfig: [WebhookPrivateKeyConfig](../dev.inmo.tgbotapi.updateshandlers.webhook/-webhook-private-key-config/index.md)? = null, scope:  = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()), mediaGroupsDebounceTimeMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000L, block: <[Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>):   
More info  


Setting up ktor server



## See also  
  
jvm  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md)| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.updateshandlers.UpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-updates-filter/as-update-receiver.md)| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>|
  


## Parameters  
  
jvm  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>listenPort| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>port which will be listen by bot<br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>listenRoute| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>address to listen by bot. If null - will be set up in root of host<br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>scope| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>Scope which will be used for<br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a>privateKeyConfig| <a name="dev.inmo.tgbotapi.extensions.utils.updates.retrieving//startListenWebhooks/#kotlin.Int#io.ktor.server.engine.ApplicationEngineFactory[*,*]#kotlin.coroutines.SuspendFunction1[kotlin.Throwable,kotlin.Unit]#kotlin.String#kotlin.String?#dev.inmo.tgbotapi.updateshandlers.webhook.WebhookPrivateKeyConfig?#kotlinx.coroutines.CoroutineScope#kotlin.Long#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]/PointingToDeclaration/"></a><br><br>If configured - server will be created with .  will be used otherwise<br><br>|
  
  



