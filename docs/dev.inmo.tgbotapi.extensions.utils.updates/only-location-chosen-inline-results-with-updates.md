//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates](index.md)/[onlyLocationChosenInlineResultsWithUpdates](only-location-chosen-inline-results-with-updates.md)



# onlyLocationChosenInlineResultsWithUpdates  
[common]  
Content  
fun <[ChosenInlineResultUpdate](../dev.inmo.tgbotapi.types.update/-chosen-inline-result-update/index.md)>.[onlyLocationChosenInlineResultsWithUpdates](only-location-chosen-inline-results-with-updates.md)(): <[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)<[UpdateIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), [LocationChosenInlineResult](../dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult/-location-chosen-inline-result/index.md)>>  
More info  


#### Return  


Mapped  with [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)s. [Pair.first](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/first.html) in this pair will be [UpdateIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081). It could be useful in cases you are using [InlineQueryUpdate.updateId](../dev.inmo.tgbotapi.types.update/-inline-query-update/update-id.md) for some reasons. [Pair.second](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/second.html) will always be [LocationChosenInlineResult](../dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult/-location-chosen-inline-result/index.md).

  



