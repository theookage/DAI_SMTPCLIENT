package smtp;

import model.mail.Mail;
import model.mail.Person;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class SmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private static final String CRLF = "\r\n";
    String smtpServerAdress;
    int smtpServerPort;
    Socket socket;
    BufferedReader in;
    BufferedWriter out; // pour éviter les exceptions

    public SmtpClient(String smtpServerAdress, int smtpServerPort) {
        // Instanciation des champs de la classe
        this.smtpServerAdress = smtpServerAdress;
        this.smtpServerPort = smtpServerPort;
    }

    /**
     * Envoi un mail via le protocole TCP.
     * Cette fonction gère toute la communication avec le serveur SMTP
     *
     * @param mail Message à envoyer qui contient toutes les infos nécessaires. (from, to, body, ...)
     */
    public void sendMail(Mail mail) throws IOException {
        LOG.info("Sendin message via SMTP");
        socket = new Socket(smtpServerAdress, smtpServerPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        LOG.info("Connected to the Server");
        String line = in.readLine();
        LOG.info(line);

        // EHLO
        out.write("EHLO " + smtpServerAdress + CRLF);
        out.flush();
        line = in.readLine();
        LOG.info(line);
        if (!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")) {
            line = in.readLine();
            LOG.info(line);
        }

        // MAIL FROM
        String theo = "MAIL FROM: " + mail.from().toString() + CRLF;
        out.write(theo);
        out.flush();
        line = in.readLine();
        LOG.info(line);

        // MAIL TO
        for (Person p : mail.to()) {
            out.write("RCPT TO:");
            out.write(p.toString());
            out.write(CRLF);
            out.flush();
        }

        // DATA
        out.write("DATA");
        out.write(CRLF);
        out.flush();
        line = in.readLine();
        LOG.info(line);
        out.write("Content-Type: text/plain charset=\"utf-8\"" + CRLF);
        out.write("From: ");
        out.write(mail.from().toString());
        out.write(CRLF);
        out.write("To: ");
        for (Person p : mail.to()) {
            out.write(p.toString());
            if (p != (mail.to().get(mail.to().size() - 1))) {
                out.write(",");
            }
        }
        out.write(CRLF + CRLF);
        out.write(mail.body());
        out.write(CRLF + "." + CRLF);
        out.flush();

        // QUIT
        out.write("QUIT" + CRLF);
        out.flush();

        // Fermeture des flux (socket, reader et writer)
        out.close();
        in.close();
        socket.close();
        LOG.info("Connection was successfully closed ! :)" + CRLF);
    }
}
