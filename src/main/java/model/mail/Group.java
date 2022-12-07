package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {
    Person sender;
    List<Person> recipients = new ArrayList<>();

    public Group(Person sender, List<Person> recipients) {
        if (recipients.size() < 2) {
            throw new RuntimeException("Il faut au moins 2 recipients !\n");
        }
        this.sender = sender;
        this.recipients = recipients;
    }

    public Group() {

    }


    public Person getSender() {
        return sender;
    }

    public List<Person> getRecipients() {
        return recipients;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public void addRecipient(Person recipient) {
        this.recipients.add(recipient);
    }
}
