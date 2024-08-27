@echo off
setlocal enabledelayedexpansion

REM 프로젝트 및 데이터베이스 설정
set PROJECT_DIR=C:\project\E-Commerce
set OUTPUT_FILE=combined_java_project.txt

REM 불필요한 디렉토리 제외 조건 추가
echo Java 및 Docker 관련 파일을 결합합니다...
if exist %OUTPUT_FILE% del %OUTPUT_FILE%

for /r "%PROJECT_DIR%\src" %%F in (*.java) do (
    echo File: %%F >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    type "%%F" >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
)

for %%F in (%PROJECT_DIR%\build.gradle %PROJECT_DIR%\settings.gradle %PROJECT_DIR%\application.properties %PROJECT_DIR%\application.yml %PROJECT_DIR%\Dockerfile %PROJECT_DIR%\docker-compose.yml) do (
    if exist %%F (
        echo File: %%F >> %OUTPUT_FILE%
        echo -------- >> %OUTPUT_FILE%
        type "%%F" >> %OUTPUT_FILE%
        echo. >> %OUTPUT_FILE%
        echo. >> %OUTPUT_FILE%
        echo -------- >> %OUTPUT_FILE%
        echo. >> %OUTPUT_FILE%
    )
)

echo Java 및 설정 파일이 %OUTPUT_FILE% 에 결합되었습니다.
pause
