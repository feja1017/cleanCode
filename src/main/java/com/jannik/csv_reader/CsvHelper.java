package com.jannik.csv_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvHelper {

    private String fileName;

    public CsvHelper(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readCsv() throws IOException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
