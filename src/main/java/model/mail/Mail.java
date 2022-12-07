package model.mail;

import java.util.List;

public record Mail(Person from, List<Person> to, String body) {
}
