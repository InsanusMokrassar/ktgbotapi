package dev.inmo.tgbotapi.requests.abstracts

import com.benasher44.uuid.uuid4
import dev.inmo.micro_utils.common.MPPFile
import dev.inmo.tgbotapi.utils.*
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.Input
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Common type for all files in Telegram Bot API which can be sent via requests like [dev.inmo.tgbotapi.requests.send.media.SendDocument].
 * You may use methods like [MPPFile.asMultipartFile] when you want to send files from your file system, but you should
 * remember about [restrictions][https://core.telegram.org/bots/api#sending-files] in Telegram for bots. In case you
 * wish to send file by its url, use [FileId] and pass your url as [FileId.fileId]
 *
 * @see MPPFile.asMultipartFile
 * @see ByteArray.asMultipartFile
 * @see ByteReadChannel.asMultipartFile
 * @see ByteReadChannelAllocator.asMultipartFile
 *
 * @see fromInput
 * @see fromFile
 * @see fromId
 * @see fromUrl
 */
@Serializable(InputFileSerializer::class)
sealed class InputFile {
    abstract val fileId: String

    companion object {
        operator fun invoke(file: MPPFile) = file.asMultipartFile()

        /**
         * Creates [MultipartFile] based on incoming [filename] and [inputSource]
         */
        fun fromInput(filename: String, inputSource: () -> Input) = MultipartFile(filename, inputSource)

        /**
         * Creates [MultipartFile] based on incoming [MPPFile] (common File in java, for example)
         */
        fun fromFile(file: MPPFile) = invoke(file)

        /**
         * Creates [FileId] from the incomming [id] [String] with believe that it is [FileId]
         */
        fun fromId(id: String) = FileId(id)

        /**
         * Creates [FileUrl] from the incomming [url] [String]
         */
        fun fromUrl(url: String) = FileUrl(url)
    }
}

internal const val attachPrefix = "attach://"

internal inline val InputFile.attachFileId
    get() = "$attachPrefix$fileId"
internal inline val InputFile.fileIdToSend
    get() = when (this) {
        is FileId -> fileId
        is MultipartFile -> attachFileId
    }

// TODO:: add checks for file url/file id regex
/**
 * Contains file id or file url
 */
@Serializable(InputFileSerializer::class)
data class FileId(
    override val fileId: String
) : InputFile()

typealias FileUrl = FileId

fun String.toInputFile() = FileId(this)

@RiskFeature
object InputFileSerializer : KSerializer<InputFile> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(FileId::class.toString(), PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: InputFile) = encoder.encodeString(value.fileIdToSend)
    override fun deserialize(decoder: Decoder): FileId = FileId(decoder.decodeString().removePrefix(attachPrefix))
}

// TODO:: add checks for files size
/**
 * Contains info about file for sending
 *
 * @see asMultipartFile
 */
@Serializable(InputFileSerializer::class)
data class MultipartFile (
    val filename: String,
    private val inputSource: () -> Input
) : InputFile() {
    @Required
    @EncodeDefault
    override val fileId: String = "${uuid4()}.${filename.fileExtension}"
    val input: Input
        get() = inputSource()
}

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannel.asMultipartFile(
    fileName: String
) = MultipartFile(
    fileName,
    inputSource = asInput().let { { it } }
)

@Suppress("NOTHING_TO_INLINE", "unused")
inline fun ByteArray.asMultipartFile(
    fileName: String
) = MultipartFile(
    fileName,
    inputSource = { ByteReadPacket(this) }
)

@Suppress("NOTHING_TO_INLINE", "unused")
suspend inline fun ByteReadChannelAllocator.asMultipartFile(
    fileName: String
) = this.invoke().asMultipartFile(fileName)

expect fun MPPFile.asMultipartFile(): MultipartFile
@Suppress("NOTHING_TO_INLINE")
inline fun MPPFile.multipartFile() = asMultipartFile()
