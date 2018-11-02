package ratpack.example.api;

import com.google.inject.AbstractModule;
import ratpack.example.api.upload.UploadHandler;

public class ApiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApiHandlers.class);

        bind(UploadHandler.class);
    }
}
