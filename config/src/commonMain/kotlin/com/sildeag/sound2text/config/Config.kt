import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.Environment
import com.sildeag.sound2text.config.FeatureFlags

interface Config {
    val environment: Environment
    val settings: AppSettings
    val flags: FeatureFlags
}