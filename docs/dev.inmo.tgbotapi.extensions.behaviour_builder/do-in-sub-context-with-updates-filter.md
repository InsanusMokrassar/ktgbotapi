//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.behaviour_builder](index.md)/[doInSubContextWithUpdatesFilter](do-in-sub-context-with-updates-filter.md)



# doInSubContextWithUpdatesFilter  
[common]  
Content  
suspend fun <[T](do-in-sub-context-with-updates-filter.md)> [BehaviourContext](-behaviour-context/index.md).[doInSubContextWithUpdatesFilter](do-in-sub-context-with-updates-filter.md)(updatesFilter: [BehaviourContextAndTypeReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextAndTypeReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), [Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>?, stopOnCompletion: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, behaviourContextReceiver: [BehaviourContextReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[T](do-in-sub-context-with-updates-filter.md)>): [T](do-in-sub-context-with-updates-filter.md)  
More info  


Creates new one [BehaviourContext](-behaviour-context/index.md), adding subsequent [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) in case [updatesFilter](do-in-sub-context-with-updates-filter.md) is provided and  as new [BehaviourContext.scope](-behaviour-context/scope.md)

  



