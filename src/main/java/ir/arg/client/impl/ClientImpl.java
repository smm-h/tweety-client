package ir.arg.client.impl;

import ir.arg.client.Client;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientImpl implements Client {

    public static void main(String[] args) {
        new ClientImpl().connectToLocalHost();
    }

    @Override
    public int getDefaultPort() {
        return 7000;
    }

    @Override
    public boolean connectToHost(@NotNull String address, int port) {
        try {
            final Socket socket = new Socket(address, port);
            final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String line = "";
            while (!line.equals(".")) {
                try {
                    System.out.print("> ");
                    line = input.readLine();
                    out.writeUTF(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                input.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (UnknownHostException u) {
            System.out.println("Unknown Host");
        } catch (IOException ignored) {
            System.out.println("IO error occurred");
        }
        return false;
    }
}
