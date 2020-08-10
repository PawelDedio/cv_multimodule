package pl.dedio.cvmodels.blocks

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class BasicInformation(
    @SerializedName("birthdate") val birthdate: LocalDate,
    @SerializedName("contact_number") val contactNumber: String,
    @SerializedName("city") val city: String
) : BaseBlock() {

    @IgnoredOnParcel
    override val blockType
        get() = BlockType.BASIC_INFORMATION
}