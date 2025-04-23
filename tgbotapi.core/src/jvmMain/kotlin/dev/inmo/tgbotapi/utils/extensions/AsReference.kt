package dev.inmo.tgbotapi.utils.extensions

import java.lang.ref.WeakReference

fun <T> T.asReference() = WeakReference(this)
