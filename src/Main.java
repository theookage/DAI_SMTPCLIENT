import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Recuperation des configs
        ConfigurationManager configurationManager = new ConfigurationManager();
        File fPropreties = new File("./config/config.propreties");
        // a) les les infos sur la config
        String smtpServerAdress = configurationManager.getConfigAdress(fPropreties);
        int smtpServerPort = configurationManager.getConfigPort(fPropreties);
        int numberOfGroups = configurationManager.getConfigNbGroup(fPropreties);
        // b) les messages (les "body")
        File fMessages = new File("./config/messages.utf8");
        String[] messages = configurationManager.getAllMessages(fMessages);
        // c) les victimes
        File fVictimes = new File("./config/victims.utf8");
        Person[] victimes = configurationManager.getAllVictims(fVictimes);

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
