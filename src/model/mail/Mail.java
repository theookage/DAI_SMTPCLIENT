package model.mail;

import java.util.List;

public class Mail {
    private Person from;
    private List<Person> to;
    private String body;

    public Mail(Person from, List<Person> to, String body){
       this.from = from;
       this.to = to;
       this.body = body;
    }

    public Person getFrom() {
        return from;
    }

    public List<Person> getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }
}
