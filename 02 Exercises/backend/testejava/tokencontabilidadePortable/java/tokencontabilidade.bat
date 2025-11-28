rem @echo off
rem set caminhoArquivoJSON=D://testejava/shiftFF.json
rem set JAVA_HOME=%~dp0java
set JAVA_HOME=%~dp0
echo %JAVA_HOME%
"%JAVA_HOME%\bin\java.exe" -jar D:\testejava\tokencontabilidadePortable\java\app.jar --token.json.path=D://testejava/shiftFH.json
pause
rem @echo on