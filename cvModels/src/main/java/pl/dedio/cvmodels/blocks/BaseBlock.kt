package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import pl.dedio.cvmodels.blocks.BlockType

abstract class BaseBlock : Parcelable {

    abstract val blockType: BlockType
}