//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.behaviour_builder.expectations](index.md)/[waitPassportMessagesWith](wait-passport-messages-with.md)



# waitPassportMessagesWith  
[common]  
Content  
inline suspend fun <[T](wait-passport-messages-with.md) : [EncryptedPassportElement](../dev.inmo.tgbotapi.types.passport.encrypted.abstracts/-encrypted-passport-element/index.md)> [BehaviourContext](../dev.inmo.tgbotapi.extensions.behaviour_builder/-behaviour-context/index.md).[waitPassportMessagesWith](wait-passport-messages-with.md)(count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1, initRequest: [Request](../dev.inmo.tgbotapi.requests.abstracts/-request/index.md)<*>? = null, noinline errorFactory: [NullableRequestBuilder](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder.expectations%2FNullableRequestBuilder%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<*> = { null }, noinline filter: [PassportMessageMapper](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder.expectations%2FPassportMessageMapper%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[PassportData](../dev.inmo.tgbotapi.types.passport/-passport-data/index.md)>  



