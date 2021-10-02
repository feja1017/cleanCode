package com.jannik.csv_reader;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CsvHelperTest {

    @Test(expected = IOException.class)
    public void readCsv_invalidFileName_exception() throws IOException {
        var csvHelper = new CsvHelper("invalid");
        csvHelper.readCsv();
    }

    @Test
    public void readCsv_validFile_validList() throws IOException {
        final var expectedList = List.of(
            "Name;Vorname;Alter",
            "Yang;Paloma;88",
            "Diaz;Jayme;45",
            "Valencia;Bauer;60"
        );

        final var csvHelper = new CsvHelper("src/main/resources/test.csv");
        final var result = csvHelper.readCsv();

        Assert.assertTrue(result.containsAll(expectedList));
    }
}
