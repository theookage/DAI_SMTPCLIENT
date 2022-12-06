package model.mail;

public class Group {
    Person sender;
    Person[] recipients;

    public Group(Person sender, Person[] recipients) {
        if(recipients.length < 2) {
            throw new RuntimeException("Il faut au moins 2 recipients !\n");
        }
        this.sender = sender;
        this.recipients = recipients;
    }

    public Person getSender() {
        return sender;
    }

    public Person[] getRecipients() {
        return recipients;
    }
}
