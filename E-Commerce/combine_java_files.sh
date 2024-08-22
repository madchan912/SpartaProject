#!/bin/bash
output_file="combined_java_files.txt"
rm -f "$output_file"
find . -name "*.java" | while read -r file; do
  echo "File: $file" >> "$output_file"
  echo "--------" >> "$output_file"
  cat "$file" >> "$output_file"
  echo -e "\n\n--------\n\n" >> "$output_file"
done
echo "모든 Java 파일이 $output_file 에 결합되었습니다."