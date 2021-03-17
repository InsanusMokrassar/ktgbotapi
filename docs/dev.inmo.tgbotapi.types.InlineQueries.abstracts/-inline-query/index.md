//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.InlineQueries.abstracts](../index.md)/[InlineQuery](index.md)



# InlineQuery  
 [common] interface [InlineQuery](index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/from/#/PointingToDeclaration/"></a>[from](from.md)| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/from/#/PointingToDeclaration/"></a> [common] abstract val [from](from.md): [User](../../dev.inmo.tgbotapi.types/-user/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/id/#/PointingToDeclaration/"></a>[id](id.md)| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/id/#/PointingToDeclaration/"></a> [common] abstract val [id](id.md): [InlineQueryIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FInlineQueryIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/offset/#/PointingToDeclaration/"></a>[offset](offset.md)| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/offset/#/PointingToDeclaration/"></a> [common] abstract val [offset](offset.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/query/#/PointingToDeclaration/"></a>[query](query.md)| <a name="dev.inmo.tgbotapi.types.InlineQueries.abstracts/InlineQuery/query/#/PointingToDeclaration/"></a> [common] abstract val [query](query.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.query/BaseInlineQuery///PointingToDeclaration/"></a>[BaseInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-base-inline-query/index.md)|
| <a name="dev.inmo.tgbotapi.types.InlineQueries.query/LocationInlineQuery///PointingToDeclaration/"></a>[LocationInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-location-inline-query/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.utils//asBaseInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[asBaseInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/as-base-inline-query.md)| <a name="dev.inmo.tgbotapi.extensions.utils//asBaseInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [InlineQuery](index.md).[asBaseInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/as-base-inline-query.md)(): [BaseInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-base-inline-query/index.md)?  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils//asLocationInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[asLocationInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/as-location-inline-query.md)| <a name="dev.inmo.tgbotapi.extensions.utils//asLocationInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [InlineQuery](index.md).[asLocationInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/as-location-inline-query.md)(): [LocationInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-location-inline-query/index.md)?  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.answers//createAnswer/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#kotlin.collections.List[dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult]#kotlin.Int?#kotlin.Boolean?#kotlin.String?#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>[createAnswer](../../dev.inmo.tgbotapi.requests.answers/create-answer.md)| <a name="dev.inmo.tgbotapi.requests.answers//createAnswer/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#kotlin.collections.List[dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts.InlineQueryResult]#kotlin.Int?#kotlin.Boolean?#kotlin.String?#kotlin.String?#kotlin.String?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [InlineQuery](index.md).[createAnswer](../../dev.inmo.tgbotapi.requests.answers/create-answer.md)(results: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[InlineQueryResult](../../dev.inmo.tgbotapi.types.InlineQueries.InlineQueryResult.abstracts/-inline-query-result/index.md)> = emptyList(), cachedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, isPersonal: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, nextOffset: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, switchPmText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, switchPmParameter: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null): [AnswerInlineQuery](../../dev.inmo.tgbotapi.requests.answers/-answer-inline-query/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils//requireBaseInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[requireBaseInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/require-base-inline-query.md)| <a name="dev.inmo.tgbotapi.extensions.utils//requireBaseInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [InlineQuery](index.md).[requireBaseInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/require-base-inline-query.md)(): [BaseInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-base-inline-query/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.extensions.utils//requireLocationInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[requireLocationInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/require-location-inline-query.md)| <a name="dev.inmo.tgbotapi.extensions.utils//requireLocationInlineQuery/dev.inmo.tgbotapi.types.InlineQueries.abstracts.InlineQuery#/PointingToDeclaration/"></a>[common]  <br>Content  <br>inline fun [InlineQuery](index.md).[requireLocationInlineQuery](../../dev.inmo.tgbotapi.extensions.utils/require-location-inline-query.md)(): [LocationInlineQuery](../../dev.inmo.tgbotapi.types.InlineQueries.query/-location-inline-query/index.md)  <br><br><br>|

