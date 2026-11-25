package dev.inmo.tgbotapi.types.payments.ton

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.gifts.Gift
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/** Address of a wallet, NFT or collection on the TON blockchain. */
@Serializable
@JvmInline
value class TonAddress(val value: String)

/** Public wallet card displayed on a user's Telegram profile. */
@Serializable
data class TonWalletCard(
    @SerialName(addressField) val address: TonAddress,
    /** Balance in the smallest currency units, represented as a decimal string. */
    @SerialName(amountField) val amount: String,
    @SerialName(stickersField) val stickers: List<TonWalletCardSticker>,
    @SerialName(backgroundNftAddressField) val backgroundNftAddress: TonAddress? = null,
)

@Serializable
data class TonWalletCardSticker(
    @SerialName(positionField) val position: AreaPosition,
    @SerialName(nftField) val nft: TonNft,
)

@Serializable
data class TonNft(
    @SerialName(addressField) val address: TonAddress,
    @SerialName(indexField) val index: String,
    @SerialName(nameField) val name: String,
    @SerialName(descriptionField) val description: String,
    @SerialName(collectionAddressField) val collectionAddress: TonAddress? = null,
    @SerialName(attributesField) val attributes: List<TonNftAttribute>? = null,
    @SerialName(giftField) val gift: Gift.Unique? = null,
)

@Serializable
data class TonNftAttribute(
    @SerialName(traitTypeField) val traitType: String,
    @SerialName(valueField) val value: String,
)
