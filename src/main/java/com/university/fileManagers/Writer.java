package com.university.fileManagers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public abstract class Writer<T> {

    protected abstract String getHeader();

    protected abstract String formatData(T data);

    public void writeCSV(String filePath, Map<String, T> dataMap) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(getHeader());
            bw.newLine();
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                bw.write(formatData(entry.getValue()));
                bw.newLine();
            }
        }
    }
}
