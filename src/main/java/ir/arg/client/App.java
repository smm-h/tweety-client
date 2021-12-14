package ir.arg.client;

import ir.arg.shared.APIMethods;
import ir.arg.shared.ErrorCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface App extends ErrorCode, APIMethods {
    String sendRequest(@NotNull String request);

    String getDeviceInfo();

    String generateToken();

    void signInWithToken(@NotNull String username, @NotNull String token);

    void signOut();

    @Nullable
    String getAuthentication();

    default boolean isSignedIn() {
        return getAuthentication() != null;
    }

    void onError(int errorCode);
}
