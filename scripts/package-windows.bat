@echo off
set JAR_NAME=sound2text-jar-with-dependencies.jar
set MAIN_CLASS=com.sildeag.sound2text.MainKt
set APP_NAME=PulseApp
set ICON_PATH=src\main\resources\icon.ico
jpackage ^
 --type exe ^
 --input target ^
 --name %APP_NAME% ^
 --main-jar %JAR_NAME% ^
 --main-class %MAIN_CLASS% ^
 --icon %ICON_PATH% ^
 --win-console ^
 --win-dir-chooser ^
 --app-version 1.0.0 ^
 --vendor "Sildeag" ^
 --dest installer
