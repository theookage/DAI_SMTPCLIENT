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

            while((c = (char)isr.read()) != '\n' && isr.ready())
                s.append(c);
            line.add(s.toString());
            s = new StringBuilder();
        }

        return line;
    }
    public String getConfigAdress(File file) throws IOException {

        String[] split;
        for (String a : getLine(file)){
            split = a.split("=");
            if (split[0].equals("ip"))
                return split[1];
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return "";
    }
    public String getConfigPort(File file) throws IOException {

        String[] split;
        for (String a : getLine(file)){
            split = a.split("=");
            if (split[0].equals("port"))
                return split[1];
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return "";
    }


}
