package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgrammingLanguages(@SerializedName("programming_languages") val programmingLanguages: List<ProgrammingLanguage>) :
    BaseBlock() {

    @IgnoredOnParcel
    override val blockType = BlockType.PROGRAMMING_LANGUAGES
}

@Parcelize
data class ProgrammingLanguage(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Float,
    @SerializedName("logo_url") val logoUrl: String
) : Parcelable