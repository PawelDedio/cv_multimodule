package pl.dedio.home.extension

import android.annotation.SuppressLint
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.threeten.bp.zone.TzdbZoneRulesProvider
import org.threeten.bp.zone.ZoneRulesProvider
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class ThreeTenTestExtension : BeforeAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        if (ZoneRulesProvider.getAvailableZoneIds().isEmpty()) {
            val stream = loadZoneRules()
            stream.use(::TzdbZoneRulesProvider).apply {
                ZoneRulesProvider.registerProvider(this)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun loadZoneRules(): InputStream {
        val classLoader = javaClass.classLoader
        val file = File(classLoader!!.getResource("TZDB.dat").file)

        return FileInputStream(file)
    }

    companion object {
        private const val MODULE_NAME = "testExtensions"
    }
}