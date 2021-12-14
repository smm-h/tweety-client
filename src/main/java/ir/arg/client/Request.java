package ir.arg.client;

import ir.arg.shared.APIMethods;
import ir.arg.shared.ErrorCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import org.json.JSONTokener;

public interface Request extends ErrorCode, APIMethods {

    default void send() {
        final String made = make();
        if (made != null)
            react(new JSONObject(new JSONTokener(getApp().sendRequest(made))));
    }

    @NotNull App getApp();

    @Nullable String make();

    void react(@NotNull final JSONObject response);
}
