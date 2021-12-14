package ir.arg.client.requests;

import ir.arg.client.App;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public abstract class RequestWithAuth extends RequestImpl {

    public RequestWithAuth(@NotNull App app) {
        super(app);
    }

    @NotNull
    public abstract String getMethod();

    @NotNull
    public abstract String getParameters();

    @Override
    public @Nullable String make() {
        final String auth = app.getAuthentication();
        if (auth == null) {
            System.out.println("Please sign-in and try again.");
            return null;
        } else {
            return "{\"method\": \"" + getMethod() + "\", " + auth + ", " + getParameters() + "}";
        }
    }

    @Override
    public void react(@NotNull JSONObject response) {
        if (response.getInt("error_code") == AUTHENTICATION_FAILED) {
            System.out.println("Failed to authenticate; please sign-in again.");
            app.signOut();
        }
    }
}
