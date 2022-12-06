package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class SmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    private static final String CRLF = "\r\n";
    String smtpServerAdress = "localhost";
    int smtpServerPort = 25;
    Socket socket;
    BufferedReader in;
    PrintWriter out; // pour éviter les exceptions

    public SmtpClient(String smtpServerAdress, int smtpServerPort){
        // Instanciation des champs de la classe
        this.smtpServerAdress = smtpServerAdress;
        this.smtpServerPort = smtpServerPort;
    }

    /**
     * Envoi un mail via le protocole TCP.
     * Cette fonction gère toute la communication avec le serveur SMTP
     * @param mail Message à envoyer qui contient toutes les infos nécessaires. (from, to, body, ...)
     */
    public void sendMail(Mail mail) throws IOException {
        LOG.info("Sendin message via SMTP");
        socket = new Socket(smtpServerAdress, smtpServerPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        LOG.info("Connected to the Server");
        String line = in.readLine();
        LOG.info(line);
        out.println("EHLO " + smtpServerAdress + CRLF);
        line = in.readLine();
        LOG.info(line);
        if(!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")) {
           line = in.readLine();
           LOG.info(line);
        }

        out.write("MAIL FROM:");
        // TODO FROM
        out.write(CRLF);
        out.flush();
        line = in.readLine();
        LOG.info(line);

        // TODO for each sur les TO

        out.write("DATA");
        out.write(CRLF);
        out.flush();
        line = in.readLine();
        LOG.info(line);
        out.write("Content-Type: text/plain charset=\"utf-8\"" + CRLF);

        // TODO from

        // TODO to

        // TODO body


        out.write("QUIT" + CRLF);
        out.flush();

        // Fermeture des flux (socket, reader et writer)
        out.close();
        in.close();
        socket.close();
        LOG.info("Connection was successfully closed ! :)" + CRLF);
    }
}
