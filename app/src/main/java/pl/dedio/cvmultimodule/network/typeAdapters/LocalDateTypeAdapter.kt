package pl.dedio.cvmultimodule.network.typeAdapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDate
import pl.dedio.cvmultimodule.util.DateFormatter
import javax.inject.Inject

class LocalDateTypeAdapter @Inject constructor(private val dateFormatter: DateFormatter) :
    TypeAdapter<LocalDate>() {

    override fun write(output: JsonWriter, value: LocalDate?) {
        if(value == null) {
            output.nullValue()
        } else {
            val formattedDate = dateFormatter.formatDateToApiFormat(value)
            output.value(formattedDate)
        }
    }

    override fun read(input: JsonReader): LocalDate? {
        if(input.peek() == JsonToken.NULL) {
            input.nextNull()
            return null
        }

        return dateFormatter.parseDateFromApi(input.nextString())
    }
}