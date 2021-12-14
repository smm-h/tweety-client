package ir.arg.client.requests;

import ir.arg.client.App;
import ir.arg.client.Request;
import org.jetbrains.annotations.NotNull;

public abstract class RequestImpl implements Request {
    final App app;

    public RequestImpl(@NotNull final App app) {
        this.app = app;
    }

    @NotNull
    @Override
    public App getApp() {
        return app;
    }
}
