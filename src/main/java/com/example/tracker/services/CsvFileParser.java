package com.example.tracker.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvFileParser implements IFileParser<List<List<String>>> {
    private final int DEFAULT_ROW_START = 2;

    public List<List<String>> parse(MultipartFile file) throws Exception {
        return this.parse(file, DEFAULT_ROW_START);
    }

    public List<List<String>> parse(MultipartFile file, int start) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File should not be empty");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("Invalid file type. Only CSV files are allowed.");
        }

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            int rowNumber = 0;
            var records = new ArrayList<List<String>>();

            String line;
            while((line = fileReader.readLine()) != null) {
                ++rowNumber;
                if (!line.trim().isEmpty()) {
                    if (!line.contains(",")) {
                        throw new IllegalStateException("Invalid CSV structure detected on line " + rowNumber);
                    }

                    String[] values = line.split(",");
                    List<String> rowData = Arrays.asList(values);
                    if (rowNumber >= start) {
                        records.add(rowData);
                    }
                }
            }

            if (rowNumber == 0) {
                throw new IllegalStateException("CSV File: " + file.getOriginalFilename() + " cannot be empty");
            }
            return records;
        }catch (Exception e) {
            throw new Exception("Failed to process CSV file without library: " + e.getMessage(), e);
        }

    }
}
