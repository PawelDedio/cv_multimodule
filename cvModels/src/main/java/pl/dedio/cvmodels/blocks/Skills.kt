package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Skills(@SerializedName("skills") val skills: List<Skill>) : BaseBlock() {

    @IgnoredOnParcel
    override val blockType
        get() = BlockType.SKILLS
}

@Parcelize
data class Skill(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Float
) : Parcelable