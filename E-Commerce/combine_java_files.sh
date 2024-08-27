#!/bin/bash

# 프로젝트 및 파일 설정
PROJECT_DIR="/Users/madchan/Sparta/project/E-Commerce"
OUTPUT_FILE="combined_java_project.txt"

# 기존의 파일이 있으면 삭제
if [ -f "$OUTPUT_FILE" ]; then
    rm "$OUTPUT_FILE"
fi

# Java 및 Docker 관련 파일 결합
echo "Java 및 Docker 관련 파일을 결합합니다..."

find "$PROJECT_DIR/src" -name "*.java" | while read -r file; do
    echo "File: $file" >> "$OUTPUT_FILE"
    echo "--------" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo -e "\n\n--------\n\n" >> "$OUTPUT_FILE"
done

for file in "$PROJECT_DIR/build.gradle" "$PROJECT_DIR/settings.gradle" \
            "$PROJECT_DIR/application.properties" "$PROJECT_DIR/application.yml" \
            "$PROJECT_DIR/Dockerfile" "$PROJECT_DIR/docker-compose.yml"; do
    if [ -f "$file" ]; then
        echo "File: $file" >> "$OUTPUT_FILE"
        echo "--------" >> "$OUTPUT_FILE"
        cat "$file" >> "$OUTPUT_FILE"
        echo -e "\n\n--------\n\n" >> "$OUTPUT_FILE"
    fi
done

echo "Java 및 설정 파일이 $OUTPUT_FILE 에 결합되었습니다."
