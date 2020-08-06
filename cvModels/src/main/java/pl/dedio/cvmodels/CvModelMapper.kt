package pl.dedio.cvmodels

import pl.dedio.cvmodels.blocks.BlockType
import pl.dedio.cvmodels.blocks.Experience
import pl.dedio.cvmultimodule.util.ResourceRepository
import javax.inject.Inject

class CvModelMapper @Inject constructor(private val resourceRepository: ResourceRepository) {

    fun mapToUiModel(apiModel: CvApiModel): CvUiModel = with(apiModel) {
        val currentPosition = extractCurrentPosition()

        CvUiModel(name, photoUrl, blocks, currentPosition)
    }

    private fun CvApiModel.extractCurrentPosition(): String? {
        val experienceBlock = blocks.find { it.blockType == BlockType.EXPERIENCE } as? Experience
        val currentJob = experienceBlock?.companies?.find { it.finishedAt == null }

        return if (currentJob == null) {
            null
        } else {
            resourceRepository.getString(
                R.string.current_position_pattern,
                currentJob.positionName,
                currentJob.name
            )
        }
    }
}