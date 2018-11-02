package ratpack.example.api;

import ratpack.example.api.upload.UploadHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

public class ApiHandlers implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.put("api/v1/upload", UploadHandler.class);
    }
}
