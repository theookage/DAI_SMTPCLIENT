package config;

import model.mail.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            if (split[0].equals("smtpServerAdress"))
                return split[1];
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return "";
    }
    private int getIntConfig(File file, String s) throws IOException {
        String[] split;
        for (String a : getLine(file)){
            split = a.split("=");
            if (split[0].equals(s))
                return Integer.parseInt(split[1]);
        }
        System.out.println("Erreur, pas trouvé d'adresse");
        return -1;
    }
    public int getConfigPort(File file) throws IOException {

        return getIntConfig(file, "smtpServerPort");
    }
    public int getConfigNbGroup(File file) throws IOException {

        return getIntConfig(file, "numberOfGroups");
    }
    private List<String> getAllText(File file) throws IOException{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        char c = 0;
        StringBuilder s = new StringBuilder();

        while(isr.ready()){
            c = (char)isr.read();
            s.append(c);
        }

        return List.of(s.toString().split("/r/n/r/n=="));


    }

    public String[] getAllMessages(File file) throws IOException{
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
        for(String line; (line = reader.readLine()) != null;) {
            victims.add(new Person(line));
        }
        return victims;
    }

    public List<String> getAllMessage(File file, int nbVictims) throws IOException{
        List<String> victims = getLine(file);
        List<String> victimsList = new ArrayList<>();
        if(nbVictims < 3){
            System.out.println("Pas assez de victime");
        }
        if(victims.size() < nbVictims){
            System.out.println("Trop de victime");
        }
        Random rand = new Random();
        for (int i = 0; i < nbVictims; i++)
            victimsList.add(victims.get(rand.nextInt(victims.size())));
        return  victimsList;

    }




}
