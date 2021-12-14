package ir.arg.client.impl;

import ir.arg.client.App;
import ir.arg.client.requests.CachedRequest;
import ir.arg.client.requests.SignIn;
import ir.arg.client.requests.SignUp;
import ir.arg.client.requests.UsernameExists;
import ir.arg.shared.RandomHex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppImpl implements App {
    public static void main(String[] args) {
        new AppImpl();
    }

    private String authentication = null;

    public AppImpl() {
//        try {
//            switchUser("arg", "abcDEF123!@#");
////            createTweet("Hello, Tweety!");
////            createTweet("Can't wait for the new Spider-man movie.");
//            sendRequest("{\"method\": \"" + GET_TWEETS_OF_USER + "\", \"username\": \"arg\"}");
////            switchUser("other_user123", "]!0.a.z.9![");
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
        connectToLocalHost();
    }

    private boolean usernameExists(@NotNull final String username) {
        final UsernameExists ue = new UsernameExists(this, username);
        ue.send();
        return ue.exists;
    }

    private void switchUser(@NotNull final String username, @NotNull final String password) {
        if (isSignedIn()) {
            signOut();
        }
        if (!usernameExists(username)) {
            new SignUp(this, username, password).send();
        }
        new SignIn(this, username, password).send();
    }

    private void createTweet(@NotNull final String contents) {
        new CachedRequest(this, CREATE_TWEET, "\"contents\": " + JSONObject.quote(contents)).send();
    }

    @Override
    public String generateToken() {
        return RandomHex.generate(32);
    }

    // TODO annotate #sendRequest as @NotNull
    @Override
    public String sendRequest(@NotNull String request) {
//        return request(request);
        return null;
    }

    @Override
    public String getDeviceInfo() {
        return "Test Device";
    }

    @Nullable
    @Override
    public String getAuthentication() {
        return authentication;
    }

    @Override
    public void signInWithToken(@NotNull String username, @NotNull String token) {
        this.authentication = "\"my_username\": " + username + ", \"token\": " + token;
        System.out.println("Signing in was successful.");
    }

    @Override
    public void signOut() {
        this.authentication = null;
        System.out.println("Signed out. Please sign in again.");
    }

    @Override
    public void onError(int errorCode) {
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
        }
        return false;
    }
}
