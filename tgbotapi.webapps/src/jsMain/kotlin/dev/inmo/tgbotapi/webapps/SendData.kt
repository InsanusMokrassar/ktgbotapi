package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.WebAppQueryId

/**
 * @param onSendData Should return the data which must be used in [WebApp.sendData]. If returns null, data will not be sent
 * @param onAnswerWebAppQuery In case if [WebAppInitData.queryId] is presented in [WebApp.initDataUnsafe], will be called
 * that callback. Before and after calling of this callback will not be used any method of answering to the telegram
 * system, so, you must use something like [answerWebAppQuery] by yourself to send the result
 */
inline fun sendDataOrWorkWithQueryId(
    onSendData: () -> String?,
    onAnswerWebAppQuery: (WebAppQueryId) -> Unit
) {
    val queryId = webApp.initDataUnsafe.queryId

    if (queryId == null) {
        webApp.sendData(onSendData() ?: return)
    } else {
        onAnswerWebAppQuery(queryId)
    }
}

/**
 * @param onSendData Should return the data which must be used in [WebApp.sendData]. If returns null, data will not be sent
 * @param onAnswerWebAppQuery In case if [WebAppInitData.queryId] is presented in [WebApp.initDataUnsafe], will be called
 * that callback. Before and after calling of this callback will not be used any method of answering to the telegram
 * system, so, you must use something like [answerWebAppQuery] by yourself to send the result
 */
inline fun handleResult(
    onSendData: () -> String?,
    onAnswerWebAppQuery: (WebAppQueryId) -> Unit
) = sendDataOrWorkWithQueryId(onSendData, onAnswerWebAppQuery)
