package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class Experience(@SerializedName("companies") val companies: List<Company>) :
    BaseBlock() {

    @IgnoredOnParcel
    override val blockType
        get() = BlockType.EXPERIENCE
}

@Parcelize
data class Company(
    @SerializedName("name") val name: String,
    @SerializedName("company_logo_url") val companyLogoUrl: String,
    @SerializedName("started_at") val startedAt: LocalDate,
    @SerializedName("finished_at") val finishedAt: LocalDate?,
    @SerializedName("position_name") val positionName: String,
    @SerializedName("projects") val projects: List<Project>
) : Parcelable

@Parcelize
data class Project(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("role") val role: String,
    @SerializedName("google_play_url") val googlePlayUrl: String?
) : Parcelable