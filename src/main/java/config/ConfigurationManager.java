package config;

import model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationManager {

    private List<String> getLine(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        char c;
        List<String> line = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        while (isr.ready()) {

            while ((c = (char) isr.read()) != '\n' && isr.ready())
                s.append(c);
            line.add(s.toString());
            s = new StringBuilder();
        }

        return line;
    }

    public String getConfigAdress(File file) throws IOException {

        String[] split;
        for (String a : getLine(file)) {
            split = a.split("=");
            if (split[0].equals("smtpServerAdress"))
                return split[1];
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return "";
    }

    private int getIntConfig(File file, String s) throws IOException {
        String[] split;
        for (String a : getLine(file)) {
            split = a.split("=");
            if (split[0].equals(s))
                return Integer.parseInt(split[1]);
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return -1;
    }

    public int getConfigPort(File file) throws IOException {
        int port = getIntConfig(file, "smtpServerPort");
        if(port < 1) {
            throw new RuntimeException("La valeur du port doit être supérieure à 1 !\n");
        }
        return getIntConfig(file, "smtpServerPort");
    }

    public int getConfigNbGroup(File file) throws IOException {

        return getIntConfig(file, "numberOfGroups");
    }

    private List<String> getAllText(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        char c;
        StringBuilder s = new StringBuilder();

        while (isr.ready()) {
            c = (char) isr.read();
            s.append(c);
        }

        return List.of(s.toString().split("/r/n/r/n=="));


    }

    public String[] getAllMessages(File file) throws IOException {
        List<String> mail = getAllText(file);
        Random rand = new Random();
        String msg = mail.get(rand.nextInt(mail.size()));
        String[] split = new String[2];
        System.arraycopy(msg.split("\n\n==\n"), 0, split, 0, 2);
        return split;
    }

    public List<Person> getAllVictims(File file) throws IOException {
        List<Person> victims = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        for (String line; (line = reader.readLine()) != null; ) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                throw new RuntimeException("Une victime ne possède pas une adresse mail valide !\n");
            }
            victims.add(new Person(line));
        }
        return victims;
    }
}
