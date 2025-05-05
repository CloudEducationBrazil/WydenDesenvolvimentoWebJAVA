@echo off
rem set caminhoArquivoJSON=D://testejava/shiftFF.json

start "" /B java -jar .\tokencontabilidade-0.0.1-SNAPSHOT.jar --token.json.path=D://testejava/shiftFF.json

rem Aguarda 20 segundos (por exemplo)
rem timeout /t 20

rem Finaliza o processo Java
rem taskkill /IM java.exe /F
@echo on