@echo off
rem ****************** CONFIGURACOES ******************
set "JAVA_PATH=D:\CONTTROL\java\bin\java.exe"
set "JAR_PATH=D:\CONTTROL\securitycontabil.jar"
set "CNPJ=04585532000199"
set "CAMINHO=D:\CONTTROL"
set "TOKEN=62984c85e80941ca82862593c4d3cecb"
set "SERVICO_NOME=TokenService"
set "NSSM_PATH=D:\CONTTROL\nssm\win64\nssm.exe"

rem ****************** CRIA PASTA DE LOGS ******************
if not exist "%CAMINHO%\logs" mkdir "%CAMINHO%\logs"

rem ****************** INSTALAÇÃO DO SERVIÇO ******************
echo =========================================
echo Instalando o serviço %SERVICO_NOME%
"%NSSM_PATH%" install %SERVICO_NOME% "%JAVA_PATH%"

rem ****************** CONFIGURAÇÕES DO SERVIÇO ******************
"%NSSM_PATH%" set %SERVICO_NOME% AppParameters "-jar \"%JAR_PATH%\" %CNPJ% \"%CAMINHO%\" \"%TOKEN%\""
"%NSSM_PATH%" set %SERVICO_NOME% AppDirectory "%CAMINHO%"
"%NSSM_PATH%" set %SERVICO_NOME% AppStdout "%CAMINHO%\logs\stdout.log"
"%NSSM_PATH%" set %SERVICO_NOME% AppStderr "%CAMINHO%\logs\stderr.log"
"%NSSM_PATH%" set %SERVICO_NOME% AppRotateFiles 1
"%NSSM_PATH%" set %SERVICO_NOME% AppEnvironmentExtra "-Xms512m -Xmx1024m"
"%NSSM_PATH%" set %SERVICO_NOME% Start SERVICE_AUTO_START

rem ****************** INICIA O SERVIÇO ******************
"%NSSM_PATH%" start %SERVICO_NOME%

rem ****************** VERIFICA ERRO ******************
if %ERRORLEVEL% NEQ 0 (
    echo =========================================
    echo Falha ao registrar ou iniciar o servico. Verifique os logs.
    pause
    exit /b %ERRORLEVEL%
) else (
    echo =========================================
    echo Servico %SERVICO_NOME% registrado e iniciado com sucesso.
    pause
)
exit