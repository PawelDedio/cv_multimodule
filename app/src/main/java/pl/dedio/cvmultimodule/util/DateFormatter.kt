package pl.dedio.cvmultimodule.util

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    fun parseDateFromApi(date: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(API_DATE_FORMAT)
        return LocalDate.parse(date, formatter)
    }

    fun formatDateToApiFormat(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(API_DATE_FORMAT)
        return date.format(formatter)
    }

    fun toReadableForm(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(READABLE_DATE_FORMAT)
        return date.format(formatter)
    }

    companion object {
        private const val API_DATE_FORMAT = "dd.MM.yyyy"
        private const val READABLE_DATE_FORMAT = "dd MMMM yyyy"
    }
}