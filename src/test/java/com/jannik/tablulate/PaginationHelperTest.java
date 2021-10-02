package com.jannik.tablulate;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PaginationHelperTest {

    private final TabulateService tabulateService = new TabulateService();

    @Before
    public void prepareTests() {
        final var exampleCsvList = List.of(
            "Name;Vorname;Alter",
            "Yang;Paloma;88",
            "Diaz;Jayme;45",
            "Valencia;Bauer;60",
            "X Æ A-12;Musk;1"
        );
        tabulateService.initializeService(exampleCsvList);
    }

    @Test
    public void getFirstPage_valid() {
        final var expectedResult = List.of(
            "No.|Name    |Vorname|Alter|",
            "---+--------+-------+-----+",
            "1  |Yang    |Paloma |88   |",
            "2  |Diaz    |Jayme  |45   |"
        );
        final var paginationHelper = new PaginationHelper(tabulateService, 2);
        final var result = paginationHelper.getFirstPage();

        assert result.containsAll(expectedResult);
    }

    @Test
    public void getNextPage_valid() {
        final var expectedResult = List.of(
            "No.|Name    |Vorname|Alter|",
            "---+--------+-------+-----+",
            "3  |Valencia|Bauer  |60   |",
            "4  |X Æ A-12|Musk   |1    |"
        );
        final var paginationHelper = new PaginationHelper(tabulateService, 2);
        paginationHelper.getFirstPage();
        final var result = paginationHelper.getNextPage();

        assert result.containsAll(expectedResult);
    }

    @Test
    public void getPreviousPage_valid() {
        final var expectedResult = List.of(
            "No.|Name    |Vorname|Alter|",
            "---+--------+-------+-----+",
            "1  |Yang    |Paloma |88   |",
            "2  |Diaz    |Jayme  |45   |"
        );
        final var paginationHelper = new PaginationHelper(tabulateService, 2);
        paginationHelper.getFirstPage();
        paginationHelper.getNextPage();
        final var result = paginationHelper.getPreviousPage();

        assert result.containsAll(expectedResult);
    }

    @Test
    public void getLastPage_valid() {
        final var expectedResult = List.of(
            "No.|Name    |Vorname|Alter|",
            "---+--------+-------+-----+",
            "3  |Valencia|Bauer  |60   |",
            "4  |X Æ A-12|Musk   |1    |"
        );
        final var paginationHelper = new PaginationHelper(tabulateService, 2);
        paginationHelper.getFirstPage();
        final var result = paginationHelper.getLastPage();

        assert result.containsAll(expectedResult);
    }
}
