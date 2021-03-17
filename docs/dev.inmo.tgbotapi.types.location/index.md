//[docs](../../index.md)/[dev.inmo.tgbotapi.types.location](index.md)



# Package dev.inmo.tgbotapi.types.location  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.location/LiveLocation///PointingToDeclaration/"></a>[LiveLocation](-live-location/index.md)| <a name="dev.inmo.tgbotapi.types.location/LiveLocation///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [LiveLocation](-live-location/index.md)(**longitude**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **latitude**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **horizontalAccuracy**: [Meters](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMeters%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **livePeriod**: [Seconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **heading**: [Degrees](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FDegrees%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **proximityAlertRadius**: [Meters](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMeters%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?) : [Location](-location/index.md), [Livable](../dev.inmo.tgbotapi.CommonAbstracts/-livable/index.md), [ProximityAlertable](../dev.inmo.tgbotapi.CommonAbstracts/-proximity-alertable/index.md), [Headed](../dev.inmo.tgbotapi.CommonAbstracts/-headed/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.location/Location///PointingToDeclaration/"></a>[Location](-location/index.md)| <a name="dev.inmo.tgbotapi.types.location/Location///PointingToDeclaration/"></a>[common]  <br>Content  <br>sealed class [Location](-location/index.md) : [Locationed](../dev.inmo.tgbotapi.CommonAbstracts/-locationed/index.md), [HorizontallyAccured](../dev.inmo.tgbotapi.CommonAbstracts/-horizontally-accured/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.location/LocationSerializer///PointingToDeclaration/"></a>[LocationSerializer](-location-serializer/index.md)| <a name="dev.inmo.tgbotapi.types.location/LocationSerializer///PointingToDeclaration/"></a>[common]  <br>Content  <br>object [LocationSerializer](-location-serializer/index.md) : <[Location](-location/index.md)>   <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.location/StaticLocation///PointingToDeclaration/"></a>[StaticLocation](-static-location/index.md)| <a name="dev.inmo.tgbotapi.types.location/StaticLocation///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [StaticLocation](-static-location/index.md)(**longitude**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **latitude**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **horizontalAccuracy**: [Meters](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMeters%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?) : [Location](-location/index.md)  <br><br><br>|

