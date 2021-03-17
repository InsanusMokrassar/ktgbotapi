//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.passport](../index.md)/[PassportData](index.md)



# PassportData  
 [common] data class [PassportData](index.md)(**data**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[EncryptedPassportElement](../../dev.inmo.tgbotapi.types.passport.encrypted.abstracts/-encrypted-passport-element/index.md)>, **credentials**: [EncryptedCredentials](../../dev.inmo.tgbotapi.types.passport.credentials/-encrypted-credentials/index.md))   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.passport/PassportData/credentials/#/PointingToDeclaration/"></a>[credentials](credentials.md)| <a name="dev.inmo.tgbotapi.types.passport/PassportData/credentials/#/PointingToDeclaration/"></a> [common] val [credentials](credentials.md): [EncryptedCredentials](../../dev.inmo.tgbotapi.types.passport.credentials/-encrypted-credentials/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.passport/PassportData/data/#/PointingToDeclaration/"></a>[data](data.md)| <a name="dev.inmo.tgbotapi.types.passport/PassportData/data/#/PointingToDeclaration/"></a> [common] val [data](data.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[EncryptedPassportElement](../../dev.inmo.tgbotapi.types.passport.encrypted.abstracts/-encrypted-passport-element/index.md)>   <br>|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.utils.passport//doInDecryptionContextWithPKCS8Key/dev.inmo.tgbotapi.types.passport.PassportData#java.security.PrivateKey#kotlin.String?#kotlin.Function1[dev.inmo.tgbotapi.types.passport.decrypted.SecureData,TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[doInDecryptionContextWithPKCS8Key](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)| <a name="dev.inmo.tgbotapi.utils.passport//doInDecryptionContextWithPKCS8Key/dev.inmo.tgbotapi.types.passport.PassportData#java.security.PrivateKey#kotlin.String?#kotlin.Function1[dev.inmo.tgbotapi.types.passport.decrypted.SecureData,TypeParam(bounds=[kotlin.Any?])]/PointingToDeclaration/"></a>[jvm]  <br>Content  <br>inline fun <[T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)> [PassportData](index.md#%5Bdev.inmo.tgbotapi.types.passport%2FPassportData%2F%2F%2FPointingToDeclaration%2F%5D%2FExtensions%2F745855401).[doInDecryptionContextWithPKCS8Key](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)(pkcs8Key: [PrivateKey](https://docs.oracle.com/javase/8/docs/api/java/security/PrivateKey.html), expectedNonce: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, crossinline block: [SecureData](../../dev.inmo.tgbotapi.types.passport.decrypted/-secure-data/index.md).() -> [T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)): [T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)  <br>inline fun <[T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)> [PassportData](index.md#%5Bdev.inmo.tgbotapi.types.passport%2FPassportData%2F%2F%2FPointingToDeclaration%2F%5D%2FExtensions%2F745855401).[doInDecryptionContextWithPKCS8Key](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)(pkcs8Key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), expectedNonce: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, crossinline block: [SecureData](../../dev.inmo.tgbotapi.types.passport.decrypted/-secure-data/index.md).() -> [T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)): [T](../../dev.inmo.tgbotapi.utils.passport/do-in-decryption-context-with-p-k-c-s8-key.md)  <br><br><br>|

