package pl.dedio.cvmodels

import com.google.gson.annotations.SerializedName
import pl.dedio.cvmodels.blocks.BaseBlock


data class CvApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("photo_url") val photoUrl: String,
    @SerializedName("blocks") val blocks: List<BaseBlock>
)