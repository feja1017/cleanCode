package com.jannik.tablulate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabulateService {

    private final List<List<String>> dataSet = new ArrayList<List<String>>();
    private final List<Integer> columns = new ArrayList<>();

    public TabulateService() { }

    public void initializeService(List<String> csvData) {
        int counter = 0;
        csvData.forEach(entry -> {
            List<String> arrayListRow = Arrays.asList(entry.split(";"));
            List<String> row = new ArrayList<>();
            row.addAll(arrayListRow);
            if(csvData.indexOf(entry) != 0) {
                row.add(0, String.valueOf(csvData.indexOf(entry)));
            } else {
                row.add(0, "No.");
            }
            calculateColumnLength(row);
            dataSet.add(row);
        });
    }

    protected void calculateColumnLength(List<String> newColumn) {
        newColumn.forEach(entry -> {
            int index = newColumn.indexOf(entry);
            if (columns.size() != newColumn.size()) {
                columns.add(entry.length());
            } else {
                if (entry.length() > columns.get(index)) {
                    columns.set(index, entry.length());
                }
            }
        });
    }

    protected String getDividerRow() {
        String divider = "";
        for (int i = 0; i < columns.size(); i++) {
            divider += "-".repeat(columns.get(i)) + "+";
        }
        return divider;
    }

    protected String getDataRow(List<String> data) {
        String dataRow = "";
        for (int i = 0; i < data.size(); i++) {
            dataRow += data.get(i) + " ".repeat(columns.get(i) - data.get(i).length()) + "|";
        }
        return dataRow;
    }

    public List<String> getTabulatedPage(List<List<String>> data) {
        List<String> tabulated = new ArrayList<>();
        data.forEach(entry -> {
            tabulated.add(getDataRow(entry));
        });
        return tabulated;
    }

    public List<String> getTabulatedHeader(){
        List<String> header = new ArrayList<>();
        header.add(getDataRow(dataSet.get(0)));
        header.add(getDividerRow());
        return header;
    }

    public List<List<String>> getDataSet() {
        return dataSet;
    }
}
