package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.stickers.MaskPosition

inline val Sticker.file_id: FileId
    get() = fileId
inline val Sticker.file_unique_id: FileUniqueId
    get() = fileUniqueId
inline val Sticker.is_animated: Boolean
    get() = this is AnimatedSticker
inline val Sticker.is_video: Boolean
    get() = this is VideoSticker
inline val Sticker.set_name: StickerSetName?
    get() = stickerSetName
inline val Sticker.mask_position: MaskPosition?
    get() = maskPosition
inline val Sticker.file_size: Long?
    get() = fileSize
inline val Sticker.premium_animation: File?
    get() = premiumAnimationFile
