@echo off
rem ****************** CONFIGURACOES ******************
set "JAVA_PATH=D:\CONTTROL\java\bin\java.exe"
set "JAR_PATH=D:\CONTTROL\securitycontabil.jar"
set "CNPJ=04585532000199"
set "CAMINHO=D:\conttrol"

rem <-- senha do Gmail para envio de token
set GMAIL_APP_PASSWORD=cofyuorxbeuxabbf

rem **************************************
rem PRIMEIRA EXECUCAO: GERA E ENVIA TOKEN
rem **************************************
echo =========================================
echo Gerando token e enviando por e-mail.

"%JAVA_PATH%" -Dspring.profiles.active=first ^
-DGMAIL_APP_PASSWORD=%GMAIL_APP_PASSWORD% ^
-Dspring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration ^
-jar "%JAR_PATH%" "%CNPJ%" "%CAMINHO%"

echo =========================================
echo Token enviado. Verifique seu e-mail.
echo.