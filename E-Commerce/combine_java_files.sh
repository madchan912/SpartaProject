#!/bin/bash

# 프로젝트 및 데이터베이스 설정
PROJECT_DIR="$(pwd)"
OUTPUT_FILE="combined_java_project.txt"
DB_DUMP_FILE="project_database_dump.sql"
MYSQL_USER="root"
MYSQL_PASSWORD="1234"
DATABASE_NAME="project"

# Java 프로젝트 파일 결합
echo "Java 프로젝트 파일을 결합합니다..."
rm -f "$OUTPUT_FILE"
find "$PROJECT_DIR" \( -name "*.java" -o -name "build.gradle" -o -name "application" -o -name "application.properties" -o -name "application.yml" \) | while read -r file; do
    echo "File: $file" >> "$OUTPUT_FILE"
    echo "--------" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo -e "\n\n--------\n\n" >> "$OUTPUT_FILE"
done
echo "Java 프로젝트 파일이 $OUTPUT_FILE 에 결합되었습니다."

# 데이터베이스 덤프 생성
echo "데이터베이스 덤프를 생성합니다..."
mysqldump -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" "$DATABASE_NAME" > "$DB_DUMP_FILE"
echo "데이터베이스 덤프가 $DB_DUMP_FILE 파일로 저장되었습니다."

echo "모든 작업이 완료되었습니다."