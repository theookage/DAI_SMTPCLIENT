package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReadAllTypeOfFile {
    List<String> readAndList(List<String> list, File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        while (br.ready()) {
            list.add(br.readLine());
        }
        return list;
    }
}
