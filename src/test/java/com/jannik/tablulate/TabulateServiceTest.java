package com.jannik.tablulate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TabulateServiceTest {

    private List<String> exampleCsvList;

    @Before
    public void prepareTests() {
        exampleCsvList = List.of(
            "Name;Vorname;Alter",
            "Yang;Paloma;88",
            "Diaz;Jayme;45",
            "Valencia;Bauer;60",
            "X Æ A-12;Musk;1"
        );
    }

    @Test
    public void getDividerRow_valid() {
        final var expectedResult = "---+--------+-------+-----+";
        final var tabulateService = new TabulateService();
        tabulateService.initializeService(exampleCsvList);
        final var result = tabulateService.getDividerRow();

        Assert.assertTrue(expectedResult.equals(result));
    }

    @Test
    public void getDataSet_valid() {
        final var expectedResult = List.of(
            List.of("No.", "Name", "Vorname", "Alter"),
            List.of("1", "Yang", "Paloma", "88"),
            List.of("2", "Diaz", "Jayme", "45"),
            List.of("3", "Valencia", "Bauer", "60"),
            List.of("4", "X Æ A-12", "Musk", "1")
        );

        final var tabulateService = new TabulateService();
        tabulateService.initializeService(exampleCsvList);
        final var dataSet = tabulateService.getDataSet();

        Assert.assertTrue(dataSet.containsAll(expectedResult));
    }

    @Test
    public void getTabulatedHeader_valid() {
        final var expectedResult = List.of(
            "No.|Name    |Vorname|Alter|",
            "---+--------+-------+-----+"
        );

        final var tabulateService = new TabulateService();
        tabulateService.initializeService(exampleCsvList);
        var result = tabulateService.getTabulatedHeader();

        Assert.assertTrue(result.containsAll(expectedResult));
    }

    @Test
    public void getTabulatedPage_valid() {
        final var expectedResult = List.of(
            "1  |Yang    |Paloma |88   |",
            "2  |Diaz    |Jayme  |45   |",
            "3  |Valencia|Bauer  |60   |",
            "4  |X Æ A-12|Musk   |1    |"
        );

        final var tabulateService = new TabulateService();
        tabulateService.initializeService(exampleCsvList);

        final var dataSetSize = tabulateService.getDataSet().size();
        final var dataSet = tabulateService.getDataSet().subList(1, dataSetSize);
        final var result = tabulateService.getTabulatedPage(dataSet);

        Assert.assertTrue(result.containsAll(expectedResult));
    }
}
