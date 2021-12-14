package ir.arg.client;

import org.jetbrains.annotations.NotNull;

public interface Client {

    int getDefaultPort();

    boolean connectToHost(@NotNull String address, int port);

    default boolean connectToHost(@NotNull String address) {
        return connectToHost(address, getDefaultPort());
    }

    default boolean connectToLocalHost() {
        return connectToHost("127.0.0.1");
    }
}
