package com.mapper.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVParser {

    private LinkedHashMap<String, ArrayList<String>> csvTable;
    private int size = 0;

    public CSVParser() {
        csvTable = new LinkedHashMap<>();
    }


    public void load(FileReader fileReader) {
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        try {
            line = reader.readLine();
            List<String> header = Arrays.asList(line.split(","));
            while ((line = reader.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(","));
                putValuesInTable(header, row);
                size++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String tableValueById(int column, int row) {
        List<List<String>> listOfValues = new ArrayList<>(csvTable.values());
        return listOfValues.get(column).get(row);
    }

    public String tableValueByKey(String key, int row) {
        return csvTable.get(key).get(row);
    }

    public List<String> getHeaderList() {
        List<String> listOfKeys = new ArrayList<>(csvTable.keySet());
        return listOfKeys;
    }

    public int amountOfRows() {
        return size;
    }

    private void putValuesInTable(List<String> keys, List<String> values) {
        for (int i = 0; i < keys.size(); i++) {
            if (!(csvTable.containsKey(keys.get(i)))) {
                ArrayList<String> valueList = new ArrayList<>();
                valueList.add(values.get(i));
                csvTable.put(keys.get(i), valueList);
            } else {
                csvTable.get(keys.get(i)).add(values.get(i));
            }
        }
    }

}

