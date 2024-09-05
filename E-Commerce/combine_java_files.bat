@echo off
setlocal enabledelayedexpansion

REM 프로젝트 및 데이터베이스 설정
set PROJECT_DIR=C:\project\E-Commerce
set OUTPUT_FILE=combined_java_project.txt

REM 기존의 출력 파일이 있으면 삭제
if exist %OUTPUT_FILE% del %OUTPUT_FILE%

REM Java 및 Docker 관련 파일 결합
echo Java 및 Docker 관련 파일을 결합합니다...

REM 모든 Java 파일 찾기
for /r "%PROJECT_DIR%" %%F in (*.java) do (
    echo File: %%F >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    type "%%F" >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
)

REM 주요 설정 파일 찾기 및 결합
for %%F in (%PROJECT_DIR%\build.gradle %PROJECT_DIR%\settings.gradle %PROJECT_DIR%\application.properties %PROJECT_DIR%\application.yml) do (
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

REM 각 모듈 디렉토리 내 Dockerfile 및 docker-compose.yml 파일 포함 (파일이 존재하는 경우에만 처리)
for /r "%PROJECT_DIR%" %%F in (Dockerfile docker-compose.yml) do (
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
