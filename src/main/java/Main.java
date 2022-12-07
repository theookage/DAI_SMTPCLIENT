import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import smtp.SmtpClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        final int nbReceiver = 2;
        // 1. Recuperation des configs
        ConfigurationManager configurationManager = new ConfigurationManager();
        File fPropreties = new File("config/config.propreties");
        // a) les les infos sur la config
        String smtpServerAdress = configurationManager.getConfigAdress(fPropreties);
        int smtpServerPort = configurationManager.getConfigPort(fPropreties);
        int numberOfGroups = configurationManager.getConfigNbGroup(fPropreties);
        // b) les messages (les "body")
        File fMessages = new File("config/messages.utf8");
        List<String> bodies = configurationManager.getAllBodies(fMessages);
        // c) les victimes
        File fVictimes = new File("config/victims.utf8");
        List<Person> victims = configurationManager.getAllVictims(fVictimes);
        if (victims.size() < 1 + nbReceiver) {
            throw new RuntimeException("Le fichier de config ne contient pas assez de victimes !\n");
        }

        // 2. Genere le nombre de groupes voulus
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; ++i) {
            List<Person> gVictims = new ArrayList<>(victims);
            Group group = new Group();
            // Pour chaque groupe ajouter 1 sender et nbReceiver receiver
            Random ran = new Random();
            Person randomSender = gVictims.get(ran.nextInt(gVictims.size()));
            group.setSender(randomSender);
            gVictims.remove(randomSender);
            for (int j = 0; j < nbReceiver; ++j) {
                Person randomReceiver = gVictims.get(ran.nextInt(gVictims.size()));
                group.addRecipient(randomReceiver);
                gVictims.remove(randomReceiver);
            }
            groups.add(group);
        }

        // 3. Créer les mails
        List<Mail> mails = new ArrayList<>();
        Random ran = new Random();
        for (Group group :
                groups) {
            // Prend un random "body"
            String randomBody = bodies.get(ran.nextInt(bodies.size()));
            Mail mail = new Mail(group.getSender(), group.getRecipients(), randomBody);
            mails.add(mail);
        }

        // 4. Envoyer les mails
        SmtpClient smtpClient = new SmtpClient(smtpServerAdress, smtpServerPort);
        for (Mail mail :
                mails) {
            smtpClient.sendMail(mail);
        }
    }
}
