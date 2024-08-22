@echo off
set MYSQL_USER=root
set MYSQL_PASSWORD=1234
set DATABASE_NAME=project
set DUMP_FILE=project_database_dump.sql
echo 데이터베이스를 복원합니다...
mysql -u %MYSQL_USER% -p%MYSQL_PASSWORD% %DATABASE_NAME% < %DUMP_FILE%
echo 데이터베이스가 %DUMP_FILE% 파일로부터 복원되었습니다.
pause