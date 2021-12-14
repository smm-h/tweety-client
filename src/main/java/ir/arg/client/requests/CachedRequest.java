package ir.arg.client.requests;

import ir.arg.client.App;
import org.jetbrains.annotations.NotNull;

public class CachedRequest extends RequestWithAuth {

    @NotNull
    private final String method, params;

    public CachedRequest(@NotNull final App app, @NotNull final String method, @NotNull final String params) {
        super(app);
        this.method = method;
        this.params = params;
    }

    @Override
    public @NotNull String getMethod() {
        return method;
    }

    @Override
    public @NotNull String getParameters() {
        return params;
    }
}
