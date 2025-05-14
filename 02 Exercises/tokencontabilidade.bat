@echo off
rem set caminhoArquivoJSON=D://testejava/shiftFF.json
rem set JAVA_HOME=%~dp0java
set JAVA_HOME=%~dp0
echo %JAVA_HOME%
"%JAVA_HOME%\bin\java.exe" -jar app.jar --token.json.path=D://testejava/shiftFH.json
rem pause
@echo on