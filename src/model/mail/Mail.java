package model.mail;

public class Mail {
    private Person from;
    private Person[] to;
    private String subject;
    private String body;

    public Mail(Person from, Person[] to, String subject, String body){
       this.from = from;
       this.to = to;
       this.subject = subject;
       this.body = body;
    }

    public Person getFrom() {
        return from;
    }

    public Person[] getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
