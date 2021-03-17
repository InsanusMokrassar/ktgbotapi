//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.api.utils](index.md)



# Package dev.inmo.tgbotapi.extensions.api.utils  


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.api.utils//updateHandlerWithMediaGroupsAdaptation/kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]#kotlin.Long/PointingToDeclaration/"></a>[updateHandlerWithMediaGroupsAdaptation](update-handler-with-media-groups-adaptation.md)| <a name="dev.inmo.tgbotapi.extensions.api.utils//updateHandlerWithMediaGroupsAdaptation/kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.types.update.abstracts.Update,kotlin.Unit]#kotlin.Long/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun .[updateHandlerWithMediaGroupsAdaptation](update-handler-with-media-groups-adaptation.md)(output: [UpdateReceiver](../dev.inmo.tgbotapi.updateshandlers/index.md#%5Bdev.inmo.tgbotapi.updateshandlers%2FUpdateReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>, mediaGroupsDebounceMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 1000L): [UpdateReceiver](../dev.inmo.tgbotapi.updateshandlers/index.md#%5Bdev.inmo.tgbotapi.updateshandlers%2FUpdateReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>  <br>More info  <br>Create [UpdateReceiver](../dev.inmo.tgbotapi.updateshandlers/index.md#%5Bdev.inmo.tgbotapi.updateshandlers%2FUpdateReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081) object which will correctly accumulate updates and send into output updates which INCLUDE [dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate](../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-media-group-update/index.md)s.  <br><br><br>|

