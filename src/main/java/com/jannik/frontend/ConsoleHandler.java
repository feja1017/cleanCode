package com.jannik.frontend;

import com.jannik.csvReader.CsvHelper;
import com.jannik.tablulate.PaginationHelper;
import com.jannik.tablulate.TabulateService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {

    TabulateService tabulateService;
    PaginationHelper paginationHelper;
    Scanner sc = new Scanner(System.in);

    public ConsoleHandler(Integer size, String fileName) throws IOException {
        tabulateService = new TabulateService();
        var csvHelper = new CsvHelper(fileName);
        tabulateService.initializeService(csvHelper.readCsv());
        this.paginationHelper = new PaginationHelper(tabulateService, size);
    }

    private void displayData(List<String> data) {
        data.forEach(System.out::println);
    }

    public void interact() {
        char c = 'F';
        do {
                switch (c) {
                    case 'F':
                        displayData(paginationHelper.getFirstPage());
                        break;
                    case 'P':
                        displayData(paginationHelper.getPreviousPage());
                        break;
                    case 'N':
                        displayData(paginationHelper.getNextPage());
                        break;
                    case 'L':
                        displayData(paginationHelper.getLastPage());
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
                System.out.println("Page " + paginationHelper.getCurrentPage() + " of " + paginationHelper.getMaxPage());
                System.out.println("F)irst page, P)revious page, N)ext page, L)ast page, E)xit");
                c = sc.next().charAt(0);
        } while (c != 'E');
    }
}
