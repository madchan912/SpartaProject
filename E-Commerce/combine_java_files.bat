@echo off
setlocal enabledelayedexpansion

REM 프로젝트 및 데이터베이스 설정
set PROJECT_DIR=%cd%
set OUTPUT_FILE=combined_java_project.txt
set DB_DUMP_FILE=project_database_dump.sql
set MYSQL_USER=root
set MYSQL_PASSWORD=1234
set DATABASE_NAME=project

REM Java 프로젝트 파일 결합
echo Java 프로젝트 파일을 결합합니다...
if exist %OUTPUT_FILE% del %OUTPUT_FILE%
for /r "%PROJECT_DIR%" %%F in (*.java build.gradle application application.properties application.yml) do (
    echo File: %%F >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    type "%%F" >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
    echo -------- >> %OUTPUT_FILE%
    echo. >> %OUTPUT_FILE%
)
echo Java 프로젝트 파일이 %OUTPUT_FILE% 에 결합되었습니다.

REM 데이터베이스 덤프 생성
echo 데이터베이스 덤프를 생성합니다...
mysqldump -u %MYSQL_USER% -p%MYSQL_PASSWORD% %DATABASE_NAME% > %DB_DUMP_FILE%
echo 데이터베이스 덤프가 %DB_DUMP_FILE% 파일로 저장되었습니다.

echo 모든 작업이 완료되었습니다.
pause