@echo off
set output_file=combined_java_files.txt
if exist %output_file% del %output_file%
for /r %%f in (*.java) do (
  echo File: %%f >> %output_file%
  echo -------- >> %output_file%
  type "%%f" >> %output_file%
  echo. >> %output_file%
  echo. >> %output_file%
  echo -------- >> %output_file%
  echo. >> %output_file%
)
echo 모든 Java 파일이 %output_file% 에 결합되었습니다.