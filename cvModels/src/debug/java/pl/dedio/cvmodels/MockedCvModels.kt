package pl.dedio.cvmodels

import org.threeten.bp.LocalDate
import pl.dedio.cvmodels.blocks.BaseBlock
import pl.dedio.cvmodels.blocks.BasicInformation

object MockedCvModels {

    val cvBlocks = listOf<BaseBlock>(BasicInformation(LocalDate.now(), "555666777", "Test city"))

    val cvUiModel = CvUiModel("Test user", "http://google.pl/", cvBlocks, "Tester at Test company")
}