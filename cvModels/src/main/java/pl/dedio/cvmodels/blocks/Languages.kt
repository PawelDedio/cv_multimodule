package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Languages(@SerializedName("languages") val languages: List<Language>) : BaseBlock() {

    @IgnoredOnParcel
    override val blockType = BlockType.LANGUAGES
}

@Parcelize
data class Language(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Float
) : Parcelable