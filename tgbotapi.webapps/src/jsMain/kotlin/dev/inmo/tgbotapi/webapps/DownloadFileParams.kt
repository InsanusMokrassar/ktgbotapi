package dev.inmo.tgbotapi.webapps

import kotlin.js.json

external interface DownloadFileParams {
    val url: String
    @JsName("file_name")
    val fileName: String
}
fun DownloadFileParams(
    url: String,
    fileName: String
) = json(
    "url" to url,
    "file_name" to fileName
).unsafeCast<DownloadFileParams>()