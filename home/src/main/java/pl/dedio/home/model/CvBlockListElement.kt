package pl.dedio.home.model

import pl.dedio.home.R

sealed class CvBlockListElement(val viewType: Int) {
    override fun equals(other: Any?): Boolean {
        return this.javaClass == other?.javaClass
    }

    override fun hashCode(): Int {
        return viewType.hashCode()
    }

    data class BasicInformation(val date: String, val contactNumber: String, val city: String) :
        CvBlockListElement(R.layout.cell_basic_information)

    data class Experience(val companies: List<ExperienceCompanyItem>) :
        CvBlockListElement(R.layout.cell_experience)

    data class ExperienceCompanyItem(
        val name: String,
        val companyLogoUrl: String,
        val startedAt: String,
        val finishedAt: String?,
        val positionName: String,
        val projects: List<ExperienceCompanyProjectItem>
    ) : CvBlockListElement(R.layout.cell_experience_company_item)

    data class ExperienceCompanyProjectItem(
        val name: String,
        val description: String,
        val role: String,
        val googlePlayUrl: String?
    ) : CvBlockListElement(R.layout.cell_experience_company_project_item)

    data class Languages(val languages: List<LanguageItem>) :
        CvBlockListElement(R.layout.cell_languages)

    data class LanguageItem(
        val name: String,
        val level: Float
    ) : CvBlockListElement(R.layout.cell_language_item)

    data class ProgrammingLanguages(val languages: List<ProgrammingLanguageItem>) :
        CvBlockListElement(R.layout.cell_programming_languages)

    data class ProgrammingLanguageItem(
        val name: String,
        val level: Float,
        val logoUrl: String
    ) : CvBlockListElement(R.layout.cell_programming_language_item)

    data class Skills(val skills: List<SkillItem>) :
        CvBlockListElement(R.layout.cell_skills)

    data class SkillItem(
        val name: String,
        val level: Float
    ) : CvBlockListElement(R.layout.cell_skill_item)
}