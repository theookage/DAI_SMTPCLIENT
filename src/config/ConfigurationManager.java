package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {
    private List<String> getLine(File file) throws IOException {

        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        char c = 0;
        List<String> line = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        while(isr.ready()){

            while((c = (char)isr.read()) != '\n')
                s.append(c);
            line.add(s.toString());
        }

        return line;
    }
    public String readConfigAdress(File file) throws IOException {

        List<String> Line = getLine(file);
        return "";
    }
}
