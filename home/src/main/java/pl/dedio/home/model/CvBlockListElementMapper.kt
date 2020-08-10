package pl.dedio.home.model

import pl.dedio.cvmodels.blocks.*
import pl.dedio.cvmultimodule.util.DateFormatter
import java.lang.IllegalStateException
import javax.inject.Inject

class CvBlockListElementMapper @Inject constructor(private val dateFormatter: DateFormatter) {

    fun mapBlocks(blocks: List<BaseBlock>): List<CvBlockListElement> {
        return blocks.map { mapBlock(it) }
    }

    fun mapBlock(block: BaseBlock) = when (block) {
        is BasicInformation -> block.toListElement()
        is Experience -> block.toListElement()
        is Languages -> block.toListElement()
        is ProgrammingLanguages -> block.toListElement()
        is Skills -> block.toListElement()
        else -> throw IllegalStateException("Wrong block type: ${block.blockType}")
    }

    private fun BasicInformation.toListElement(): CvBlockListElement.BasicInformation {
        val date = dateFormatter.toReadableForm(birthdate)

        return CvBlockListElement.BasicInformation(date, contactNumber, city)
    }

    private fun Experience.toListElement(): CvBlockListElement.Experience {
        val companies = companies.toCompaniesListElements()

        return CvBlockListElement.Experience(companies)
    }

    private fun List<Company>.toCompaniesListElements() = map {
        val readableStartedAt = dateFormatter.toReadableForm(it.startedAt)
        val readableFinishedAt = if (it.finishedAt == null) {
            null
        } else {
            dateFormatter.toReadableForm(it.finishedAt!!)
        }

        val projects = it.projects.toProjectsListElements()

        CvBlockListElement.ExperienceCompanyItem(
            it.name,
            it.companyLogoUrl,
            readableStartedAt,
            readableFinishedAt,
            it.positionName,
            projects
        )
    }

    private fun List<Project>.toProjectsListElements() = map {
        CvBlockListElement.ExperienceCompanyProjectItem(
            it.name,
            it.description,
            it.role,
            it.googlePlayUrl
        )
    }

    private fun Languages.toListElement(): CvBlockListElement.Languages {
        val languages = this.languages.toLanguagesListElements()

        return CvBlockListElement.Languages(languages)
    }

    private fun List<Language>.toLanguagesListElements() = map {
        CvBlockListElement.LanguageItem(it.name, it.level)
    }

    private fun ProgrammingLanguages.toListElement(): CvBlockListElement.ProgrammingLanguages {
        val languages = programmingLanguages.toProgrammingLanguagesListElement()

        return CvBlockListElement.ProgrammingLanguages(languages)
    }

    private fun List<ProgrammingLanguage>.toProgrammingLanguagesListElement() = map {
        CvBlockListElement.ProgrammingLanguageItem(it.name, it.level, it.logoUrl)
    }

    private fun Skills.toListElement(): CvBlockListElement.Skills {
        val skills = this.skills.toSkillsListElement()

        return CvBlockListElement.Skills(skills)
    }

    private fun List<Skill>.toSkillsListElement() = map {
        CvBlockListElement.SkillItem(it.name, it.level)
    }
}