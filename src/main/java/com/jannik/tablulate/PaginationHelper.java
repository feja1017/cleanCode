package com.jannik.tablulate;

import java.util.List;

public class PaginationHelper {

    TabulateService tabulateService;
    Integer size;
    Integer currentPage = -1;
    Integer maxPage;

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
        if (this.currentPage - 1 >= 1) {
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
        List<List<String>> sublist = dataset.subList(size * (currentPage - 1), size * currentPage);
        result.addAll(tabulateService.getTabulatedPage(sublist));
        return result;
    }

    public List<String> getLastPage() {
        List<String> result = this.tabulateService.getTabulatedHeader();
        List<List<String>> dataset = tabulateService.getDataSet();
        List<List<String>> sublist = dataset.subList(dataset.size() - (dataset.size() % size), dataset.size());
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
