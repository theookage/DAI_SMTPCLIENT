package model.mail;

public class Person {
    private final String mailAdress;

    public Person(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String toString() {
        return this.mailAdress;
    }
}
