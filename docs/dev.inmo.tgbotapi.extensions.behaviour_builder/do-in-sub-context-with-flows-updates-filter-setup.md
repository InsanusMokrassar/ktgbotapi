//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.behaviour_builder](index.md)/[doInSubContextWithFlowsUpdatesFilterSetup](do-in-sub-context-with-flows-updates-filter-setup.md)



# doInSubContextWithFlowsUpdatesFilterSetup  
[common]  
Content  
suspend fun <[T](do-in-sub-context-with-flows-updates-filter-setup.md)> [BehaviourContext](-behaviour-context/index.md).[doInSubContextWithFlowsUpdatesFilterSetup](do-in-sub-context-with-flows-updates-filter-setup.md)(newFlowsUpdatesFilterSetUp: [BehaviourContextAndTypeReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextAndTypeReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md)>?, stopOnCompletion: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, behaviourContextReceiver: [BehaviourContextReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[T](do-in-sub-context-with-flows-updates-filter-setup.md)>): [T](do-in-sub-context-with-flows-updates-filter-setup.md)  
More info  


Creates new one [BehaviourContext](-behaviour-context/index.md), adding subsequent [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) in case [newFlowsUpdatesFilterSetUp](do-in-sub-context-with-flows-updates-filter-setup.md) is provided and  as new [BehaviourContext.scope](-behaviour-context/scope.md). You must do all subscription/running of longPolling manually.



## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//doInSubContextWithFlowsUpdatesFilterSetup/dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext#kotlin.coroutines.SuspendFunction2[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter,kotlin.Unit]?#kotlin.Boolean#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>newFlowsUpdatesFilterSetUp| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//doInSubContextWithFlowsUpdatesFilterSetup/dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext#kotlin.coroutines.SuspendFunction2[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter,kotlin.Unit]?#kotlin.Boolean#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a><br><br>As a parameter receives [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) from old [this](-behaviour-context/flows-updates-filter.md)<br><br>|
  
  



