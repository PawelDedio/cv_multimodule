package pl.dedio.cvmodels.blocks

import android.os.Parcelable
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory

abstract class BaseBlock : Parcelable {

    abstract val blockType: BlockType
}

val baseBlockTypeAdapter : RuntimeTypeAdapterFactory<BaseBlock> = RuntimeTypeAdapterFactory.of(BaseBlock::class.java, "type")
    .registerSubtype(BasicInformation::class.java, BlockType.BASIC_INFORMATION.typeName)
    .registerSubtype(Experience::class.java, BlockType.EXPERIENCE.typeName)
    .registerSubtype(Languages::class.java, BlockType.LANGUAGES.typeName)
    .registerSubtype(ProgrammingLanguages::class.java, BlockType.PROGRAMMING_LANGUAGES.typeName)
    .registerSubtype(Skills::class.java, BlockType.SKILLS.typeName)