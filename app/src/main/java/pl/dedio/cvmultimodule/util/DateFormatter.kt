package pl.dedio.cvmultimodule.util

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    fun parseDateFromApi(date: String): LocalDate {
        val formatter = getFormatter(API_DATE_FORMAT)
        return LocalDate.parse(date, formatter)
    }

    fun formatDateToApiFormat(date: LocalDate): String {
        val formatter = getFormatter(API_DATE_FORMAT)
        return date.format(formatter)
    }

    fun toReadableForm(date: LocalDate): String {
        val formatter = getFormatter(READABLE_DATE_FORMAT)
        return date.format(formatter)
    }

    private fun getFormatter(pattern: String) = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)

    companion object {
        private const val API_DATE_FORMAT = "dd.MM.yyyy"
        private const val READABLE_DATE_FORMAT = "dd MMMM yyyy"
    }
}