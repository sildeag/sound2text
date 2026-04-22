bash
#!/usr/bin/env bash
echo "Checking for invalid imports..."
# commonMain must stay pure
grep -R --line-number --include="*.kt" \
 -e "import android\." \
 -e "import androidx\.\(?!compose\)" \
 -e "import java\.awt\." \
 -e "import javax\." \
 -e "import org\.koin\.android" \
 -e "import org\.koin\.androidx" \
 core/src/commonMain \
 di/src/commonMain \
 ui-common/src/commonMain
# androidMain must not contain desktop imports
grep -R --line-number --include="*.kt" \
 -e "import java\.awt\." \
 -e "import org\.jetbrains\.skiko" \
 -e "import compose\.desktop" \
 */src/androidMain
# jvmMain must not contain Android imports
grep -R --line-number --include="*.kt" \
 -e "import android\." \
 -e "import androidx\.lifecycle" \
 -e "import androidx\.activity" \
 -e "import androidx\.navigation" \
 */src/jvmMain
echo "Import check complete."