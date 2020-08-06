package pl.dedio.cvmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pl.dedio.cvmodels.blocks.BaseBlock

@Parcelize
data class CvUiModel(
    val name: String,
    val photoUrl: String,
    val blocks: List<BaseBlock>,
    val currentPosition: String?
) : Parcelable {
    fun hasCurrentPosition() = currentPosition != null
}