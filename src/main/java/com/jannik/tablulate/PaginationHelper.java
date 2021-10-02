package com.jannik.tablulate;

import java.util.List;

public class PaginationHelper {

    private TabulateService tabulateService;
    private Integer size;
    private Integer currentPage = -1;
    private Integer maxPage;

    public PaginationHelper(TabulateService tabulateService, Integer size) {
        this.tabulateService = tabulateService;
        this.size = size;
        this.maxPage = (int) Math.ceil((double) tabulateService.getDataSet().size() / this.size);
    }

    public List<String> getFirstPage() {
        List<String> result = this.tabulateService.getTabulatedHeader();
        List<List<String>> sublist = tabulateService.getDataSet().subList(1, 1 + size);
        result.addAll(tabulateService.getTabulatedPage(sublist));
        this.currentPage = 1;
        return result;
    }

    public List<String> getPreviousPage() {
        if (this.currentPage - 1 > 1) {
            this.currentPage = this.currentPage - 1;
            return getPage();
        }
        return getFirstPage();
    }

    public List<String> getNextPage() {
        if (this.currentPage + 1 < maxPage) {
            this.currentPage = this.currentPage + 1;
            return getPage();
        }
        return getLastPage();
    }

    private List<String> getPage() {
        List<String> result = this.tabulateService.getTabulatedHeader();
        List<List<String>> dataset = tabulateService.getDataSet();

        final var subListFrom = size * (currentPage - 1) + 1;
        final var subListTo = size * currentPage + 1;
        List<List<String>> sublist = dataset.subList(subListFrom, subListTo);

        result.addAll(tabulateService.getTabulatedPage(sublist));
        return result;
    }

    public List<String> getLastPage() {
        List<String> result = this.tabulateService.getTabulatedHeader();
        List<List<String>> dataset = tabulateService.getDataSet();

        final var subListFrom = dataset.size() - (dataset.size() % size) - 1;
        final var subListTo = dataset.size();
        List<List<String>> sublist = dataset.subList(subListFrom, subListTo);

        result.addAll(tabulateService.getTabulatedPage(sublist));
        this.currentPage = maxPage;
        return result;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getMaxPage() {
        return maxPage;
    }
}
