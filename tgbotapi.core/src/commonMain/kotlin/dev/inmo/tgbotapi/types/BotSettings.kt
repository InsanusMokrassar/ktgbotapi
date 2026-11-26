package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private inline fun <T : Any, reified V : T> variantSerializer(parent: KSerializer<T>): KSerializer<V> =
    MapperSerializer(parent, { value -> value }, { value ->
        value as? V ?: throw SerializationException("Unexpected variant of ${parent.descriptor.serialName}")
    })

/** Controls whether callbacks identify their source with a message or an inline message ID. */
@Serializable(CallbackQueryMode.Serializer::class)
sealed interface CallbackQueryMode {
    val wireName: String

    @Serializable(Serializer.ForDefault::class)
    data object Default : CallbackQueryMode {
        override val wireName: String = "default"
    }

    @Serializable(Serializer.ForAuto::class)
    data object Auto : CallbackQueryMode {
        override val wireName: String = "auto"
    }

    @Serializable(Serializer.ForInline::class)
    data object Inline : CallbackQueryMode {
        override val wireName: String = "inline"
    }

    object Serializer : KSerializer<CallbackQueryMode> {
        object ForDefault : KSerializer<Default> by variantSerializer<CallbackQueryMode, Default>(Serializer)
        object ForAuto : KSerializer<Auto> by variantSerializer<CallbackQueryMode, Auto>(Serializer)
        object ForInline : KSerializer<Inline> by variantSerializer<CallbackQueryMode, Inline>(Serializer)

        override val descriptor: SerialDescriptor = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): CallbackQueryMode = when (val value = decoder.decodeString()) {
            Default.wireName -> Default
            Auto.wireName -> Auto
            Inline.wireName -> Inline
            else -> throw SerializationException("Unknown CallbackQueryMode value: $value")
        }

        override fun serialize(encoder: Encoder, value: CallbackQueryMode) = encoder.encodeString(value.wireName)
    }
}

/** One chosen inline result is delivered per denominator results. Zero disables feedback. */
@Serializable(InlineFeedbackShareDenominator.Serializer::class)
sealed interface InlineFeedbackShareDenominator {
    val denominator: Int

    @Serializable(Serializer.ForDisabled::class)
    data object Disabled : InlineFeedbackShareDenominator {
        override val denominator: Int = 0
    }

    @Serializable(Serializer.ForAll::class)
    data object All : InlineFeedbackShareDenominator {
        override val denominator: Int = 1
    }

    @Serializable(Serializer.ForOneInTen::class)
    data object OneInTen : InlineFeedbackShareDenominator {
        override val denominator: Int = 10
    }

    @Serializable(Serializer.ForOneInHundred::class)
    data object OneInHundred : InlineFeedbackShareDenominator {
        override val denominator: Int = 100
    }

    @Serializable(Serializer.ForOneInThousand::class)
    data object OneInThousand : InlineFeedbackShareDenominator {
        override val denominator: Int = 1000
    }

    object Serializer : KSerializer<InlineFeedbackShareDenominator> {
        object ForDisabled : KSerializer<Disabled> by variantSerializer<InlineFeedbackShareDenominator, Disabled>(Serializer)
        object ForAll : KSerializer<All> by variantSerializer<InlineFeedbackShareDenominator, All>(Serializer)
        object ForOneInTen : KSerializer<OneInTen> by variantSerializer<InlineFeedbackShareDenominator, OneInTen>(Serializer)
        object ForOneInHundred : KSerializer<OneInHundred> by variantSerializer<InlineFeedbackShareDenominator, OneInHundred>(Serializer)
        object ForOneInThousand : KSerializer<OneInThousand> by variantSerializer<InlineFeedbackShareDenominator, OneInThousand>(Serializer)

        override val descriptor: SerialDescriptor = Int.serializer().descriptor

        override fun deserialize(decoder: Decoder): InlineFeedbackShareDenominator = when (val value = decoder.decodeInt()) {
            Disabled.denominator -> Disabled
            All.denominator -> All
            OneInTen.denominator -> OneInTen
            OneInHundred.denominator -> OneInHundred
            OneInThousand.denominator -> OneInThousand
            else -> throw SerializationException("Unknown InlineFeedbackShareDenominator value: $value")
        }

        override fun serialize(encoder: Encoder, value: InlineFeedbackShareDenominator) = encoder.encodeInt(value.denominator)
    }
}
