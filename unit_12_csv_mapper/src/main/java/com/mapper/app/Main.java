package com.mapper.app;

import com.mapper.lib.CSVMapper;
import com.mapper.lib.CSVParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Human> props;
        CSVParser csv = new CSVParser();
        try(FileReader fl = new FileReader(args[0])) {
            csv.load(fl);
            props = CSVMapper.initClass(Human.class, csv);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(props);
    }
}
