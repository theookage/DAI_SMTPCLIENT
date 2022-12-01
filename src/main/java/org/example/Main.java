package org.example;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        final int DESTINATION_PORT = 25;

        Socket clientSocket = new Socket("127.0.0.1", DESTINATION_PORT);
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
        BufferedWriter toServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));

        

        fromServer.close();
        toServer.close();
        clientSocket.close();

    }
}