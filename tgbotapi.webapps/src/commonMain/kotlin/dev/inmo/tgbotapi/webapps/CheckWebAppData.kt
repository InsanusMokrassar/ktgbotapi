package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.utils.*

/**
 * @param rawData Data from [dev.inmo.tgbotapi.webapps.WebApp.initData]
 * @param hash Data from [dev.inmo.tgbotapi.webapps.WebApp.initDataUnsafe] from the field [dev.inmo.tgbotapi.webapps.WebAppInitData.hash]
 */
fun TelegramAPIUrlsKeeper.checkWebAppLink(rawData: String, hash: String) = rawData.hmacSha256(webAppDataSecretKey).hex() == hash
