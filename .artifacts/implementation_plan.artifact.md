# Optimization Plan for AGP 9.5.0-alpha01 (Rabbit 1) on Limited RAM

This plan optimizes the project configuration to support the latest experimental versions (AGP 9.5.0-alpha01 and Gradle 9.6.1) while significantly reducing memory overhead to prevent the 3+ hour sync issue.

## Proposed Changes

### [Component Name] Gradle Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/Android/Projects/sound2text/gradle/libs.versions.toml)
- Lower `jvm` version from `24` to `21` to match the local environment and prevent background toolchain provisioning overhead.

#### [MODIFY] [gradle.properties](file:///D:/Android/Projects/sound2text/gradle.properties)
- Disable experimental and high-memory parallel sync flags (`org.gradle.parallel`, `org.gradle.tooling.parallel`).
- Comment out hardcoded `org.gradle.java.home` to allow the IDE and Gradle to share the same JVM instance, saving several hundred MBs of RAM.
- (Optional but recommended) Maintain `-Xmx2048m` but add `-XX:MaxMetaspaceSize=512m` to prevent meta-data overflow without increasing the main heap too much.

## Verification Plan

### Automated Tests
- Run `./gradlew help` to verify basic project configuration.
- Trigger a standard "Sync Project with Gradle Files" in Android Studio.

### Manual Verification
- Monitor the "Build" output window to ensure the sync moves past the "Configuring" stage within a few minutes.
- Check the "Gradle" console for any remaining memory warnings.
