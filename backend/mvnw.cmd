@echo off
setlocal
set "MAVEN_VERSION=3.9.15"
set "MAVEN_HOME=%~dp0.mvn\wrapper\apache-maven-%MAVEN_VERSION%"
set "MAVEN_BIN=%MAVEN_HOME%\bin\mvn.cmd"

if exist "%MAVEN_BIN%" goto RUN_MAVEN

echo.
echo Maven %MAVEN_VERSION% was not found. Downloading it automatically...
echo This is a one-time download and requires internet access.
echo.

if not exist "%~dp0.mvn\wrapper" mkdir "%~dp0.mvn\wrapper"
set "ZIP=%~dp0.mvn\wrapper\apache-maven-%MAVEN_VERSION%-bin.zip"
set "URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip"

powershell -NoProfile -ExecutionPolicy Bypass -Command "try { Invoke-WebRequest -UseBasicParsing -Uri '%URL%' -OutFile '%ZIP%' } catch { Write-Error $_; exit 1 }"
if errorlevel 1 (
  echo.
  echo Maven download failed. Check your internet connection and try again.
  exit /b 1
)

powershell -NoProfile -ExecutionPolicy Bypass -Command "Expand-Archive -LiteralPath '%ZIP%' -DestinationPath '%~dp0.mvn\wrapper' -Force"
if errorlevel 1 (
  echo Maven extraction failed.
  exit /b 1
)

del /q "%ZIP%" >nul 2>&1

if not exist "%MAVEN_BIN%" (
  echo Maven installation was not found after extraction.
  exit /b 1
)

:RUN_MAVEN
call "%MAVEN_BIN%" %*
exit /b %ERRORLEVEL%
