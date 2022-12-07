import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Recuperation des configs
        ConfigurationManager configurationManager = new ConfigurationManager();
        File propreties = new File("./config/config.propreties");
        // a) les les infos sur la config
        String smtpServerAdress = configurationManager.getConfigAdress(propreties);
        System.out.println(smtpServerAdress);
        int smtpServerPort;
        int numberOfGroups = 0;
        // b) les messages (les "body")
        String[] messages;
        // c) les victimes
        Person[] victimes;

        /*// 2. Genere le nombre de groupes voulus
        Group[] groups = new Group[0];
        for(int i = 0; i < numberOfGroups; ++i) {

        }

        // 3. Assigner les groupes à une Prank
        for (Group group :
                groups) {
        }*/

    }
}
