package pl.dedio.cvmodels.blocks

import com.google.gson.annotations.SerializedName

enum class BlockType(@SerializedName("type") val typeName: String) {

    BASIC_INFORMATION(BlockTypes.BASIC_INFORMATION),
    EXPERIENCE(BlockTypes.EXPERIENCE),
    PROGRAMMING_LANGUAGES(BlockTypes.PROGRAMMING_LANGUAGES),
    SKILLS(BlockTypes.SKILLS),
    LANGUAGES(BlockTypes.LANGUAGES),
}

object BlockTypes {
    const val BASIC_INFORMATION = "basic_information"
    const val EXPERIENCE = "experience"
    const val PROGRAMMING_LANGUAGES = "programming_languages"
    const val SKILLS = "skills"
    const val LANGUAGES = "languages"
}